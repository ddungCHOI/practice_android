package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox chkbox;
    TextView text1, text2;

    RadioGroup rgroup;
    RadioButton rbutton[] = new RadioButton[3]; //치코리타(0), 브케인(1), 리아코(2)

    Button btn;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 각 위젯을 변수에 대입
        chkbox = (CheckBox) findViewById(R.id.checkBox);
        text1 = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);

        rgroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbutton[0] = (RadioButton) findViewById(R.id.radioButton);
        rbutton[1] = (RadioButton) findViewById(R.id.radioButton2);
        rbutton[2] = (RadioButton) findViewById(R.id.radioButton3);

        btn = (Button) findViewById(R.id.button);
        img = (ImageView) findViewById(R.id.imageView);

        // 체크박스의 체크 여부
        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // 체크 상태와 체크 상태가 아닐 때 각각 보여져야 할 화면 정의
                if (chkbox.isChecked() == true) {
                    text1.setVisibility(android.view.View.GONE);
                    text2.setVisibility(android.view.View.VISIBLE);
                    rgroup.setVisibility(android.view.View.VISIBLE);
                    btn.setVisibility(android.view.View.VISIBLE);
                    img.setImageResource(0);
                } else {
                    text1.setVisibility(android.view.View.VISIBLE);
                    text2.setVisibility(android.view.View.GONE);
                    rgroup.setVisibility(android.view.View.GONE);
                    btn.setVisibility(android.view.View.GONE);
                    img.setImageResource(R.drawable.samuel_oak);
                }
            }
        });

        // 라디오버튼을 클릭할 때, 배열 처리하여 이미지 뷰를 변경시킴
        final int draw[] = {R.drawable.chikorita, R.drawable.cyndaquil, R.drawable.totodile};

        for (int i = 0; i < rbutton.length; i++) {
            final int index;
            index = i;
            rbutton[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    img.setImageResource(draw[index]);
                }
            });
        }

        // 선택완료 버튼
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                switch (rgroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        img.setImageResource(R.drawable.ash_ketchum);
                        Toast.makeText(getApplicationContext(), "치코리타! 너로 정했다!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton2:
                        img.setImageResource(R.drawable.ash_ketchum);
                        Toast.makeText(getApplicationContext(), "브케인! 너로 정했다!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton3:
                        img.setImageResource(R.drawable.ash_ketchum);
                        Toast.makeText(getApplicationContext(), "리아코! 너로 정했다!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "포켓몬 먼저 선택해야합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}