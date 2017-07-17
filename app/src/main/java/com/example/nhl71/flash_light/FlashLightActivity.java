package com.example.nhl71.flash_light;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.nhl71.flash_light.Systems.Vibrator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nhl71 on 17/05/2017.
 */

public class FlashLightActivity extends Activity {
    Switch twinkle;
    CheckBox flashLight;
    Camera camera;
    Camera.Parameters p;
    Handler handler;
    Timer timer;
    ImageButton back;
    boolean light = false;
    boolean process = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_flashlight);
        twinkle = (Switch) findViewById(R.id.twinkle);
        flashLight = (CheckBox) findViewById(R.id.switchFlashLight);
        back = (ImageButton) findViewById(R.id.back);
        if(camera != null){
            camera.release();
        }
        camera = Camera.open();
        p = camera.getParameters();
        flashLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator.setVibrator(50,FlashLightActivity.this);
                if(!twinkle.isChecked()) {
                    if (flashLight.isChecked()) {
                        turnOnFlashLight();
                        twinkle.setEnabled(false);
                    } else {
                        turnOffFlashLight();
                        twinkle.setEnabled(true);
                    }
                }else {
                    if(flashLight.isChecked()) {

                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if(process == true) {
                                    handler.sendEmptyMessage(0);
                                }
                            }
                        },0,40);

                        twinkle.setEnabled(false);
                    }
                    else {
                        timer.cancel();
                        turnOffFlashLight();
                        twinkle.setEnabled(true);
                    }
                }


            }
        });
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0){
                    process = false;
                    if (light) {
                        turnOffFlashLight();
                        light = false;
                    } else {
                        turnOnFlashLight();
                        light = true;
                    }
                    process = true;
                }
            }
        };
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator.setVibrator(50,FlashLightActivity.this);
                finish();
            }
        });

    }
    public void turnOnFlashLight(){
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
    }
    public void turnOffFlashLight(){
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(p);
        camera.stopPreview();
    }
}

