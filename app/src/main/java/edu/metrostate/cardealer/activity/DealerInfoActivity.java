package edu.metrostate.cardealer.activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.Serializable;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.functionality.VehicleJSONParser;
import edu.metrostate.cardealer.inventory.Dealership;
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

        Dealership workingDealer = (Dealership)getIntent().getSerializableExtra("workingDealer");


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

        //transferring inventory
        EditText transferInventory = findViewById(R.id.recipient);
        Button transfer = findViewById(R.id.transferTo);
        transfer.setOnClickListener(v -> StateManager.dealerGroup.transferInventory(workingDealer.getDealerID(), transferInventory.getText().toString()));


        //exporting inventory information to JSON
        Button exportJSON = findViewById(R.id.JSON);
        exportJSON.setOnClickListener(v -> {
            VehicleJSONParser.write(workingDealer);
            //this is sending the application back to the DealerListActivity for some reason
        });

        //changing dealership name
        EditText newName = findViewById(R.id.newName);
        Button changeName = findViewById(R.id.changeName);
        changeName.setOnClickListener(v -> {

            workingDealer.setName(newName.getText().toString());
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
            Intent intent = new Intent(DealerInfoActivity.this, VehicleListActivity.class);
            intent.putExtra("workingDealer", (Serializable) workingDealer);
            startActivity(intent);
        });


    }
}