package com.drac_android.infonepal.MenuUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.drac_android.infonepal.FormUI.AddDetail;
import com.drac_android.infonepal.R;
import com.drac_android.infonepal.Utilities.Utils;

public class MenuUI extends AppCompatActivity {

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menuui);

        ImageView addDetailIV = (ImageView) findViewById(R.id.add_detail);
        addDetailIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DeviceID", Utils.getDeviceId(mContext));

                Intent i = new Intent(MenuUI.this,AddDetail.class);
                startActivity(i);
                finish();
            }
        });

        ImageView viewDetailIV = (ImageView) findViewById(R.id.view_detail);
        viewDetailIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuUI.this,AddDetail.class);
              Log.d("DeviceID", Utils.getDeviceId(mContext));
                startActivity(i);
                finish();
            }
        });


    }
}
