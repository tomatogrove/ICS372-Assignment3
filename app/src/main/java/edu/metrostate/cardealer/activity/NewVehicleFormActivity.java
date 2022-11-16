package edu.metrostate.cardealer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.storage.StateManager;

public class NewVehicleFormActivity extends AppCompatActivity {

    private CarDealerApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle_form);

        app = (CarDealerApplication) getApplication();
    }

    @Override
    protected void onPause() {
        super.onPause();

        StateManager.save(app.getExternalFilesDir(null));
    }

    @Override
    protected void onResume() {
        super.onResume();

        StateManager.load(app.getExternalFilesDir(null));
    }
}
