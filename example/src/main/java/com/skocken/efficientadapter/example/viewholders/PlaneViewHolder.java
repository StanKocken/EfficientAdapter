package com.skocken.efficientadapter.example.viewholders;

import com.skocken.efficientadapter.example.R;
import com.skocken.efficientadapter.example.models.Plane;
import com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class PlaneViewHolder extends EfficientViewHolder<Plane> {

    public PlaneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void updateView(Context context, Plane object) {
        ((TextView) findViewByIdEfficient(R.id.manufacturer_textview)).setText(
                object.getManufacturer());
        ((TextView) findViewByIdEfficient(R.id.model_textview)).setText(
                object.getModel());

    }

    @Override
    public boolean isClickable() {
        return true;
    }
}
