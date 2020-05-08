package com.example.projecthouyu.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecthouyu.R;
import com.example.projecthouyu.presentation.controller.MainController;


public class DetailActivity extends AppCompatActivity {

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        }
}
