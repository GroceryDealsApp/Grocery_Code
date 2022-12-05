package com.example.cosc341_finalproject.maps;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.cosc341_finalproject.BuildConfig;
import com.example.cosc341_finalproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Location userCurrentLocation;
    private boolean isRouteShown;
    LatLng storeLocation;
    private String drivingMode = "driving";
    private HashMap<String,String> routeData;
    private TextView distance,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        distance = findViewById(R.id.distance);
        time = findViewById(R.id.time);
        getBundleData();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void getBundleData(){
        double lat = getIntent().getExtras().getDouble("Lat");
        double lng = getIntent().getExtras().getDouble("Lng");
        drivingMode = getIntent().getExtras().getString("mode");
        storeLocation = new LatLng(lat,lng);
    }

    private void getCurrentLocation(){
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        userCurrentLocation = location;
                        if(mMap != null && userCurrentLocation != null && !isRouteShown) {
                            isRouteShown = true;
                            showRouteToStore();
                        }
                    }
                });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);
        settings.setMyLocationButtonEnabled(true);
        settings.setScrollGesturesEnabled(true);
        settings.setZoomGesturesEnabled(true);
        settings.setTiltGesturesEnabled(true);
        settings.setRotateGesturesEnabled(true);

        if(userCurrentLocation != null && !isRouteShown) {
            isRouteShown = true;
            showRouteToStore();
        }
    }

    private void showRouteToStore(){
        LatLng origin = new LatLng(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude());

        setCurrentLocationOnMap(origin);
        setStoreLocationOnMap(storeLocation);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 16));

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin, storeLocation);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
        recenter();
    }
    private void setCurrentLocationOnMap(LatLng latLng){
        MarkerOptions options = new MarkerOptions();
        // Setting the position of the marker
        options.position(latLng);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        // Add new marker to the Google Map Android API V2
        mMap.addMarker(options);
    }

    private void setStoreLocationOnMap(LatLng latLng){
        MarkerOptions options = new MarkerOptions();
        // Setting the position of the marker
        options.position(latLng);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        // Add new marker to the Google Map Android API V2
        mMap.addMarker(options);
    }

    public void recenter() {
        LatLng origin = new LatLng(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude());
        Point nothEastPoint = mMap.getProjection().toScreenLocation(origin);
        Point souhWestPoint = mMap.getProjection().toScreenLocation(storeLocation);

        Point newNorthEast = new Point(nothEastPoint.x, nothEastPoint.y);
        Point newSouhWestPoint = new Point(souhWestPoint.x, souhWestPoint.y);

        LatLngBounds newBounds = LatLngBounds.builder()
                .include(mMap.getProjection().fromScreenLocation(newNorthEast))
                .include(mMap.getProjection().fromScreenLocation(newSouhWestPoint))
                .build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(newBounds, 0));
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }


    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                routeData = parser.getEta(jObject);
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = null;
            PolylineOptions lineOptions = null;

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.RED);
                lineOptions.geodesic(true);

            }
            if(routeData != null) {
                distance.setText("Distance : " + routeData.get("distance"));
                time.setText("ETA : " + routeData.get("duration"));
            }
            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode="+drivingMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters+"&key="+ BuildConfig.MAPS_API_KEY;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}


