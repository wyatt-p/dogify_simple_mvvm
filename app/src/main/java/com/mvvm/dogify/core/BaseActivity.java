package com.mvvm.dogify.core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Base activity used to house common activity functions like getting layout and creating data
 * binder
 * @author Wyatt Paro
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {
    private ViewDataBinding binding;

    @LayoutRes
    public abstract int getViewLayoutID();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getViewLayoutID());
    }

    protected ViewDataBinding binding() {
        return binding;
    }
}
