package com.mvvm.dogify.breeds.repository;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import com.mvvm.dogify.breeds.entities.ImageResult;
import com.mvvm.dogify.breeds.entities.NameResult;
import com.mvvm.dogify.breeds.ui.DogService;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Production breed repo, inheriting base {@link com.mvvm.dogify.breeds.repository.BreedRepository}.
 * Used in production environment
 * @author Wyatt Paro
 */
public class ProdBreedRepository implements BreedRepository {
    private DogService dogService;

    private BehaviorSubject<List<String>> breedNamesObservable;
    private BehaviorSubject failureObservable;

    public ProdBreedRepository(DogService dogService) {
        this.dogService = dogService;
        breedNamesObservable = BehaviorSubject.create();
        failureObservable = BehaviorSubject.create();
    }

    @Override
    public Observable<List<String>> getBreedNames() {
        return breedNamesObservable;
    }

    @Override
    public Observable getFailure() {
        return failureObservable;
    }

    @Override
    public Observable<String> getBreedImage(final String breedName) {
        return Observable.fromCallable(() -> {
            String imageUrl = null;
            if (dogService != null) {
                ImageResult result = dogService.getImage(breedName).execute().body();
                if (result != null && result.message != null) {
                    imageUrl = result.message;
                }
            }
            return imageUrl;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public void refreshDogBreeds() {
        Completable.fromAction(() -> {
            List<String> breedNames = new ArrayList<>();

            if (dogService != null) {
                NameResult result = dogService.getBreeds().execute().body();
                if (result != null && result.message != null) {
                    breedNames = result.message;
                }
            }
            breedNamesObservable.onNext(breedNames);
        }).subscribeOn(Schedulers.io()).subscribe(() -> {},
                error -> failureObservable.onNext(error));
    }
}