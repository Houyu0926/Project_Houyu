package com.example.projecthouyu.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecthouyu.R;
import com.example.projecthouyu.Singletons;
import com.example.projecthouyu.presentation.controller.DetailController;


public class DetailActivity extends AppCompatActivity {

    private Button returnButton;
    private Button menuButton;
    private TextView textView;

    private DetailController detailController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailController = new DetailController(
                Singletons.getSharedPreferencesInstance(getApplicationContext()),
                Singletons.getGoonInstance(),
                this
        );
        detailController.onStart();
    }



    public void NoDetailInformation() {
        Toast.makeText(getApplicationContext(),"No detail information", Toast.LENGTH_SHORT).show();
    }


    public void showStatus(String status) {
        textView = (TextView) findViewById(R.id.detail_text);
        textView.setText(status);
    }

}