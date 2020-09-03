package com.Softwarica.hoey.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.FavouriteActivity;


public class PlaylistFragment extends Fragment {
    ConstraintLayout constFavourite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_playlist, container, false);

        constFavourite=v.findViewById(R.id.ConstFavourite);
        constFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), FavouriteActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
