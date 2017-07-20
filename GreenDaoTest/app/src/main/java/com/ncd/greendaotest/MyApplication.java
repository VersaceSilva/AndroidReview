package com.ncd.greendaotest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.ncd.greendaotest.db.DaoMaster;
import com.ncd.greendaotest.db.DaoSession;

/**
 * Created by 80549 on 2017/7/20.
 */

public class MyApplication extends Application {

    private DaoSession daoSession;
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
        myApplication = this;
    }

    /**
     * 初始化 GreenDao
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MyApplication getApplication() {
        return myApplication;
    }
}
