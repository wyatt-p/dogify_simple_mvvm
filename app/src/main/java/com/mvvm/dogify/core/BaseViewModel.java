package com.mvvm.dogify.core;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base view model class housing common view model functions such
 * as housing and disposing of subscriptions.
 * @author Wyatt Paro
 */
public abstract class BaseViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable;

    public BaseViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
        clearReferences();
    }

    protected abstract void clearReferences();
}
