package com.drac_android.infonepal;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.drac_android.infonepal.MenuUI.MenuUI;
import com.drac_android.infonepal.Processing.Request.UrlRequest;
import com.drac_android.infonepal.ServerResponse.ActivationResponse;
import com.drac_android.infonepal.ServerResponse.ErrorCodes;
import com.drac_android.infonepal.Services.VolleyLibrary;
import com.drac_android.infonepal.Utilities.Components.AlertUsers;

import org.json.JSONObject;

import java.util.HashMap;

public class ActivationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);

        ((Button) findViewById(R.id.button_activation)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for the Activation
                validateform();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please contact  9864474777 for the Activation Key", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    EditText activationKeyEt;
    boolean viewLoaded= false;
    StringBuilder errorBuilder = new StringBuilder();


    private void loadFormViews(){
        this.activationKeyEt = (EditText) findViewById(R.id.activationkey);
        viewLoaded = true;
    }
    private void validateform() {

        this.activationKeyEt = (EditText) findViewById(R.id.activationkey);



         boolean valid = true;
//        if (!viewLoaded)
//
//        loadFormViews();
        String activationKey = activationKeyEt.getText().toString();
        if(activationKey.isEmpty()){
            activationKeyEt.setHint("Invalid activation key!");
            errorBuilder.append("Invalid activation key!. \n");
          valid = false;
        }

        if (valid){
            HashMap<String,String> credentials = new HashMap<>();
            credentials.put("Activation Key",activationKey);
            submitForm(credentials);

        }else{
            AlertUsers.showError(ActivationActivity.this,"Following errors occured: \n"+errorBuilder.toString(),"Retry",null);

        }



    }

    private void submitForm(HashMap<String, String> credentials) {

        ////Submit Activation Key


        final ProgressDialog dialog = ProgressDialog.show(this, "", "Logging in");
        JSONObject cred = new JSONObject(credentials);

        final UrlRequest request = new UrlRequest(Request.Method.POST,
                UrlRequest.ACTIVATION_ENDPOINT,
                cred,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // We got the login Response.
                        Log.d("LoginResponse",response.toString());
                        dialog.dismiss();
                        ActivationResponse lResponse = ActivationResponse.parseActivation(response.toString());
                        processActivation(lResponse, response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                displayVolleyError(error);
            }
        });



        request.setTag(this);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });
        VolleyLibrary.getInstance(this).addToRequestQueue(request);
    }



    /* Volley Error Processing*/
    private void displayVolleyError(VolleyError error){
        error.printStackTrace();
        //TODO: handle failure
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            AlertUsers.showError(this,"Connection Error: There was an error communicating with our servers. \n \nDetails: "+error.getMessage(),"Okay",null);
        } else if (error instanceof AuthFailureError) {
            //TODO
            AlertUsers.showError(this,"Authentication Error: There was an authentication failure. \n \nDetails: "+error.getMessage(),"Okay",null);
        } else if (error instanceof ServerError) {
            //TODO
            String data= new String(error.networkResponse.data);
            AlertUsers.showError(this,"Server Error:There was an internal server error. \n \nDetails: "+error.getMessage()+" \n Server Response: "+data,"Okay",null);
        } else if (error instanceof NetworkError) {
            //TODO
            AlertUsers.showError(this,"Network Error: There was an error communicating with our servers. \n \nDetails: "+error.getMessage(),"Okay",null);
        } else if (error instanceof ParseError) {
            //TODO
            String data= new String(error.networkResponse.data);
            AlertUsers.showError(this,"Parsing Error: Invalid response from our servers. \n \nDetails: "+error.getMessage()+" \n Server Response: "+data,"Okay",null);
        }
    }


    private void processActivation(ActivationResponse lResponse, String response){
        if(lResponse!=null){ //1. Check if activation response object is null.. meaning invalid response.
            if(lResponse.success){//2. Check if the login was success.

                Intent i = new Intent(ActivationActivity.this,MenuUI.class);
                startActivity(i);
                finish();

            }
        }else{
            AlertUsers.showError(ActivationActivity.this,"Parsing Error: Invalid response from our servers. \n Server Response: "+response,"Okay",null);
        }

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
