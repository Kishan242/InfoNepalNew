package com.drac_android.infonepal.Processing.Request;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.drac_android.infonepal.Utilities.Utils;

import org.json.JSONObject;

import java.util.Map;

public class UrlRequest extends JsonObjectRequest {



    private static final String server="url here";

    public static final String ACTIVATION_ENDPOINT = "/Activation";

    private static int MY_SOCKET_TIMEOUT_MS = 10000; //10 seconds timeout
    Context mContext;


    public UrlRequest(int method, String endpoint, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, server+endpoint, jsonRequest, listener, errorListener);
        this.mContext = mContext;
        setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> mHeaders = new ArrayMap<>();
        mHeaders.put("EXOTRAC-DEVICEID", "HEADER");
//        UserSession session = UserSession.getSession(mContext);

        return mHeaders;
    }


}
