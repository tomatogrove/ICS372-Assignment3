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
import edu.metrostate.cardealer.inventory.DealerGroup;
import edu.metrostate.cardealer.inventory.Dealership;

public class CarDealerApplication extends Application {
    private static File stateFile;
    private final List<Dealership> dealerList = new ArrayList<>();
    private Dealership workingDealer;
    private DealerGroup groupOfDealers;

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO: Remove this code
        for(int i = 0; i < 20; i++) {
            dealerList.add(new Dealership(Integer.toString(i), "GlobalBrand " + i));
        }
        setStateFile();
        StateManager.load();

    }


    public static File getStateFile() {
        return stateFile;
    }
    public DealerGroup getGroupOfDealers() {return groupOfDealers;}
    public Dealership getWorkingDealer() {return workingDealer;}

    public void setWorkingDealer(Dealership workingDealer) {
        this.workingDealer = workingDealer;
    }


    public List<Dealership> getDealerList() {
        return dealerList;
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
