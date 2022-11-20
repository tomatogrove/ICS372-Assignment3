package edu.metrostate.cardealer.activity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;


import androidx.navigation.ui.AppBarConfiguration;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.databinding.ActivityDealerInfoBinding;

public class DealerInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_info);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        TextView dealerName = findViewById(R.id.dealerName);
        dealerName.setText(app.getWorkingDealer().getName());
    }
}