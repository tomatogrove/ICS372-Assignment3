package edu.metrostate.cardealer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;


import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;

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
    }
}