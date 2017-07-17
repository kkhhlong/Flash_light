package com.example.nhl71.flash_light;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by nhl71 on 16/05/2017.
 */

public class MenuActivity extends Activity implements View.OnClickListener {
    ImageButton bulb,light,policelight,flashlight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);


        bulb = (ImageButton) findViewById(R.id.bulb);
        bulb.setOnClickListener(this);
        light = (ImageButton) findViewById(R.id.light);
        light.setOnClickListener(this);
        policelight = (ImageButton) findViewById(R.id.policelight);
        policelight.setOnClickListener(this);
        flashlight = (ImageButton) findViewById(R.id.flashlight);
        flashlight.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        com.example.nhl71.flash_light.Systems.Vibrator.setVibrator(50,MenuActivity.this);
        switch (v.getId()){
            case R.id.bulb:
                Intent iBulb = new Intent(MenuActivity.this,BulbActivity.class);
                startActivity(iBulb);
                break;
            case R.id.light:
                Intent iLight = new Intent(MenuActivity.this,ScreenActivity.class);
                startActivity(iLight);
                break;
            case R.id.policelight:
                Intent iPolice = new Intent(MenuActivity.this,PoliceLightActivity.class);
                startActivity(iPolice);
                break;
            case R.id.flashlight:
                Intent iFlashLight = new Intent(MenuActivity.this,FlashLightActivity.class);
                startActivity(iFlashLight);
                break;
        }
    }
}
