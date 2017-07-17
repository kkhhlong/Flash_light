package com.example.nhl71.flash_light.Systems;

import android.app.Activity;
import android.content.Context;

/**
 * Created by nhl71 on 17/05/2017.
 */

public  class Vibrator extends Activity {
    public  static void setVibrator(int milliseconds, Context ct){
        android.os.Vibrator vt;
        vt = (android.os.Vibrator) ct.getSystemService(VIBRATOR_SERVICE);
        vt.vibrate(milliseconds);
    }


}
