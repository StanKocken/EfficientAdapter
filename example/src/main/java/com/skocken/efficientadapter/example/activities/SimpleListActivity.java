package com.skocken.efficientadapter.example.activities;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Plane;
import com.skocken.efficientadapter.example.viewholders.BookViewHolder;
import com.skocken.efficientadapter.example.viewholders.PlaneViewHolder;
import com.skocken.efficientadapter.lib.adapter.AbsViewHolderAdapter;
import com.skocken.efficientadapter.lib.adapter.SimpleAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SimpleListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(true);
        setContentView(recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        SimpleAdapter adapter = new SimpleAdapter<Plane>(R.layout.item_plane, PlaneViewHolder.class,
                generateListOfPlane());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AbsViewHolderAdapter.OnItemClickListener<Plane>() {
            @Override
            public void onItemClick(AbsViewHolderAdapter<Plane> parent, View view, Plane object,
                    int position) {
                Toast.makeText(view.getContext(),
                        "Click on: " + object.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Plane> generateListOfPlane() {
        List<Plane> listPlane = new ArrayList<Plane>();
        listPlane.add(new Plane("Airbus", "A300"));
        listPlane.add(new Plane("Airbus", "A310"));
        listPlane.add(new Plane("Airbus", "A310 MRTT"));
        listPlane.add(new Plane("Airbus", "CC-150 Polaris Canadian Armed Forces"));
        listPlane.add(new Plane("Airbus", "A318"));
        listPlane.add(new Plane("Airbus", "A319"));
        listPlane.add(new Plane("Airbus", "A319CJ"));
        listPlane.add(new Plane("Airbus", "A320"));
        listPlane.add(new Plane("Airbus", "A321"));
        listPlane.add(new Plane("Airbus", "A330"));
        listPlane.add(new Plane("Airbus", "A330 MRTT"));
        listPlane.add(new Plane("Airbus", "A340"));
        listPlane.add(new Plane("Airbus", "A350"));
        listPlane.add(new Plane("Airbus", "A380"));
        listPlane.add(new Plane("Airbus", "A400M"));
        listPlane.add(new Plane("Airbus", "Beluga"));
        listPlane.add(new Plane("Airbus", "E-Fan"));
        listPlane.add(new Plane("Boeing", "Model B"));
        listPlane.add(new Plane("Boeing", "Model C"));
        listPlane.add(new Plane("Boeing", "Model 1"));
        listPlane.add(new Plane("Boeing", "Model 2"));
        listPlane.add(new Plane("Boeing", "Model 3"));
        listPlane.add(new Plane("Boeing", "Model 4"));
        listPlane.add(new Plane("Boeing", "Model 5"));
        listPlane.add(new Plane("Boeing", "Model 6"));
        listPlane.add(new Plane("Boeing", "Model 7"));
        listPlane.add(new Plane("Boeing", "Model 8"));
        listPlane.add(new Plane("Boeing", "10"));
        listPlane.add(new Plane("Boeing", "15"));
        listPlane.add(new Plane("Boeing", "21"));
        listPlane.add(new Plane("Boeing", "40"));
        listPlane.add(new Plane("Boeing", "42"));
        listPlane.add(new Plane("Boeing", "50"));
        listPlane.add(new Plane("Boeing", "53"));
        listPlane.add(new Plane("Boeing", "54"));
        listPlane.add(new Plane("Boeing", "55"));
        listPlane.add(new Plane("Boeing", "56"));
        listPlane.add(new Plane("Boeing", "57"));
        listPlane.add(new Plane("Boeing", "58"));
        listPlane.add(new Plane("Boeing", "63"));
        listPlane.add(new Plane("Boeing", "64"));
        listPlane.add(new Plane("Boeing", "66"));
        listPlane.add(new Plane("Boeing", "67"));
        listPlane.add(new Plane("Boeing", "69"));
        listPlane.add(new Plane("Boeing", "74"));
        listPlane.add(new Plane("Boeing", "77"));
        listPlane.add(new Plane("Boeing", "80"));
        listPlane.add(new Plane("Boeing", "81"));
        listPlane.add(new Plane("Boeing", "83"));
        listPlane.add(new Plane("Boeing", "89"));
        listPlane.add(new Plane("Boeing", "93"));
        listPlane.add(new Plane("Boeing", "95"));
        listPlane.add(new Plane("Boeing", "96"));
        listPlane.add(new Plane("Boeing", "97"));
        listPlane.add(new Plane("Boeing", "99"));
        listPlane.add(new Plane("Boeing", "100"));
        listPlane.add(new Plane("Boeing", "101"));
        listPlane.add(new Plane("Boeing", "102"));
        listPlane.add(new Plane("Boeing", "200"));
        listPlane.add(new Plane("Boeing", "202"));
        listPlane.add(new Plane("Boeing", "203"));
        listPlane.add(new Plane("Boeing", "204"));
        listPlane.add(new Plane("Boeing", "205"));
        listPlane.add(new Plane("Boeing", "209"));
        listPlane.add(new Plane("Boeing", "214"));
        listPlane.add(new Plane("Boeing", "215"));
        listPlane.add(new Plane("Boeing", "218"));
        listPlane.add(new Plane("Boeing", "221"));
        listPlane.add(new Plane("Boeing", "222"));
        listPlane.add(new Plane("Boeing", "223"));
        listPlane.add(new Plane("Boeing", "226"));
        listPlane.add(new Plane("Boeing", "227"));
        listPlane.add(new Plane("Boeing", "235"));
        listPlane.add(new Plane("Boeing", "236"));
        listPlane.add(new Plane("Boeing", "246"));
        listPlane.add(new Plane("Boeing", "247"));
        listPlane.add(new Plane("Boeing", "248"));
        listPlane.add(new Plane("Boeing", "251"));
        listPlane.add(new Plane("Boeing", "256"));
        listPlane.add(new Plane("Boeing", "264"));
        listPlane.add(new Plane("Boeing", "266"));
        listPlane.add(new Plane("Boeing", "267"));
        listPlane.add(new Plane("Boeing", "272"));
        listPlane.add(new Plane("Boeing", "273"));
        listPlane.add(new Plane("Boeing", "274"));
        listPlane.add(new Plane("Boeing", "276"));
        listPlane.add(new Plane("Boeing", "278"));
        listPlane.add(new Plane("Boeing", "281"));
        listPlane.add(new Plane("Boeing", "294"));
        listPlane.add(new Plane("Boeing", "299"));
        listPlane.add(new Plane("Boeing", "306"));
        listPlane.add(new Plane("Boeing", "307 Stratoliner / Stratofreighter / Strato-clipper / C-75"));
        listPlane.add(new Plane("Boeing", "314 Clipper"));
        listPlane.add(new Plane("Boeing", "316"));
        listPlane.add(new Plane("Boeing", "320"));
        listPlane.add(new Plane("Boeing", "322"));
        listPlane.add(new Plane("Boeing", "333"));
        listPlane.add(new Plane("Boeing", "334"));
        listPlane.add(new Plane("Boeing", "337"));
        listPlane.add(new Plane("Boeing", "341"));
        listPlane.add(new Plane("Boeing", "344"));
        listPlane.add(new Plane("Boeing", "345"));
        listPlane.add(new Plane("Boeing", "345-2"));
        listPlane.add(new Plane("Boeing", "367 Stratofreighter"));
        listPlane.add(new Plane("Boeing", "367-80"));
        listPlane.add(new Plane("Boeing", "377 Stratocruiser"));
        listPlane.add(new Plane("Boeing", "400"));
        listPlane.add(new Plane("Boeing", "424"));
        listPlane.add(new Plane("Boeing", "432"));
        listPlane.add(new Plane("Boeing", "448"));
        listPlane.add(new Plane("Boeing", "450"));
        listPlane.add(new Plane("Boeing", "451"));
        listPlane.add(new Plane("Boeing", "452"));
        listPlane.add(new Plane("Boeing", "464"));
        listPlane.add(new Plane("Boeing", "473"));
        listPlane.add(new Plane("Boeing", "474"));
        listPlane.add(new Plane("Boeing", "479"));
        listPlane.add(new Plane("Boeing", "701"));
        listPlane.add(new Plane("Boeing", "707"));
        listPlane.add(new Plane("Boeing", "717 Stratotanker"));
        listPlane.add(new Plane("Boeing", "717 (MD-95)"));
        listPlane.add(new Plane("Boeing", "720"));
        listPlane.add(new Plane("Boeing", "727"));
        listPlane.add(new Plane("Boeing", "737"));
        listPlane.add(new Plane("Boeing", "739"));
        listPlane.add(new Plane("Boeing", "747"));
        listPlane.add(new Plane("Boeing", "747-400"));
        listPlane.add(new Plane("Boeing", "747SP"));
        listPlane.add(new Plane("Boeing", "747-8"));
        listPlane.add(new Plane("Boeing", "757"));
        listPlane.add(new Plane("Boeing", "767"));
        listPlane.add(new Plane("Boeing", "E-767"));
        listPlane.add(new Plane("Boeing", "KC-767"));
        listPlane.add(new Plane("Boeing", "777"));
        listPlane.add(new Plane("Boeing", "787"));

        return listPlane;
    }
}
