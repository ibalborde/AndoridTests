package com.example.rosariorescue.adapters;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rosariorescue.AnimalDataSource;
import com.example.rosariorescue.ui.fragments.AnimalsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    private ArrayList<AnimalDataSource> dataSources = new ArrayList<>();

    // MARK: - Init

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // MARK: - Interface

    public void addFragment(AnimalDataSource animalDataSource) {
        this.fragments.add(new AnimalsFragment(animalDataSource));
        this.dataSources.add(animalDataSource);
    }

    // MARK: - FragmentPagerAdapter

    @Override
    public int getCount() {
        return dataSources.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        AnimalDataSource dataSource = dataSources.get(position);
        return dataSource.title;
    }

}
