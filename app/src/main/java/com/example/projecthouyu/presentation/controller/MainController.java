package com.example.projecthouyu.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projecthouyu.Constants;
import com.example.projecthouyu.data.DogAPI;
import com.example.projecthouyu.presentation.model.Dog;
import com.example.projecthouyu.presentation.model.RestDogResponse;
import com.example.projecthouyu.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(SharedPreferences sharedPreferences, Gson gson, MainActivity view) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
        this.view = view;
    }

    public void onStart(){

        List<Dog> dogList = getDataFromCache();

        if(dogList != null){
            view.showList(dogList);
        }else {
            MakeAPICall();
        }

    }

    private void MakeAPICall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DogAPI dogapi = retrofit.create(DogAPI.class);

        Call<RestDogResponse> call = dogapi.getBreedResponse();
        call.enqueue(new Callback<RestDogResponse>() {
            @Override
            public void onResponse(Call<RestDogResponse> call, Response<RestDogResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Dog> dogList = response.body().getMessages();
                    Toast.makeText(view.getApplicationContext(),"API success", Toast.LENGTH_SHORT).show();
                    saveList(dogList);
                    view.showList(dogList);
                }else{
                    view.error();
                }
            }

            @Override
            public void onFailure(Call<RestDogResponse> call, Throwable t) {
                view.error();
            }
        });
    }

    private void saveList(List<Dog> dogList) {
        String jsonString = gson.toJson(dogList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_DOG_LIST, jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(),"Dog List Saved", Toast.LENGTH_SHORT).show();

    }

    private List<Dog> getDataFromCache() {
        String jsonDog = sharedPreferences.getString(Constants.KEY_DOG_LIST, null);

        if(jsonDog == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Dog>>(){}.getType();
            return gson.fromJson(jsonDog, listType);
        }
    }

    public void onItemClick(Dog dog){

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
