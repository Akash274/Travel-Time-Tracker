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

/**
 * Created by Aakash on 2/8/2015.
 */
public class SourceActivity extends Activity {
    CSVAdapter mAdapter;
    String Loc1;
    double Lat1;
    double Lng1;
    String pActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        Intent activityRoute = getIntent();
        pActivity = activityRoute.getExtras().getString("callingActivity");
        String Bus= pActivity+".csv";
        ListView mList = (ListView) findViewById(R.id.listView2);
        mAdapter = new CSVAdapter(this, -1, Bus);
        mList.setAdapter(mAdapter);

        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Loc1 = mAdapter.getItem(position).getName();
                Lat1 = mAdapter.getItem(position).getLat();
                Lng1 = mAdapter.getItem(position).getLng();
                Intent getMap =  new Intent(SourceActivity.this, RouteActivity.class);
                startActivity(getMap);
                getMap.putExtra("Loc1", Loc1);
                getMap.putExtra("Lat1", Lat1);
                getMap.putExtra("Lng1", Lng1);
                getMap.putExtra("callingActivity", pActivity);
                startActivity(getMap);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_skip) {
            Loc1=null;
            Lat1= 0;
            Lng1= 0;
            Intent getMap =  new Intent(SourceActivity.this, RouteActivity.class);
            startActivity(getMap);
            getMap.putExtra("Loc1", Loc1);
            getMap.putExtra("Lat1", Lat1);
            getMap.putExtra("Lng1", Lng1);
            getMap.putExtra("callingActivity", pActivity);
            startActivity(getMap);
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
            this.startActivity(new Intent(SourceActivity.this,MainActivity.class));
        }
        return true;
    }
}
