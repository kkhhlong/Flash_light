package com.example.nhl71.flash_light;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Handler;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

public class MainActivity extends Activity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
       ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
        else
            nextActivity();


    }
    void nextActivity(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iMenu = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(iMenu);
                finish();
            }
        },3000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent iMenu = new Intent(MainActivity.this,MenuActivity.class);
                        startActivity(iMenu);
                        finish();
                    }
                },2000);
            }
            else {
                finish();
            }
        }
    }
}
