package com.example.projecthouyu.data;

import com.example.projecthouyu.presentation.model.RestDogResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogAPI {
    @GET("dog.json")
    Call<RestDogResponse> getBreedResponse();
}
