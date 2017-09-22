package com.education.capstone_stage2.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by Sara on 9/22/2017.
 */

public class LocalUtils {

    public static void SetSettingLanguage(Context context, boolean isEnglish) {
        Locale locale = null;
        if (!isEnglish) {
            locale = new Locale("ar");
            Locale.setDefault(locale);


        } else {

            locale = new Locale("en");
            Locale.setDefault(locale);

        }
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        // Resources resources=context.getResources();
        //resources.updateConfiguration(configuration, resources.getDisplayMetrics());

    }

}
