package io.innofang.greendaodemo;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import io.innofang.greendaodemo.dao.DaoMaster;
import io.innofang.greendaodemo.dao.DaoSession;

/**
 * Author: Inno Fang
 * Time: 2017/10/4 11:01
 * Description:
 */


public class App extends Application {

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
