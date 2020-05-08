package com.example.projecthouyu.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projecthouyu.Constants;
import com.example.projecthouyu.data.DogAPI;
import com.example.projecthouyu.R;
import com.example.projecthouyu.presentation.model.Dog;
import com.example.projecthouyu.presentation.model.RestDogResponse;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Dog> dogList = getDataFromCache();

        if(dogList != null){
            showList(dogList);
        }else {
            MakeAPICall();
        }

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


    private static final String BASE_URL = "https://raw.githubusercontent.com/Houyu0926/Project_Houyu/master/";

    private void MakeAPICall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DogAPI dogapi = retrofit.create(DogAPI.class);

        Call<RestDogResponse> call = dogapi.getBreedResponse();
        call.enqueue(new Callback<RestDogResponse>() {
            @Override
            public void onResponse(Call<RestDogResponse> call, Response<RestDogResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Dog> dogList = response.body().getMessages();
                    Toast.makeText(getApplicationContext(),"API success", Toast.LENGTH_SHORT).show();
                    saveList(dogList);
                    showList(dogList);
                }else{
                    error();
                }
            }

            @Override
            public void onFailure(Call<RestDogResponse> call, Throwable t) {
                error();
            }
        });
    }

    private void saveList(List<Dog> dogList) {
        String jsonString = gson.toJson(dogList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_DOG_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(),"Dog List Saved", Toast.LENGTH_SHORT).show();

    }

    private void error() {
        Toast.makeText(getApplicationContext(),"API error", Toast.LENGTH_SHORT).show();
    }


    private void showList(List<Dog> dogList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(dogList);
        recyclerView.setAdapter(mAdapter);

    }
}
