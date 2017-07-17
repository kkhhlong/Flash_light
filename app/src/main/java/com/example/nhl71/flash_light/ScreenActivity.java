package com.example.nhl71.flash_light;

import android.app.Activity;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import com.example.nhl71.flash_light.Systems.AdjustedBrightness;

/**
 * Created by nhl71 on 16/05/2017.
 */

public class ScreenActivity extends Activity implements View.OnClickListener{
    Button red,white,purple,yellow,blue,green,pink;
    LinearLayout screen;
    ImageButton back;
    int curBrightnessValue ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_screen);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);


        curBrightnessValue = AdjustedBrightness.getBrightnessLevel(getContentResolver());
        AdjustedBrightness.setBrightnessLevel(getContentResolver(),255);

        connectView();
        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
        white.setOnClickListener(this);
        blue.setOnClickListener(this);
        green.setOnClickListener(this);
        purple.setOnClickListener(this);
        pink.setOnClickListener(this);


    }
    void connectView(){
        red= (Button) findViewById(R.id.red);
        white= (Button) findViewById(R.id.white);
        yellow= (Button) findViewById(R.id.yellow);
        blue= (Button) findViewById(R.id.blue);
        green= (Button) findViewById(R.id.green);
        purple= (Button) findViewById(R.id.purple);
        pink= (Button) findViewById(R.id.pink);
        screen = (LinearLayout) findViewById(R.id.screen);

    }



    @Override
    public void onClick(View v) {
       com.example.nhl71.flash_light.Systems.Vibrator.setVibrator(50,ScreenActivity.this);
        switch (v.getId()){
            case R.id.white:
                screen.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.yellow:
                screen.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case R.id.blue:
                screen.setBackgroundColor(getResources().getColor(R.color.blue));
                break;
            case R.id.green:
                screen.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case R.id.purple:
                screen.setBackgroundColor(getResources().getColor(R.color.purple));
                break;
            case R.id.pink:
                screen.setBackgroundColor(getResources().getColor(R.color.pink));
                break;
            case R.id.red:
                screen.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case  R.id.back:
                finish();
                break;

        }

    }
    @Override
    public void finish() {
        super.finish();
        AdjustedBrightness.setBrightnessLevel(getContentResolver(),curBrightnessValue);
    }



}
