package com.example.chapter11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TodoViewHolder> {

    private ArrayList<Todo> todoList;

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        protected TextView todoView;
        protected ImageView imgView;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            this.todoView = (TextView) itemView.findViewById(R.id.todoView);
            this.imgView = (ImageView) itemView.findViewById(R.id.imgView);

            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // position으로 아이템 위치 알아냄
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        todoList.remove(position); // 포지션이 삭제되지 않았다면 리스트에서 포지션의 아이템 제거
                        notifyDataSetChanged(); // 어댑터에게 변경을 알림
                    }
                }
            });
        }
    }

    public MyAdapter(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.todoView.setText(todoList.get(position).getTodoName()); // todoView.setText()와 비슷한 맥락
    }

    @Override
    public int getItemCount() {
        if (todoList == null) {
            return 0;
        } else {
            return todoList.size();
        }
    }
}
