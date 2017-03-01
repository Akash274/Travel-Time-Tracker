package com.Traveline.akash.traveltimetracker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity {


    LatLng LOCATION_DESTINATION;
    LatLng LOCATION_SOURCE;
    LatLng MY_LOCATION;
    Location myLocation;


    private GoogleMap map;
    double lat;
    double lng;
    String loc;
    String Loc1;
    String pActivity;
    double Lat1;
    double Lng1;
    final ArrayList<LatLng> Bus_stoplist= CSVAdapter.Bus_stoplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        Intent activityMaps = getIntent();
        Loc1 = activityMaps.getExtras().getString("Loc1");
        Lat1 = activityMaps.getExtras().getDouble("Lat1");
        Lng1 = activityMaps.getExtras().getDouble("Lng1");
        loc = activityMaps.getExtras().getString("Loc");
        lat = activityMaps.getExtras().getDouble("Lat");
        lng = activityMaps.getExtras().getDouble("Lng");
        pActivity = activityMaps.getExtras().getString("callingActivity");
        LOCATION_SOURCE = new LatLng(Lat1,Lng1);
        LOCATION_DESTINATION= new LatLng(lat, lng);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!= ConnectionResult.SUCCESS){

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else {
            Maps(loc);
        }
    }


    public void Maps (String loc) {
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.getUiSettings().setCompassEnabled(true);
        map.setTrafficEnabled(true);
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.addMarker(new MarkerOptions().position(LOCATION_DESTINATION).title(loc));

        if(Lat1 ==0) {}
        else{
            map.addMarker(new MarkerOptions().position(LOCATION_SOURCE).title(Loc1));
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(LOCATION_DESTINATION)
                .zoom(17)
                .bearing(90)
                .tilt(30)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(
                cameraPosition));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_gotolocation:
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(LOCATION_DESTINATION)
                        .zoom(17)
                        .bearing(90)
                        .tilt(30)
                        .build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(
                        cameraPosition));
                break;

             case R.id.menu_showcurrentlocation:

                 myLoc();
                 CameraPosition myPosition = new CameraPosition.Builder()
                        .target(MY_LOCATION).zoom(17).bearing(90).tilt(30).build();
                map.animateCamera(
                        CameraUpdateFactory.newCameraPosition(myPosition));
                 map.addMarker(new MarkerOptions()
                         .position(MY_LOCATION)
                         .title("You are here!")
                         .snippet("Your Location")
                         .icon(BitmapDescriptorFactory.defaultMarker(
                                 BitmapDescriptorFactory.HUE_AZURE)));
                break;

            case R.id.menu_Route:

                for (int i = 0; i < Bus_stoplist.size()-1; i++) {
                     map.addPolyline(new PolylineOptions()
                            .add(Bus_stoplist.get(i),Bus_stoplist.get(i+1))
                            .width(2).color(Color.BLUE).geodesic(true));
                }

                break;

            case R.id.menu_show_navigation:
                    String format = "google.navigation:q=" + lat + "," + lng;
                    Uri uri = Uri.parse(format);
                    Uri gmmIntentUri = Uri.parse(format);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);



        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            finish();
            this.startActivity(new Intent(MapsActivity.this,SourceActivity.class).putExtra("callingActivity", pActivity));
        }
        return true;
    }

    public void myLoc ()
    {
        myLocation = map.getMyLocation();
        MY_LOCATION = new LatLng(myLocation.getLatitude(),
                myLocation.getLongitude());
    }

}