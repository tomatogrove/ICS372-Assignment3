package edu.metrostate.cardealer.functionality;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Vehicle;

public class Vehicle_ViewList extends AppCompatActivity {
    ListView vehicle_list;
    String[] arr = {"Null", "Data"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_view_list);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Vehicle> vehiclelist = bundle.getParcelableArrayList("vehiclelist_data");
        if(vehiclelist.size()==0){
            vehicle_list = findViewById(R.id.vehicle_list);
            ArrayAdapter<String> vehicleArray;
            vehicleArray
                    = new ArrayAdapter<String>(
                    this,
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    arr
            );
            vehicle_list.setAdapter(vehicleArray);
        }else{
        vehicle_list = findViewById(R.id.vehicle_list);
        ArrayAdapter<Vehicle> vehicleArray;
        vehicleArray
                = new ArrayAdapter<Vehicle>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
               vehiclelist);
        vehicle_list.setAdapter(vehicleArray);
        }
    }
    }
