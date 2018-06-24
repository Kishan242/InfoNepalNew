package com.drac_android.infonepal.FormUI;

        import android.content.Context;
        import android.content.Intent;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.provider.Settings;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.drac_android.infonepal.MenuUI.MenuUI;
        import com.drac_android.infonepal.R;
        import com.drac_android.infonepal.Utilities.Utils;

        import org.w3c.dom.Text;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

public class AddDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adddetail);


        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("STATUSGPS", String.valueOf(statusOfGPS));
        //  AlarmScheduler.startCrumps(this);
        if(statusOfGPS == false) {

            Toast.makeText(this,"PLease Turn on the GPS to use this APP ,Thank You!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        setListener();

    }
    public void setListener() {
       final  Context c = this;

        final RadioGroup houseinfo = (RadioGroup)findViewById(R.id.housetypeRG);
        RadioButton commercialrb = (RadioButton) findViewById(R.id.commercialRB);
        RadioButton residentialrb = (RadioButton) findViewById(R.id.residentialRB);
        final Button getlatlong = (Button) findViewById(R.id.button_get_location);
        Button submit = (Button) findViewById(R.id.submitButton);
        final TextView lat = (TextView) findViewById(R.id.lat);
       final TextView lon = (TextView) findViewById(R.id.lon);
        EditText numofshutters = (EditText) findViewById(R.id.numofshutters);
        EditText numoffloors = (EditText) findViewById(R.id.numoffloors);
        final LinearLayout commercila_layout = (LinearLayout) findViewById(R.id.commercial_layout);
        final TextView typeofhouse = (TextView) findViewById(R.id.type_of_house);


        getlatlong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getlatlong.setClickable(false);

                //    startRow.setVisibility(View.VISIBLE);


                Location curLoc = Utils.getLocation(c);

                double latitue = curLoc.getLatitude();
                double longitude = curLoc.getLongitude();

                if (curLoc!=null){
                    lat.setText(Double.toString(curLoc.getLatitude()));
                    lon.setText(Double.toString(curLoc.getLongitude()));

                    Log.d("LATITUDE", Double.toString(curLoc.getLatitude()));
                    Log.d("Longitude", Double.toString(curLoc.getLongitude()));
                }

                else{
                    Log.d("LATITUDE", "GPS Unavailable.");
                    Log.d("Longitude", "GPS Unavailable.");
                }

            }
        });

        commercialrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commercila_layout.setVisibility(View.VISIBLE);
                houseinfo.setVisibility(View.GONE);
                typeofhouse.setVisibility(View.GONE);

            }
        });

            }
}
