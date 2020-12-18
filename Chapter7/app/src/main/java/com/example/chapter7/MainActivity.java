 package com.example.chapter7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
     TextView text;
     LinearLayout baseLayout;
     ImageView img;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        text = (TextView) findViewById(R.id.text);
        img = (ImageView) findViewById(R.id.img);
        registerForContextMenu(img);
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) { //옵션 메뉴 파일 등록
         MenuInflater mInflater = getMenuInflater();
         mInflater.inflate(R.menu.menu_option, menu);
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) { //메뉴 선택 시 작동할 내용
         switch (item.getItemId()) {
             case R.id.ball:
                 baseLayout.setBackgroundColor(Color.rgb(110, 240, 255));
                 text.setText("몬스터볼");
                 return true;
             case R.id.gym:
                 baseLayout.setBackgroundColor(Color.rgb(255, 50, 50));
                 text.setText("체육관");
                 return true;
             case R.id.park:
                 baseLayout.setBackgroundColor(Color.rgb(155, 240, 72));
                 text.setText("공원");
                 return true;
         }
         return false;
     }

     @Override
     public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //컨텍스트 메뉴 파일 등록
         super.onCreateContextMenu(menu, v, menuInfo);

         MenuInflater mInflater = getMenuInflater();
         mInflater.inflate(R.menu.menu_context, menu);
         menu.setHeaderTitle("피카츄에게 무엇을 할까?");
     }

     @Override
     public boolean onContextItemSelected(@NonNull MenuItem item) { //컨텍스트 메뉴 시 작동할 내용
         switch(item.getItemId()) {
             case R.id.fruit:
                 final String[] fruitArray = new String[] {"라즈열매", "나나열매", "파인열매"};
                 AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                 dlg.setTitle("어떤 열매를 먹일까?");
                 dlg.setItems(fruitArray, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         LayoutInflater inflater = getLayoutInflater();
                         View toastView = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));

                         Toast toast = new Toast(getApplicationContext());
                         toast.setGravity(Gravity.CENTER, 0, -100);
                         toast.setDuration(Toast.LENGTH_SHORT);
                         toast.setView(toastView);
                         toast.show();
                     }
                 });
                 dlg.setPositiveButton("취소", null);
                 dlg.show();
                 return true;

             case R.id.sleep:
                 Toast.makeText(MainActivity.this, "잘잤다!", Toast.LENGTH_SHORT).show();
                 return true;
         }
         return super.onContextItemSelected(item);
     }
 }
