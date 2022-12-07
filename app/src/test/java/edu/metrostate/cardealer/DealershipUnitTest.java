package edu.metrostate.cardealer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.inventory.DealerGroup;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DealershipUnitTest {

    @Test
    public void setName() {
        Dealership testDealer1 = new Dealership("123","oldName");
        String newName = "newName";
        testDealer1.setName(newName);
        assertEquals(newName, testDealer1.getName());
    }

    @Test
    public void setRenting() {
        //default value is false on construction. Testing by changing to true
        Dealership testDealer1 = new Dealership("123","oldName");
        testDealer1.setRenting(true);
        assertTrue(testDealer1.isRenting());
        testDealer1.setRenting(false);
        assertFalse(testDealer1.isRenting());
    }

    @Test
    public void setVehicleAcquisition() {
        //set to true upon construction changing to false before testing
        Dealership testDealer1 = new Dealership("123","oldName");
        testDealer1.setVehicleAcquisition(false);
        assertFalse(testDealer1.isVehicleAcquisition());
        testDealer1.setVehicleAcquisition(true);
        assertTrue(testDealer1.isVehicleAcquisition());
    }
    //Testing method from DealerGroup class
    @Test
    public void transferInventory() {
        //setting up test dealers with a vehicle in each inventory
        DealerGroup testGroup1 = new DealerGroup();
        Dealership testDealer1 = new Dealership("123","oldName");
        Dealership testDealer2 = new Dealership("456","newName");
        Vehicle Murano = new Vehicle("1000","123","SUV","Murano","Nissan",10000.00,new Date());
        Vehicle Rogue = new Vehicle("2000","456","SUV","Rogue","Nissan",10000.00,new Date());
        testDealer1.addIncomingVehicle(Murano);
        testDealer2.addIncomingVehicle(Rogue);
        List<Dealership> testGroup = new ArrayList<>();
        testGroup.add(testDealer1);
        testGroup.add(testDealer2);
        testGroup1.addIncomingDealers(testGroup);
        //transferring testDealer1's inventory to testDealer2's inventory
        testGroup1.transferInventory(testDealer1.getDealerID(), testDealer2.getDealerID());
        //Checking if Murano is now contained in testDealer2's inventory instead of testDealer1
        assertTrue(testDealer2.getVehicleInventory().contains(Murano));


    }



}