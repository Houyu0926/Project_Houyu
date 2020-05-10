package com.example.projecthouyu.presentation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthouyu.R;
import com.example.projecthouyu.Singletons;
import com.example.projecthouyu.presentation.controller.MainController;
import com.example.projecthouyu.presentation.model.Dog;

import java.util.List;

@SuppressLint("Registered")
public class DogListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doglist);

        mainController = new MainController(
                Singletons.getSharedPreferencesInstance(getApplicationContext()),
                Singletons.getGoonInstance(),
                this
        );
        mainController.onStart();
    }

    public void error() {
        Toast.makeText(getApplicationContext(),"API error", Toast.LENGTH_SHORT).show();
    }


    public void showList(List<Dog> dogList){
         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

         mAdapter = new ListAdapter(dogList, getApplicationContext(), new ListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Dog item) {
                mainController.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void navigateToDetails(Dog dog) {
        Intent myIntent = new Intent(DogListActivity.this, DetailActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        DogListActivity.this.startActivity(myIntent);

        Toast.makeText(getApplicationContext(),dog.getBreed(), Toast.LENGTH_SHORT).show();
    }


}