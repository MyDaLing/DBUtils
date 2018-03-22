package com.testpro.mdaling.dbutils.bean;

import com.testpro.mdaling.dbutils.annotation.DBField;
import com.testpro.mdaling.dbutils.annotation.DBTable;

/**
 * Created by M_DaLing on 2018/3/22.
 */

@DBTable("teacher")
public class Teacher {
    @DBField("_id")
    Integer tearcherId;
    @DBField("teacher_name")
    String teacherName;
    String teacherMajor;
}
