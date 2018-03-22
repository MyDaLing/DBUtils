package com.testpro.mdaling.dbutils.db;

import android.database.sqlite.SQLiteDatabase;

import com.testpro.mdaling.dbutils.annotation.DBField;
import com.testpro.mdaling.dbutils.annotation.DBTable;

import java.lang.reflect.Field;

/**
 * Created by M_DaLing on 2018/3/22.
 */

public class BaseDB<T> implements IBaseDB {

    private SQLiteDatabase sqLiteDatabase;

    private Class<T> enterClass;

    private String tableName;

    private boolean isInit = false;

    protected boolean init(SQLiteDatabase sqLiteDatabase, Class<T> enterClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.enterClass = enterClass;
        if (!isInit) {
            DBTable annotation = enterClass.getAnnotation(DBTable.class);
            if (annotation != null && !annotation.value().isEmpty()) {
                tableName = annotation.value();
            } else {
                tableName = enterClass.getName();
            }

            if (!sqLiteDatabase.isOpen()) {
                return false;
            }

            String createTableSql = getCreateTableSql();
            sqLiteDatabase.execSQL(createTableSql);
            isInit = true;
        }

        return isInit;
    }

    private String getCreateTableSql() {
        //create table if not exists tb_user(_id INTEGER,name TEXT,password TEXT)
        StringBuffer sql = new StringBuffer();
        sql.append("create table if not exists ");
        sql.append(tableName + "(");
        Field[] fields = enterClass.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            DBField annotation = field.getAnnotation(DBField.class);
            if (annotation != null && !annotation.value().isEmpty()) {
                if (type == String.class) {
                    sql.append(annotation.value() + " TEXT,");
                } else if (type == Integer.class) {
                    sql.append(annotation.value() + " INTEGER,");
                } else if (type == Long.class) {
                    sql.append(annotation.value() + " BIGINT,");
                } else if (type == Double.class) {
                    sql.append(annotation.value() + "DOUBLE,");
                } else if (type == byte[].class) {
                    sql.append(annotation.value() + "BLOB,");
                } else {
                    continue;
                }
            } else {
                if (type == String.class) {
                    sql.append(field.getName() + " TEXT,");
                } else if (type == Integer.class) {
                    sql.append(field.getName() + " INTEGER,");
                } else if (type == Long.class) {
                    sql.append(field.getName() + " BIGINT,");
                } else if (type == Double.class) {
                    sql.append(field.getName() + "DOUBLE,");
                } else if (type == byte[].class) {
                    sql.append(field.getName() + "BLOB,");
                } else {
                    continue;
                }
            }
        }

        if (sql.charAt(sql.length() - 1) == ',') {
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        return sql.toString();
    }

}
