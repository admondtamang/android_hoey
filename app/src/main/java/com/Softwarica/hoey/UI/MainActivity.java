package com.Softwarica.hoey.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Api.UserApi;
import com.Softwarica.hoey.Fragment.Home;
import com.Softwarica.hoey.Fragment.Library;
import com.Softwarica.hoey.Fragment.PlaylistFragment;
import com.Softwarica.hoey.Fragment.Profile;
import com.Softwarica.hoey.Fragment.Search;
import com.Softwarica.hoey.Model.User;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.Utils.ShakeDetectionService;
import com.google.android.exoplayer2.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout mainFrameLayout;

    LinearLayout linearLayout;
    public static User globalUser;
    private static final String TAG = "MainActivity";
    private SensorManager sm;
    private float acelVal, acelLast, shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Shake detection
        Intent i = new Intent(this, ShakeDetectionService.class);
        startService(i);

        // Start home fragment at startup
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,
                new Home()).commit();
        // Calling user
        userDetail();

        mainFrameLayout = findViewById(R.id.main_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        linearLayout = findViewById(R.id.bottomSheetNowPlaying);
//        final BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(linearLayout);

//        String message="Hello";
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
//                .setSmallIcon(R.drawable.exo_icon_shuffle_on)
//                .setContentTitle("New Notification")
//                .setContentText(message)
//                .setAutoCancel(true);
//
//        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0,builder.build());


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_Home:
                        fragment = new Home();
//                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case R.id.nav_Search:
                        fragment = new Search();
                        break;
                    case R.id.nav_Library:
                        fragment = new Library();
                        break;
                    case R.id.nav_User:
                        fragment = new Profile();
                        break;
                    case R.id.nav_playlist:
                        fragment = new PlaylistFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,
                        fragment).commit();
                return true;
            }
        });
    }

    public void userDetail() {
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<User> userCall = userApi.userDetail(Url.token);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                globalUser = response.body();
//                Toast.makeText(MainActivity.this, "Hello " + globalUser.getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


}
