package com.abmn.utility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.abmn.utility.UI.UEditText;
import com.abmn.utility.Internet.UConfig;
import com.abmn.utility.Internet.ProgressDisplay;
import com.abmn.utility.SharedPref.USession;
import com.abmn.utility.UI.UPinView;
import com.abmn.utility.UI.USnackBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UConfig uConfig = new UConfig(this);
        if (!uConfig.isConnected()){
            uConfig.isConnectedAlert("", "");
        }

        ProgressDisplay progressDisplay = new ProgressDisplay(this);

        progressDisplay.showProgress(); //this for show progress
        progressDisplay.hideProgress(); //this for hide progress

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

        UPinView uPinView = findViewById(R.id.uPinView);

        EditText checkET = findViewById(R.id.checkET);

        findViewById(R.id.checkBtnET).setOnClickListener(v -> {
            UEditText.hideKeyboard(this, v);
            String check = checkET.getText().toString();
            if (TextUtils.isEmpty(check)){
                UEditText.setError(checkET, "Required Field");
            }else {
                new USnackBar().SnackbarWithAction(v, "Here is your input text: " + check, "Go", this, CheckActivity.class);
            }
        });
    }
}