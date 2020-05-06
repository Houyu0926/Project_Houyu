package com.example.projecthouyu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NBATeams team1 = new NBATeams("OKC","Oklahoma City Thunder", "Billy Donovan", 1967, 1);

    }
}
