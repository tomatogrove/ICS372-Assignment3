package edu.metrostate.cardealer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import edu.metrostate.cardealer.R;
import edu.metrostate.cardealer.adapter.DealershipAdapter;
import edu.metrostate.cardealer.storage.StateManager;

public class DealerListActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();

        StateManager.save();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_list);

        // Create an adapter for the list view
        DealershipAdapter adapter = new DealershipAdapter(this, StateManager.dealerGroup.getDealers());

        // Find the list view and add the adapter
        ListView dealerListAdapter = (findViewById(R.id.dealer_list));
        dealerListAdapter.setAdapter(adapter);

        dealerListAdapter.setOnItemClickListener((parent, view, position, id) -> {
            //showDialog(adapter.getItem(position));
            // Create the intent with the new activity
            Intent intent = new Intent(DealerListActivity.this, DealerInfoActivity.class);
            intent.putExtra("workingDealerID", adapter.getItem(position).getDealerID());
            startActivity(intent);
        });


    }
}