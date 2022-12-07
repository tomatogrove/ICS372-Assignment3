package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
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

        vehicle = (Button) findViewById(R.id.vehicles);
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

    // https://stackoverflow.com/questions/8854359/exception-open-failed-eacces-permission-denied-on-android
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

        // https://stackoverflow.com/questions/65876736/how-do-you-request-manage-external-storage-permission-in-android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                showPermissionsDialog();
            }
        }
    }

    private void requestPermissions() {
        try {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            startActivity(intent);
        }
    }

    private void showPermissionsDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Requesting Permissions")
                .setCancelable(false)
                .setMessage("Storage Permissions must be enabled for this app.")
                .setPositiveButton( "OK", (dialog1, id) -> {
                        dialog1.dismiss();
                        requestPermissions();
                }).create();

        dialog.show();
    }
}
