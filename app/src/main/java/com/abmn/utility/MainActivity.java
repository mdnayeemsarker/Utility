package com.abmn.utility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.abmn.utility.UI.UEditText;
import com.abmn.utility.UI.ProgressDisplay;
import com.abmn.utility.UI.UPinView;
import com.abmn.utility.UI.USnackBar;

import org.json.JSONArray;
import org.json.JSONException;

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

        uConfig.setData("string", "Utility By MD NAYEEM SARKER");
        Log.d("uConfig String: ", uConfig.getData("string"));
        uConfig.setInteger("int", 150);
        Log.d("uConfig integer: ", String.valueOf(uConfig.getInteger("int")));
        uConfig.setBoolean("bool", true);
        Log.d("uConfig bool: ", String.valueOf(uConfig.getBoolean("bool")));
        uConfig.setDouble("double", 165.656);
        Log.d("uConfig double: ", String.valueOf(uConfig.getDouble("double")));
        uConfig.setFloat("float", .656F);
        Log.d("uConfig float: ", String.valueOf(uConfig.getFloat("float")));
        uConfig.clearData();
        Log.d("uConfig clear data: ", "Clear all session data");

        String jsonString = "[\n" +
            "  {\n" +
            "    \"name\": \"Alice\",\n" +
            "    \"age\": 30,\n" +
            "    \"city\": \"New York\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Bob\",\n" +
            "    \"age\": 25,\n" +
            "    \"city\": \"Los Angeles\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Charlie\",\n" +
            "    \"age\": 35,\n" +
            "    \"city\": \"Chicago\"}\n" +
            "]";
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            uConfig.setJSONArray("array", jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONArray getArray = uConfig.getJSONArray("array");
        Log.d("array", getArray.toString());

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