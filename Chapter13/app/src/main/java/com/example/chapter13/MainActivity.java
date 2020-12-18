package com.example.chapter13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout layoutCon;
    LinearLayout layoutLinear;
    ProgressBar barProgress;
    TextView txtPercent;
    Button btnChange, btnDownload;

    ArrayList<Integer> myColors = new ArrayList<Integer>();
    int myAva = 2; // 초기엔 색깔 2가지만 이용할 수 있음
    int myInit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("111");

        layoutCon = (ConstraintLayout) findViewById(R.id.layoutCon);
        layoutLinear = (LinearLayout) findViewById(R.id.layoutLinear);
        barProgress = (ProgressBar) findViewById(R.id.barProgress);
        txtPercent = (TextView) findViewById(R.id.txtPercent);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        btnChange = (Button) findViewById(R.id.btnChange);

        int myColor1 = ContextCompat.getColor(this, R.color.myColor1);
        int myColor2 = ContextCompat.getColor(this, R.color.myColor2);
        int myColor3 = ContextCompat.getColor(this, R.color.myColor3);
        int myColor4 = ContextCompat.getColor(this, R.color.myColor4);
        int myColor5 = ContextCompat.getColor(this, R.color.myColor5);

        myColors.add(myColor1);
        myColors.add(myColor2);
        myColors.add(myColor3);
        myColors.add(myColor4);
        myColors.add(myColor5);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDownload.setVisibility(View.INVISIBLE);
                layoutLinear.setVisibility(View.VISIBLE);
                new Thread() {
                    public void run() {
                        for (int i= barProgress.getProgress(); i < 100; i++) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    barProgress.setProgress(barProgress.getProgress() + 1);
                                    txtPercent.setText(barProgress.getProgress() + "%");
                                }
                            });
                            SystemClock.sleep(30);
                        }
                        layoutLinear.setVisibility(View.INVISIBLE);
                        barProgress.setProgress(0);
                        myAva = myColors.size();
                    }
                }.start();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInit = (myInit + 1) % myAva; // 다운받기 전에는 두가지에서만 반복 (0,1), 다운로드 뒤에는 순차적으로 반복 (0,1,2,3,4,5)
                layoutCon.setBackgroundColor(myColors.get(myInit));
            }
        });
    }
}