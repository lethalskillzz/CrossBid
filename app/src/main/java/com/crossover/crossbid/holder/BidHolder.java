package com.crossover.crossbid.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crossover.crossbid.R;
import com.crossover.crossbid.app.AppConfig;
import com.crossover.crossbid.model.BidItem;

import java.util.List;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class BidHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private List<BidItem> bidItems;

    public ImageView bid_image;
    public TextView bid_title;
    public TextView bid_btn;
    public TextView bid_count;
    public TextView bid_price;

    public BidHolder(View convertView, List<BidItem> bidItems, int viewType) {
        super(convertView);
        this.bidItems = bidItems;

        itemView.setOnClickListener(this);


        bid_image = (ImageView) convertView
                .findViewById(R.id.bid_img);
        bid_title = (TextView) convertView
                .findViewById(R.id.bid_title);

        bid_count = (TextView) convertView
                .findViewById(R.id.bid_count);
        bid_price = (TextView) convertView
                .findViewById(R.id.bid_price);

        if (viewType != AppConfig.VIEW_BID_WON) {

            bid_btn = (TextView) convertView
                    .findViewById(R.id.bid_btn);

            bid_btn.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bid_btn:

                break;
        }
    }

}
