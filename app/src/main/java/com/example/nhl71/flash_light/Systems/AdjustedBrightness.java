package com.example.nhl71.flash_light.Systems;

import android.content.ContentResolver;
import android.provider.Settings;

/**
 * Created by nhl71 on 17/05/2017.
 */

public class AdjustedBrightness {
    public static int getBrightnessLevel(ContentResolver cr){
        int level = 0;
        try {
            level = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return  level;
    }
    public static void setBrightnessLevel(ContentResolver cr , int level){
        Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS,level);
    }
}
