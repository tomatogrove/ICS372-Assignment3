package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.adapter.VehicleAdapter;


public class EditSpecificVehicleActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specific_vehicle);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        // Create an adapter for the list view
        VehicleAdapter adapter = new VehicleAdapter(this, app.getVehicleList());

        // Find the list view and add the adapter
        ListView vehicleList = ((ListView)findViewById(R.id.vehicle_list));
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(adapter.getItem(position));
            }
        });
    }

    public void showDialog(Vehicle vehicle) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Rental Status");
        builder.setCancelable(true);
        builder.setTitle("Vehicle ID: " + vehicle.getVehicleID());
        builder.setMessage("Rental Status: " + vehicle.isRented());
        builder.setMessage("Press 'Update' to change the rental status");
        builder.setNegativeButton("Cancel",(dialog1, id) -> dialog1.cancel());
        builder.setPositiveButton( "Update", (dialog1, id) -> editRentalStatus(vehicle));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void editRentalStatus(Vehicle vehicle){
        if(vehicle.isRented()){
            vehicle.setRented(false);
        } else {
            vehicle.setRented(true);
        }
    }
}