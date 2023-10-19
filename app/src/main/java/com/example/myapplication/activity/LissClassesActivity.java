package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ClassesAdapter;
import com.example.myapplication.model.Classes;
import com.example.myapplication.sqlite.ClassesDao;

import java.util.List;

public class LissClassesActivity extends AppCompatActivity {
    private ListView lvClasses;
    private List<Classes> list;
    private ClassesAdapter clsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liss_classes);

        lvClasses = findViewById(R.id.lvClasses);
        fillClassesListView();

        lvClasses.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClassesDao dao = new ClassesDao(LissClassesActivity.this);
                Classes cls = list.get(i);
                dao.delete(""+cls.getId());

                fillClassesListView();
                return false;
            }
        });
    }

    private void fillClassesListView() {
        ClassesDao dao = new ClassesDao(this);
        list = dao.getAll();

        clsAdapter = new ClassesAdapter(this,list);
        lvClasses.setAdapter(clsAdapter);
    }
}