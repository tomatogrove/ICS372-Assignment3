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
    private static File stateFile;

    @Override
    public void onCreate() {
        super.onCreate();

        setStateFile();
        StateManager.load();

    }


    public static File getStateFile() {
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

}
