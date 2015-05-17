package com.example.aleks.athena;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import java.security.KeyStore;


public class MainActivity extends Activity implements LocationListener {

    protected LocationManager locationManager;
//    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;

//    protected LocationManager locationManager;
    protected boolean LocationBool = true;
    public Location location;
//    LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLat = (TextView) findViewById(R.id.textview1);

        if (LocationBool)
        {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0,this);
            }
            else
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 0,this);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onLocationChanged(Location location) {
//        txtLat = (TextView) findViewById(R.id.textview1);
//        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    public Location GetLocationInfo()
    {
        if (LocationBool)
        {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0,this);
                if (locationManager != null)
                {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
            }
            else
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 0,this);
                if (locationManager != null)
                {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
        return location;
        }
        return null;
    }

    public void GetLocationOnClick(View x)
    {
        GetLocation();
    }
    public void GetLocation()
    {
        Log.d("Location", "Search started!");
       // double longitude = location.getLongitude();
       // double latitude = location.getLatitude();

        GetLocationInfo();

        txtLat = (TextView) findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }
}
