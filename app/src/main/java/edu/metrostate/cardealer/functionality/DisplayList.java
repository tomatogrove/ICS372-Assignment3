package edu.metrostate.cardealer.functionality;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import edu.metrostate.cardealer.R;

public class DisplayList extends AppCompatActivity {

   ListView vehicleList;
    String list ;
   String vehicleInfo [] = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listvehicle);
        Intent intent = getIntent();

        list= intent.getStringExtra("vehicleList");
        vehicleInfo[0] = list;
        ArrayAdapter<String> vehicles;
        vehicles = new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                vehicleInfo);
        vehicleList.setAdapter(vehicles);
    }
}
