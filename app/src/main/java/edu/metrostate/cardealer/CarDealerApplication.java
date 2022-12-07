package edu.metrostate.cardealer;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import edu.metrostate.cardealer.storage.StateManager;

import edu.metrostate.cardealer.inventory.Vehicle;

public class CarDealerApplication extends Application {
    private static File stateFile;
    private static File externalDir;

    @Override
    public void onCreate() {
        super.onCreate();

        setStateFile();
        StateManager.load();

    }


    public static File getStateFile() {
        return stateFile;
    }

    public static File getExportDirectory() {
        return externalDir;
    }

    private void setStateFile() {
        externalDir = getExternalFilesDir(null);
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
