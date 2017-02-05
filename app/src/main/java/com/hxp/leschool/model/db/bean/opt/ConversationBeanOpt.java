package com.hxp.leschool.model.db.bean.opt;

import android.content.Context;

import com.hxp.leschool.model.db.bean.ConversationBean;
import com.hxp.leschool.model.db.bean.ConversationBeanDao;
import com.hxp.leschool.model.db.bean.DaoMaster;
import com.hxp.leschool.model.db.bean.DaoSession;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.ChangeConversationEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 会话实体类操作类
 * Created by hxp on 17-2-5.
 */

public class ConversationBeanOpt {

    private static ConversationBeanOpt conversationBeanOpt = new ConversationBeanOpt();

    private Context context = MyApplication.getInstance();
    private DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "LeSchool.db");
    private DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
    private DaoSession daoSession = daoMaster.newSession();
    private ConversationBeanDao conversationBeanDao = daoSession.getConversationBeanDao();

    private ConversationBeanOpt() {
    }

    public static ConversationBeanOpt getInstance() {
        return conversationBeanOpt;
    }

    //插入一条数据
    public void insert(String userName, String avatar, String msg, String lastTime, int num, int type) {
        conversationBeanDao.insert(new ConversationBean(null, userName, avatar, msg, lastTime, num, type));
        EventBus.getDefault().postSticky(new AddConversationEvent());
    }

    //修改或添加一条数据
    public void changeORadd(String userName, String avatar, String msg, String lastTime, int num, int type) {
        ConversationBean conversationBean = conversationBeanDao.queryBuilder().where(ConversationBeanDao.Properties.UserName.eq(userName)).unique();
        if (conversationBean != null) {//修改
            conversationBean.setMsg(msg);
            conversationBean.setLastTime(lastTime);
            conversationBean.setNum(num);
            conversationBeanDao.update(conversationBean);
            EventBus.getDefault().postSticky(new ChangeConversationEvent());
        } else {//添加
            insert(userName, avatar, msg, lastTime, num, type);
        }
    }

    //查询全部数据
    public ArrayList<ConversationBean> get() {
        ArrayList<ConversationBean> data = (ArrayList<ConversationBean>) conversationBeanDao.loadAll();
        return data;
    }

    //查询一条数据是否存在
    public boolean query(String userName) {
        if (conversationBeanDao.queryBuilder().where(ConversationBeanDao.Properties.UserName.eq(userName)).unique() == null) {
            return false;
        } else {
            return true;
        }
    }
}
