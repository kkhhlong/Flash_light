package com.example.nhl71.flash_light;

import android.app.Activity;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.nhl71.flash_light.Systems.AdjustedBrightness;
import com.example.nhl71.flash_light.Systems.Vibrator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nhl71 on 17/05/2017.
 */

public class PoliceLightActivity extends Activity implements View.OnClickListener {
    Handler handler;
    Timer timer;
    CheckBox policeSwitch;
    ImageButton back;
    RelativeLayout screen;
    LevelListDrawable background;
    int curBrightness;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_policelight);
        policeSwitch = (CheckBox) findViewById(R.id.policeSwitch);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        screen = (RelativeLayout) findViewById(R.id.screen);
        background = (LevelListDrawable) screen.getBackground();

        policeSwitch.setOnClickListener(this);
        curBrightness = AdjustedBrightness.getBrightnessLevel(getContentResolver());
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0){
                    if(background.getLevel()==17){
                        background.setLevel(0);
                        return;
                    }
                 background.setLevel(background.getLevel()+1);
                }
            }
        };


    }

    @Override
    public void onClick(View v) {
        Vibrator.setVibrator(50,PoliceLightActivity.this);
        switch (v.getId()){
            case R.id.policeSwitch:
                if(policeSwitch.isChecked()) {
                    AdjustedBrightness.setBrightnessLevel(getContentResolver(),255);
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(0);
                        }
                    }, 0, 50);
                }
                else {
                    AdjustedBrightness.setBrightnessLevel(getContentResolver(),curBrightness);
                    timer.cancel();
                    background.setLevel(0);
                }
                break;
            case  R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        AdjustedBrightness.setBrightnessLevel(getContentResolver(),curBrightness);
    }
}
