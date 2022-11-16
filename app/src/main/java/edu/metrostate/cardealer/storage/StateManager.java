package edu.metrostate.cardealer.storage;

import edu.metrostate.cardealer.functionality.VehicleJSONParser;
import edu.metrostate.cardealer.inventory.DealerGroup;

import java.io.File;

public class StateManager {

    //lets panels change dealerGroup with class methods like addIncomingVehicles() etc
    public static DealerGroup dealerGroup;
    private static final File storage = new File("programState.json");


    public static void load(File file) {
        StateManager.dealerGroup = VehicleJSONParser.readAll(file);
    }

    public static void save(File file) {
        File storageFile = new File(file, "programState.json");
        VehicleJSONParser.writeAll(storageFile, StateManager.dealerGroup);
    }

    @Deprecated
    private static void load() {
        StateManager.dealerGroup = VehicleJSONParser.readAll(storage);
    }

    @Deprecated
    private static void save() {
        VehicleJSONParser.writeAll(storage, StateManager.dealerGroup);
    }

}
