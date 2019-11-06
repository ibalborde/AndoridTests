package com.example.rosariorescue.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


import com.example.rosariorescue.BaseActivity;
import com.example.rosariorescue.R;
import com.example.rosariorescue.adapters.PhotoPickerAdapter;
import com.example.rosariorescue.data.PhotoPackCreator;
import com.example.rosariorescue.data.PhotoPickingItem;
import com.example.rosariorescue.event.PhotoPackPickedImageDeleteEvent;
import com.example.rosariorescue.util.PhotoPickerUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class PhotoPickerActivity extends BaseActivity {

    // MARK: - Static

    private static final String TAG = PhotoPickerActivity.class.getSimpleName();

    // MARK: - Components
    private ImageView portada;

    private View emptySelectionView;

    private PhotoPickerAdapter photoPickerAdapter;

    // MARK: - Life Cycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.add_animal);

        //toolbar
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.baseline_keyboard_arrow_left_white_36);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //this.emptySelectionView = findViewById(R.id.empty_view);

        this.photoPickerAdapter = new PhotoPickerAdapter();

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(photoPickerAdapter);

        portada = findViewById(R.id.image_add);

        // Galery Button
        //AppCompatImageButton addFromGaleryButton = findViewById(R.id.image_add);
        //addFromGaleryButton.setOnClickListener(v -> PhotoPickerUtil.tryPickFromGallery(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        MenuItem select_photos = menu.findItem(R.id.action_select_photos);
        if(TAG.equals("PhotoPickerActivity")){
            select_photos.setVisible(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_select_photos:
                PhotoPickerUtil.tryPickFromGallery(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PhotoPickerUtil.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<PhotoPickingItem> photos = PhotoPickerUtil.onActivityResult(requestCode, resultCode, data);
        if (photos.isEmpty()) {
            return;
        }

        for (PhotoPickingItem photoPickingItem : photos) {
            if (PhotoPackCreator.get().addImage(photoPickingItem)) {
                photoPickerAdapter.addLast();
            }
        }
        portada.setImageURI(photos.get(0).getUri());

      //  this.updateEmptySelectionView();
    }

    // MARK: - Intent

    public static void displayFrom(Activity activity) {
        Intent intent = new Intent(activity, PhotoPickerActivity.class);
        activity.startActivity(intent);
    }

    // MARK: - Subscriptions


    @Subscribe
    public void onPhotoPackPickedImageDelete(PhotoPackPickedImageDeleteEvent event) {
        photoPickerAdapter.remove(event.index);
        //this.updateEmptySelectionView();
    }

    // MARK: - Internal

//    private void updateEmptySelectionView() {
//        int count = PhotoPackCreator.get().getDiffPhotosCount();
//        boolean isEmpty = count == 0;
//        int visibility = isEmpty ? View.VISIBLE : View.INVISIBLE;
//        this.emptySelectionView.setVisibility(visibility);
//    }

}
