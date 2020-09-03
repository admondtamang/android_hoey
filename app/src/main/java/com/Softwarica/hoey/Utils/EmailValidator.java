package com.Softwarica.hoey.Utils;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EmailValidator implements TextWatcher {

/**
 * Email validation pattern.
 */
// validating email id
public static final Pattern EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
);

// validating password with retype password
private boolean isValidPassword(String pass) {
    if (pass != null && pass.length() > 6) {
        return true;
    }
    return false;
}

private boolean mIsValid = false;

public boolean isValid() {
    return mIsValid;
}

public static boolean isValidEmail(CharSequence email) {
    return email != null && EMAIL_PATTERN.matcher(email).matches();
}

public static boolean isValidPassword(CharSequence pass) {
    return pass != null && isValidPassword(pass);
}

@Override
public void beforeTextChanged(CharSequence s, int start, int count, int after) {

}

@Override
public void onTextChanged(CharSequence s, int start, int before, int count) {

}

@Override
public void afterTextChanged(Editable editableText) {
 mIsValid=isValidEmail(editableText);
    mIsValid=isValidPassword(editableText);
}
}