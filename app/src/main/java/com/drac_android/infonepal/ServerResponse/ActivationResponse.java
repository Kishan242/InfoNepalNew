package com.drac_android.infonepal.ServerResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class ActivationResponse {

    @SerializedName("Success")
    public boolean success;

    @SerializedName("ErrorCode")
    public int errorCode;

    @SerializedName("ErrorMessage")
    public String error;


    @SerializedName("Data")
    public String activationcode;

    public static ActivationResponse parseActivation(String loginJson){
        Gson gson = new GsonBuilder().create();

        ActivationResponse response = gson.fromJson(loginJson, ActivationResponse.class);
        return response;

    }






}

