package edu.metrostate.cardealer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;

class DealershipTest {

    @Test
    void getDealerID() {
        Dealership test = new Dealership("123");
        assertEquals("123", test.getDealerID());
    }

    @Test
    void setDealerID() {
        Dealership test = new Dealership("123");
        test.setDealerID("456");

        assertEquals("456", test.getDealerID());
    }

    @Test
    void setRenting() {
        Dealership test = new Dealership("123");
        test.setRenting(true);

        assertEquals(true, test.isRenting());
    }

    @Test
    void getVehicleInventory() {
        Date now = new Date(System.currentTimeMillis());
        List<Vehicle> expected = new ArrayList<>();
        expected.add(new Vehicle("1", "123", "suv", "Range Rover", "Land Rover", 17000.0, "pounds",now));
        expected.add(new Vehicle("2", "123", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        List<Vehicle> ans = createFakeDealership().getVehicleInventory();

        assertTrue(expected.size() == ans.size());
    }

    @Test
    void clearInventory() {
        List<Vehicle> expected = new ArrayList<>();
        Dealership test = createFakeDealership();
        test.clearInventory();
        List<Vehicle> ans = test.getVehicleInventory();

        assertTrue(expected.size() == ans.size());
    }

    @Test
    void isVehicleRented() {
        Date now = new Date(System.currentTimeMillis());
        Dealership test = createFakeDealership();
        Vehicle v1 = new Vehicle("1", "123", "suv", "Range Rover", "Land Rover", 17000.0, "pounds",now);

        test.isVehicleRented(v1);
        assertEquals(false, test.isVehicleRented(v1));
    }

    @Test
    void setAllVehicleDealerIds() {
        Date now = new Date(System.currentTimeMillis());
        Dealership test = createFakeDealership2();
        Vehicle testVehicle1 = test.getVehicleById("1");
        Vehicle testVehicle2 = test.getVehicleById("2");
        test.setAllVehicleDealerIds("123");

        assertEquals("123", testVehicle1.getDealershipID());
        assertEquals("123", testVehicle2.getDealershipID());

    }

    private static Dealership createFakeDealership() {
        Date now = new Date(System.currentTimeMillis());
        Dealership d1 = new Dealership("123");

        Vehicle v1 = new Vehicle("1", "123", "suv", "Range Rover", "Land Rover", 17000.0, "pounds",now);
        Vehicle v2 = new Vehicle("2", "123", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now);

        d1.addIncomingVehicle(v1);
        d1.addIncomingVehicle(v2);
        return d1;
    }

    private static Dealership createFakeDealership2() {
        Date now = new Date(System.currentTimeMillis());
        Dealership d1 = new Dealership("123");

        Vehicle v1 = new Vehicle("1", "456", "suv", "Range Rover", "Land Rover", 17000.0, "pounds",now);
        Vehicle v2 = new Vehicle("2", "456", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now);

        d1.addIncomingVehicle(v1);
        d1.addIncomingVehicle(v2);
        return d1;
    }
}