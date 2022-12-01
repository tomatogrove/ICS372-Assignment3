package edu.metrostate.cardealer.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class Vehicle_ViewListActivity extends AppCompatActivity {
    ListView listview;
    ArrayAdapter arrayAdapter;
    List<Vehicle> vehicleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_view_list);

        vehicleList = StateManager.dealerGroup.getAllVehicles();

        listview = findViewById(R.id.listview_list);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                vehicleList);
        listview.setAdapter(arrayAdapter);

    }
    }
