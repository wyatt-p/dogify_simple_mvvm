package com.mvvm.dogify.breeds.ui.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

import com.mvvm.dogify.BR;
import com.mvvm.dogify.R;
import com.mvvm.dogify.breeds.ui.BreedsActivityViewModel;

/**
 * Adapter for recycler view. Uses view model context from activity and
 * data binding for view updates.
 * @author Wyatt Paro
 */
public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.ViewHolder> {
    private List<String> data;
    private BreedsActivityViewModel viewModel;
    private LifecycleOwner owner;

    public BreedsAdapter(BreedsActivityViewModel viewModel, LifecycleOwner owner) {
        this.viewModel = viewModel;
        this.owner = owner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_breed;
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public void setBreedData(List<String> data) {
        this.data = data;
    }

    /**
     * View holder for adapter, note that binding is used here so that we may
     * re-apply UI/data binding given view creation and re-use
     * @author Wyatt Paro
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setLifecycleOwner(owner);
        }

        public void bind(BreedsActivityViewModel viewModel, Integer position) {
            viewModel.fetchImageURL(position);
            binding.setVariable(BR.index, position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.executePendingBindings();
        }
    }
}
