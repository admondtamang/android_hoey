package com.Softwarica.hoey.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Softwarica.hoey.Adapter.SongAdapter;
import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.MainActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Library extends Fragment {

    private static final String TAG = "Library";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_library, container, false);
        ViewPager2 viewPager = v.findViewById(R.id.viewpagerLibrary);
        viewPager.setAdapter(new ViewPagerAdapterLibrary(getActivity()));

        TabLayout tablayout = v.findViewById(R.id.tabsRegister);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tablayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Songs");
                        tab.setIcon(R.drawable.ic_music_note_black_24dp);

                        break;
                    case 1:
                        tab.setText("Albums");
                        tab.setIcon(R.drawable.ic_album_black_24dp);
                        break;
                    case 2:
                        tab.setText("Artists");
                        tab.setIcon(R.drawable.ic_person_black_24dp);
                        break;
                }
            }
        }
        );

        tabLayoutMediator.attach();



        return v;

    }
}
