package com.testpro.mdaling.dbutils.db;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by M_DaLing on 2018/3/22.
 */

public class BaseDBFactory {

    private static BaseDBFactory baseDBFactory = new BaseDBFactory();

    private SQLiteDatabase sqLiteDatabase;

    private String sqliteDataBasePath;

    private BaseDBFactory() {
        //可以不写死通过配置文件获取
        sqliteDataBasePath = "data/data/com.testpro.mdaling.dbutils/school.db";
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDataBasePath, null);
    }

    public static BaseDBFactory newInstance() {
        return baseDBFactory;
    }

    public <T> BaseDB<T> getBaseDb(Class<T> enterClass) {

        BaseDB<T> baseDB = null;

        try {
            baseDB = BaseDB.class.newInstance();
            baseDB.init(sqLiteDatabase, enterClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseDB;

    }


}
