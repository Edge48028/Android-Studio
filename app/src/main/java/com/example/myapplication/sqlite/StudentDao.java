package com.example.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.helper.DateTimeHelper;
import com.example.myapplication.model.Classes;
import com.example.myapplication.model.Student;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private SQLiteDatabase db;

    public StudentDao(Context context) {
        DbHelper helper = new DbHelper(context);
        //tạo ra đối tượng thể hiện lớp SQLiteDatabase tạo thực hiện các thao tác liên quan với csdl
        this.db = helper.getWritableDatabase();

    }
    //phương thức cho phép thêm dữ liệu mới vào bảng students trong Sqlite
    public long insert(Student emp){
        ContentValues values = new ContentValues();
        values.put("id",emp.getId());
        values.put("name",emp.getName());
        values.put("dob", DateTimeHelper.toString(emp.getDob()));
        values.put("classid",emp.getClassId());
        return db.insert("students",null,values);
    }
    //phương thức cho phép cập nhật lại đối tượng sinh viên trong sqlite
    public long update(Student emp){
        ContentValues values = new ContentValues();
        values.put("name",emp.getName());
        values.put("dob", DateTimeHelper.toString(emp.getDob()));
        values.put("classid",emp.getClassId());
        return db.update("students",values,
                " id = ?",new String[]{emp.getId()});
    }
    //phương thức cho phép xóa đối tượng sinh viên theo id
    public long delete(String id){

        return db.delete("students",
                " id = ?",new String[]{id});
    }
    @SuppressLint("Range")
    //Đọc thông tin chi tiết và tạo ra danh sách các sinh viên
    public List<Student> get(String sql, String ... selectArgs) throws ParseException {
        List<Student> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);
        //Duyệt qua danh sách và tạo ra đối tượng sinh viên, thiết lập và cho vào trong danh sách
        while (cursor.moveToNext()){
            Student cls = new Student();
            cls.setId(cursor.getString(cursor.getColumnIndex("id")));
            cls.setName(cursor.getString(cursor.getColumnIndex("name")));
            cls.setDob(DateTimeHelper.toDate( cursor.getString(cursor.getColumnIndex("dob"))));
            cls.setClassId(cursor.getInt(cursor.getColumnIndex("classid")));
            list.add(cls);
        }
        return list;
    }
    //Gọi thực hiện phương thức get() và lấy dữ liệu từ cơ sở dữ liệu
    public List<Student> getAll() throws ParseException {
        String sql = "SELECT * FROM students";
        return  get(sql);
    }
    //Phương thức lấy về thông tin các sinh viên có các lớp học mã lớp học truyền vào
    public List<Student>getAllByClass(Integer classId) throws ParseException {
        String sql = "SELECT * FROM students WHERE classid =?";


        return  get(sql,""+ classId);
    }
}
