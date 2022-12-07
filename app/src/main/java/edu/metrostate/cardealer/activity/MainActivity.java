package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.metrostate.cardealer.R;

public class MainActivity extends AppCompatActivity {

    private Button vehicle;
    private Button dealer;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicle = (Button) findViewById(R.id.all_vehicles);
        vehicle.setOnClickListener(v -> openFileImport());

        dealer = (Button) findViewById(R.id.dealerships);
        dealer.setOnClickListener(v -> openDealer());

        getFilePermissions();
    }

    private void openFileImport() {
        Intent intent = new Intent(MainActivity.this, Vehicle_ImportFileActivity.class);
        startActivity(intent);
    }

    private void openDealer(){
        Intent intent = new Intent(MainActivity.this, DealerListActivity.class);
        startActivity(intent);
    }

    private void getFilePermissions() {
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
