package edu.metrostate.cardealer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.List;


import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.parsing.AndroidVehicleXMLParser;
import edu.metrostate.cardealer.parsing.VehicleJSONParser;
import edu.metrostate.cardealer.storage.StateManager;

public class Vehicle_ImportFileActivity extends AppCompatActivity {
    String path;
    List<Dealership> dealers;
    List<Vehicle> vehicleJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_import_file);
        //button that redirects the dealer to view the list
        Button send_button= findViewById(R.id.send_data);
        send_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Vehicle_ViewListActivity.class);
            startActivity(intent);

        });

        Button addButton = findViewById(R.id.add_vehicle);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewVehicleFormActivity.class);
            startActivity(intent);

        });
    }

    //launch file explorer for JSON FILE
    public void buttonJsonFile(View view){

        Intent data = new Intent(Intent.ACTION_GET_CONTENT);
        data.setType("*/*");
        data = Intent.createChooser(data, "Choose a File");
        jsonActivityResultLauncher.launch(data);
    }
    //launch file explorer for XML file
    public void buttonXmlFile(View view){

        Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        data.setType("*/*");
        data = Intent.createChooser(data, "Choose a File");
        xmlActivityResultLauncher.launch(data);

    }
    //Parse and save the data into the state, check for file extension
    ActivityResultLauncher<Intent> jsonActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK){

                        Intent data = result.getData();


                        Uri uri = null;
                        if (data != null) {
                            uri = data.getData();
                        }
                        File file = null;
                        if (uri != null) {
                            file = new File(uri.getPath());
                        }
                        path = null;
                        if (file != null) {
                            path = file.getAbsolutePath();
                        }

                        if (path != null) {
                            if(path.substring(path.lastIndexOf(".") + 1).equals("json"))
                            {
                                final TextView jsonField = findViewById(R.id.json_path);
                                jsonField.setText(path);

                                vehicleJson = VehicleJSONParser.read(file);
                                StateManager.dealerGroup.addIncomingVehicles(vehicleJson);

                                showSuccessDialog();

                            }else{
                                final TextView errorField = findViewById(R.id.error_message);
                                errorField.setText("Wrong File Format");
                            }
                        }
                    }


                }
            }
    );
    //Parse and save the data into the state for XML
    ActivityResultLauncher<Intent> xmlActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri uri = null;
                        if (data != null) {
                            uri = data.getData();
                        }
                        File file = null;
                        if (uri != null) {
                            file = new File(uri.getPath());
                        }
                        if (file != null) {
                            path = file.getAbsolutePath();
                        }
                        if(path.substring(path.lastIndexOf(".") + 1).equals("xml"))
                        {
                            final TextView xmlField = findViewById(R.id.xml_path);
                            xmlField.setText(path);
                            dealers = AndroidVehicleXMLParser.read(file);
                            StateManager.dealerGroup.addIncomingDealers(dealers);

                            showSuccessDialog();

                        }else{
                            final TextView errorField = findViewById(R.id.error_message);
                            errorField.setText("Wrong file format");
                        }
                    }

                }
            }
    );

    private void showSuccessDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("Save Success")
                .setCancelable(false)
                .setMessage("File successfully imported.")
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StateManager.save();
    }
}