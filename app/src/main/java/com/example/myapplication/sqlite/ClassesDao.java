package com.example.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.Classes;

import java.util.ArrayList;
import java.util.List;

public class ClassesDao {
    private SQLiteDatabase db;

    public ClassesDao(Context context) {
        DbHelper helper = new DbHelper(context);
        //tạo ra đối tượng thể hiện lớp SQLiteDatabase tạo thực hiện các thao tác liên quan với csdl
        this.db = helper.getWritableDatabase();

    }
    //phương thức cho phép thêm dữ liệu mới vào bảng Classes trong Sqlite
    public long insert(Classes emp){
        ContentValues values = new ContentValues();
        values.put("id",emp.getId());
        values.put("name",emp.getName());
        return db.insert("classes",null,values);
    }
    @SuppressLint("Range")
    //Đọc thông tin chi tiết và tạo ra danh sách các lớp
    public List<Classes>get(String sql, String ... selectArgs){
        List<Classes> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);
        //Duyệt qua danh sách và tạo ra đối lớp học, thiết lập và cho vào trong danh sách
        while (cursor.moveToNext()){
            Classes cls = new Classes();
            cls.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cls.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(cls);
        }
        return list;
    }
    //Gọi thực hiện phương thức get() và lấy dữ liệu từ cơ sở dữ liệu
    public List<Classes>getAll(){
        String sql = "SELECT * FROM classes";
        return  get(sql);
    }
    //Phương thức xóa lớp học trong cơ sở dữ liệu
    public int delete(String id){
       return db.delete("classes","id=?",new String[]{id});
    }
}

