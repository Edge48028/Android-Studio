package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.helper.DateTimeHelper;
import com.example.myapplication.model.Student;

import java.util.List;

public class StudentsAdapter extends BaseAdapter {
    private Context context;
    private List<Student> list;

    public StudentsAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override

    //phương thức cho phép tạo view được hiển thị cho mỗi một dữ liệu trong view
    public View getView(int i, View view, ViewGroup viewGroup) {
        //nếu view chưa có thì tạo view mới
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_student_item,null);

        }
        //tìm kiếm các giá trị trong layout view đang hiển thị
        TextView tvStudent = view.findViewById(R.id.tvStudentId);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDob = view.findViewById(R.id.tvDob);
        //Lấy thông tin của đối tượng được lựa chọn để hiển thị
        Student std = list.get(i);
        //sửa thông tin
        tvStudent.setText(std.getId());
        tvName.setText(std.getName());
        tvDob.setText(DateTimeHelper.toString(std.getDob()));

        return view;
    }
}
