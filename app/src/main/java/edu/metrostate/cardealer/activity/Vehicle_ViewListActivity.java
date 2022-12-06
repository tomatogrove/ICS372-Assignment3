package edu.metrostate.cardealer.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.adapter.VehicleAdapter;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class Vehicle_ViewListActivity extends AppCompatActivity {
    ListView listview;
    VehicleAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_view_list);

        listview = findViewById(R.id.listview_list);
        //get the list
        arrayAdapter = new VehicleAdapter(this);
        listview.setAdapter(arrayAdapter);

    }
}
