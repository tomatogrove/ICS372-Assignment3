package edu.metrostate.cardealer.functionality;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;


public class Vehicle_Import extends AppCompatActivity {
    String path;
    List<Vehicle> vehicles;
    List<Dealership> vehiclesXml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_file);

    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        for(String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);

                String outDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents" ;

                File outFile = new File(outDir, filename);

                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
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
                        path= null;
                        if (file != null) {
                            path = file.getAbsolutePath();
                        }

                        if(path.substring(path.lastIndexOf(".") + 1, path.length()).equals("json"))
                        {
                            final TextView jsonField = findViewById(R.id.json_path);
                            jsonField.setText(path);
                            vehicles = VehicleJSONParser.read(new File(file.getAbsolutePath()));
                        }else{
                            final TextView errorField = findViewById(R.id.error_message);
                            errorField.setText("Wrong File Format");
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
                        path = file.getAbsolutePath();
                        if(path.substring(path.lastIndexOf(".") + 1, path.length()).equals("xml"))
                        {
                            final TextView xmlField = findViewById(R.id.xml_path);
                            xmlField.setText(path);
                           vehiclesXml = VehicleXMLParser.read(new File(file.getAbsolutePath()));

                        }else{
                            final TextView errorField = findViewById(R.id.error_message);
                            errorField.setText("Wrong file format");
                        }
                    }

                }
            }
    );

    public void viewList(){
        Intent intent = new Intent(getApplicationContext(), DisplayList.class);
        intent.putExtra("vehicleList", path);
        startActivity(intent);
    }


}