package com.arce.angel.request.Request;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.arce.angel.request.Listener.ResponseListener;
import com.arce.angel.request.Util.InternetConexion;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Angel on 10/04/2016.
 */
public class WebRequest {

    public static void sendAsynchronousRequestGET(String url, Context context, View view, final ResponseListener responseListener) {

        if (!InternetConexion.InternetAvailable(context, view)) {
            responseListener.onError("Not internet conexion");
            return;
        }

        try {
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    responseListener.onResponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    responseListener.onError("Error");
                    Log.d("Volley", error.getMessage());
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy( // total timeout 20 segundos
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, //Timeout - Specifies Socket Timeout in millis per every retry attempt.
                    1, //Number Of Retries - Number of times retry is attempted.
                    3)); // Back Off Multiplier - A multiplier which is used to determine exponential time set to socket for every retry attempt.

            VolleySingleton.getInstance(context).addToRequestQueue(request);

        } catch (Exception bbb) {
            bbb.printStackTrace();
        }
    }

    public static void sendAsynchronousRequestPOST(String url, JSONObject params, Context context, View view, final ResponseListener responseListener) {

        if (!InternetConexion.InternetAvailable(context, view)) {
            responseListener.onError("Not internet conexion");
            return;
        }

        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    responseListener.onResponse(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    responseListener.onError("Error");
                    Log.d("Volley", error.getMessage());
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy( // total timeout 20 segundos
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, //Timeout - Specifies Socket Timeout in millis per every retry attempt.
                    1, //Number Of Retries - Number of times retry is attempted.
                    3)); // Back Off Multiplier - A multiplier which is used to determine exponential time set to socket for every retry attempt.

            VolleySingleton.getInstance(context).addToRequestQueue(request);

        } catch (Exception bbb) {
            bbb.printStackTrace();
        }
    }

    /*public static final String SOAP_ACTION = "http://opi.fuller.com.mx/Test/GetUsuariosActivos";
    public static final String METHOD_NAME = "GetUsuariosActivos";
    public static final String NAMESPACE = "http://opi.fuller.com.mx/Test/";
    public static final String URL = "http://104.239.175.163:8083/test/test.asmx";*/

    /*private class MakeRequest extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            InternetConexion.InternetAvailable(getApplicationContext(), rootView);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            SoapObject request = new SoapObject(ConstantsUtil.NAMESPACE, ConstantsUtil.METHOD_NAME);
            request.addProperty("zona", 7281);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(ConstantsUtil.URL);

            try {
                transporte.call(ConstantsUtil.SOAP_ACTION, envelope);

                SoapObject resSoap = (SoapObject) envelope.getResponse();

                totalPositions = resSoap.getPropertyCount();

                dataSet = new String[totalPositions][3];
                dataSetTemp = new String[totalPositions][3];

                for (int i = 0; i < totalPositions; i++) {
                    SoapObject modelo = (SoapObject) resSoap.getProperty(i);

                    dataSet[i][0] = modelo.getProperty(0).toString();
                    dataSet[i][1] = modelo.getProperty(1).toString();
                    dataSet[i][2] = modelo.getProperty(2).toString();

                    dataSetTemp[i][0] = modelo.getProperty(0).toString();
                    dataSetTemp[i][1] = modelo.getProperty(1).toString();
                    dataSetTemp[i][2] = modelo.getProperty(2).toString();
                }

            } catch (Exception bbb) {
                resul = false;
            }

            return resul;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if (aBoolean) {
                // Request Success

                tableView.setDataAdapter(new SimpleTableDataAdapter(getApplicationContext(), dataSet));
            } else {
                // Request Failed
            }

            progressDialog.dismiss();
        }
    }*/
}
