package com.example.chapter12;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "todo_db";
    private static final int DB_VERSION = 1;
    private static volatile DBHelper instance;

    private SQLiteDatabase db = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper getInstance(Context context) {
         if (instance == null) {
             synchronized (DBHelper.class) {
                 if (instance == null) { // 클래스 필드인 instance가 null일 때
                     instance = new DBHelper(context); // 인스턴스 생성
                 }
             }
         }
         return instance; // 인스턴스 반환
     }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoDBContract.SQL_CREATE_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TodoDBContract.SQL_DROP_TBL);
        onCreate(db);
    }

    public void insert(Todo todo) {
        db = getWritableDatabase();
        db.execSQL(TodoDBContract.SQL_INSERT + "(" + '"' + todo.getTodoName() + '"' + ")");
        db.close();
    }

    public void delete(String title) {
        db = getWritableDatabase();
        db.execSQL(TodoDBContract.SQL_DELETED + '"' + title + '"');
        db.close();
    }

    public ArrayList<Todo> getAll() {
        ArrayList<Todo> tmp = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor = db.rawQuery(TodoDBContract.SQL_SELECT, null);

        while(cursor.moveToNext()) {
            String editTodo = cursor.getString(0);

            Todo data = new Todo(editTodo);
            tmp.add(data);
        }
        return tmp;
    }
}
