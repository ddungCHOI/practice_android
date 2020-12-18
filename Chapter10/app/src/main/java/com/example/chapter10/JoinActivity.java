package com.example.chapter10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editpassword;
    Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        setTitle("111");

        editEmail = (EditText) findViewById(R.id.editEmail);
        editpassword = (EditText) findViewById(R.id.editpassword);

        /*
        1. putExtra() : 회원 가입에서 이메일과 비밀번호를 입력
        2. setResult() : 성공을 알리는 코드와 인텐트를 보냄
        3. 가입하기 버튼 클릭하면 현재 액티비티를 끝냄
        4. Main에서 getExtra를 통해 이메일과 비밀번호를 받음 (Main 에서는 onActivityResult를 생성해야 함)
        */

        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("email", editEmail.getText().toString());
                intent.putExtra("password", editpassword.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
