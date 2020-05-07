package com.example.projecthouyu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DogAPI {
    @GET("myAPI.json")
    Call<RestDogResponse> getBreedResponse();
}
