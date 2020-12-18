package com.example.chapter12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ArrayList<Todo> TodoList;
    private MyAdapter TodoAdapter;
    private EditText editTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("111");

        dbHelper = DBHelper.getInstance(getApplicationContext());

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        TodoList = dbHelper.getAll();
        TodoAdapter = new MyAdapter(TodoList);
        recycler.setAdapter(TodoAdapter);

        editTodo = (EditText) findViewById(R.id.editTodo);

        // 버튼 클릭
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo data = new Todo(editTodo.getText().toString()); // EditText에 입력한 문자열 가져오기
                TodoList.add(data);
                dbHelper.insert(data);

                TodoAdapter.notifyDataSetChanged(); // 어댑터에게 변경을 알림
                editTodo.setText(null);
            }
        });
    }
}