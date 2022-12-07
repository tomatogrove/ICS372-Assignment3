package edu.metrostate.cardealer.inventory;

import java.util.Date;

public class Vehicle {

	private String vehicleID;
	private String dealershipID;
	private String vehicleType;
	private String vehicleModel;
	private String vehicleManufacturer;
	private String unit;
	private Double price;
	private Date acquisitionDate;
	private boolean rented;

	public Vehicle() {}

	public Vehicle(String vehicleID, String dealershipID, String vehicleType, String vehicleModel, String vehicleManufacturer,
		   Double price, String unit, Date acquisitionDate) {
		this.vehicleID = vehicleID;
		this.dealershipID = dealershipID;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.vehicleManufacturer = vehicleManufacturer;
		this.price = price;
		this.unit = unit;
		this.acquisitionDate = acquisitionDate;
		rented = false;
	}


	public Vehicle(String vehicleID, String dealershipID, String vehicleType, String vehicleModel, String vehicleManufacturer,
			Double price, Date acquisitionDate) {
		this.vehicleID = vehicleID;
		this.dealershipID = dealershipID;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.vehicleManufacturer = vehicleManufacturer;
		this.price = price;
		this.acquisitionDate = acquisitionDate;
		rented = false;
	}


	public String getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getDealershipID() {
		return dealershipID;
	}
	public void setDealershipID(String dealershipID) {
		this.dealershipID = dealershipID;
	}

	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}
	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	public String getUnit() { return unit; }
	public void setUnit(String unit) { this.unit = unit; }

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public boolean isRented() { return rented; }
	public void setRented(boolean rented) { this.rented = rented; }

	@Override
	public String toString() {
		return String.format("  %-20s%-20s%-20s%-20s%-20s%-20s%-20s%-40s%-20s\n\n", vehicleID, dealershipID, vehicleType,
				vehicleModel, vehicleManufacturer, unit, price, acquisitionDate, rented);
	}


	// vehicle is equal if the vehicleID and dealerShipID are the same
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vehicle vehicle = (Vehicle) o;
		return vehicleID.equals(vehicle.vehicleID) && dealershipID.equals(vehicle.dealershipID);
	}
}
