package edu.metrostate.cardealer.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Vehicle implements Parcelable {

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
	private Vehicle(Parcel in) {
		this.vehicleID = in.readString();
		this.dealershipID =in.readString();
		this.vehicleType = in.readString();
		this.vehicleModel = in.readString();
		this.vehicleManufacturer = in.readString();
		String priceValue = 	String.valueOf(this.price);
		priceValue	= in.readString();
		this.unit = in.readString();
		String acquisition_date = String.valueOf(this.acquisitionDate);
		acquisition_date = in.readString();
		String rent = String.valueOf(rented);
		rent	= in.readString();


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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
	parcel.writeString(vehicleID);
		parcel.writeString( dealershipID);
		parcel.writeString(vehicleType);
		parcel.writeString(vehicleModel);
		parcel.writeString(vehicleManufacturer);
		parcel.writeString(unit);
		parcel.writeString(String.valueOf(price));
		parcel.writeString(String.valueOf(acquisitionDate));
		parcel.writeString(String.valueOf(rented));
	}
	public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
		public Vehicle createFromParcel(Parcel in) {
			return new Vehicle(in);
		}

		public Vehicle[] newArray(int size) {
			return new Vehicle[size];

		}
	};

}
