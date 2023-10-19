package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.model.Classes;
import com.example.myapplication.sqlite.ClassesDao;


public class NewClassDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private EditText etClassid, etName;
    public NewClassDialog(@NonNull Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_class);

        etClassid = findViewById(R.id.etClassid);
        etName = findViewById(R.id.etName);

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                //lấy thông tin người dùng nhập vào trên from và lưu vào
                // cơ sở dữ liệu
                Classes cls = new Classes();
                cls.setName(etName.getText().toString());
                ClassesDao dao = new ClassesDao(context);
                dao.insert(cls);
                Toast.makeText(context,"lớp đã được lưu!",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btnClose:
                dismiss();
                break;
        }
    }
}
