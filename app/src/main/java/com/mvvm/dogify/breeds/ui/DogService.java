package com.mvvm.dogify.breeds.ui;
import com.mvvm.dogify.breeds.entities.ImageResult;
import com.mvvm.dogify.breeds.entities.NameResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit dog service (provided)
 * @author Wyatt Paro
 */
public interface DogService
{
    @GET("breeds/list")
    Call<NameResult> getBreeds();

    @GET("breed/{name}/images/random")
    Call<ImageResult> getImage(@Path("name") String name);
}
