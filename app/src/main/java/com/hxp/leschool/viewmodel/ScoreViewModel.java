package com.hxp.leschool.viewmodel;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hxp.leschool.adapter.ScoreAdapter;
import com.hxp.leschool.databinding.ScoreAtBinding;
import com.hxp.leschool.model.opt.ScoreModelOpt;
import com.hxp.leschool.model.opt.ScoreModelOpt.ScoreModelOptCallback;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.ScoreActivity;
import com.hxp.leschool.widget.RecycleItemDivider;
import com.hxp.leschool.widget.SubNavbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 成绩查询
 * Created by hxp on 17-1-22.
 */


public class ScoreViewModel implements ScoreModelOptCallback {

    private ScoreActivity mScoreActivity;
    private ScoreAtBinding mScoreAtBinding;
    private RequestQueue mQueue;
    private String mCookie;
    public ScoreModelOpt mScoreModelOpt;
    private ScoreAdapter mScoreAdapter;
    private Document mTxtXmDoc;
    private String mTxtXm;
    private SubNavbar mSubNavbar;
    private boolean mIsLogin = false;
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                getVerCode();
            }
            if (msg.what == 2) {
                getVerCode();
            }
        }
    };

    public ScoreViewModel(ScoreActivity scoreActivity, ScoreAtBinding scoreAtBinding) {

        Log.d("fragment", "ScoreViewModel创建");
        mScoreActivity = scoreActivity;
        mScoreAtBinding = scoreAtBinding;

        mSubNavbar = mScoreAtBinding.subNavbarScoreContent;
        mSubNavbar.setTitle("成绩");

        mScoreModelOpt = new ScoreModelOpt(this);
        mScoreAdapter = new ScoreAdapter(this);

        mScoreAtBinding.rvScoreContent.setLayoutManager(new LinearLayoutManager(mScoreActivity, LinearLayoutManager.VERTICAL, false));
        mScoreAtBinding.rvScoreContent.setAdapter(mScoreAdapter);
        mScoreAtBinding.rvScoreContent.setItemAnimator(new DefaultItemAnimator());
        mScoreAtBinding.rvScoreContent.addItemDecoration(new RecycleItemDivider(mScoreActivity, RecycleItemDivider.VERTICAL_LIST));
        mScoreAtBinding.swifreshScoreContent.setRefreshing(true);

        mScoreAtBinding.setMScoreViewModel(this);

        mScoreAtBinding.swifreshScoreContent.setProgressViewOffset(true, 0, 50);
        mScoreAtBinding.swifreshScoreContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mScoreAtBinding.swifreshScoreContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (mIsLogin) {
                            query();
                            Toast.makeText(mScoreActivity, "已刷新成绩", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mScoreActivity, "请先登陆", Toast.LENGTH_SHORT).show();
                            mScoreAtBinding.swifreshScoreContent.setRefreshing(false);
                        }

                    }
                }
        );
        mQueue = Volley.newRequestQueue(MyApplication.getInstance());

        getCookie();
    }

    //获取Cookie
    private void getCookie() {
        StringRequest cookieRequest = new StringRequest("http://58.20.60.38:8081/JWWEB/_data/index_LOGIN.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("fragment", "获取Cookie成功返回");
                        getVerCode();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fragment", "获取Cookie错误");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate, sdch");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Connection", "keep-alive");
                headers.put("Host", "58.20.60.38:8081");
                headers.put("Upgrade-Insecure-Requests", "1");
                headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                Log.d("fragment", "返回cookie请球头");
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    Map<String, String> headers = response.headers;
                    String rawCookies = headers.get("Set-Cookie");
                    String dataString = new String(response.data, "UTF-8");
                    if (mCookie == null) {
                        mCookie = rawCookies.substring(0, rawCookies.lastIndexOf(";"));
                        Log.d("fragment", "成功获取Cookie");
                    }
                    Log.d("fragment", "原始cookie:" + rawCookies);
                    Log.d("fragment", "解析得到cookie:" + mCookie);
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        mQueue.add(cookieRequest);
    }

    //获取验证码
    private void getVerCode() {
        ImageRequest verCodeRequest = new ImageRequest("http://58.20.60.38:8081/JWWEB/sys/ValidateCode.aspx", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d("fragment", "获取验证码成功返回图片");
                mScoreAtBinding.imgScoreVerImg.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fragment", "获取验证码错误:" + error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate, sdch");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Connection", "keep-alive");
                headers.put("Cookie", mCookie);
                headers.put("Host", "58.20.60.38:8081");
                headers.put("Upgrade-Insecure-Requests", "1");
                headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                Log.d("fragment", "返回验证码请球头");
                return headers;
            }
        };
        mQueue.add(verCodeRequest);
    }

    //访问用户中心
    private void getUserCenter() {
        StringRequest userCenterRequest = new StringRequest("http://58.20.60.38:8081/JWWEB/MAINFRM.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("fragment", "用户中心成功返回:" + response);
                        Log.d("fragment", "用户中心title:" + Jsoup.parse(response).select("title").text());
                        getScoreOption();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fragment", "用户中心错误");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate, sdch");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Connection", "keep-alive");
                headers.put("Cookie", mCookie);
                headers.put("Host", "58.20.60.38:8081");
                headers.put("Referer", "http://58.20.60.38:8081/JWWEB/_data/index_LOGIN.aspx");
                headers.put("Upgrade-Insecure-Requests", "1");
                headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                Log.d("fragment", "返回cookie请球头");
                return headers;
            }
        };
        mQueue.add(userCenterRequest);
    }

    //访问成绩选项页面并初始化选项
    private void getScoreOption() {
        StringRequest scoreOptionRequest = new StringRequest("http://58.20.60.38:8081/JWWEB/xscj/Stu_MyScore.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTxtXmDoc = Jsoup.parse(response);
                        getTxtXm();
                        Log.d("fragment", "成绩选项页面成功返回:" + response);
                        Log.d("fragment", "成绩选项页面title:" + Jsoup.parse(response).select("title").text());
                        query();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fragment", "成绩选项页面错误");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate, sdch");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Connection", "keep-alive");
                headers.put("Cookie", mCookie);
                headers.put("Host", "58.20.60.38:8081");
                headers.put("Referer", "http://58.20.60.38:8081/JWWEB/sys/menu.aspx");
                headers.put("Upgrade-Insecure-Requests", "1");
                headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                Log.d("fragment", "返回成绩选项页面请球头");
                return headers;
            }
        };
        mQueue.add(scoreOptionRequest);
    }

    //登陆
    public void btn_Score_login(View view) {
        if (mScoreAtBinding.etScoreUserName.getText().toString().equals("") || mScoreAtBinding.etScorePassword.getText().toString().equals("") || mScoreAtBinding.etScoreVerCode.getText().toString().equals("")) {
            Toast.makeText(mScoreActivity, "请先输入用户信息", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest loginRequest = new StringRequest(Request.Method.POST, "http://58.20.60.38:8081/JWWEB/_data/index_LOGIN.aspx", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Document loginDoc = Jsoup.parse(response);
                    String loginInfo = loginDoc.select("span#divLogNote").select("font").text();
                    if (loginInfo.equals("验证码错误！ 登录失败！")) {
                        Toast.makeText(mScoreActivity, "验证码错误 请重试", Toast.LENGTH_SHORT).show();
                        mScoreAtBinding.etScoreVerCode.setText("");
                        Log.d("fragment", "登陆返回信息:" + loginInfo);
                        Message msg = Message.obtain();
                        msg.what = 1;
                        mHandle.sendMessage(msg);
                    } else if (loginInfo.equals("账号或密码不正确！请重新输入。")) {
                        Toast.makeText(mScoreActivity, "账号或密码不正确！请重试", Toast.LENGTH_SHORT).show();
                        mScoreAtBinding.etScoreUserName.setText("");
                        mScoreAtBinding.etScorePassword.setText("");
                        mScoreAtBinding.etScoreVerCode.setText("");
                        Log.d("fragment", "登陆返回信息:" + loginInfo);
                        Message msg = Message.obtain();
                        msg.what = 2;
                        mHandle.sendMessage(msg);
                    } else {
                        getUserCenter();
                    }
                    Log.d("fragment", "登陆返回:" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("fragment", "登陆错误:" + error.getMessage(), error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("__VIEWSTATE", "dDw2MjA0Mjg3Nzg7dDw7bDxpPDE+O2k8Mz47aTw1Pjs+O2w8dDxwPGw8VGV4dDs+O2w86ZW/5rKZ5Yy75a2m6ZmiOz4+Ozs+O3Q8cDxsPFRleHQ7PjtsPFw8c2NyaXB0IHR5cGU9InRleHQvamF2YXNjcmlwdCJcPgpcPCEtLQpmdW5jdGlvbiBDaGtWYWx1ZSgpewogdmFyIHZVPSQoJ1VJRCcpLmlubmVySFRNTFw7CiB2VT12VS5zdWJzdHJpbmcoMCwxKSt2VS5zdWJzdHJpbmcoMiwzKVw7CiB2YXIgdmNGbGFnID0gIllFUyJcOyBpZiAoJCgnVXNlcklEJykudmFsdWU9PScnKXsKIGFsZXJ0KCfpobvlvZXlhaUnK3ZVKyfvvIEnKVw7JCgnVXNlcklEJykuZm9jdXMoKVw7cmV0dXJuIGZhbHNlXDsKfQogZWxzZSBpZiAoJCgnUGFzc1dvcmQnKS52YWx1ZT09JycpewogYWxlcnQoJ+mhu+W9leWFpeWvhuegge+8gScpXDskKCdQYXNzV29yZCcpLmZvY3VzKClcO3JldHVybiBmYWxzZVw7Cn0KIGVsc2UgaWYgKCQoJ2NDb2RlJykudmFsdWU9PScnICYmIHZjRmxhZyA9PSAiWUVTIil7CiBhbGVydCgn6aG75b2V5YWl6aqM6K+B56CB77yBJylcOyQoJ2NDb2RlJykuY0NvZGUuZm9jdXMoKVw7cmV0dXJuIGZhbHNlXDsKfQogZWxzZSB7ICQoJ2RpdkxvZ05vdGUnKS5pbm5lckhUTUw9J1w8Zm9udCBjb2xvcj0icmVkIlw+5q2j5Zyo6YCa6L+H6Lqr5Lu96aqM6K+BLi4u6K+356iN5YCZIVw8L2ZvbnRcPidcOwogcmV0dXJuIHRydWVcO30KfQpmdW5jdGlvbiBTZWxUeXBlKG9iail7CiB2YXIgcz1vYmoub3B0aW9uc1tvYmouc2VsZWN0ZWRJbmRleF0uZ2V0QXR0cmlidXRlKCd1c3JJRCcpXDsKIHZhciB3PW9iai5vcHRpb25zW29iai5zZWxlY3RlZEluZGV4XS5nZXRBdHRyaWJ1dGUoJ1B3ZElEJylcOwogJCgnVUlEJykuaW5uZXJIVE1MPXNcOwogc2VsVHllTmFtZSgpXDsKfQpmdW5jdGlvbiBvcGVuV2luTG9nKHRoZVVSTCx3LGgpewp2YXIgVGZvcm0scmV0U3RyXDsKZXZhbCgiVGZvcm09J3dpZHRoPSIrdysiLGhlaWdodD0iK2grIixzY3JvbGxiYXJzPW5vLHJlc2l6YWJsZT1ubyciKVw7CnBvcD13aW5kb3cub3Blbih0aGVVUkwsJ3dpbktQVCcsVGZvcm0pXDsgLy9wb3AubW92ZVRvKDAsNzUpXDsKZXZhbCgiVGZvcm09J2RpYWxvZ1dpZHRoOiIrdysicHhcO2RpYWxvZ0hlaWdodDoiK2grInB4XDtzdGF0dXM6bm9cO3Njcm9sbGJhcnM9bm9cO2hlbHA6bm8nIilcOwppZih0eXBlb2YocmV0U3RyKSE9J3VuZGVmaW5lZCcpIGFsZXJ0KHJldFN0cilcOwp9CmZ1bmN0aW9uIHNob3dMYXkoZGl2SWQpewp2YXIgb2JqRGl2ID0gZXZhbChkaXZJZClcOwppZiAob2JqRGl2LnN0eWxlLmRpc3BsYXk9PSJub25lIikKe29iakRpdi5zdHlsZS5kaXNwbGF5PSIiXDt9CmVsc2V7b2JqRGl2LnN0eWxlLmRpc3BsYXk9Im5vbmUiXDt9Cn0KZnVuY3Rpb24gc2VsVHllTmFtZSgpewogICQoJ3R5cGVOYW1lJykudmFsdWU9JE4oJ1NlbF9UeXBlJylbMF0ub3B0aW9uc1skTignU2VsX1R5cGUnKVswXS5zZWxlY3RlZEluZGV4XS50ZXh0XDsKfQp3aW5kb3cub25sb2FkPWZ1bmN0aW9uKCl7Cgl2YXIgc1BDPU1TSUU/d2luZG93Lm5hdmlnYXRvci51c2VyQWdlbnQrd2luZG93Lm5hdmlnYXRvci5jcHVDbGFzcyt3aW5kb3cubmF2aWdhdG9yLmFwcE1pbm9yVmVyc2lvbisnIFNOOk5VTEwnOndpbmRvdy5uYXZpZ2F0b3IudXNlckFnZW50K3dpbmRvdy5uYXZpZ2F0b3Iub3NjcHUrd2luZG93Lm5hdmlnYXRvci5hcHBWZXJzaW9uKycgU046TlVMTCdcOwp0cnl7JCgncGNJbmZvJykudmFsdWU9c1BDXDt9Y2F0Y2goZXJyKXt9CnRyeXskKCdVc2VySUQnKS5mb2N1cygpXDt9Y2F0Y2goZXJyKXt9CnRyeXskKCd0eXBlTmFtZScpLnZhbHVlPSROKCdTZWxfVHlwZScpWzBdLm9wdGlvbnNbJE4oJ1NlbF9UeXBlJylbMF0uc2VsZWN0ZWRJbmRleF0udGV4dFw7fWNhdGNoKGVycil7fQp9CmZ1bmN0aW9uIG9wZW5XaW5EaWFsb2codXJsLHNjcix3LGgpCnsKdmFyIFRmb3JtXDsKZXZhbCgiVGZvcm09J2RpYWxvZ1dpZHRoOiIrdysicHhcO2RpYWxvZ0hlaWdodDoiK2grInB4XDtzdGF0dXM6IitzY3IrIlw7c2Nyb2xsYmFycz1ub1w7aGVscDpubyciKVw7CndpbmRvdy5zaG93TW9kYWxEaWFsb2codXJsLDEsVGZvcm0pXDsKfQpmdW5jdGlvbiBvcGVuV2luKHRoZVVSTCl7CnZhciBUZm9ybSx3LGhcOwp0cnl7Cgl3PXdpbmRvdy5zY3JlZW4ud2lkdGgtMTBcOwp9Y2F0Y2goZSl7fQp0cnl7Cmg9d2luZG93LnNjcmVlbi5oZWlnaHQtMzBcOwp9Y2F0Y2goZSl7fQp0cnl7ZXZhbCgiVGZvcm09J3dpZHRoPSIrdysiLGhlaWdodD0iK2grIixzY3JvbGxiYXJzPW5vLHN0YXR1cz1ubyxyZXNpemFibGU9eWVzJyIpXDsKcG9wPXBhcmVudC53aW5kb3cub3Blbih0aGVVUkwsJycsVGZvcm0pXDsKcG9wLm1vdmVUbygwLDApXDsKcGFyZW50Lm9wZW5lcj1udWxsXDsKcGFyZW50LmNsb3NlKClcO31jYXRjaChlKXt9Cn0KZnVuY3Rpb24gY2hhbmdlVmFsaWRhdGVDb2RlKE9iail7CnZhciBkdCA9IG5ldyBEYXRlKClcOwpPYmouc3JjPSIuLi9zeXMvVmFsaWRhdGVDb2RlLmFzcHg/dD0iK2R0LmdldE1pbGxpc2Vjb25kcygpXDsKfQovLy0tXD4KXDwvc2NyaXB0XD47Pj47Oz47dDw7bDxpPDE+Oz47bDx0PDtsPGk8MD47PjtsPHQ8cDxsPFRleHQ7PjtsPFw8b3B0aW9uIHZhbHVlPSdTVFUnIHVzcklEPSflrabjgIDlj7cnXD7lrabnlJ9cPC9vcHRpb25cPgpcPG9wdGlvbiB2YWx1ZT0nVEVBJyB1c3JJRD0n5bel44CA5Y+3J1w+5pWZ5biI5pWZ6L6F5Lq65ZGYXDwvb3B0aW9uXD4KXDxvcHRpb24gdmFsdWU9J1NZUycgdXNySUQ9J+W4kOOAgOWPtydcPueuoeeQhuS6uuWRmFw8L29wdGlvblw+Clw8b3B0aW9uIHZhbHVlPSdBRE0nIHVzcklEPSfluJDjgIDlj7cnXD7pl6jmiLfnu7TmiqTlkZhcPC9vcHRpb25cPgo7Pj47Oz47Pj47Pj47Pj47PuDZ8gjjdWqdJBRtNbRyRjy10DZO");
                    map.put("__VIEWSTATEGENERATOR", "4B596BA9");
                    map.put("pcInfo", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36undefined5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 SN:NULL");
                    map.put("typeName", "");
                    map.put("Sel_Type", "STU");
                    map.put("UserID", mScoreAtBinding.etScoreUserName.getText().toString());
                    map.put("PassWord", mScoreAtBinding.etScorePassword.getText().toString());
                    map.put("cCode", mScoreAtBinding.etScoreVerCode.getText().toString());
                    map.put("sbtState", "");
                    Log.d("fragment", "cCode" + mScoreAtBinding.etScoreVerCode.getText().toString());
                    return map;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    headers.put("Accept-Encoding", "gzip, deflate");
                    headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                    headers.put("Cache-Control", "max-age=0");
                    headers.put("Connection", "keep-alive");
                    //headers.put("Content-Type", "application/x-www-form-urlencoded");
                    headers.put("Cookie", mCookie);
                    headers.put("Host", "58.20.60.38:8081");
                    headers.put("Origin", "http://58.20.60.38:8081");
                    headers.put("Referer", "http://58.20.60.38:8081/JWWEB/_data/index_LOGIN.aspx");
                    headers.put("Upgrade-Insecure-Requests", "1");
                    headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                    Log.d("fragment", "返回登陆请球头");
                    return headers;
                }
            };
            //stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mQueue.add(loginRequest);
        }
    }

    //查询
    public void query() {
        StringRequest queryRequest = new StringRequest(Request.Method.POST, "http://58.20.60.38:8081/JWWEB/xscj/Stu_MyScore_rpt.aspx", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("fragment", "查询成功返回" + response);
                mScoreModelOpt.get(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "查询错误:" + error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("SJ", "0");
                map.put("btn_search", "");
                map.put("SelXNXQ", "0");
                map.put("txt_xm", mTxtXm);
                map.put("zfx_flag", "0");
                map.put("zxf", "0");
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Cache-Control", "max-age=0");
                headers.put("Connection", "keep-alive");
                //headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Cookie", mCookie);
                headers.put("Host", "58.20.60.38:8081");
                headers.put("Origin", "http://58.20.60.38:8081");
                headers.put("Referer", "http://58.20.60.38:8081/JWWEB/xscj/Stu_MyScore.aspx");
                headers.put("Upgrade-Insecure-Requests", "1");
                headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                Log.d("fragment", "返回登陆请球头");
                return headers;
            }
        };
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(queryRequest);
    }

    //获取txt_xm
    private void getTxtXm() {
        mTxtXm = mTxtXmDoc.select("input[name=txt_xm]").attr("value");
        Log.d("txt", mTxtXm);
    }

    @Override
    public void refresh() {
        mScoreAtBinding.swifreshScoreContent.setRefreshing(false);
        mScoreAdapter.notifyDataSetChanged();
        mIsLogin = true;
    }
}