package edu.metrostate.cardealer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.functionality.VehicleJSONParser;
import edu.metrostate.cardealer.inventory.DealerGroup;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;

class VehicleJSONParserTest {

    private static final File READ_TO = new File("edu/metrostate/cardealer/resources/dealer123Inventory.json");
    private static final File READ = new File("dealer123Inventory.json");
    private static final File MOCK_STATE = new File("edu/metrostate/cardealer/resources/TestStateStorage.json");
    private static final File FAKE_PATH = new File("");
    private static final DealerGroup TEST_DEALER_GROUP = createTestDealership();
    private static final List<Vehicle> TEST_VEHICLES_1 = createTestVehicles1();
    private static final List<Vehicle> TEST_VEHICLES_2 = createTestVehicles2();


    @Test
    void read() {
        List<Vehicle> readVehicles = VehicleJSONParser.read(READ_TO);

        for (int i = 0; i < TEST_VEHICLES_1.size(); i++) {
            assertEquals(readVehicles.get(i).getVehicleID(), TEST_VEHICLES_1.get(i).getVehicleID());
            assertEquals(readVehicles.get(i).getVehicleType(), TEST_VEHICLES_1.get(i).getVehicleType());
            assertEquals(readVehicles.get(i).getVehicleModel(), TEST_VEHICLES_1.get(i).getVehicleModel());
            assertEquals(readVehicles.get(i).getUnit(), TEST_VEHICLES_1.get(i).getUnit());
            assertEquals(readVehicles.get(i).getPrice(), TEST_VEHICLES_1.get(i).getPrice());
        }
    }

    @Test
    void readFake() {
        List<Vehicle> readVehicles = VehicleJSONParser.read(FAKE_PATH);
        assertEquals(readVehicles.size(), 0);
    }

    @Test
    void write() {
        Dealership writeDealer = new Dealership();
        writeDealer.setDealerID("123");
        writeDealer.setVehicleInventory(TEST_VEHICLES_2);

        VehicleJSONParser.write(writeDealer);
        assertTrue(READ.length() > 0);
    }

    @Test
    void readAll() {
        DealerGroup readDealerGroup = VehicleJSONParser.readAll();

        List<Dealership> readDealers = readDealerGroup.getDealers();
        List<Dealership> testDealers = TEST_DEALER_GROUP.getDealers();
        for (int i = 0; i < testDealers.size(); i++) {

            assertEquals(readDealers.get(i).getDealerID(), testDealers.get(i).getDealerID());
            assertEquals(readDealers.get(i).getName(), testDealers.get(i).getName());

            List<Vehicle> readVehicles = readDealers.get(i).getVehicleInventory();
            List<Vehicle> testVehicles = testDealers.get(i).getVehicleInventory();
            for (int j = 0; j < testVehicles.size(); j++) {
                assertEquals(readVehicles.get(i).getVehicleID(), testVehicles.get(i).getVehicleID());
                assertEquals(readVehicles.get(i).getVehicleType(), testVehicles.get(i).getVehicleType());
                assertEquals(readVehicles.get(i).getVehicleModel(), testVehicles.get(i).getVehicleModel());
                assertEquals(readVehicles.get(i).getUnit(), testVehicles.get(i).getUnit());
                assertEquals(readVehicles.get(i).getPrice(), testVehicles.get(i).getPrice());
            }
        }
    }

    @Test
    void writeAll() {
        VehicleJSONParser.writeAll();

        readAll();
    }

    private static DealerGroup createTestDealership() {
        DealerGroup dealerGroup = new DealerGroup();
        Date now = new Date(System.currentTimeMillis());

        Dealership dealer1 = new Dealership("1", "Wacky Dealer One");

        dealer1.addIncomingVehicle(new Vehicle("1", "1", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer1.addIncomingVehicle(new Vehicle("2", "1", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer1.addIncomingVehicle(new Vehicle("3", "1", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer1.addIncomingVehicle(new Vehicle("4", "1", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));


        Dealership dealer2 = new Dealership("2", "Quirky Dealer Two");

        dealer2.addIncomingVehicle(new Vehicle("a", "2", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer2.addIncomingVehicle(new Vehicle("b", "2", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer2.addIncomingVehicle(new Vehicle("c", "2", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer2.addIncomingVehicle(new Vehicle("d", "2", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));

        dealerGroup.getDealers().addAll(List.of(dealer1, dealer2));

        return dealerGroup;
    }

    private static List<Vehicle> createTestVehicles1() {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("89343883", "12513", "pickup", "Silverado", "Chevy", 70444.0, "dollars", new Date(1515354694451L)));
        vehicles.add(new Vehicle("83883", "12513", "sedan", "Model 3", "Tesla", 50444.0, "dollars", new Date(1515354694451L)));
        vehicles.add(new Vehicle("48934j", "12513", "suv", "Explorer", "Ford", 20123.0, "dollars", new Date(1515354694451L)));

        return vehicles;
    }

    private static List<Vehicle> createTestVehicles2() {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("a", "123", "pickup", "Silverado", "Chevy", 70444.0, "dollars", new Date(1515354694451L)));
        vehicles.add(new Vehicle("b", "123", "sedan", "Model 3", "Tesla", 50444.0, "dollars", new Date(1515354694451L)));
        vehicles.add(new Vehicle("c", "123", "suv", "Explorer", "Ford", 20123.0, "dollars", new Date(1515354694451L)));

        return vehicles;
    }
}
