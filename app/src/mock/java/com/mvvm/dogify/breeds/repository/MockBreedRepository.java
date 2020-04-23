package com.mvvm.dogify.breeds.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Mock repo, will be injected if running in mock mode. Useful when debugging without production
 * ready model in place. Useful for testing.
 *
 * Note: testing not implement, so this class is more of a placeholder for now
 * @author Wyatt Paro
 */
@Singleton
public class MockBreedRepository implements BreedRepository {

    @Inject
    public MockBreedRepository() {
    }

    @Override
    public Observable<List<String>> getBreedNames() {
        return Observable.empty();
    }

    @Override
    public Observable<String> getBreedImage(String breedName) {
        return Observable.empty();
    }

    @Override
    public Observable getFailure() {
        return Observable.empty();
    }

    @Override
    public void refreshDogBreeds() {
        //TBD
    }
}