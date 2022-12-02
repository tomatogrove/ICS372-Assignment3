package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.adapter.VehicleAdapter;
import edu.metrostate.cardealer.storage.StateManager;

public class VehicleListActivity extends AppCompatActivity {

    CarDealerApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        // Get the application instance from the activity
        app = (CarDealerApplication) getApplication();

        // Create an adapter for the list view
        VehicleAdapter adapter = new VehicleAdapter(this);

        // Find the list view and add the adapter
        ListView vehicleList = findViewById(R.id.vehicle_list);
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener((parent, view, position, id) -> showDialog(adapter.getItem(position)));

    }

//    // these two are not really necessary for the vehicle list activity since this activity is not changing state....
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        StateManager.save(app.getStateFile());
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        StateManager.load(app.getStateFile());
//    }


    public void showDialog(Vehicle vehicle) {

        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("My alert")
                .setCancelable(false)
                .setTitle("Vehicle ID: " + vehicle.getVehicleID())
                .setMessage("Model: " + vehicle.getVehicleModel())
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();

    }
}