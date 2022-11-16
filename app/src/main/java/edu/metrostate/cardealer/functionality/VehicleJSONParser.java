package edu.metrostate.cardealer.functionality;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.inventory.DealerGroup;
import edu.metrostate.cardealer.inventory.Dealership;
import edu.metrostate.cardealer.inventory.Vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;


public class VehicleJSONParser {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static List<Vehicle> read(File file) {
		List<Vehicle> vehicleList = new ArrayList<>();
		if (file.length() > 0) {
			try {
				vehicleList = mapper.readValue(file, VehicleJSONWrapper.class).getVehicles();
			} catch (IOException e) {
				e.printStackTrace();
				return vehicleList;
			}
		}

		return vehicleList;
	}


	public static void write(Dealership dealer) {
		String filePath = "./dealer" + dealer.getDealerID() + "Inventory.json";
		File file = new File(filePath);
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, dealer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// only for use with StateManager's load()
	public static DealerGroup readAll(File file) {
		DealerGroup dealerGroup = new DealerGroup();
		try {
			dealerGroup = mapper.readValue(file, DealerGroup.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dealerGroup;
	}

	public static DealerGroup readAll(InputStream stream) {
		DealerGroup dealerGroup = new DealerGroup();
		try {
			dealerGroup = mapper.readValue(stream, DealerGroup.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dealerGroup;
	}

	// only for use with StateManager's save()
	public static void writeAll(File file, DealerGroup dealerGroup) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, dealerGroup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeAll(OutputStream stream, DealerGroup dealerGroup) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(stream, dealerGroup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}