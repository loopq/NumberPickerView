package com.example.administrator.numberpickerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.numberpickerview.widget.NumberPickerView;
import com.example.numberpickerview.interfaces.ValueChooseListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.showpopupwindow)
    Button showpopupwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.showpopupwindow)
    public void onClick(View v) {
        NumberPickerView numberPickerView = new NumberPickerView(this);
        numberPickerView.setMaxValue(10);
        numberPickerView.setMinValue(1);
        numberPickerView.setOnCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zhuhu","cancel");
            }
        });

        numberPickerView.setOnValueChooseListener(new ValueChooseListener() {
            @Override
            public void onValueChoose(int value) {
                Toast.makeText(MainActivity.this, "value:"+value, Toast.LENGTH_SHORT).show();
            }
        });
        numberPickerView.showPopupWindow();
    }
}
