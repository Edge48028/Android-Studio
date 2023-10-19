package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Classes;

import java.util.List;

public class ClassesAdapter extends BaseAdapter {
    private Context context;
    private List<Classes> list;

    public ClassesAdapter(Context context, List<Classes> list) {
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
        if ( view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_classes_item,null);
        }
        TextView tvId = view.findViewById(R.id.tvClassId);
        TextView tvName = view.findViewById(R.id.tvName);

        Classes cls = list.get(i);
        tvId.setText(""+cls.getId());
        tvName.setText(cls.getName());
        return view;

    }
}
