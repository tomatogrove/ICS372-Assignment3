package edu.metrostate.cardealer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Date;

import edu.metrostate.cardealer.inventory.Vehicle;

class VehicleTest {

    @Test
    void getVehicleID() {
        Vehicle test = createVehicle();
        assertEquals("1", test.getVehicleID());
    }

    @Test
    void setVehicleID() {
        Vehicle test = createVehicle();
        test.setVehicleID("1111");
        assertEquals("1111", test.getVehicleID());
    }

    @Test
    void getDealershipID() {
        Vehicle test = createVehicle();
        assertEquals("456", test.getDealershipID());
    }

    @Test
    void setDealershipID() {
        Vehicle test = createVehicle();
        test.setDealershipID("123");
        assertEquals("123", test.getDealershipID());
    }

    @Test
    void getVehicleType() {
        Vehicle test = createVehicle();
        assertEquals("suv", test.getVehicleType());
    }

    @Test
    void setVehicleType() {
        Vehicle test = createVehicle();
        test.setVehicleType("sports car");
        assertEquals("sports car", test.getVehicleType());
    }

    @Test
    void getVehicleModel() {
        Vehicle test = createVehicle();
        assertEquals("Range Rover", test.getVehicleModel());
    }

    @Test
    void setVehicleModel() {
        Vehicle test = createVehicle();
        test.setVehicleModel("Discovery");
        assertEquals("Discovery", test.getVehicleModel());
    }

    @Test
    void getVehicleManufacturer() {
        Vehicle test = createVehicle();
        assertEquals("Land Rover", test.getVehicleManufacturer());
    }

    @Test
    void setVehicleManufacturer() {
        Vehicle test = createVehicle();
        test.setVehicleModel("Toyota");
        assertEquals("Toyota", test.getVehicleManufacturer());
    }

    @Test
    void getUnit() {
        Vehicle test = createVehicle();
        assertEquals("pounds", test.getUnit());
    }

    @Test
    void setUnit() {
        Vehicle test = createVehicle();
        test.setUnit("dollars");
        assertEquals("dollars", test.getUnit());
    }

    @Test
    void getPrice() {
        Vehicle test = createVehicle();
        assertTrue(17000.0 == test.getPrice());
    }

    @Test
    void setPrice() {
        Vehicle test = createVehicle();
        test.setPrice(25000.0);
        assertTrue(25000.0 == test.getPrice());
    }

    @Test
    void getAcquisitionDate() {
        Vehicle test = createVehicle();
        Date expected = new Date(System.currentTimeMillis());
        assertEquals(expected, test.getAcquisitionDate());
    }

    @Test
    void isRented() {
        Vehicle test = createVehicle();
        assertEquals(false, test.isRented());
    }

    @Test
    void setRented() {
        Vehicle test = createVehicle();
        test.setRented(true);
        assertEquals(true, test.isRented());
    }

    private static Vehicle createVehicle() {
        Date now = new Date(System.currentTimeMillis());
        Vehicle v1 = new Vehicle("1", "456", "suv", "Range Rover", "Land Rover", 17000.0, "pounds",now);
        return v1;
    }
}