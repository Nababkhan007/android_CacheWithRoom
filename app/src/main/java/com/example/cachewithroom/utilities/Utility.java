package com.example.cachewithroom.utilities;

import android.app.Activity;
import android.net.ConnectivityManager;

import java.util.Objects;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Utility {
    public static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
        return Objects.requireNonNull(cm).getActiveNetworkInfo() != null;
    }
}
