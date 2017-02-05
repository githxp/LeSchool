package com.hxp.leschool.model.db.bean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hxp.leschool.model.db.bean.ConversationBean;

import com.hxp.leschool.model.db.bean.ConversationBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig conversationBeanDaoConfig;

    private final ConversationBeanDao conversationBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        conversationBeanDaoConfig = daoConfigMap.get(ConversationBeanDao.class).clone();
        conversationBeanDaoConfig.initIdentityScope(type);

        conversationBeanDao = new ConversationBeanDao(conversationBeanDaoConfig, this);

        registerDao(ConversationBean.class, conversationBeanDao);
    }
    
    public void clear() {
        conversationBeanDaoConfig.clearIdentityScope();
    }

    public ConversationBeanDao getConversationBeanDao() {
        return conversationBeanDao;
    }

}
