package edu.metrostate.cardealer.functionality;

import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleXMLWrapper {

    @JacksonXmlProperty(localName = "Dealer")
    private List<ImportedDealership> dealers;

    public VehicleXMLWrapper() { }

    public List<ImportedDealership> getDealers() {
        return dealers;
    }
    public void setDealer(List<ImportedDealership> dealers) {
        this.dealers = dealers;
    }

    public List<Dealership> getValidDealers() {
        List<Dealership> dealers = new ArrayList<>();

        for (ImportedDealership importedDealership : this.dealers) {
            Dealership dealer = new Dealership();

            dealer.setDealerID(importedDealership.getId());
            dealer.setName(importedDealership.getName());
            dealer.setVehicleInventory(importedDealership.getValidVehicles(importedDealership.getId()));

            dealers.add(dealer);
        }

        return dealers;
    }

    public static class ImportedDealership {
        @JacksonXmlProperty(isAttribute = true)
        private String id;

        @JacksonXmlProperty(localName = "Name")
        private String name;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Vehicle")
        private List<ImportedVehicle> vehicles;

        public ImportedDealership() { }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public List<ImportedVehicle> getVehicles() { return vehicles; }
        public void setVehicles(List<ImportedVehicle> vehicles) { this.vehicles = vehicles; }

        public List<Vehicle> getValidVehicles(String id) {
            List<Vehicle> vehicles = new ArrayList<>();
            Date now = new Date(System.currentTimeMillis());

            for (ImportedVehicle importedVehicle : this.vehicles) {
                Vehicle vehicle = new Vehicle();

                vehicle.setVehicleType(importedVehicle.getType());
                vehicle.setVehicleID(importedVehicle.getId());
                vehicle.setUnit(importedVehicle.getImportedPrice().getUnit());
                vehicle.setPrice(Double.valueOf(importedVehicle.getImportedPrice().getPrice()));
                vehicle.setVehicleManufacturer(importedVehicle.getMake());
                vehicle.setVehicleModel(importedVehicle.getModel());
                vehicle.setDealershipID(id);

                // default values
                vehicle.setRented(false);
                vehicle.setAcquisitionDate(now);

                vehicles.add(vehicle);
            }

            return vehicles;
        }
    }

    public static class ImportedVehicle {
        @JacksonXmlProperty(isAttribute = true)
        private String type;
        @JacksonXmlProperty(isAttribute = true)
        private String id;

        @JacksonXmlProperty(localName = "Price")
        private ImportedPrice importedPrice;

        @JacksonXmlProperty(localName = "Make")
        private String make;

        @JacksonXmlProperty(localName = "Model")
        private String model;

        public ImportedVehicle() { }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public ImportedPrice getImportedPrice() { return importedPrice; }
        public void setImportedPrice(ImportedPrice importedPrice) { this.importedPrice = importedPrice; }

        public String getMake() { return make; }
        public void setMake(String make) { this.make = make; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
    }

    public static class ImportedPrice {
        @JacksonXmlProperty(isAttribute = true)
        private String unit;
        @JacksonXmlText
        private String price;

        public ImportedPrice() { }

        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }

        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }
    }
}
