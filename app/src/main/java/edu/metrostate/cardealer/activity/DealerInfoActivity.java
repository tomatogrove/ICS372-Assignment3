package edu.metrostate.cardealer.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.parsing.VehicleJSONParser;
import edu.metrostate.cardealer.storage.StateManager;

public class DealerInfoActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();

        StateManager.save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_info);
        //Getting the DealerID of the dealership object selected from DealerListActivity.java
        Bundle selectedDealer = getIntent().getExtras();
        Dealership workingDealer = StateManager.dealerGroup.getDealerByID(selectedDealer.getString("workingDealerID"));

        //TextViews at the top to show details about the selected Dealership
        TextView dealerID = findViewById(R.id.dealerID);
        dealerID.setText(getString(R.string.Dealer_ID, workingDealer.getDealerID()));
        TextView dealerName = findViewById(R.id.dealerName);
        dealerName.setText(getString(R.string.Dealer_Name, workingDealer.getName()));

        TextView dealerRenting = findViewById(R.id.dealerRenting);
        dealerRenting.setText(getString(R.string.Dealer_Renting, workingDealer.isRenting()));

        TextView dealerAcquisition = findViewById(R.id.dealerAcquisition);
        dealerAcquisition.setText(getString(R.string.Dealer_VehicleAcquisition, workingDealer.isVehicleAcquisition()));

        TextView dealerExport = findViewById(R.id.dealerExport);
        dealerExport.setText(getString(R.string.Dealer_Export));

        //Button and EditTexts that change a Dealership's details
        //transferring inventory
        EditText transferInventory = findViewById(R.id.recipient);
        Button transfer = findViewById(R.id.transferTo);
        transfer.setOnClickListener(v -> {
            Dealership dealer2 = StateManager.dealerGroup.getDealerByID(transferInventory.getText().toString().trim());
            if (dealer2 != null && !workingDealer.getDealerID().equals(dealer2.getDealerID())
                && dealer2.isVehicleAcquisition()) {
                List<Vehicle> vehicles = StateManager.dealerGroup.transferInventory(workingDealer.getDealerID(), dealer2.getDealerID());
                showTransferSuccessDialog(vehicles);
            } else {
                showTransferFailureDialog();
            }
        });


        //exporting inventory information to JSON
        Button exportJSON = findViewById(R.id.JSON);
        exportJSON.setOnClickListener(v -> {
            VehicleJSONParser.write(workingDealer);
            showExportDialog();
        });

        //changing dealership name
        EditText newName = findViewById(R.id.newName);
        Button changeName = findViewById(R.id.changeName);
        changeName.setOnClickListener(v -> {

            workingDealer.setName(newName.getText().toString().trim());
            dealerName.setText(getString(R.string.Dealer_Name, workingDealer.getName()));
        });

        //changing ability to rent
        Button enableRenting = findViewById(R.id.enableRent);
        enableRenting.setOnClickListener(v -> {
            workingDealer.setRenting(true);
            dealerRenting.setText(getString(R.string.Dealer_Renting, workingDealer.isRenting()));
        });

        Button disableRenting = findViewById(R.id.disableRent);
        disableRenting.setOnClickListener(v -> {
            workingDealer.setRenting(false);
            dealerRenting.setText(getString(R.string.Dealer_Renting, workingDealer.isRenting()));
        });

        //changing ability to acquire vehicles
        Button enableAcquisition = findViewById(R.id.enableVehicleAcquisition);
        enableAcquisition.setOnClickListener(v -> {
            workingDealer.setVehicleAcquisition(true);
            dealerAcquisition.setText(getString(R.string.Dealer_VehicleAcquisition, workingDealer.isVehicleAcquisition()));
        });

        Button disableAcquisition = findViewById(R.id.disableVehicleAcquisition);
        disableAcquisition.setOnClickListener(v -> {
            workingDealer.setVehicleAcquisition(false);
            dealerAcquisition.setText(getString(R.string.Dealer_VehicleAcquisition, workingDealer.isVehicleAcquisition()));
        });

        //Navigating to the next screen
        Button nextScreen = findViewById(R.id.viewInventory);
        nextScreen.setOnClickListener(v -> {
            Intent intent = new Intent(DealerInfoActivity.this, EditSpecificVehicleActivity.class);
            intent.putExtra("workingDealerID", workingDealer.getDealerID());
            startActivity(intent);
        });
    }

    private void showTransferFailureDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Transfer Failed")
                .setMessage("Could not transfer to chosen dealer. Please ensure dealer"
                        + " exists, is accepting vehicles, and is not the same as the current dealer.")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    private void showTransferSuccessDialog(List<Vehicle> vehicles) {
        StringBuilder builder = new StringBuilder();
        builder.append("Non-rented vehicles transferred!");
        if (vehicles.size() > 0) {
            builder.append("\n\nThe following vehicles are currently rented and cannot be transferred:");
            for (Vehicle vehicle : vehicles) {
                builder.append("\nVehicle ").append(vehicle.getVehicleID());
            }
        }
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Transfer Succeeded!")
                .setCancelable(false)
                .setMessage(builder)
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    public void showExportDialog() {

        Dialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("JSON export")
                .setMessage("Exported JSON file to downloads!")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

}