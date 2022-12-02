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
        // these will change at least a little based on what is on the main screen
        findViewById(R.id.show_vehicle).setOnClickListener(v -> {
            // Create the intent with the new activity
            Intent intent = new Intent(MainActivity.this, Vehicle_ImportFileActivity.class);

            // Launch the new Activity
            startActivity(intent);
        });

//        findViewById(R.id.dealers).setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, DealerListActivity.class);
//            startActivity(intent);
//        });

    }
}