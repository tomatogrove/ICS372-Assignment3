package edu.metrostate.cardealer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.functionality.VehicleXMLParser;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;

class VehicleXMLParserTest {

    private static final File READ_FILE_ONE = new File("edu/metrostate/cardealer/resources/Dealers.xml");
    private static final File READ_FILE_MULTI = new File("edu/metrostate/cardealer/resources/MultiDealers.xml");
    private static final File FAKE_PATH = new File("");
    private static final Dealership DEALER = createTestDealership();
    private static final List<Dealership> DEALERS = createTestDealerships();


    @Test
    void readOneDealer() {
        List<Dealership> readDealer = VehicleXMLParser.read(READ_FILE_ONE);

        assertEquals(readDealer.get(0).getDealerID(), DEALER.getDealerID());

        List<Vehicle> readVehicles = readDealer.get(0).getVehicleInventory();
        List<Vehicle> testVehicles = DEALER.getVehicleInventory();

        for (int i = 0; i < readVehicles.size(); i++) {
            assertEquals(readVehicles.get(i).getVehicleID(), testVehicles.get(i).getVehicleID());
            assertEquals(readVehicles.get(i).getVehicleType(), testVehicles.get(i).getVehicleType());
            assertEquals(readVehicles.get(i).getVehicleModel(), testVehicles.get(i).getVehicleModel());
            assertEquals(readVehicles.get(i).getUnit(), testVehicles.get(i).getUnit());
            assertEquals(readVehicles.get(i).getPrice(), testVehicles.get(i).getPrice());
        }
    }

    @Test
    void readFake() {
        List<Dealership> readDealers = VehicleXMLParser.read(FAKE_PATH);
        assertEquals(readDealers.size(), 0);
    }

    @Test
    void readManyDealers() {
        List<Dealership> readDealers = VehicleXMLParser.read(READ_FILE_MULTI);

        for (int i = 0; i < DEALERS.size(); i++) {

            assertEquals(readDealers.get(i).getDealerID(), DEALERS.get(i).getDealerID());

            List<Vehicle> readVehicles = readDealers.get(i).getVehicleInventory();
            List<Vehicle> testVehicles = DEALERS.get(i).getVehicleInventory();
            for (int j = 0; j < testVehicles.size(); j++) {
                assertEquals(readVehicles.get(i).getVehicleID(), testVehicles.get(i).getVehicleID());
                assertEquals(readVehicles.get(i).getVehicleType(), testVehicles.get(i).getVehicleType());
                assertEquals(readVehicles.get(i).getVehicleModel(), testVehicles.get(i).getVehicleModel());
                assertEquals(readVehicles.get(i).getUnit(), testVehicles.get(i).getUnit());
                assertEquals(readVehicles.get(i).getPrice(), testVehicles.get(i).getPrice());
            }
        }
    }

    private static Dealership createTestDealership() {
        Dealership dealer = new Dealership("485", "Wacky Bob’s Automall");
        Date now = new Date(System.currentTimeMillis());
        dealer.addIncomingVehicle(new Vehicle("848432", "485", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer.addIncomingVehicle(new Vehicle("52523", "485", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer.addIncomingVehicle(new Vehicle("151e5dde", "485", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer.addIncomingVehicle(new Vehicle("ern222", "485", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));

        return dealer;
    }

    private static List<Dealership> createTestDealerships() {

        Date now = new Date(System.currentTimeMillis());

        Dealership dealer1 = new Dealership("1", "Wacky Dealer One");
        dealer1.addIncomingVehicle(new Vehicle("a", "485", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer1.addIncomingVehicle(new Vehicle("b", "485", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer1.addIncomingVehicle(new Vehicle("c", "485", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer1.addIncomingVehicle(new Vehicle("d", "485", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));

        Dealership dealer2 = new Dealership("2", "Wacky Dealer One");
        dealer2.addIncomingVehicle(new Vehicle("1", "485", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer2.addIncomingVehicle(new Vehicle("2", "485", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer2.addIncomingVehicle(new Vehicle("3", "485", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer2.addIncomingVehicle(new Vehicle("4", "485", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));

        return new ArrayList<>(List.of(dealer1, dealer2));
    }

}
