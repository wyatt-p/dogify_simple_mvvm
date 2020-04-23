package com.mvvm.dogify.breeds.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import com.mvvm.dogify.R;
import com.mvvm.dogify.breeds.ui.adapters.BreedsAdapter;
import com.mvvm.dogify.core.BaseActivity;

/**
 * Main activity (View). Houses all UI logic.
 * @author Wyatt Paro
 */
public class BreedsActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    RecyclerView breedsRecycler;
    SwipeRefreshLayout swipeRefresh;
    BreedsAdapter breedsAdapter;

    @Override
    public int getViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BreedsActivityViewModel breedsActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedsActivityViewModel.class);

        setupRecyclerView(breedsActivityViewModel);
        subscribeToData(breedsActivityViewModel);

        if(savedInstanceState == null) {
            refreshDogBreeds(breedsActivityViewModel);
        }
    }

    private void subscribeToData(BreedsActivityViewModel viewModel) {
        viewModel.getBreedList().observe(this, data -> {
            swipeRefresh.setRefreshing(false);
            breedsAdapter.setBreedData(data);
            breedsAdapter.notifyDataSetChanged();
        });

        viewModel.getFailedFetchedData().observe(this, data -> {
            swipeRefresh.setRefreshing(false);
        });
    }

    private void setupRecyclerView(final BreedsActivityViewModel breedsActivityViewModel) {
        breedsAdapter = new BreedsAdapter(breedsActivityViewModel, this);
        breedsRecycler = binding().getRoot().findViewById(R.id.breed_list);
        breedsRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        breedsRecycler.setAdapter(breedsAdapter);

        swipeRefresh = binding().getRoot().findViewById(R.id.swiper_breed_refresh);
        swipeRefresh.setOnRefreshListener(() -> {
            refreshDogBreeds(breedsActivityViewModel);
        });
    }

    private void refreshDogBreeds(BreedsActivityViewModel viewModel) {
        swipeRefresh.setRefreshing(true);
        breedsAdapter.setBreedData(null);
        breedsAdapter.notifyDataSetChanged();
        viewModel.refreshBreedList();
    }

    @Override
    protected void onDestroy() {
        swipeRefresh = null;
        breedsRecycler = null;
        super.onDestroy();
    }
}
