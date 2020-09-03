package com.Softwarica.hoey.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.Softwarica.hoey.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro2 {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("Welcome to Hoey", "Listen to music any time and anywhere"
                , R.drawable.a, ContextCompat.getColor(getApplicationContext(), R.color.white)));
        addSlide(AppIntro2Fragment.newInstance("Interactive visuals", "Infographic visuals"
                , R.drawable.b, ContextCompat.getColor(getApplicationContext(), R.color.white)));
        addSlide(AppIntro2Fragment.newInstance("Effective UI", "Pleasing user experience"
                , R.drawable.c, ContextCompat.getColor(getApplicationContext(), R.color.white)));

        // Hide Skip/Done button.
        showSkipButton(false);
        sharedPreferences=getApplicationContext().getSharedPreferences("IntoPreferences", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if(sharedPreferences!=null){
            boolean checkstatus=sharedPreferences.getBoolean("checkstatus",false);
            if(checkstatus==true){
                startActivity(new Intent(getApplicationContext(),Splash_screen.class));
                finish();
            }
        }
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(getApplicationContext(),Splash_screen.class));
        editor.putBoolean("checkstatus",true).commit();
        finish();
    }

}
