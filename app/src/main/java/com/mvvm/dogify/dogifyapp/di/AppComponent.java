package com.mvvm.dogify.dogifyapp.di;

import android.app.Application;

import javax.inject.Singleton;

import com.mvvm.dogify.breeds.component.GlobalModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Dagger component used for DI, includes android supported modules as well as modules from
 * mock/prod areas (depending on build variant used)
 * @author Wyatt Paro
 */
@Singleton
@Component(modules = {
        GlobalModule.class,
        AppBuilder.class,
        AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Application application);
    }
}
