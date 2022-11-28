package edu.metrostate.cardealer.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.functionality.VehicleJSONParser;

public class DealerInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_info);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        TextView dealerID = findViewById(R.id.dealerID);
        dealerID.setText(getString(R.string.Dealer_ID) + app.getWorkingDealer().getDealerID());

        TextView dealerName = findViewById(R.id.dealerName);
        dealerName.setText(getString(R.string.Dealer_Name) + app.getWorkingDealer().getName());

        TextView dealerRenting = findViewById(R.id.dealerRenting);
        dealerRenting.setText(getString(R.string.Dealer_Renting) + app.getWorkingDealer().isRenting());

        TextView dealerAcquisition = findViewById(R.id.dealerAcquisition);
        dealerAcquisition.setText(getString(R.string.Dealer_VehicleAcquisition) + app.getWorkingDealer().isVehicleAcquisition());

        TextView dealerExport = findViewById(R.id.dealerExport);
        dealerExport.setText(getString(R.string.Dealer_Export));

        //transferring inventory
        EditText transferInventory = findViewById(R.id.recipient);
        Button transfer = findViewById(R.id.transferTo);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getGroupOfDealers().transferInventory(app.getWorkingDealer().getDealerID(), transferInventory.getText().toString());
            }
        });


        //exporting inventory information to JSON
        Button exportJSON = findViewById(R.id.JSON);
        exportJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VehicleJSONParser.write(app.getWorkingDealer());
                //this is sending the application back to the DealerListActivity for some reason
            }
        });

        //changing dealership name
        EditText newName = findViewById(R.id.newName);
        Button changeName = findViewById(R.id.changeName);
        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getWorkingDealer().setName(newName.getText().toString());
                dealerName.setText(getString(R.string.Dealer_Name) + app.getWorkingDealer().getName());
            }
        });

        //changing ability to rent
        Button enableRenting = findViewById(R.id.enableRent);
        enableRenting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getWorkingDealer().setRenting(true);
                dealerRenting.setText(getString(R.string.Dealer_Renting) + app.getWorkingDealer().isRenting());
            }
        });

        Button disableRenting = findViewById(R.id.disableRent);
        disableRenting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getWorkingDealer().setRenting(false);
                dealerRenting.setText(getString(R.string.Dealer_Renting) + app.getWorkingDealer().isRenting());
            }
        });

        //changing ability to acquire vehicles
        Button enableAcquisition = findViewById(R.id.enableVehicleAcquisition);
        enableAcquisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getWorkingDealer().setVehicleAcquisition(true);
                dealerAcquisition.setText(getString(R.string.Dealer_VehicleAcquisition) + app.getWorkingDealer().isVehicleAcquisition());
            }
        });

        Button disableAcquisition = findViewById(R.id.disableVehicleAcquisition);
        disableAcquisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getWorkingDealer().setVehicleAcquisition(false);
                dealerAcquisition.setText(getString(R.string.Dealer_VehicleAcquisition) + app.getWorkingDealer().isVehicleAcquisition());
            }
        });

        //Navigating to the next screen
        Button nextScreen = findViewById(R.id.viewInventory);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(DealerInfoActivity.this, **Name of Mike's activity.class**);
                //startActivity(intent);
            }
        });


    }
}