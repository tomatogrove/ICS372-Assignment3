package edu.metrostate.cardealer;

import org.junit.jupiter.api.Test;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.metrostate.cardealer.inventory.DealerGroup;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;


class DealerGroupTest {
    Date now = new Date(System.currentTimeMillis());
    DealerGroup dg = new DealerGroup();

    Dealership d1 = new Dealership("123");
    Dealership d2 = new Dealership("456");
    Vehicle v1 = new Vehicle("1", "123", "suv", "Range Rover", "Land Rover", 17000.0, "pounds",now);
    Vehicle v2 = new Vehicle("2", "123", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now);
    Vehicle v3 = new Vehicle("3", "456", "sedan", "G70", "Genesis", 36600.0, "dollars", now);
    Vehicle v4 = new Vehicle("4", "456", "sports car", "Miata", "Mazda", 22330.0, "dollars", now);


    @Test
    void getDealerByID() {
        assertEquals("123", dg.getDealerByID("123"));
    }

    @Test
    void addIncomingVehicles() {
        List<Vehicle> test = new ArrayList<>();
        test.add(v1);
        test.add(v2);
        test.add(v3);
        Set<String> expected = new HashSet<>();
        expected.add(v1.getDealershipID());
        expected.add(v2.getDealershipID());
        expected.add(v4.getDealershipID());
        assertEquals(expected, dg.addIncomingVehicles(test));
    }

    @Test
    void addIncomingDealers() {
        List<Dealership> test = new ArrayList<>();
        test.add(d1);
        test.add(d2);
        Set<String> expected = new HashSet<>();
        expected.add(d1.getDealerID());
        expected.add(d2.getDealerID());
        assertEquals(expected, dg.addIncomingDealers(test));
    }

    @Test
    void transferInventory(String dealerID, String id) {
        List<Dealership> test = new ArrayList<>();
        test.add(d1);
        test.add(d2);

        d1.addIncomingVehicle(v1);
        d1.addIncomingVehicle(v2);
        d2.addIncomingVehicle(v3);
        d2.addIncomingVehicle(v4);
        boolean expected = true;

        dg.addIncomingDealers(test);

        assertEquals(expected, dg.transferInventory(d1.getDealerID(), d2.getDealerID()));
    }
}