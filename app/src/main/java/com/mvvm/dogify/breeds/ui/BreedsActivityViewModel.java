package com.mvvm.dogify.breeds.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mvvm.dogify.breeds.repository.BreedRepository;
import com.mvvm.dogify.core.BaseViewModel;

/**
 * View model for {@link BreedsActivity}. View model knows nothing of UI, but will post
 * LiveData to subscribers (from UI). Additionally, subscribes to Rx observables (given
 * rep) to listen/send data from/to model.
 * @author Wyatt Paro
 */
public class BreedsActivityViewModel extends BaseViewModel {

    private BreedRepository breedRepository;
    MutableLiveData<Map<String, String>> breedMap;

    MutableLiveData<List<String>> breedList;
    MutableLiveData failedFetch;

    private Map<String, String> breedImages;

    public BreedsActivityViewModel(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
        breedMap = new MutableLiveData<>();
        breedList = new MutableLiveData<>();
        failedFetch = new MutableLiveData();
        breedImages = new HashMap<>();

        subscribeToData();
    }

    private void subscribeToData() {
        addDisposable(breedRepository.getBreedNames().retry().subscribe(list -> {
            breedList.postValue(list);
        }));

        addDisposable(breedRepository.getFailure().subscribe(error -> {
            failedFetch.postValue(true);
        }));
    }

    public String getDogBreed(Integer index) {
        String breed = null;
        if (index != null && index < breedList.getValue().size()) {
            breed = breedList.getValue().get(index);
        }

        return breed;
    }

    public void refreshBreedList() {
        breedImages.clear();
        breedMap.postValue(breedImages);
        breedRepository.refreshDogBreeds();
    }


    @Override
    protected void clearReferences() {
        breedRepository = null;
    }

    public void fetchImageURL(final Integer breedIndex) {
        if (breedIndex != null && breedIndex < breedList.getValue().size()) {
            final String breedName = breedList.getValue().get(breedIndex);

            if (breedName != null && breedMap.getValue().get(breedName) == null) {
                breedRepository.getBreedImage(breedName).subscribe(item -> {
                            breedImages.put(breedName, item);
                            breedMap.postValue(breedImages);
                        });
            }
        }
    }

    public LiveData<Map<String, String>> getBreedMap() {
        return breedMap;
    }

    public LiveData<List<String>> getBreedList() {
        return breedList;
    }

    public LiveData getFailedFetchedData() {
        return failedFetch;
    }
}
