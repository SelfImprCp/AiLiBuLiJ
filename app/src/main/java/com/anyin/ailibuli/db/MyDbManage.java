
/**
package com.anyin.ailibuli.db;


import android.os.Environment;

import org.xutils.DbManager;


/**
 * 数据库的管理类，设置数据保存的位置 ，数据库的名称，版本
 * Created by Administrator on 2016/5/21.

public class MyDbManage {

    private final String DB_NAME = "cpdb";
    private int DB_VERSION = 1;
    private String DB_PATH = Environment.getExternalStorageDirectory() + "/DB/";


    public static MyDbManage dbManage;


    public static MyDbManage getMyDbManage() {
        if (dbManage == null) {
            dbManage = new MyDbManage();
        }
        return dbManage;

    }

    public
    DbManager.DaoConfig daoConfig;


    /**
     * 初始化DB
     * 在appAppContext 中

    public void initDB() {


        daoConfig = new DbManager.DaoConfig()
                .setDbName(DB_NAME)
                // 不设置dbDir时, 默认存储在app的私有目录.
             //   .setDbDir(new File(DB_PATH)) // "sdcard"的写法并非最佳实践, 这里为了简单, 先这样写了.
                .setDbVersion(DB_VERSION)

//                .setDbOpenListener(new DbManager.DbOpenListener() {
//                    @Override
//                    public void onDbOpened(DbManager db) {
//                        // 开启WAL, 对写入加速提升巨大
//                        db.getDatabase().enableWriteAheadLogging();
//                    }
//                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });





    }


    /**
     *
     * @return

    public DbManager.DaoConfig getDaoConfig() {
        if (daoConfig != null)
            return daoConfig;
        else {
            initDB();
         return  daoConfig;
        }


    }

}
*/