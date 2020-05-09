package com.example.projecthouyu.presentation.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthouyu.R;
import com.example.projecthouyu.presentation.controller.DetailController;
import com.example.projecthouyu.presentation.model.Dog;

import java.util.List;


public class DetailActivity extends AppCompatActivity {

    private ListAdapter listAdapter;
    private MainActivity mainActivity;
    private String detailInformation;
    private TextView textView;
    private Dog dog;
    private DetailController detailController;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailController.onStart();
        }


    public void NoDetailInformation() {
        Toast.makeText(getApplicationContext(),"No detail information", Toast.LENGTH_SHORT).show();
    }


    public void showStatus(String status) {
        textView = (TextView)findViewById(R.id.detail_text);
        textView.setText(status);
    }

}
