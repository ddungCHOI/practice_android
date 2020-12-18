package com.example.chapter11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Todo> TodoArrayList;
    private MyAdapter TodoAdapter;
    private EditText editTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        TodoArrayList = new ArrayList<>();
        TodoAdapter = new MyAdapter(TodoArrayList);
        recycler.setAdapter(TodoAdapter);

        editTodo = (EditText) findViewById(R.id.editTodo);

        // 버튼 클릭
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String todo = editTodo.getText().toString(); // EditText에 입력한 문자열 가져오기
                Todo newTodo = new Todo(todo); // 문자열로 객체 생성 (todo)
                TodoArrayList.add(newTodo); // TodoArrayList에 객체 추가

                TodoAdapter.notifyDataSetChanged(); // 어댑터에게 변경을 알림
                editTodo.setText(null);
            }
        });
    }
}