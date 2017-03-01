package com.Traveline.akash.traveltimetracker;




/**
 * Created by Aakash on 12/25/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] Bus_number={
                 "15 NRI-Nerul D.Y.Patil"
                ,"20 Ghansoli Railway Station-Sector46-48"
                ,"27 NRI-Thane Station"
                ,"30 Kalamboli Roadpali-Uran Pensioner park"
                ,"31 Koparkhairane Bus Stand-Uran Pensioner park"
                ,"39 Ghansoli-Uran"
                ,"50 Koparkhairane Bus Stand-Panvel Railway Station"
                ,"51 Koparkhairane Bus Stand-Kalamboli Bus Stand"
                ,"31 Juinagar Railway Station-TOWNSHIP"
                ,"108 Nerul(sec46-48)-Mantralaya"
                ,"31 Juinagar Railway Station-TOWNSHIP"
        };
        ListAdapter theadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                Bus_number);
        ListView bus_number = (ListView) findViewById(R.id.listView);
        bus_number.setAdapter(theadapter);
        bus_number.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String BusnumberPicked = String.valueOf(adapterView.getItemAtPosition(position));
                Intent getRoute =  new Intent(MainActivity.this,SourceActivity.class);
                startActivity(getRoute);
                getRoute.putExtra("callingActivity", BusnumberPicked);
                startActivity(getRoute);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        }

}
