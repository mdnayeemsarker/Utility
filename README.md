# Utility
> This is an Simple and more easy android library where you can find many amazing solution. 

## Installation Utility solution

//Add it in your root build.gradle at the end of repositories:

```bash
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' }
	}
}
```

Add Dependency Just go to your build.gradle Module:

//Add the dependency
dependencies {
```bash
	implementation 'com.github.mdnayeemsarker:Utility:1.1.3'
```
}

//If you want to make some beautiful UI, just take this so easy step by step  
## PinView for otp verification
```bash
//for xml
<com.abmn.utility.UI.UPinView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:focusableInTouchMode="true"
    android:inputType="number"
    android:textSize="18sp"
    app:hideLineWhenFilled="false"
    app:itemCount="6"
    app:viewType="line"/>
    
//For java class
UPinView uPinView = findViewById(R.id.uPinView);
//enjoy
```

## Request Focus and set error message
```bash
//for xml
<EditText
    android:id="@+id/checkET"
    android:layout_gravity="center"
    android:layout_margin="10dp"
    android:hint="input text"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:inputType="text"
    android:importantForAutofill="no"
    tools:ignore="HardcodedText" />

<Button
    android:id="@+id/checkBtnET"
    android:text="Check"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    tools:ignore="HardcodedText" />
        
//For java class
EditText checkET = findViewById(R.id.checkET);
findViewById(R.id.checkBtnET).setOnClickListener(v -> {
    String check = checkET.getText().toString();
    if (TextUtils.isEmpty(check)){
        UEditText.setError(checkET, "Required Field");
    }else {
        //your logic
    }
});
```
## for hide keyboard
```bash
findViewById(R.id.checkBtnET).setOnClickListener(v -> {
    UEditText.hideKeyboard(this, v);
});
//enjoy

```

## for snackbar
```bash
findViewById(R.id.checkBtnET).setOnClickListener(v -> {
    String check = checkET.getText().toString();
    if (TextUtils.isEmpty(check)){
        UEditText.setError(checkET, "Required Field");
    }else {
        new USnackBar().Snackbar(v, "Here is your input text: " + check);
    }
});
//enjoy
```
## for snackbar with intent action
```bash
findViewById(R.id.checkBtnET).setOnClickListener(v -> {
    String check = checkET.getText().toString();
    if (TextUtils.isEmpty(check)){
        UEditText.setError(checkET, "Required Field");
    }else {
      new USnackBar().SnackbarWithAction(v, "Here is your input text: " + check, "Go", this, CheckActivity.class);
    }
});
//enjoy
```

//If you need store some local data in application cash
## Declare USession class as a local or global variable in your activity or fragment and use the feature
```bash
UConfig uConfig = new UConfig(this); // this for activity
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
```

//If you use this dependency then you can check if the device connected ot not with the internet connection.

## You can use UConfig in your activity class or fragment.
```bash
UConfig uConfig = new UConfig(this);
if (!uConfig.isConnected()){
    uConfig.isConnectedAlert("", ""); // if you want to add your custorm alert title and message give the value otherwise show default
}
```
## If you want To display a nice progress display do this as it is work in activity java class or fragment
```bash
ProgressDisplay progressDisplay = new ProgressDisplay(this); //firs timeinit this and then
progressDisplay.showProgress(); //this for show progress
progressDisplay.hideProgress(); //this for hide progress
```

## If you want To store an array list in your local data then try to use this
```bash
ProgressDisplay progressDisplay = new ProgressDisplay(this); //firs timeinit this and then
UConfig uConfig = new UConfig(this); // this for activity
try {
    JSONArray jsonArray = new JSONArray(jsonString); //make an array to string and use this 
    uConfig.setJSONArray("array", jsonArray);
} catch (JSONException e) {
    throw new RuntimeException(e);
}

//this way you can get array any whare in your project  
JSONArray getArray = uConfig.getJSONArray("array");
Log.d("array", getArray.toString());
```

//One more thing if you choose this dependency you not need to configure the SharedPreferences
simply use this.

## Please stay with me, more advance feature are coming. Thank you for using this library. 

## Getting Touch with me

 * Mail: dev.ab.nayeem@gmail.com
 * Feel free to [learn more about MD NAYEEM SARKER](https://github.com/mdnayeemsarker).

## License

MIT © [MD NAYEEM SARKER](https://github.com/mdnayeemsarker)
