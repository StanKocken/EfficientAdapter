package com.skocken.efficientadapter.example.activities;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Book;
import com.skocken.efficientadapter.example.models.Plane;
import com.skocken.efficientadapter.example.viewholders.BookViewHolder;
import com.skocken.efficientadapter.example.viewholders.PlaneViewHolder;
import com.skocken.efficientadapter.lib.adapter.AbsViewHolderAdapter;
import com.skocken.efficientadapter.lib.adapter.HeterogeneousAdapter;
import com.skocken.efficientadapter.lib.viewholder.AbsViewHolder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HeterogeneousListActivity extends Activity {

    private static final int VIEW_TYPE_BOOK = 0;

    private static final int VIEW_TYPE_PLANE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(false);
        setContentView(recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        HeterogeneousAdapter adapter = new HeterogeneousAdapter(generateListObjects()) {
            @Override
            public int getItemViewType(int position) {
                if (get(position) instanceof Plane) {
                    return VIEW_TYPE_PLANE;
                } else {
                    return VIEW_TYPE_BOOK;
                }
            }

            @Override
            protected Class<? extends AbsViewHolder> getViewHolderClass(int viewType) {
                switch (viewType) {
                    case VIEW_TYPE_BOOK:
                        return BookViewHolder.class;
                    case VIEW_TYPE_PLANE:
                        return PlaneViewHolder.class;
                    default:
                        return null;
                }
            }

            @Override
            protected int getLayoutResId(int viewType) {
                switch (viewType) {
                    case VIEW_TYPE_BOOK:
                        return R.layout.item_book;
                    case VIEW_TYPE_PLANE:
                        return R.layout.item_plane;
                    default:
                        return 0;
                }
            }
        };

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AbsViewHolderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AbsViewHolderAdapter parent, View view, Object object,
                    int position) {
                Toast.makeText(view.getContext(),
                        "Click on: " + object.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Object> generateListObjects() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(new Plane("Airbus", "A300"));
        objects.add(new Plane("Airbus", "A310"));
        objects.add(new Book("Lorem", "ipsum dolor sit amet", "consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit."));
        objects.add(new Plane("Airbus", "A310 MRTT"));
        objects.add(new Plane("Airbus", "CC-150 Polaris Canadian Armed Forces"));
        objects.add(new Plane("Airbus", "A318"));
        objects.add(new Plane("Airbus", "A319"));
        objects.add(new Plane("Airbus", "A319CJ"));
        objects.add(new Plane("Airbus", "A320"));
        objects.add(new Plane("Airbus", "A321"));
        objects.add(new Plane("Airbus", "A330"));
        objects.add(new Plane("Airbus", "A330 MRTT"));
        objects.add(new Plane("Airbus", "A340"));
        objects.add(new Plane("Airbus", "A350"));
        objects.add(new Plane("Airbus", "A380"));
        objects.add(new Plane("Airbus", "A400M"));
        objects.add(new Plane("Airbus", "Beluga"));
        objects.add(new Plane("Airbus", "E-Fan"));
        objects.add(new Book("Ut velit", "mauris", "egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna."));
        objects.add(new Plane("Boeing", "Model B"));
        objects.add(new Plane("Boeing", "Model C"));
        objects.add(new Plane("Boeing", "Model 1"));
        objects.add(new Plane("Boeing", "Model 2"));
        objects.add(new Plane("Boeing", "Model 3"));
        objects.add(new Plane("Boeing", "Model 4"));
        objects.add(new Plane("Boeing", "Model 5"));
        objects.add(new Plane("Boeing", "Model 6"));
        objects.add(new Plane("Boeing", "Model 7"));
        objects.add(new Plane("Boeing", "Model 8"));
        objects.add(new Plane("Boeing", "10"));
        objects.add(new Plane("Boeing", "15"));
        objects.add(new Plane("Boeing", "21"));
        objects.add(new Plane("Boeing", "40"));
        objects.add(new Plane("Boeing", "42"));
        objects.add(new Plane("Boeing", "50"));
        objects.add(new Plane("Boeing", "53"));
        objects.add(new Plane("Boeing", "54"));
        objects.add(new Plane("Boeing", "55"));
        objects.add(new Book("Aliquam convallis", "sollicitudin purus", "Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet."));
        objects.add(new Plane("Boeing", "56"));
        objects.add(new Plane("Boeing", "57"));
        objects.add(new Plane("Boeing", "58"));
        objects.add(new Plane("Boeing", "63"));
        objects.add(new Plane("Boeing", "64"));
        objects.add(new Plane("Boeing", "66"));
        objects.add(new Plane("Boeing", "67"));
        objects.add(new Plane("Boeing", "69"));
        objects.add(new Plane("Boeing", "74"));
        objects.add(new Plane("Boeing", "77"));
        objects.add(new Plane("Boeing", "80"));
        objects.add(new Plane("Boeing", "81"));
        objects.add(new Plane("Boeing", "83"));
        objects.add(new Plane("Boeing", "89"));
        objects.add(new Plane("Boeing", "93"));
        objects.add(new Plane("Boeing", "95"));
        objects.add(new Plane("Boeing", "96"));
        objects.add(new Plane("Boeing", "97"));
        objects.add(new Plane("Boeing", "99"));
        objects.add(new Plane("Boeing", "100"));
        objects.add(new Plane("Boeing", "101"));
        objects.add(new Plane("Boeing", "102"));
        objects.add(new Plane("Boeing", "200"));
        objects.add(new Plane("Boeing", "202"));
        objects.add(new Plane("Boeing", "203"));
        objects.add(new Plane("Boeing", "204"));
        objects.add(new Plane("Boeing", "205"));
        objects.add(new Plane("Boeing", "209"));
        objects.add(new Plane("Boeing", "214"));
        objects.add(new Plane("Boeing", "215"));
        objects.add(new Plane("Boeing", "218"));
        objects.add(new Plane("Boeing", "221"));
        objects.add(new Plane("Boeing", "222"));
        objects.add(new Plane("Boeing", "223"));
        objects.add(new Plane("Boeing", "226"));
        objects.add(new Plane("Boeing", "227"));
        objects.add(new Plane("Boeing", "235"));
        objects.add(new Plane("Boeing", "236"));
        objects.add(new Plane("Boeing", "246"));
        objects.add(new Plane("Boeing", "247"));
        objects.add(new Plane("Boeing", "248"));
        objects.add(new Plane("Boeing", "251"));
        objects.add(new Plane("Boeing", "256"));
        objects.add(new Plane("Boeing", "264"));
        objects.add(new Plane("Boeing", "266"));
        objects.add(new Plane("Boeing", "267"));
        objects.add(new Plane("Boeing", "272"));
        objects.add(new Plane("Boeing", "273"));
        objects.add(new Plane("Boeing", "274"));
        objects.add(new Plane("Boeing", "276"));
        objects.add(new Plane("Boeing", "278"));
        objects.add(new Plane("Boeing", "281"));
        objects.add(new Plane("Boeing", "294"));
        objects.add(new Plane("Boeing", "299"));
        objects.add(new Plane("Boeing", "306"));
        objects.add(new Plane("Boeing",
                "307 Stratoliner / Stratofreighter / Strato-clipper / C-75"));
        objects.add(new Plane("Boeing", "314 Clipper"));
        objects.add(new Plane("Boeing", "316"));
        objects.add(new Plane("Boeing", "320"));
        objects.add(new Plane("Boeing", "322"));
        objects.add(new Plane("Boeing", "333"));
        objects.add(new Plane("Boeing", "334"));
        objects.add(new Plane("Boeing", "337"));
        objects.add(new Plane("Boeing", "341"));
        objects.add(new Plane("Boeing", "344"));
        objects.add(new Plane("Boeing", "345"));
        objects.add(new Plane("Boeing", "345-2"));
        objects.add(new Plane("Boeing", "367 Stratofreighter"));
        objects.add(new Plane("Boeing", "367-80"));
        objects.add(new Plane("Boeing", "377 Stratocruiser"));
        objects.add(new Plane("Boeing", "400"));
        objects.add(new Plane("Boeing", "424"));
        objects.add(new Plane("Boeing", "432"));
        objects.add(new Plane("Boeing", "448"));
        objects.add(new Plane("Boeing", "450"));
        objects.add(new Plane("Boeing", "451"));
        objects.add(new Plane("Boeing", "452"));
        objects.add(new Plane("Boeing", "464"));
        objects.add(new Plane("Boeing", "473"));
        objects.add(new Plane("Boeing", "474"));
        objects.add(new Plane("Boeing", "479"));
        objects.add(new Plane("Boeing", "701"));
        objects.add(new Plane("Boeing", "707"));
        objects.add(new Plane("Boeing", "717 Stratotanker"));
        objects.add(new Plane("Boeing", "717 (MD-95)"));
        objects.add(new Plane("Boeing", "720"));
        objects.add(new Plane("Boeing", "727"));
        objects.add(new Plane("Boeing", "737"));
        objects.add(new Plane("Boeing", "739"));
        objects.add(new Plane("Boeing", "747"));
        objects.add(new Plane("Boeing", "747-400"));
        objects.add(new Plane("Boeing", "747SP"));
        objects.add(new Plane("Boeing", "747-8"));
        objects.add(new Plane("Boeing", "757"));
        objects.add(new Plane("Boeing", "767"));
        objects.add(new Plane("Boeing", "E-767"));
        objects.add(new Plane("Boeing", "KC-767"));
        objects.add(new Plane("Boeing", "777"));
        objects.add(new Plane("Boeing", "787"));

        return objects;
    }
}
