package com.example.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Chronometer myCm;
    Button myStart, myStop, myRecord, myContinue, myReset;
    LinearLayout mylinear, mylinear2, mylinear3;
    long Stoppedtime = 0; // base로 넘겨주는 시간은 long 타입

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCm = (Chronometer) findViewById(R.id.cm_timer);

        myStart = (Button) findViewById(R.id.btnStart);
        myStop = (Button) findViewById(R.id.btnStop);
        myRecord = (Button) findViewById(R.id.btnRecord);
        myContinue = (Button) findViewById(R.id.btnContinue);
        myReset = (Button) findViewById(R.id.btnReset);

        mylinear = (LinearLayout) findViewById(R.id.linear);
        mylinear2 = (LinearLayout) findViewById(R.id.linear2); // 중지, 구간 기록
        mylinear3 = (LinearLayout) findViewById(R.id.linear3); // 계속, 초기화

        myStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myCm.setBase(SystemClock.elapsedRealtime()); // 비정상 종료 후 재실행 시 0으로 초기화
                myCm.start();
                myStart.setVisibility(View.GONE);
                mylinear2.setVisibility(View.VISIBLE);
            }
        });

        myStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCm.stop();
                Stoppedtime = myCm.getBase() - SystemClock.elapsedRealtime();
                mylinear2.setVisibility(View.GONE);
                mylinear3.setVisibility(View.VISIBLE);
            }
        });

        myContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCm.setBase(SystemClock.elapsedRealtime() + Stoppedtime);
                myCm.start();
                mylinear3.setVisibility(View.GONE);
                mylinear2.setVisibility(View.VISIBLE);
            }
        });

        myRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);
                TextView markText = new TextView(MainActivity.this);
                markText.setLayoutParams(params);
                markText.setGravity(Gravity.CENTER);
                markText.setText(myCm.getText());
                markText.setTextSize(25);
                mylinear.addView(markText,0);
            }
        });

        myReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylinear.removeAllViews(); // 모든 뷰 제거하여 초기화
                myCm.setBase(SystemClock.elapsedRealtime()); // 0으로 초기화
                mylinear3.setVisibility(View.GONE);
                myStart.setVisibility(View.VISIBLE);
            }
        });
    }
}
