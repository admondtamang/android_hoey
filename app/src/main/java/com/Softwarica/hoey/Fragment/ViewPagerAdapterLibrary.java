package com.Softwarica.hoey.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class ViewPagerAdapterLibrary extends FragmentStateAdapter {
    public ViewPagerAdapterLibrary(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Songs();
            case 1:
                return new AlbumsFragment();
            case 2:
                return new ArtistFragment();
            default:
                return new Songs();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
