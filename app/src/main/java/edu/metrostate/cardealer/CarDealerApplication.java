package edu.metrostate.cardealer;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class CarDealerApplication extends Application {
//    private final List<Vehicle> vehicleList = new ArrayList<>();
    private File stateFile;

    @Override
    public void onCreate() {
        super.onCreate();

        setStateFile();
        StateManager.load(getStateFile());

//        for (Dealership dealer: StateManager.dealerGroup.getDealers()) {
//            vehicleList.addAll(dealer.getVehicleInventory());
//        }

//        //TODO: Remove this code
//        for(int i = 0; i < 20; i++) {
//            vehicleList.add(new Vehicle(Integer.toString(i), "Model " + i));
//        }

    }


//    public List<Vehicle> getVehicleList() {
//        return vehicleList;
//    }

    public File getStateFile() {
        return stateFile;
    }


    private void setStateFile() {
        File externalDir = getExternalFilesDir(null);
        File outputFile = new File(externalDir, "programState.json");

        if (!(outputFile.exists() && outputFile.isFile())) {
            try {
                Files.createFile(outputFile.toPath());
            } catch (IOException ex) {
                Log.e("FileCreation", "Error creating file", ex);
            }
        }
        stateFile = outputFile;
    }

    public void writeFile() {

        //TODO: Remove this code
        // Gets the output path which is /sdcard/Android/data/edu.metrostate.cardealer/files directory
        File externalDir = getExternalFilesDir(null);

        // Establishes the output file as "myfile.txt"
        File outputFile = new File(externalDir, "programState.txt");

        try {
            Files.createFile(outputFile.toPath());

            // Saves the string "My data" to the file
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }



}
