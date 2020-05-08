package com.example.projecthouyu.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projecthouyu.Constants;
import com.example.projecthouyu.R;
import com.example.projecthouyu.Singletons;
import com.example.projecthouyu.presentation.controller.MainController;
import com.example.projecthouyu.presentation.model.Dog;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                Singletons.getSharedPreferencesInstance(getApplicationContext()),
                Singletons.getGsonInstance(),
                this
        );

        controller.onStart();

        }



    public void error() {
        Toast.makeText(getApplicationContext(),"API error", Toast.LENGTH_SHORT).show();
    }


    public void showList(List<Dog> dogList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(dogList);
        recyclerView.setAdapter(mAdapter);

    }
}
