package com.crossover.crossbid.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crossover.crossbid.model.BidItem;

import java.util.List;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private List<BidItem> bidItems;
    private final int VIEW_ITEM_STORE = 4;
    private final int VIEW_ITEM_SHOP = 3;
    private final int VIEW_ITEM_DIGEST = 2;
    private final int VIEW_ITEM_REPO = 1;

    public TextView grid_title;
    public ImageView grid_image;

}
