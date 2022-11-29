package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.metrostate.cardealer.R;

public class EditSpecificVehicle extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specific_vehicle);

        findViewById(R.id.show_edit_specific_vehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent with the new activity
                Intent intent = new Intent(EditSpecificVehicle.this, VehicleListActivity.class);

                // Launch the new Activity
                startActivity(intent);
            }
        });
    }


}