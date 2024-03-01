package com.abmn.utility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.abmn.utility.Internet.UConfig;
import com.abmn.utility.Internet.ProgressDisplay;
import com.abmn.utility.SharedPref.USession;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UConfig.isConnectedAlert(this, "", "");

        ProgressDisplay progressDisplay = new ProgressDisplay(this);

        progressDisplay.showProgress();

        UConfig.RequestToVolley((result, response) -> Log.d("response", response), 0, this, "https://jsonplaceholder.typicode.com/todos/1", new HashMap<>(), true);

        USession uSession = new USession(this);
        uSession.setData("string", "Utility By MD NAYEEM SARKER");
        Log.d("uSession String: ", uSession.getData("string"));
        uSession.setInteger("int", 150);
        Log.d("uSession integer: ", String.valueOf(uSession.getInteger("int")));
        uSession.setBoolean("bool", true);
        Log.d("uSession bool: ", String.valueOf(uSession.getBoolean("bool")));
        uSession.setDouble("double", 165.656);
        Log.d("uSession double: ", String.valueOf(uSession.getDouble("double")));
        uSession.setFloat("float", .656F);
        Log.d("uSession float: ", String.valueOf(uSession.getFloat("float")));
        uSession.clearData();
        Log.d("uSession clear data: ", "Clear all session data");
    }
}