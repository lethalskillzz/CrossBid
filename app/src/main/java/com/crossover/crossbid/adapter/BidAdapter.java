package com.crossover.crossbid.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crossover.crossbid.R;
import com.crossover.crossbid.app.AppConfig;
import com.crossover.crossbid.holder.BidHolder;
import com.crossover.crossbid.holder.ProgressViewHolder;
import com.crossover.crossbid.model.BidItem;

import java.util.List;

/**
 * Created by Ibrahim on 10/06/2016.
 */
public class BidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BidItem> bidItems;
    private Context context;

    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;


    public BidAdapter(Context context, RecyclerView recyclerView, List<BidItem> bidItems) {

        this.bidItems = bidItems;
        this.context = context;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {

        int viewType = 0;
        if (bidItems.get(position) != null) {

            switch (bidItems.get(position).getType()) {

                case AppConfig.VIEW_BID_ALL:
                    viewType = AppConfig.VIEW_BID_ALL;
                    break;

                case AppConfig.VIEW_BID_MY:
                    viewType = AppConfig.VIEW_BID_MY;
                    break;

                case AppConfig.VIEW_BID_WON:
                    viewType = AppConfig.VIEW_BID_WON;
                    break;

            }
        } else {
            viewType = AppConfig.VIEW_PROG;
        }
        return viewType;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh = null;
        if (viewType == AppConfig.VIEW_PROG) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loading_footer_item, parent, false);

            vh = new ProgressViewHolder(v);
        }else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_bid_row, parent, false);

            vh = new BidHolder(v, bidItems, viewType);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BidHolder) {

            BidItem item = bidItems.get(position);

            ((BidHolder) holder).bid_title.setText(item.getTitle());
            ((BidHolder) holder).bid_price.setText(item.getPrice());
            ((BidHolder) holder).bid_count.setText(item.getCount());

            if (item.getType() == AppConfig.VIEW_BID_WON) {
                ((BidHolder) holder).bid_btn.setVisibility(View.GONE);
            } else {
                ((BidHolder) holder).bid_btn.setVisibility(View.VISIBLE);
                if(item.getIsBid()) {
                    ((BidHolder) holder).bid_btn.setText(R.string.btn_unbid);
                    ((BidHolder) holder).bid_btn.setBackgroundResource(R.drawable.btn_blue);
                } else {
                    ((BidHolder) holder).bid_btn.setText(R.string.btn_bid);
                    ((BidHolder) holder).bid_btn.setBackgroundResource(R.drawable.btn_green);
                }

            }

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }

    }


    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return this.bidItems.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}
