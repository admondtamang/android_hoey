package com.Softwarica.hoey.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Api.UserApi;
import com.Softwarica.hoey.Model.User;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.serverresponse.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    ImageButton btnSignup;
    EditText etEmail, etPassword, etUsername;
    TextView intentLogin;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);
        intentLogin = findViewById(R.id.intentLogin);

        intentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("enter email address");
                    return;
                }
                if (etEmail.getText().toString().trim().matches(emailPattern)) {
                    signUp();
                    return;
                } else {
                    etEmail.setError("Invalid email address");
                    return;
                }
            }
        });

    }

    public void signUp() {
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        User user = new User(username, email, password);
        if (SignUpToken(user)) {
            Toast.makeText(Signup.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean SignUpToken(User user) {

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<UserResponse> signUpCall = usersAPI.registerUser(user);
        signUpCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Signup.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Signup.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}
