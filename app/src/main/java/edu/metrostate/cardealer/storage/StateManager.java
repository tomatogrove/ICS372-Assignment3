package edu.metrostate.cardealer.storage;

import android.content.Context;
import android.content.res.AssetManager;

import edu.metrostate.cardealer.functionality.VehicleJSONParser;
import edu.metrostate.cardealer.inventory.DealerGroup;

import java.io.File;
import java.io.InputStream;

public class StateManager {

    //lets panels change dealerGroup with class methods like addIncomingVehicles() etc
    public static DealerGroup dealerGroup;
    private static final File storage = new File("src/main/assets/programState.json");


    public static void load(InputStream stream) {
        StateManager.dealerGroup = VehicleJSONParser.readAll(stream);
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
