package com.testpro.mdaling.dbutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.testpro.mdaling.dbutils.bean.Student;
import com.testpro.mdaling.dbutils.db.BaseDBFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCreateTable(View view) {
        BaseDBFactory baseDBFactory = BaseDBFactory.newInstance();
        baseDBFactory.getBaseDb(Student.class);
        Toast.makeText(this, "执行成功", Toast.LENGTH_SHORT).show();
    }
}
