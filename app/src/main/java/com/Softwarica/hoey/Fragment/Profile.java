package com.Softwarica.hoey.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.Login;
import com.Softwarica.hoey.UI.MainActivity;
import com.Softwarica.hoey.UI.MapsActivity;
import com.Softwarica.hoey.UI.UpdateProfile;
import com.bumptech.glide.Glide;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment implements View.OnClickListener {
    ImageView profileImg;
    TextView username, email;
    Button btnEdit, btnLogout, btnGps;
    private static final String TAG = "Profile";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImg = v.findViewById(R.id.imgProfile);
        username = v.findViewById(R.id.tvUsername);
        email = v.findViewById(R.id.tvEmail);
        btnEdit = v.findViewById(R.id.btnEdit);
        btnLogout = v.findViewById(R.id.btnLogout);
        btnGps = v.findViewById(R.id.btnGps);
        btnGps.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        username.setText(MainActivity.globalUser.getUsername());
        email.setText(MainActivity.globalUser.getEmail());
        String profileImage = Url.uploads + MainActivity.globalUser.getPicture();
        Log.e(TAG, "onCreateView: " + profileImage);
        if (MainActivity.globalUser.getPicture() != null) {
            Glide.with(getActivity()).load(profileImage).into(profileImg);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
                break;
            case R.id.btnLogout:
                SharedPreferenceLogout();
                break;
            case R.id.btnGps:
                Intent intent1 = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent1);
        }
    }


    private void SharedPreferenceLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("Are you Leaving?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Hoey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.remove("token");
                editor.commit();
                Url.token = "Bearer ";
                Intent back_login = new Intent(getActivity(), Login.class);
                startActivity(back_login);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}