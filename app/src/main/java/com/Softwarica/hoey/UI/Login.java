package com.Softwarica.hoey.UI;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricPrompt;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.Token.LoginToken;
import com.Softwarica.hoey.Utils.BroadCastReceiver;
import com.Softwarica.hoey.Utils.CreateChannel;
import com.Softwarica.hoey.Utils.StrictModeClass;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity {

    ImageButton btnLogin;
    EditText etUsername, etPassword;
    TextView intentSignup;
    private static final String TAG = "Login";
    private CheckBox cbRem;
    SharedPreferences rememberMe;
    public NotificationManagerCompat notificationManagerCompat;
    BroadCastReceiver broadCastReceiver = new BroadCastReceiver(this);
    Vibrator vibrator;

    // For finger print auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Finger print
        Executor executor = Executors.newSingleThreadExecutor();
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("finger print Authentication")
                .setSubtitle("Subtitle")
                .setDescription("Desc")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).build();
        checkConnection();

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        //Shared preferences
        cbRem = findViewById(R.id.remember);

        SharedPreferences sharedPreferences = getSharedPreferences("Hoey", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "empty");
        if (!token.equals("empty")) {
            Url.token = token;
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        intentSignup = findViewById(R.id.intentSignup);
        intentSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginToken loginToken = new LoginToken();
                StrictModeClass.StrictMode();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (validInput(username, password)) {
                    if (loginToken.checkUser(username, password)) {

                        SharedPreferences sharedPreferences = getSharedPreferences("Hoey", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", Url.token);
                        editor.commit();
                        String token = Url.token;
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        loginNotification();
                        finish();

                    } else {
                        Toast.makeText(Login.this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                        vibrator.vibrate(1000);
                        etUsername.requestFocus();
                    }
                }
            }
        });
    }

    public Boolean validInput(String username, String password) {
        if (username.trim().equals("") || password.trim().equals("")) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            etUsername.setError("Empty input fields!");
            return false;
        }
        return true;
    }

    public void checkConnection() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (null == activeNetwork) {
            notificationManagerCompat = NotificationManagerCompat.from(this);
            CreateChannel channel = new CreateChannel(this);
            channel.createChannel();
        }
    }

    private void loginNotification() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.hoey)
                .setContentTitle("Hoey")
                .setContentText("Hello User")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);
    }

}
