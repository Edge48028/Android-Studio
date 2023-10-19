package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.activity.LissClassesActivity;
import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.ManageStudentsActivity;
import com.example.myapplication.dialog.NewClassDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNewClass).setOnClickListener(this);
        findViewById(R.id.btnListClasses).setOnClickListener(this);
        findViewById(R.id.btnManageStudents).setOnClickListener(this);
        findViewById(R.id.btnLogout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNewClass:
                NewClassDialog dialog = new NewClassDialog(this);
                dialog.show();
                break;
            case R.id.btnListClasses:
            // Thực hiện kích vào button danh sách lớp trên giao diện để hiển thị ra danh sách lớp
                Intent intent = new Intent(this, LissClassesActivity.class);
                startActivity(intent);
                break;
            case R.id.btnManageStudents:
                // Thực hiện kích vào button danh sách lớp trên giao diện
                // để hiển thị ra mục quản lí sinh viên
                Intent mngIntent = new Intent(this, ManageStudentsActivity.class);
                startActivity(mngIntent);
                break;
            case R.id.btnLogout:
                // Thực hiện kích vào button đăng xuất trên giao diện
                // để quay trở lại trang đăng nhập
                Intent logoutintent = new Intent(this, LoginActivity.class);
                startActivity(logoutintent);
                break;
        }
    }
}