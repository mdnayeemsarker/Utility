package com.abmn.utility.UI;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class UEditText {
    public static void setError(EditText editText, String data) {
        editText.requestFocus();
        editText.setError(data);
    }

    public static void hideKeyboard(Activity activity, View root) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(root.getApplicationWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
