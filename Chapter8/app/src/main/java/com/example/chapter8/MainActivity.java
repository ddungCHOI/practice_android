package com.example.chapter8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnRef, btnMod;
    LinearLayout linear;
    CalendarView cal;
    EditText edit;
    TextView txtSchedule;

    int cDay, cMonth, cYear;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRef = (Button) findViewById(R.id.btnRef);
        btnMod = (Button) findViewById(R.id.btnMod);
        linear = (LinearLayout) findViewById(R.id.linear);
        cal = (CalendarView) findViewById(R.id.cal);
        edit = (EditText) findViewById(R.id.edit);
        txtSchedule = (TextView) findViewById(R.id.txtSchedule);

        Calendar calendar = Calendar.getInstance();
        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH);
        cDay = calendar.get(Calendar.DAY_OF_MONTH);

        fileName = cYear + cMonth + cDay + ".txt";
        ReadSchedule(); // 앱 종료 후에 재실행 하였을 때, 현재 날짜 기준으로 일정이 있다면 보여줌

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                cYear = year;
                cMonth = month;
                cDay = day;

                ReadSchedule();
            }
        });

        btnRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fileName = cYear + cMonth + cDay + ".txt";
                    FileOutputStream outFs = openFileOutput(fileName,
                            Context.MODE_PRIVATE);
                    String str = edit.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();

                    txtSchedule.setText(edit.getText());

                    linear.setVisibility(View.INVISIBLE);
                    btnMod.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                }

                edit.setText(null);
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear.setVisibility(View.VISIBLE);
                btnMod.setVisibility(View.INVISIBLE);
                edit.setText(null);
            }
        });
    }

    private void ReadSchedule() {
        linear.setVisibility(View.INVISIBLE);
        btnMod.setVisibility(View.VISIBLE);
        try {
            fileName = cYear + cMonth + cDay + ".txt";
;           FileInputStream inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            String str = new String(txt);

            txtSchedule.setText(str);

            edit.setText(null);
        } catch (IOException e) {
            txtSchedule.setText("일정이 없습니다.");
        }
        edit.setText(null);
    }
}