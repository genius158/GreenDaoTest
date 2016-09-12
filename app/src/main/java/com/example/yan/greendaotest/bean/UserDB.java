package com.example.yan.greendaotest.bean;

import android.content.Context;

import com.example.yan.greendaotest.DBManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by yan on 2016/9/12.
 */
public class UserDB {

    DBManager dbManager;

    public UserDB(Context context) {
        dbManager = DBManager.getInstance(context);
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(dbManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(dbManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(dbManager.getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(dbManager.getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        List<User> list = qb.list();
        return list;
    }

    /**
     * 删除一条记录
     */
    public void deleteUser(Long id) {
        DaoMaster daoMaster = new DaoMaster(dbManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();

        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build().list();
        for (User user : userList) {
            userDao.delete(user);
        }
    }

    /**
     * 更新一条记录
     */
    public void updateUser(Long id) {
        UserDao userDao = new DaoMaster(dbManager.getWritableDatabase()).newSession().getUserDao();

        User user = userDao.queryBuilder()
                .where(UserDao.Properties.Id.eq(id)).build().unique();
        if (user != null) {
            user.setName("王五11");
            userDao.update(user);
        }
    }
}
