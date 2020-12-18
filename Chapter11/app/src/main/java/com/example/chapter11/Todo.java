package com.example.chapter11;

public class Todo {

    private String todoName;

    public Todo(String todoName) { // Main 액티비티
        this.todoName = todoName;
    }

    public String getTodoName() { // 어댑터
        return todoName;
    }
}
