package com.Traveline.akash.traveltimetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RouteActivity extends Activity {
    CSVAdapter mAdapter;
    String pActivity;
    String Loc1;
    double Lat1;
    double Lng1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent activityRoute = getIntent();
        pActivity = activityRoute.getExtras().getString("callingActivity");
        Loc1 = activityRoute.getExtras().getString("Loc1");
        Lat1 = activityRoute.getExtras().getDouble("Lat1");
        Lng1 = activityRoute.getExtras().getDouble("Lng1");
        String Bus= pActivity+".csv";
        ListView mList = (ListView) findViewById(R.id.listView);
        mAdapter = new CSVAdapter(this, -1, Bus);
        mList.setAdapter(mAdapter);

        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String Loc = mAdapter.getItem(position).getName();
                double Lat = mAdapter.getItem(position).getLat();
                double Lng = mAdapter.getItem(position).getLng();
                Intent getMap =  new Intent(RouteActivity.this, MapsActivity.class);
                startActivity(getMap);
                getMap.putExtra("Loc1", Loc1);
                getMap.putExtra("Lat1", Lat1);
                getMap.putExtra("Lng1", Lng1);
                getMap.putExtra("Loc", Loc);
                getMap.putExtra("Lat", Lat);
                getMap.putExtra("Lng", Lng);
                getMap.putExtra("callingActivity", pActivity);
                startActivity(getMap);


            }
        });
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            finish();
            this.startActivity(new Intent(RouteActivity.this,MainActivity.class));
        }
        return true;
    }
}
