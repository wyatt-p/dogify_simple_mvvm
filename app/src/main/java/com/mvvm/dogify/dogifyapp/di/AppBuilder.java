package com.mvvm.dogify.dogifyapp.di;

import android.arch.lifecycle.ViewModelProvider;

import com.mvvm.dogify.breeds.ui.BreedsActivity;
import com.mvvm.dogify.breeds.ui.di.BreedsModule;
import com.mvvm.dogify.core.di.ViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Dagger module for main app builder
 */
@Module
public abstract class AppBuilder {

    @ContributesAndroidInjector(modules = {BreedsModule.class})
    abstract BreedsActivity bindBreedsActivity();

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

}
