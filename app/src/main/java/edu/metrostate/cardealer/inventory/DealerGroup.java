package edu.metrostate.cardealer.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DealerGroup {

	private List<Dealership> dealers;
	
	public DealerGroup() {
		this.dealers = new ArrayList<>();
	}

	public List<Dealership> getDealers() {
		return dealers;
	}
	
	public Dealership getDealerByID(String dealerID) {
		Dealership getDealer = null;
		for(int i = 0; i < dealers.size(); i++) {
			if(dealerID.equalsIgnoreCase(dealers.get(i).getDealerID()) ) {
				getDealer = dealers.get(i);
			}
		}
		return getDealer;
	}

	@JsonIgnore
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		for (Dealership dealer : dealers) {
			vehicles.addAll(dealer.getVehicleInventory());
		}
		return vehicles;
	}
	
	public String displayDealerVehicles() {
		StringBuilder sb = new StringBuilder();
		for(Dealership dealer : dealers) {
			sb.append(dealer.toString());
		}
		return sb.toString();
	}

	public Set<String> addIncomingVehicles(List<Vehicle> vehicles) {
		Set<String> disabledDealers = new HashSet<>();
		for (Vehicle vehicle : vehicles) {

			Dealership dealer = getDealerByID(vehicle.getDealershipID());
			if (dealer == null) {
				dealer = new Dealership(vehicle.getDealershipID());
				dealers.add(dealer);
			}

			if (dealer.isVehicleAcquisition()) {
				dealer.addIncomingVehicle(vehicle);
			} else {
				disabledDealers.add(dealer.getDealerID());
			}
		}

		return disabledDealers;
	}

	public Set<String> addIncomingDealers(List<Dealership> dealers) {
		Set<String> disabledDealers = new HashSet<>();
		for (Dealership dealer : dealers) {
			Dealership oldDealer = getDealerByID(dealer.getDealerID());
			if (oldDealer != null) {
				String newName = dealer.getName();
				oldDealer.setName(newName);
			} else {
				this.dealers.add(dealer);
			}
			disabledDealers.addAll(addIncomingVehicles(new ArrayList<>(dealer.getVehicleInventory())));
		}
		return disabledDealers;
	}

	public boolean transferInventory(String d1, String d2) {
		Dealership dealer1 = getDealerByID(d1);
		Dealership dealer2 = getDealerByID(d2);
		if (dealer2 != null && !d1.equals(d2) && dealer2.isVehicleAcquisition()) {
			dealer1.setAllVehicleDealerIds(d2);
			for (Vehicle vehicle : dealer1.getVehicleInventory()) {
				dealer2.addIncomingVehicle(vehicle);
			}
			dealer1.clearInventory();
			return true;
		}
		return false;
	}

}
