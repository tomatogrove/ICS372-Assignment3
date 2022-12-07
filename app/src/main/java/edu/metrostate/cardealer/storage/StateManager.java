package edu.metrostate.cardealer.storage;

import edu.metrostate.cardealer.parsing.VehicleJSONParser;
import edu.metrostate.cardealer.inventory.DealerGroup;

public class StateManager {

    //lets activities change dealerGroup with class methods like addIncomingVehicles() etc
    public static DealerGroup dealerGroup;


    public static void load() {
        StateManager.dealerGroup = VehicleJSONParser.readAll();
    }

    public static void save() {
        VehicleJSONParser.writeAll();
    }

}
