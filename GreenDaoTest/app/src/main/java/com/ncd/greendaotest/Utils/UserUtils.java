package com.ncd.greendaotest.Utils;

import android.text.TextUtils;

import com.ncd.greendaotest.MyApplication;
import com.ncd.greendaotest.entity.User;
import com.ncd.greendaotest.db.DaoSession;
import com.ncd.greendaotest.db.UserDao;

import java.util.List;

/**
 * Created by 80549 on 2017/7/20.
 */

public class UserUtils {
    private static final String TAG = UserUtils.class.getSimpleName();

    private static UserUtils instance;
    private DaoSession daoSession;
    private UserDao userDao;

    private UserUtils() {
        initDao();
    }

    public static UserUtils getInstance() {
        if (null == instance) {
            synchronized (UserUtils.class) {
                if (null == instance) {
                    instance = new UserUtils();
                }
            }
        }
        return instance;
    }

    //获取DAO
    private void initDao() {
        if (null == daoSession) {
            daoSession = MyApplication.getApplication().getDaoSession();
        }
        if (null == userDao) {
            userDao = daoSession.getUserDao();
        }
    }

    //插入 若数据库中存在此id,则会报错
    public void insert(User user) {
        if (null != userDao && null != user) {
            userDao.insert(user);
        } else {
            return;
        }
    }

    //插入 若数据库中存在此id,则会更新数据
    public void insertOrReplace(User user) {
        if (null != userDao && null != user) {
            userDao.insertOrReplace(user);
        } else {
            return;
        }
    }

    //删除
    public void delete(User user) {
        if (null != userDao && null != user) {
            userDao.delete(user);
        } else {
            return;
        }
    }

    //根据id删除
    public void deleteByUserId(String id) {
        if (null != userDao && !TextUtils.isEmpty(id)) {
            userDao.deleteByKey(Long.valueOf(id));
        } else {
            return;
        }
    }

    //更新
    public void update(User user) {
        if (null != userDao && null != user) {
            userDao.update(user);
        } else {
            return;
        }
    }

    //查询所有
    public List<User> query() {
        if (null != userDao) {
            return userDao.loadAll();
        } else {
            return null;
        }
    }

    //根据id查询
    public User queryById(String id) {
        if (null != userDao && !TextUtils.isEmpty(id)) {
            return userDao.loadByRowId(Long.valueOf(id));
        } else {
            return null;
        }
    }

    //根据条件查询
    public List<User> queryByArgs(String selection, String selectionArg) {
        if (null != userDao && !TextUtils.isEmpty(selection)) {
            return userDao.queryRaw(selection, selectionArg);
        } else {
            return null;
        }
    }
}
