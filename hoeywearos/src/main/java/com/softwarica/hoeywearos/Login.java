package com.softwarica.hoeywearos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.softwarica.hoeywearos.Api.Url;
import com.softwarica.hoeywearos.Token.LoginToken;

public class Login extends WearableActivity {

    ImageButton btnLogin;
    EditText etUsername, etPassword;
    private static final String TAG = "Login";

    // For finger print auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginToken loginToken = new LoginToken();
                StrictModeClass.StrictMode();

                if (loginToken.checkUser(etUsername.getText().toString(), etPassword.getText().toString())) {
                    String token = Url.token;
                    Log.e(TAG, "onClick: " + token);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                    etUsername.requestFocus();
                }
            }
        });
    }



}
