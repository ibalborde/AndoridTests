package com.example.rosariorescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class AdapterForAnimalsPager extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Animal animal;

    public AdapterForAnimalsPager(Context context, Animal animal) {
        this.context = context;
        this.animal = animal;
    }

    @Override
    public int getCount() {
        return animal.getNumOfPhtos();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        List<Integer> photosList = animal.getPhotos();
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.animal_pager_images,container,false);
        ImageView imgslide = view.findViewById(R.id.animal_image_full);
        imgslide.setImageResource(photosList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}