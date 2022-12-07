package edu.metrostate.cardealer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.inventory.Vehicle;
import edu.metrostate.cardealer.storage.StateManager;

public class VehicleAdapter extends ArrayAdapter<Vehicle> {
    public VehicleAdapter(Context context) {
        super(context, R.layout.vehicle_item, StateManager.dealerGroup.getAllVehicles());
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_item, parent, false);
        }
        TextView dealerId = convertView.findViewById(R.id.dealer_id);
        TextView id = convertView.findViewById(R.id.vehicle_id);
        TextView model = convertView.findViewById(R.id.vehicle_model);
        TextView type = convertView.findViewById(R.id.vehicle_type);
        TextView manufacturer = convertView.findViewById(R.id.vehicle_manufacturer);
        TextView price = convertView.findViewById(R.id.vehicle_price);
        TextView date = convertView.findViewById(R.id.vehicle_date);
        TextView rented = convertView.findViewById(R.id.vehicle_rented);
        dealerId.setText("Dealer ID : " +getItem(position).getDealershipID());
        id.setText("ID : " +getItem(position).getVehicleID());
        model.setText("Model: " + getItem(position).getVehicleModel());
        type.setText("Type: " +getItem(position).getVehicleType());
        manufacturer.setText("Manufacturer: " + getItem(position).getVehicleManufacturer());
        price.setText("Price: " + getItem(position).getPrice()+ " " +getItem(position).getUnit());
        date.setText("Date: "+getItem(position).getAcquisitionDate());
        rented.setText("Rented: "+getItem(position).isRented());

        return convertView;
    }
}
