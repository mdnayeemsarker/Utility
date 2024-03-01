package com.abmn.utility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.abmn.utility.Internet.UConfig;
import com.abmn.utility.Internet.ProgressDisplay;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UConfig.isConnectedAlert(this, "", "");

        ProgressDisplay progressDisplay = new ProgressDisplay(this);

        progressDisplay.showProgress();

        UConfig.RequestToVolley((result, response) -> {
            Log.d("response", response);
        }, 0, this, "https://jsonplaceholder.typicode.com/todos/1", new HashMap<>(), true);
    }
}