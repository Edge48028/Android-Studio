package com.example.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper  {
    private static final String DB_NAME = "myapplication";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //tạo bảng trong cơ sở dữ liệu
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String classesSql = "CREATE TABLE classes(id integer primary key autoincrement,"+
                "name text not null)";
        String studentsSql = "CREATE TABLE students(id text primary key, "+
                "name text not null, classid integer, dob text, "+
                "FOREIGN KEY (classid) REFERENCES classes(id))";
        sqLiteDatabase.execSQL(classesSql);
        sqLiteDatabase.execSQL(studentsSql);

    }
    //thực hiện update trong cơ sở dữ liệu
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //thực hiện xóa bảng hiện tại
        String classesSql = "DROP TABLE IF EXISTS classes";
        String studentsSql = "DROP TABLE IF EXISTS students";
        //thực hiện tạo lại bảng
        sqLiteDatabase.execSQL(studentsSql);
        sqLiteDatabase.execSQL(classesSql);
        onCreate(sqLiteDatabase);
    }
}
