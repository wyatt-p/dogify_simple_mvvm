package com.mvvm.dogify.dogifyapp;

import com.mvvm.dogify.dogifyapp.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Main application, created and used for dagger injection assistance
 * @author Wyatt Paro
 */
public class DogifyApp extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }
}
