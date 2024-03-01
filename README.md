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
	implementation 'com.github.mdnayeemsarker:Utility:1.0.4'
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
USession uSession = new USession(this); // this for activity
uSession.setData("string", "Utility By MD NAYEEM SARKER"); //for set string data
Log.d("uSession String: ", uSession.getData("string")); //for get string data
uSession.setInteger("int", 150); //for set integer data
Log.d("uSession integer: ", String.valueOf(uSession.getInteger("int"))); //for get integer data
uSession.setBoolean("bool", true); //for set bool data
Log.d("uSession bool: ", String.valueOf(uSession.getBoolean("bool"))); //for get bool data
uSession.setDouble("double", 165.656); //for set double data
Log.d("uSession double: ", String.valueOf(uSession.getDouble("double"))); //for get double data
uSession.setFloat("float", .656F); //for set float data
Log.d("uSession float: ", String.valueOf(uSession.getFloat("float"))); //for get float data
uSession.clearData(); //for clear all sesion data
Log.d("uSession clear data: ", "Clear all session data");
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

//One more thing if you choose this dependency you not need to configure the SharedPreferences
simply use this.

## Please stay with me, more advance feature are coming. Thank you for using this library. 

## Getting Touch with me

 * Mail: dev.ab.nayeem@gmail.com
 * Feel free to [learn more about MD NAYEEM SARKER](https://github.com/mdnayeemsarker).

## License

MIT © [MD NAYEEM SARKER](https://github.com/mdnayeemsarker)
