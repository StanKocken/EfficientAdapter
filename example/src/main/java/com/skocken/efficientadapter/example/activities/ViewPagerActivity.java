package com.skocken.efficientadapter.example.activities;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Book;
import com.skocken.efficientadapter.example.viewholders.BookViewHolder;
import com.skocken.efficientadapter.lib.adapter.EfficientAdapter;
import com.skocken.efficientadapter.lib.adapter.EfficientPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPager viewPager = new ViewPager(this);
        setContentView(viewPager);

        EfficientPagerAdapter<Book> adapter =
                new EfficientPagerAdapter<>(R.layout.item_book, BookViewHolder.class,
                        generateListOfPlane());

        viewPager.setAdapter(adapter);

        adapter.setOnItemLongClickListener(
                new EfficientAdapter.OnItemLongClickListener<Book>() {
                    @Override
                    public void onLongItemClick(EfficientAdapter<Book> adapter, View view,
                            Book object, int position) {
                        Toast.makeText(view.getContext(),
                                "Longclick on: " + object.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private List<Book> generateListOfPlane() {
        List<Book> objects = new ArrayList<>();
        objects.add(new Book("Lorem", "ipsum dolor sit amet",
                "consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit."));
        objects.add(new Book("Ut velit", "mauris",
                "egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna."));
        objects.add(new Book("Aliquam convallis", "sollicitudin purus",
                "Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet."));

        return objects;
    }

}
