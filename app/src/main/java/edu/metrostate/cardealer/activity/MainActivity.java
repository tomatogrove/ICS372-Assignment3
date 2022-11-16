package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.metrostate.cardealer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_vehicle).setOnClickListener(v -> {
            // Create the intent with the new activity
            Intent intent = new Intent(MainActivity.this, VehicleListActivity.class);

            // Launch the new Activity
            startActivity(intent);
        });

        findViewById(R.id.add_vehicle).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewVehicleFormActivity.class);
            startActivity(intent);
        });

    }
}