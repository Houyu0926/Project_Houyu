package com.example.projecthouyu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        showList();
        MakeAPICall();

        }


    private static final String BASE_URL = "https://raw.githubusercontent.com/Houyu0926/Project_Houyu/master/";

    private void MakeAPICall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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
