package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ClassesAdapter;
import com.example.myapplication.adapter.StudentsAdapter;
import com.example.myapplication.helper.DateTimeHelper;
import com.example.myapplication.model.Classes;
import com.example.myapplication.model.Student;
import com.example.myapplication.sqlite.ClassesDao;
import com.example.myapplication.sqlite.StudentDao;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ManageStudentsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etStudentId,etName,etDob;
    private Spinner spClasses;
    private List<Classes> classesList;

    private List<Student> studentList;
    private ListView lvStudents;
    private StudentsAdapter studentsAdapter;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
        //khai báo biến và lưu thông tin người dùng nhập vào cho biến
        etStudentId = findViewById(R.id.etStudentId);
        etName = findViewById(R.id.etName);
        etDob = findViewById(R.id.etDob);
        spClasses = findViewById(R.id.spClasses);
        //
        lvStudents = findViewById(R.id.lvStudents);
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student std = studentList.get(i);
                etStudentId.setText(std.getId());
                etName.setText(std.getName());
                etDob.setText(DateTimeHelper.toString(std.getDob()));
                isEdit = true;
            }
        });

        fillClassesToSpinner();

        //gắn phương thức xử lí sự kiện vào các button
        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
    }
    //Phương thức cho phép đổi dữ liêu lấy từ cơ sở dữ liệu
    private void fillStudentsToListView(){
        StudentDao dao = new StudentDao(this);
        try {
            Classes cls = (Classes) spClasses.getSelectedItem();
            studentList = dao.getAllByClass(cls.getId());

            studentsAdapter = new StudentsAdapter(this,studentList);
            lvStudents.setAdapter(studentsAdapter);

        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"Error: "+ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void fillClassesToSpinner(){
        ClassesDao dao = new ClassesDao(this);
        classesList = dao.getAll();
        ClassesAdapter classesAdapter = new ClassesAdapter(this,classesList);
        spClasses.setAdapter(classesAdapter);
        //Phương thức khi người dùng lựa chọn lớp học mới thì gọi
        // thực hiện phương thức fillStudentsToListView để cập nhật lại nội dung hiển thị
        spClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillStudentsToListView();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        StudentDao dao = new StudentDao(this);
        switch (view.getId()){
            //mã xử lí lưu thông tin vào cơ sở dữ liệu
            case R.id.btnSave:
                try {
                    //Thiết lập thông tin sinh viên
                    Student std = new Student();
                    std.setId(etStudentId.getText().toString());
                    std.setName(etName.getText().toString());
                    std.setDob(DateTimeHelper.toDate(etDob.getText().toString()));
                    //Đưa thông tin sinh viên vào và thông báo
                    Classes cls = (Classes) spClasses.getSelectedItem();
                    std.setClassId(cls.getId());
                    String msg;
                    //check xem thông tin đưa vào là cập nhật hay thêm mới
                    if (!isEdit){
                        dao.insert(std);
                        msg = "Đã lưu thông tin sinh viên!";
                    }else{
                        dao.update(std);
                        msg = "Thông tin đã được cập nhật";
                    }
                    // Xóa trắng thông tin sau khi luu
                    Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    etStudentId.setText("");
                    etName.setText("");
                    etDob.setText("");
                    isEdit = false;
                    fillStudentsToListView();

                }catch (Exception ex){
                    //đoạn này là có lỗi thì khai báo
                    ex.printStackTrace();
                    Toast.makeText(this,"Error: "+ ex.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
                break;
                //mã xử lí thông tin khi ấn vào nút xóa trên giao diện
            case  R.id.btnDelete:
                //Xóa thông tin ra khỏi cơ sở dữ liệu
                if (isEdit && !etStudentId.getText().toString().equals("")){
                    String id = etStudentId.getText().toString();
                    dao.delete(id);
                    //Hiển thị lại thông tin sau khi xóa
                    fillStudentsToListView();
                    //thông báo cho người dùng sau khi xử lí xóa
                    Snackbar.make(view,"Sinh viên đã được xóa",
                        Snackbar.LENGTH_LONG).show();
                    //Xóa trắng thông tin sau khi xử lí
                    etStudentId.setText("");
                    etName.setText("");
                    etDob.setText("");
                }
                break;
        }
    }
}