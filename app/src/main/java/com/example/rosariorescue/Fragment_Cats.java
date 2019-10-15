package com.example.rosariorescue;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class Fragment_Cats extends Fragment implements AnimalAdapter.OnAnimalCardListener {

    View v;
    private RecyclerView myRecyclerView, recyclerViewForDialog;
    private ViewPager viewPager;
    private List<Animal> AnimalsList;
    private AnimalAdapter animalAdapter;
    private AnimalAdapterDialog animalAdapterDialog;
    private Animal animal;
    private Dialog mDialog;
    private TextView descriptionDialog;
    private ImageView imageDialog;

    public Fragment_Cats() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cats, container, false);
        View b = inflater.inflate(R.layout.dialog_animal_pager, container, false);
        myRecyclerView =  v.findViewById(R.id.recycler_view_cats);
        animalAdapter = new AnimalAdapter(getContext(), AnimalsList, this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(animalAdapter);

//        viewPager = b.findViewById(R.id.viewPagerDialog);
//        viewPager.setAdapter(animalAdapterDialog);
//        viewPager.setPadding(130, 0, 130, 0);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AnimalsList = new ArrayList<>();

        prepareAlbums();


    }



    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.cat2,
                R.drawable.cat3,
                R.drawable.cat4};

        Animal a = new Animal("Cuco", 13, covers[0], "Test Description cat1", 1);
        AnimalsList.add(a);

        a = new Animal("Gato2", 8, covers[1], "Test Description cat2", 0);
        AnimalsList.add(a);

        a = new Animal("Gato3", 11, covers[2], "Test Description cat3", 1);
        AnimalsList.add(a);


    }



    @Override
    public void onAnimalCardClick(int position) {

        mDialog = new Dialog(getContext());
        mDialog.setContentView(R.layout.dialog_animal);


        animal = AnimalsList.get(position);

        descriptionDialog = mDialog.findViewById(R.id.description_dialog);
        imageDialog = mDialog.findViewById(R.id.image_dialog);

        descriptionDialog.setText(animal.getDescription());
        imageDialog.setImageResource(animal.getThumbnail());
        mDialog.show();
    }
}
