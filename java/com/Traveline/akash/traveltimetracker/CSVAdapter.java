package com.Traveline.akash.traveltimetracker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVAdapter extends ArrayAdapter<Location> {


    LatLng a;
    final static ArrayList<LatLng> Bus_stoplist = new ArrayList<>();
    Context ctx;
    public CSVAdapter(Context context, int resource, String Bus_No) {
        super(context, resource);
        this.ctx = context;
        loadArrayFile(Bus_No);
    }

    private void loadArrayFile(String BusNo) {
        InputStream is = null;
        try{

            is = ctx.getAssets().open(BusNo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String Line;

            while((Line = reader.readLine())!= null ){
                String[] RowData = Line.split(",");
                Location Loc = new Location();
                Loc.set_id(Integer.parseInt(RowData[0]));
                Loc.setLat(Double.parseDouble(RowData[1]));
                Loc.setLng(Double.parseDouble(RowData[2]));
                Loc.setName(RowData[3]);

                a = new LatLng(Double.parseDouble(RowData[1]),Double.parseDouble(RowData[2]));
                Bus_stoplist.add(a);

                this.add(Loc);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                is.close();
            } catch(IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
    }
    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){
        TextView mView =(TextView)convertView;
        if(null == mView)
        {
            mView = new TextView(parent.getContext());
        }

        mView.setText(getItem(pos).getName());
        return mView;
    }

}