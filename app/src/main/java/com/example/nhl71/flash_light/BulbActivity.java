package com.example.nhl71.flash_light;

import android.app.Activity;

import android.os.Bundle;
import com.example.nhl71.flash_light.Systems.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.example.nhl71.flash_light.Systems.AdjustedBrightness;

/**
 * Created by nhl71 on 16/05/2017.
 */

public class BulbActivity extends Activity {
    ImageButton bulb,back;
    boolean light = false;
    int curBrightnessValue ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bulb);



           curBrightnessValue = AdjustedBrightness.getBrightnessLevel(getContentResolver());

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator.setVibrator(50,BulbActivity.this);
                finish();
            }
        });
        bulb = (ImageButton) findViewById(R.id.bulb);
        bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator.setVibrator(100,BulbActivity.this);
                if(light){
                    light = false;
                    bulb.setImageResource(R.drawable.bulboff);

                    bulb.setBackgroundColor(getResources().getColor(R.color.dark));
                    AdjustedBrightness.setBrightnessLevel(getContentResolver(),curBrightnessValue);
                }
                else {
                    light = true;
                    bulb.setImageResource(R.drawable.bulbon);

                    bulb.setBackground(getResources().getDrawable(R.drawable.background_bulblight_on));
                    AdjustedBrightness.setBrightnessLevel(getContentResolver(),255);
                }
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        AdjustedBrightness.setBrightnessLevel(getContentResolver(),curBrightnessValue);
    }

}
