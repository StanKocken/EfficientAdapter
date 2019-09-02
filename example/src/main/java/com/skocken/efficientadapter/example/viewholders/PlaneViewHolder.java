package com.skocken.efficientadapter.example.viewholders;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Plane;
import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;

public class PlaneViewHolder extends EfficientViewHolder<Plane> {

    public PlaneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void updateView(@NonNull Context context, Plane item) {
        setText(R.id.manufacturer_textview, item.getManufacturer());
        setText(R.id.model_textview, item.getModel());
    }

    @Override
    public boolean isClickable() {
        return true;
    }
}
