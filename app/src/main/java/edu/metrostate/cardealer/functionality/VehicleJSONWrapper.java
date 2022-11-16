package edu.metrostate.cardealer.functionality;

import edu.metrostate.cardealer.inventory.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VehicleJSONWrapper {
    private List<ImportedVehicle> car_inventory;

    public VehicleJSONWrapper() { }

    public List<ImportedVehicle> getCar_inventory() {
        return car_inventory;
    }

    public void setCar_inventory(ArrayList<ImportedVehicle> car_inventory) {
        this.car_inventory = car_inventory;
    }

    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        for (ImportedVehicle importedVehicle : car_inventory) {
            Vehicle vehicle = new Vehicle();

            vehicle.setPrice(importedVehicle.getPrice());
            vehicle.setVehicleID(importedVehicle.getVehicle_id());
            vehicle.setDealershipID(importedVehicle.getDealership_id());
            vehicle.setVehicleType(importedVehicle.getVehicle_type());
            vehicle.setVehicleModel(importedVehicle.getVehicle_model());
            vehicle.setVehicleManufacturer(importedVehicle.getVehicle_manufacturer());
            vehicle.setAcquisitionDate(new Date(importedVehicle.getAcquisition_date()));

            //default values
            vehicle.setUnit("dollars");
            vehicle.setRented(false);

            vehicles.add(vehicle);
        }

        return vehicles;
    }

    public static class ImportedVehicle {
        private double price;
        private String vehicle_id;
        private String dealership_id;
        private String vehicle_type;
        private String vehicle_model;
        private String vehicle_manufacturer;
        private long acquisition_date;

        public ImportedVehicle() { }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }

        public String getVehicle_id() { return vehicle_id; }
        public void setVehicle_id(String vehicle_id) { this.vehicle_id = vehicle_id; }

        public String getDealership_id() { return dealership_id; }
        public void setDealership_id(String dealership_id) { this.dealership_id = dealership_id; }

        public String getVehicle_type() { return vehicle_type; }
        public void setVehicle_type(String vehicle_type) { this.vehicle_type = vehicle_type; }

        public String getVehicle_model() { return vehicle_model; }
        public void setVehicle_model(String vehicle_model) { this.vehicle_model = vehicle_model; }

        public String getVehicle_manufacturer() { return vehicle_manufacturer; }
        public void setVehicle_manufacturer(String vehicle_manufacturer) { this.vehicle_manufacturer = vehicle_manufacturer; }

        public long getAcquisition_date() { return acquisition_date; }
        public void setAcquisition_date(long acquisition_date) { this.acquisition_date = acquisition_date; }
    }

}
