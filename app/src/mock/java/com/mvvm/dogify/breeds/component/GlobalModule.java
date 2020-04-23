package com.mvvm.dogify.breeds.component;

import javax.inject.Singleton;

import com.mvvm.dogify.breeds.repository.BreedRepository;
import com.mvvm.dogify.breeds.repository.MockBreedRepository;
import dagger.Binds;
import dagger.Module;

/**
 * Global module for MOCK data.
 * @author Wyatt Paro
 */
@Module
public abstract class GlobalModule {

    @Singleton
    @Binds
    abstract BreedRepository bindBreedRepository(MockBreedRepository mockBreedRepository);
}
