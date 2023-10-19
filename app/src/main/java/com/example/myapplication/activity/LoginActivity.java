package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearLayout4;
    private EditText etUser, etPassWord;
    private Button btnLogin;
    private String strU , strP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linearLayout4 = (LinearLayout)findViewById(R.id.linearLayout4);
        linearLayout4.setBackgroundResource(R.drawable.hinhda1);
        etUser = findViewById(R.id.etUser);
        etPassWord = findViewById(R.id.etPassWord);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }
    public int isLogin(String user, String pass){
        if(user.equals("admin")&& pass.equals("admin")){
            return 1;
        }
        else{
            return 0;
        }
    }


    @Override
    //Phương thức dùng để kiểm tra mật khẩu và tài khoản
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                strU = etUser.getText().toString();
                strP = etPassWord.getText().toString();
                if (strU.isEmpty()||strP.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                    "User hoặc Password không được để trống",
                    Toast.LENGTH_LONG).show();
                }
                else{
                    if (isLogin(strU,strP)==1){
                        Toast.makeText(getApplicationContext(),
                        "Đăng nhập thành công", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(this, MainActivity.class);
//                        startActivity(intent);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        },2000);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                        "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_LONG).show();
                        }

                }
                break;
        }
    }
}