package com.fflorio.smaato_acc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fflorio.smaatoacclibrary.view.SmartDataItemView;

public class MainActivity extends AppCompatActivity{

    private SmartDataItemView smartDataItemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smartDataItemView = findViewById(R.id.smartDataItemView);
        if(savedInstanceState == null) {
            smartDataItemView.loadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
