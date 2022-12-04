package edu.metrostate.cardealer.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String dealerID;
    private boolean vehicleAcquisition;
    private String name;
    private boolean isRenting;
    private List<Vehicle> vehicleInventory;

    public Dealership(){
        vehicleAcquisition = true;
        isRenting = true;
        name = "N/A";
    }

    public Dealership(String dealerID, String name) {
        this.dealerID = dealerID;
        this.name = name;
        vehicleAcquisition = true;
        vehicleInventory = new ArrayList<>();
        isRenting = false;
    }

    public Dealership(String newDealer) {
        dealerID = newDealer;
        name = "N/A";
        vehicleAcquisition = true;
        vehicleInventory = new ArrayList<>();
        isRenting = false;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDealerID() {
        return dealerID;
    }
    public void setDealerID(String dealerID) { this.dealerID = dealerID; }

    public boolean isVehicleAcquisition() {
        return vehicleAcquisition;
    }
    public void setVehicleAcquisition(boolean acquiring) {vehicleAcquisition = acquiring; }

    public boolean isRenting() { return isRenting; }
    public void setRenting(boolean renting) { isRenting = renting; }

    public List<Vehicle> getVehicleInventory() { return vehicleInventory; }
    public void setVehicleInventory(List<Vehicle> vehicleInventory) { this.vehicleInventory = vehicleInventory; }

    //methods
    public void addIncomingVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleType().equalsIgnoreCase("SUV") || vehicle.getVehicleType().equalsIgnoreCase("Sedan") || vehicle.getVehicleType().equalsIgnoreCase("Pickup") || vehicle.getVehicleType().equalsIgnoreCase("Sports Car")) {
            if (vehicleAcquisition) {
                if (vehicleInventory.contains(vehicle)) {

                    // if truly breaks the app, then maybe need to copy and edit list.....?
                    Vehicle removeVehicle = getVehicleById(vehicle.getVehicleID());
                    vehicleInventory.remove(removeVehicle);
                }
                vehicleInventory.add(vehicle);
            }
        }
    }

    public Vehicle getVehicleById(String vehicleID) {
        for (Vehicle vehicle : vehicleInventory) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                return vehicle;
            }
        }
        return null;
    }

    public void enableDealerVehicleAcquisition() {
        this.vehicleAcquisition = true;
    }

    public void disableDealerVehicleAcquisition() {
        this.vehicleAcquisition = false;
    }

    @Override
    public String toString() {
        StringBuilder inventory = new StringBuilder("  Dealer: " + getDealerID() + "\tName: " + getName() + "\n");

        inventory.append(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-40s\n", "  Vehicle ID", "Dealership ID",
                "Vehicle Type", "Vehicle Model", "Vehicle Manufacturer", "Unit","Price", "Acquisition Date", "Rent Status"));

        for( Vehicle vehicle : this.vehicleInventory) {
            inventory.append("------------------------------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------------------------------\n");
            inventory.append(vehicle.toString());
        }
        inventory.append("\n\n");
        return inventory.toString();
    }

    public void clearInventory() {
        vehicleInventory.clear();
    }

    public boolean isVehicleRented(Vehicle vehicle) {
        return getVehicleById(vehicle.getVehicleID()) != null && getVehicleById(vehicle.getVehicleID()).isRented();
    }

    public void setAllVehicleDealerIds(String d2) {
        for (Vehicle vehicle : vehicleInventory) {
            vehicle.setDealershipID(d2);
        }
    }
}
