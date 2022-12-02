package edu.metrostate.cardealer.storage;

import edu.metrostate.cardealer.functionality.VehicleJSONParser;
import edu.metrostate.cardealer.inventory.DealerGroup;

import java.io.File;

public class StateManager {

    //lets panels change dealerGroup with class methods like addIncomingVehicles() etc
    public static DealerGroup dealerGroup;
    @Deprecated


    public static void load() {
        StateManager.dealerGroup = VehicleJSONParser.readAll();
    }

    public static void save() {
        VehicleJSONParser.writeAll();
    }



}
