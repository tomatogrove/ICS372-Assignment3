package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.adapter.VehicleAdapter;
import edu.metrostate.cardealer.storage.StateManager;


public class EditSpecificVehicleActivity extends AppCompatActivity {


    VehicleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specific_vehicle);

        String dealerID = getIntent().getStringExtra("workingDealerID");
        Dealership dealer = StateManager.dealerGroup.getDealerByID(dealerID);


        // Create an adapter for the list view
        adapter = new VehicleAdapter(this, dealer.getVehicleInventory());

        // Find the list view and add the adapter
        ListView vehicleList = ((ListView)findViewById(R.id.listview_list));
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener((parent, view, position, id) -> {
            if (dealer.isRenting()) {
                showDialog(adapter.getItem(position));
                adapter = new VehicleAdapter(this, dealer.getVehicleInventory());
            } else {
                showRentingNotEnabledDialog();
            }
        });
    }

    private void showRentingNotEnabledDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit Rental Status")
                .setCancelable(false)
                .setMessage("Dealer does not have renting enabled. Please enable renting before changing vehicle rental status")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    public void showDialog(Vehicle vehicle) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Rental Status");
        builder.setCancelable(true);
        builder.setTitle("Vehicle ID: " + vehicle.getVehicleID());
        builder.setMessage("Rental Status: " + vehicle.isRented());
        builder.setMessage("Press 'Update' to change the rental status");
        builder.setMessage("Sports Cars cannot be rented");
        builder.setNegativeButton("Cancel",(dialog1, id) -> dialog1.cancel());
        builder.setPositiveButton( "Update", (dialog1, id) -> editRentalStatus(vehicle));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void editRentalStatus(Vehicle vehicle) {
        if (!vehicle.getVehicleType().equals("sports car")) {
            if(vehicle.isRented()){
                vehicle.setRented(false);
            } else {
                vehicle.setRented(true);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        StateManager.save();
    }
}