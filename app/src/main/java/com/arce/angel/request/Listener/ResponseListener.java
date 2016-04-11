package com.arce.angel.request.Listener;

import java.text.ParseException;

/**
 * Created by Angel on 10/04/2016.
 */
public interface ResponseListener {

    public void onResponse(String response);

    public void onError(String message);

}
