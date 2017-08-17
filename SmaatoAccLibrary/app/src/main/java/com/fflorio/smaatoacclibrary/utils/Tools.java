package com.fflorio.smaatoacclibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by francesco on 2017-08-17.
 */

public class Tools {

    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static boolean isEmpty(final String value) {
        return value == null || value.length() == 0;
    }
}