package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.metrostate.cardealer.R;

public class MainActivity extends AppCompatActivity {

    private Button vehicle;
    private Button dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicle = (Button) findViewById(R.id.all_vehicles);
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileImport();
            }
        });

        dealer = (Button) findViewById(R.id.specific_dealer);
        dealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDealer();
            }
        });
    }
    private void openFileImport() {
        Intent intent = new Intent(this, Vehicle_ImportFileActivity.class);
        startActivity(intent);
    }

    private void openDealer(){
        Intent intent = new Intent(this, DealerListActivity.class);
        startActivity(intent);
    }
}