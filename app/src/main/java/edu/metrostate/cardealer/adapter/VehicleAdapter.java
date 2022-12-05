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

public class VehicleAdapter extends ArrayAdapter<Vehicle> {
    public VehicleAdapter(Context context, List<Vehicle> shelterList) {
        super(context, R.layout.vehicle_item, shelterList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_item, parent, false);
        }

        TextView id = convertView.findViewById(R.id.vehicle_id);
        TextView model = convertView.findViewById(R.id.vehicle_model);


        id.setText(getItem(position).getVehicleID());
        model.setText(getItem(position).getVehicleType());

        return convertView;
    }
}
