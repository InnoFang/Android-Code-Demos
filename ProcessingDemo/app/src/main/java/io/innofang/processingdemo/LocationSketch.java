package io.innofang.processingdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Author: Inno Fang
 * Time: 2017/7/30 14:30
 * Description:
 */


public class LocationSketch extends Sketch {

    Context context;
    LocationManager locationManager;
    MyLocationListener locationListener;

    boolean hasLocation = false;
    private float currentLatitude;
    private float currentLongitude;
    private float currentAccuracy;
    private String currentProvider;

    public LocationSketch(Context context) {
        super(context);
        this.context = context;
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        orientation(PORTRAIT);
        textFont(createFont("SansSerif", 26 * displayDensity));
        textAlign(CENTER, CENTER);
        requestPermission("android.permission.ACCESS_FINE_LOCATION", "initLocation");
    }

    public void draw() {
        background(0);
        if (hasPermission("android.permission.ACCESS_FINE_LOCATION")) {
            text("Latitude: " + currentLatitude + "\n" +
                    "Longitude: " + currentLongitude + "\n" +
                    "Accuracy: " + currentAccuracy + "\n" +
                    "Provider: " + currentProvider, 0, 0, width, height);
        } else {
            text("No permissions to access location", 0, 0, width, height);
        }
    }

    public void initLocation(boolean granted) {
        if (granted) {
            locationListener = new MyLocationListener();
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            hasLocation = true;
        } else {
            hasLocation = false;
        }
    }

    class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            currentLatitude = (float) location.getLatitude();
            currentLongitude = (float) location.getLongitude();
            currentAccuracy = (float) location.getAccuracy();
            currentProvider = location.getProvider();
        }

        public void onProviderDisabled(String provider) {
            currentProvider = "";
        }

        public void onProviderEnabled(String provider) {
            currentProvider = provider;
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
