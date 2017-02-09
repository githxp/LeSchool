package com.hxp.leschool.model.opt;

import android.util.Log;

import com.hxp.leschool.model.bean.ScoreModel;
import com.hxp.leschool.model.bean.ScorePersonModel;
import com.hxp.leschool.viewmodel.ScoreViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hxp on 17-2-8.
 */

public class ScoreModelOpt {

    private ScoreViewModel mScoreViewModel;
    public ScorePersonModel mScorePersonModel = new ScorePersonModel();
    private ScoreModel mScoreModel;
    public ArrayList<ScoreModel> mData = new ArrayList<>();
    private ScoreModelOptCallback mScoreModelOptCallback;

    public ScoreModelOpt(ScoreViewModel scoreViewModel) {
        this.mScoreViewModel = scoreViewModel;
        mScoreModelOptCallback = scoreViewModel;
    }

    public void get(String response) {
        Document doc = Jsoup.parse(response);
        Elements tables = doc.select("center > table");
        Pattern stuNumPat = Pattern.compile("学号：[0-9]{14}");
        Pattern stuNamePat = Pattern.compile("姓名：.{1,255}");
        Matcher stuNumMat = stuNumPat.matcher(tables.get(0).text());
        Matcher stuNameMat = stuNamePat.matcher(tables.get(0).text());
        if (stuNumMat.find()) {
            Log.d("fragment", stuNumMat.group());
            mScorePersonModel.setStuNum(stuNumMat.group());
        } else {
            Log.d("fragment", "stuNumMat未找到");
        }
        if (stuNameMat.find()) {
            Log.d("fragment", stuNameMat.group());
            mScorePersonModel.setStuName(stuNameMat.group());
        } else {
            Log.d("fragment", "setStuName未找到");
        }


        for (int i = 3; i < tables.size(); i += 3) {
            Elements trs = tables.get(i).select("tbody").select("tr");
            for (int j = 0; j < trs.size(); j++) {
                mScoreModel = new ScoreModel();
                if (trs.get(j).select("td").get(1).text().equals("")) {
                    mScoreModel.setTitle("无");
                } else {
                    mScoreModel.setTitle(trs.get(j).select("td").get(1).text().substring(trs.get(j).select("td").get(1).text().lastIndexOf("]") + 1));
                }

                if (trs.get(j).select("td").get(6).text().equals("")) {
                    mScoreModel.setMidScore("无");
                } else {
                    mScoreModel.setMidScore(trs.get(j).select("td").get(6).text().substring(trs.get(j).select("td").get(6).text().lastIndexOf("]") + 1));
                }

                if (trs.get(j).select("td").get(8).text().equals("")) {
                    mScoreModel.setEndScore("无");
                } else {
                    mScoreModel.setEndScore(trs.get(j).select("td").get(8).text().substring(trs.get(j).select("td").get(8).text().lastIndexOf("]") + 1));
                }

                if (trs.get(j).select("td").get(10).text().equals("")) {
                    mScoreModel.setFinScore("无");
                } else {
                    mScoreModel.setFinScore(trs.get(j).select("td").get(10).text().substring(trs.get(j).select("td").get(10).text().lastIndexOf("]") + 1));
                }

                if (trs.get(j).select("td").get(11).text().equals("")) {
                    mScoreModel.setMajorOrMinor("无");
                } else {
                    mScoreModel.setMajorOrMinor(trs.get(j).select("td").get(11).text().substring(trs.get(j).select("td").get(11).text().lastIndexOf("]") + 1));
                }
                mData.add(mScoreModel);
                //Log.d("fragment", "title:" + trs.get(i).select("td").get(1).text() + "score:" + trs.get(i).select("td").get(6).text());
            }
        }
        mScoreModelOptCallback.refresh();
    }

    public int getCount() {
        return mData.size();
    }

    public interface ScoreModelOptCallback {
        void refresh();
    }
}
