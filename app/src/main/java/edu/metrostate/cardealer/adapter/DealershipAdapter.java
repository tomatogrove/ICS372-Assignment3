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
import edu.metrostate.cardealer.inventory.Dealership;

public class DealershipAdapter extends ArrayAdapter<Dealership> {
    public DealershipAdapter(Context context, List<Dealership> shelterList) {
        super(context, R.layout.dealer_item, shelterList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dealer_item, parent, false);
        }

        TextView id = convertView.findViewById(R.id.dealership_id);
        TextView dealershipName = convertView.findViewById(R.id.dealership_name);


        id.setText(getItem(position).getDealerID());
        dealershipName.setText(getItem(position).getName());

        return convertView;
    }
}
