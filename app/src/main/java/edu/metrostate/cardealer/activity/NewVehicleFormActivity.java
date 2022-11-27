package edu.metrostate.cardealer.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.activity.util.TextChangedListener;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class NewVehicleFormActivity extends AppCompatActivity{

    private CarDealerApplication app;
    private Vehicle newVehicle;

    private EditText vehicleID;
    private EditText dealershipID;
//    private EditText vehicleType;
    private Spinner vehicleTypeSpinner;
    private EditText vehicleModel;
    private EditText vehicleManufacturer;
    private EditText unit;
    private EditText price;
    private EditText acquisitionDate;
    private Date acquisitionDateValue;
    private Button setDate;
    private CheckBox rented;
    private Button save;
    private Button reset;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle_form);

        app = (CarDealerApplication) getApplication();
        resetVehicle();

        vehicleID = findViewById(R.id.vehicle_id);
        dealershipID = findViewById(R.id.dealer_id);
//        vehicleType = findViewById(R.id.vehicle_type);
        vehicleModel = findViewById(R.id.vehicle_model);
        vehicleManufacturer = findViewById(R.id.vehicle_manufacturer);
        unit = findViewById(R.id.vehicle_unit);
        price = findViewById(R.id.vehicle_price);
//        acquisitionDate = findViewById(R.id.vehicle_acquisition);
        rented = findViewById(R.id.vehicle_rented);
        rented.setEnabled(false);
        vehicleTypeSpinner = findViewById(R.id.vehicle_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(adapter);
        save = findViewById(R.id.save_button);
        reset = findViewById(R.id.reset_button);
        setDate = findViewById(R.id.set_date);
        LocalDate today = LocalDate.now();
        setDate.setText(String.format("%d/%d/%d", today.getMonthValue(), today.getDayOfMonth(), today.getYear()));

        datePickerDialog = new DatePickerDialog(this);


        // pattern from https://stackoverflow.com/questions/11134144/android-edittext-onchange-listener
        vehicleID.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                newVehicle.setVehicleID(vehicleID.getText().toString());
            }
        });

        dealershipID.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                newVehicle.setDealershipID(dealershipID.getText().toString());
            }
        });

        // from https://stackoverflow.com/questions/13097784/basic-spinner-example
        vehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String vehicleTypeValue = adapterView.getItemAtPosition(pos).toString();
                if (!vehicleTypeValue.equals("sports car")) {
                    rented.setEnabled(true);
                } else {
                    rented.setEnabled(false);
                    rented.setChecked(false);
                    newVehicle.setRented(false);
                    showRentingDisabledDialog();
                }
                newVehicle.setVehicleType(vehicleTypeValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        vehicleModel.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                newVehicle.setVehicleModel(vehicleModel.getText().toString());
            }
        });

        vehicleManufacturer.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                newVehicle.setVehicleManufacturer(vehicleManufacturer.getText().toString());
            }
        });

        unit.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                newVehicle.setUnit(unit.getText().toString());
            }
        });

        price.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                newVehicle.setPrice(Double.valueOf(price.getText().toString()));
            }
        });



        rented.setOnCheckedChangeListener((buttonView, isChecked) -> newVehicle.setRented(isChecked));

        save.setOnClickListener( v -> {
            if (validateVehicle()) {
                List<Vehicle> vehicle = new ArrayList<>();
                vehicle.add(newVehicle);
                StateManager.dealerGroup.addIncomingVehicles(vehicle);
                showSuccessDialog();
                resetVehicle();
            } else {
                showErrorDialog();
            }
        });

        reset.setOnClickListener(v -> resetVehicle());

        setDate.setOnClickListener(v -> {
            datePickerDialog.show();
        });

        // https://stackoverflow.com/questions/39916178/how-to-show-datepickerdialog-on-button-click
        datePickerDialog.setOnDateSetListener((datePicker, year, month, day) -> {
            setDate.setText(String.format("%d/%d/%d", month, day, year));
            LocalDate localDate = LocalDate.of(year, month, day);
            //erroring out line below
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            newVehicle.setAcquisitionDate(date);
        });

    }

    private void showRentingDisabledDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Renting Not Allowed")
                .setCancelable(false)
                .setMessage("Cannot rent out a vehicle that is a sports car.")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    private void showSuccessDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Save Success")
                .setCancelable(false)
                .setMessage("Vehicle " + newVehicle.getVehicleID() + " successfully saved.")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    private void showErrorDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Save Unsuccessful")
                .setCancelable(false)
                .setMessage("Vehicle failed to save.\nInvalid fields.")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    private boolean validateVehicle() {
        return newVehicle.getVehicleID() != null || newVehicle.getDealershipID() != null
                || StateManager.dealerGroup.getDealerByID(newVehicle.getDealershipID()) == null ||
                !StateManager.dealerGroup.getDealerByID(newVehicle.getDealershipID()).isVehicleAcquisition();
    }

    private void resetVehicle() {
        newVehicle = new Vehicle();
        newVehicle.setRented(false);
        newVehicle.setUnit("dollars");
        newVehicle.setVehicleType("suv");
        newVehicle.setAcquisitionDate(new Date(System.currentTimeMillis()));
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
