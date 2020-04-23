package com.mvvm.dogify.breeds.ui.di;

import android.arch.lifecycle.ViewModel;

import com.mvvm.dogify.breeds.repository.BreedRepository;
import com.mvvm.dogify.breeds.ui.BreedsActivityViewModel;
import com.mvvm.dogify.core.di.ViewModelKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Breeds module provides dependencies for som specific members of {@link com.mvvm.dogify.breeds.ui.BreedsActivity}
 */
@Module
public abstract class BreedsModule {

    private BreedsModule() {
        throw new IllegalStateException("Public constructor is auto generated");
    }

    @Provides
    @IntoMap
    @ViewModelKey(BreedsActivityViewModel.class)
    public static ViewModel provideBreedsActivityViewModel(BreedRepository breedRepository) {
        return new BreedsActivityViewModel(breedRepository);
    }

}
