package com.example.rosariorescue.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.rosariorescue.R;
import com.example.rosariorescue.models.Animal;

import java.util.List;

public class AnimalsPagerAdapter extends PagerAdapter {

    // MARK: - Data

    private LayoutInflater inflater;

    private Animal animal;

    // MARK: - Init

    public AnimalsPagerAdapter(Context context, Animal animal) {
        this.inflater = LayoutInflater.from(context);
        this.animal = animal;
    }

    // MARK: - PagerAdapter

    @Override
    public int getCount() {
        return animal.getPhotos().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        List<Integer> photosList = animal.getPhotos();
        View view = inflater.inflate(R.layout.animal_pager_images,container,false);

        ImageView imgslide = view.findViewById(R.id.animal_image_full);
        imgslide.setImageResource(photosList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

}