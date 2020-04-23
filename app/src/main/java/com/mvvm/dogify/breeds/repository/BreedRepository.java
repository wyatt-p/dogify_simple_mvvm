package com.mvvm.dogify.breeds.repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Repo IF used to define the model
 * @author Wyatt Paro
 */
public interface BreedRepository {
    public Observable<List<String>> getBreedNames();

    public Observable<String> getBreedImage(final String breedName);

    public Observable getFailure();

    public void refreshDogBreeds();
}
