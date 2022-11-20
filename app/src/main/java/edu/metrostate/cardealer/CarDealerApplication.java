package edu.metrostate.cardealer;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.inventory.Dealership;

public class CarDealerApplication extends Application {
    private final List<Dealership> dealerList = new ArrayList<>();
    private Dealership workingDealer;

    @Override
    public void onCreate() {
        super.onCreate();


        //TODO: Remove this code
        for(int i = 0; i < 20; i++) {
            dealerList.add(new Dealership(Integer.toString(i), "GlobalBrand " + i));
        }

    }

    public Dealership getWorkingDealer() {
        return workingDealer;
    }

    public void setWorkingDealer(Dealership workingDealer) {
        this.workingDealer = workingDealer;
    }


    public List<Dealership> getDealerList() {
        return dealerList;
    }


    public void writeFile() {

        //TODO: Remove this code
        // Gets the output path which is /sdcard/Android/data/edu.metrostate.cardealer/files directory
        File externalDir = getExternalFilesDir(null);

        // Establishes the output file as "myfile.txt"
        File outputFile = new File(externalDir, "myfile.txt");

        try {
            Files.createFile(outputFile.toPath());

            // Saves the string "My data" to the file
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }



}
