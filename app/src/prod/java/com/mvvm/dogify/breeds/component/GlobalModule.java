package com.mvvm.dogify.breeds.component;

import javax.inject.Singleton;

import com.mvvm.dogify.breeds.repository.BreedRepository;
import com.mvvm.dogify.breeds.repository.ProdBreedRepository;
import com.mvvm.dogify.breeds.ui.DogService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Global module for PROD data
 * @author Wyatt Paro
 */
@Module
public abstract class GlobalModule {

    @Singleton
    @Provides
    public static BreedRepository provideBreedRepository(DogService service) {
        return new ProdBreedRepository(service);
    }

    @Singleton
    @Provides
    public static DogService provideDogService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        DogService service = retrofit.create(DogService.class);

        return service;
    }
}
