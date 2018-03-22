package com.testpro.mdaling.dbutils.bean;

import com.testpro.mdaling.dbutils.annotation.DBField;
import com.testpro.mdaling.dbutils.annotation.DBTable;

import java.util.zip.Inflater;

/**
 * Created by M_DaLing on 2018/3/22.
 */

@DBTable("student")
public class Student {
    @DBField("_id")
    Integer studentId;
    @DBField("student_name")
    String studentName;
    @DBField("student_class")
    String studentClass;
}
