package com.example.chapter10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast; // 사용하지 않음

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editpassword;
    TextView textJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("111");

        textJoin = (TextView) findViewById(R.id.textJoin);

        // 회원 가입 액티비티를 호출하는 코드 추가, Intent 객체 활용, 이후 Menifest에 새로운 액티비티 등록해야함
        textJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivityForResult(intent, 201);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 조건문을 통해 resultCode 부분을 다루어주어야 뒤로가기 버튼을 눌렀을 때 비정상 종료가 일어나지 않는다.
        if (resultCode == RESULT_OK) {
            editEmail = (EditText) findViewById(R.id.editEmail);
            editpassword = (EditText) findViewById(R.id.editpassword);

            // Toast.makeText(getApplicationContext(), "아이디" + Id + "비밀번호" + Pass, Toast.LENGTH_LONG).show(); // 전달이 잘 되는지 토스트로 확인해봄

            editEmail.setText(data.getStringExtra("email"));
            editpassword.setText(data.getStringExtra("password"));
        }
    }
}