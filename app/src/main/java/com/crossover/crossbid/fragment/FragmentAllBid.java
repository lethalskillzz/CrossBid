package com.crossover.crossbid.fragment;

/**
 * Created by Ibrahim on 08/06/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crossover.crossbid.R;
import com.crossover.crossbid.adapter.BidAdapter;
import com.crossover.crossbid.app.PrefManager;
import com.crossover.crossbid.helper.DBHelper;
import com.crossover.crossbid.model.BidItem;

import java.util.List;

public class FragmentAllBid extends Fragment {

    private DBHelper db;
    private PrefManager pref;

    private RecyclerView rView;
    private List<BidItem> bidItems;
    private BidAdapter bidAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isRefreshing = false;


    public FragmentAllBid() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bid_all, container, false);

        db = new DBHelper(getActivity());
        pref = new PrefManager(getActivity());

        LinearLayoutManager lLayout = new LinearLayoutManager(getActivity());
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.all_bid_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        rView = (RecyclerView) rootView.findViewById(R.id.all_bid_recycler_view);
        rView.setLayoutManager(lLayout);
        rView.setHasFixedSize(true);

        setBidAdapter();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }


    private void setBidAdapter() {
        bidAdapter = new BidAdapter(getActivity(), rView, bidItems);
        rView.setAdapter(bidAdapter);

        bidAdapter.setOnLoadMoreListener(new BidAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (!isRefreshing) {
                    //add progress item
                    bidItems.add(null);
                    bidAdapter.notifyItemInserted(bidItems.size() - 1);


                }
            }
        });
        bidItems = db.getAllBids(Integer.parseInt(pref.getUserId()));
    }



}