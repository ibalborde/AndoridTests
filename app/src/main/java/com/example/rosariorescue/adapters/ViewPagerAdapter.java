package com.example.rosariorescue.adapters;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lastFragment = new ArrayList<>();
    private final List<String> lastTitles = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lastFragment.get(position);
    }

    @Override
    public int getCount() {
        return lastTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lastTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String title){
        lastFragment.add(fragment);
        lastTitles.add(title);
    }
}
