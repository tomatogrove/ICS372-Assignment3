package edu.metrostate.cardealer.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.CarDealerApplication;
import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.functionality.VehicleJSONParser;
import edu.metrostate.cardealer.functionality.VehicleXMLParser;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class Vehicle_ImportFileActivity extends AppCompatActivity {
    String path;
    List<Dealership> vehicles;
    List<Vehicle> vehicleJson;
    private CarDealerApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_import_file);

        Button send_button= findViewById(R.id.send_data);
        send_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Vehicle_ViewListActivity.class);
            startActivity(intent);

        });}
    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;

        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) {
            for(String filename : files) {
                InputStream in;
                OutputStream out;
                try {
                    in = assetManager.open(filename);

                    String outDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents" ;

                    File outFile = new File(outDir, filename);

                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                    in.close();
                    out.flush();
                    out.close();
                } catch(IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public void buttonJsonFile(View view){
        copyAssets();
        Intent data = new Intent(Intent.ACTION_GET_CONTENT);
        data.setType("*/*");
        data = Intent.createChooser(data, "Choose a File");
        jsonActivityResultLauncher.launch(data);
    }
    public void buttonXmlFile(View view){
        copyAssets();
        Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        data.setType("*/*");
        data = Intent.createChooser(data, "Choose a File");
        xmlActivityResultLauncher.launch(data);

    }

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
                            if(path.substring(path.lastIndexOf(".") + 1, path.length()).equals("json"))
                            {
                                final TextView jsonField = findViewById(R.id.json_path);
                                jsonField.setText(path);

                                vehicleJson = (ArrayList<Vehicle>) VehicleJSONParser.read(file);
                            }else{
                                final TextView errorField = findViewById(R.id.error_message);
                                errorField.setText("Wrong File Format");
                            }
                        }
                    }


                }
            }
    );
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
                        if(path.substring(path.lastIndexOf(".") + 1, path.length()).equals("xml"))
                        {   vehicles = VehicleXMLParser.read(file);
                            final TextView xmlField = findViewById(R.id.xml_path);
                            xmlField.setText(path);
                        }else{
                            final TextView errorField = findViewById(R.id.error_message);
                            errorField.setText("Wrong file format");
                        }
                    }

                }
            }
    );
    public void addVehicle(){
        //need to change to Mike class
        Intent intent = new Intent(this, Vehicle_ViewListActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        app = (CarDealerApplication) getApplication();
        super.onPause();

        StateManager.save();
    }
}