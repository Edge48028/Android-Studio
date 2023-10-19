package com.example.myapplication.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//Đây là lớp chủ yếu để chuyển đổi kiểu dữ liệu date sang string và ngược lại
public class DateTimeHelper {
    private static final String Pattern = "dd/MM/yyyy";
    public static Date toDate(String st) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
        return sdf.parse(st);
    }
    public static String toString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
        return sdf.format(date);
    }
}
