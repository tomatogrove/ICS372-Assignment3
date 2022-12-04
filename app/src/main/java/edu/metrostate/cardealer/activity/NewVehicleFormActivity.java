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
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.activity.util.TextChangedListener;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class NewVehicleFormActivity extends AppCompatActivity{

    private CarDealerApplication app;
    private Vehicle newVehicle;

    private EditText vehicleID;
    private EditText dealershipID;
    private Spinner vehicleTypeSpinner;
    private EditText vehicleModel;
    private EditText vehicleManufacturer;
    private EditText unit;
    private EditText price;
    private Button setAcquisitionDate;
    private CheckBox rented;
    private Button save;
    private Button reset;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle_form);

        app = (CarDealerApplication) getApplication();


        // edit text
        vehicleID = findViewById(R.id.vehicle_id);
        dealershipID = findViewById(R.id.dealer_id);
        vehicleModel = findViewById(R.id.vehicle_model);
        vehicleManufacturer = findViewById(R.id.vehicle_manufacturer);
        unit = findViewById(R.id.vehicle_unit);
        price = findViewById(R.id.vehicle_price);
        rented = findViewById(R.id.vehicle_rented);

        // spinner
        vehicleTypeSpinner = findViewById(R.id.vehicle_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(adapter);

        // buttons
        save = findViewById(R.id.save_button);
        reset = findViewById(R.id.reset_button);
        setAcquisitionDate = findViewById(R.id.set_date);

        resetForm();
        resetVehicle();

        datePickerDialog = new DatePickerDialog(this);


        // pattern from https://stackoverflow.com/questions/11134144/android-edittext-onchange-listener
        vehicleID.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (vehicleID.getText().toString().equals("")) {
                    newVehicle.setVehicleID(null);
                }
                newVehicle.setVehicleID(vehicleID.getText().toString());
            }
        });

        dealershipID.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (dealershipID.getText().toString().equals("")) {
                    newVehicle.setDealershipID(null);
                }
                newVehicle.setDealershipID(dealershipID.getText().toString());
            }
        });


        vehicleModel.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (vehicleModel.getText().toString().equals("")) {
                    newVehicle.setVehicleModel(null);
                }
                newVehicle.setVehicleModel(vehicleModel.getText().toString());
            }
        });

        vehicleManufacturer.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (vehicleManufacturer.getText().toString().equals("")) {
                    newVehicle.setVehicleManufacturer(null);
                }
                newVehicle.setVehicleManufacturer(vehicleManufacturer.getText().toString());
            }
        });

        unit.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (unit.getText().toString().equals("")) {
                    newVehicle.setUnit(null);
                }
                newVehicle.setUnit(unit.getText().toString());
            }
        });

        price.addTextChangedListener(new TextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (price.getText().toString().equals("")) {
                    newVehicle.setPrice(null);
                } else {
                    try {
                        newVehicle.setPrice(Double.valueOf(price.getText().toString()));
                    } catch (NumberFormatException e) {
                        showInvalidFieldDialog("Value entered is not a valid price!");
                    }
                }
            }
        });


        rented.setOnCheckedChangeListener((buttonView, isChecked) -> newVehicle.setRented(isChecked));

        // from https://stackoverflow.com/questions/13097784/basic-spinner-example
        vehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String vehicleTypeValue = adapterView.getItemAtPosition(pos).toString();
                if (!vehicleTypeValue.equals("sports car")) {
                    rented.setEnabled(true);
                } else {
                    if (rented.isChecked()) {
                        rented.setChecked(false);
                        showInvalidFieldDialog("Cannot rent out a vehicle that is a sports car.");
                    }
                    rented.setEnabled(false);
                    newVehicle.setRented(false);
                }
                newVehicle.setVehicleType(vehicleTypeValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        save.setOnClickListener( v -> {
            List<String> errors = validateVehicle();
            if (errors.size() == 0) {
                List<Vehicle> vehicle = new ArrayList<>();
                vehicle.add(newVehicle);
                StateManager.dealerGroup.addIncomingVehicles(vehicle);
                showSuccessDialog();
                resetForm();
                resetVehicle();
            } else {
                showErrorDialog(errors);
            }
        });

        reset.setOnClickListener(v -> {
            resetForm();
            resetVehicle();
        });

        setAcquisitionDate.setOnClickListener(v -> datePickerDialog.show());

        // https://stackoverflow.com/questions/39916178/how-to-show-datepickerdialog-on-button-click
        datePickerDialog.setOnDateSetListener((datePicker, year, month, day) -> {
            setAcquisitionDate.setText(String.format("%d/%d/%d", month, day, year));
            LocalDate localDate = LocalDate.of(year, month, day);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            newVehicle.setAcquisitionDate(date);
        });

    }


    private void showInvalidFieldDialog(String message) {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Field Invalid")
                .setCancelable(false)
                .setMessage(message)
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

    private void showErrorDialog(List<String> errors) {
        StringBuilder builder = new StringBuilder();
        for (String error : errors) {
            builder.append(error);
        }

        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Save Unsuccessful")
                .setCancelable(false)
                .setMessage("Vehicle failed to save due to the following reason(s):\n\n" + builder)
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    private List<String> validateVehicle() {
        List<String> errors = new ArrayList<>();

        if (newVehicle.getVehicleID() == null) {
            errors.add("Vehicle ID is a required field.\n");
        }

        if (newVehicle.getDealershipID() == null) {
            errors.add("Dealer ID is a required field.\n");
        } else if (StateManager.dealerGroup.getDealerByID(newVehicle.getDealershipID()) != null){
            Dealership dealer = StateManager.dealerGroup.getDealerByID(newVehicle.getDealershipID());
            if (!dealer.isVehicleAcquisition()) {
                errors.add("Dealer " + newVehicle.getDealershipID() + " is not accepting new vehicles.\n");
            }
            if (!dealer.isRenting() && newVehicle.isRented()) {
                errors.add("Dealer " + newVehicle.getDealershipID() + " is not renting out vehicles.\n");
            }
            if (dealer.getVehicleById(newVehicle.getVehicleID()) != null) {
                errors.add("Dealer " + newVehicle.getDealershipID() + " already has vehicle " + newVehicle.getVehicleID() + ".\n");
            }
        }
        if (newVehicle.getVehicleModel() == null) {
            errors.add("Vehicle model is a required field.\n");
        }
        if (newVehicle.getVehicleManufacturer() == null) {
            errors.add("Vehicle manufacturer is a required field.\n");
        }
        if (newVehicle.getPrice() == null) {
            errors.add("Price is a required field.\n");
        }

        return errors;
    }

    private void resetVehicle() {
        newVehicle = new Vehicle();
        newVehicle.setRented(false);
        newVehicle.setUnit("dollars");
        newVehicle.setVehicleType("suv");
        newVehicle.setAcquisitionDate(new Date(System.currentTimeMillis()));
    }

    private void resetForm() {
        vehicleID.getText().clear();
        dealershipID.getText().clear();
        vehicleModel.getText().clear();
        vehicleManufacturer.getText().clear();
        price.getText().clear();
        unit.setText("dollars");
        rented.setChecked(false);
        LocalDate today = LocalDate.now();
        setAcquisitionDate.setText(String.format("%d/%d/%d", today.getMonthValue(), today.getDayOfMonth(), today.getYear()));
        vehicleTypeSpinner.setSelection(0);
    }

    @Override
    protected void onPause() {
        super.onPause();

        StateManager.save();
    }

}
