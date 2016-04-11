package com.arce.angel.request.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Angel on 07/04/2016.
 */
public class InternetConexion {

    public static boolean InternetAvailable(Context context, View view) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Snackbar.make(view, "Lo sentimos, no se encontró conexión a Internet.", Snackbar.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
