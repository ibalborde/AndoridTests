package com.example.rosariorescue.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


import com.example.rosariorescue.BaseActivity;
import com.example.rosariorescue.R;
import com.example.rosariorescue.StaticAlbums;
import com.example.rosariorescue.adapters.PhotoPickerAdapter;
import com.example.rosariorescue.data.PhotoPackCreator;
import com.example.rosariorescue.data.PhotoPickingItem;
import com.example.rosariorescue.event.PhotoPackPickedImageDeleteEvent;
import com.example.rosariorescue.util.PhotoPickerUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PhotoPickerActivity extends BaseActivity implements ValueEventListener {

    // MARK: - Static

    private static final String TAG = PhotoPickerActivity.class.getSimpleName();

    private View emptySelectionView;

    private PhotoPickerAdapter photoPickerAdapter;

    private Switch buttonSwitch;
    private ImageView addPhoto;
    public Uri imageUri;
    private int status;
    private int type;
    private int cant_images;
    private TextInputEditText description;
    private RadioButton cat_add;
    private RadioButton dog_add;
    private RadioButton other_add;
    private RadioGroup grupo_add;
    private Button button_add;
    private List<Integer> listPhotos;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mAnimalReference = mRootReference.child("Animal");

    // MARK: - Life Cycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
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

        buttonSwitch = findViewById(R.id.status_add);
        cat_add = findViewById(R.id.cat_add);
        dog_add = findViewById(R.id.dog_add);
        other_add = findViewById(R.id.other_add);
        description = findViewById(R.id.description_add);
        button_add = findViewById(R.id.button_add);
        grupo_add = findViewById(R.id.radioGroup_add);

        if(!buttonSwitch.isChecked()){
            buttonSwitch.setText(R.string.status_wanted);
            status = 0;
        }else{
            buttonSwitch.setText(R.string.status_found);
            status = 1;
        }
        type = comprobarType(grupo_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addAnimal();
            }
        });

        this.emptySelectionView = findViewById(R.id.empty_view);

        this.photoPickerAdapter = new PhotoPickerAdapter();

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(photoPickerAdapter);

    }


    public int comprobarType(View view) {
        if (grupo_add.getCheckedRadioButtonId() == R.id.cat_add) {
            return 0;
        }else if(grupo_add.getCheckedRadioButtonId() == R.id.dog_add){
            return 1;
        }else {
            return 2;
        }
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
        updateEmptySelectionView();
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

        this.updateEmptySelectionView();
    }

//    public static void displayFrom(Activity activity) {
//        Intent intent = new Intent(activity, PhotoPickerActivity.class);
//        activity.startActivity(intent);
//    }


    @Subscribe
    public void onPhotoPackPickedImageDelete(PhotoPackPickedImageDeleteEvent event) {
        photoPickerAdapter.remove(event.index);
        this.updateEmptySelectionView();
    }

    private void updateEmptySelectionView() {
        int count = PhotoPackCreator.get().getDiffPhotosCount();
        boolean isEmpty = count == 0;
        int visibility = isEmpty ? View.VISIBLE : View.INVISIBLE;
        this.emptySelectionView.setVisibility(visibility);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public void addAnimal() {
        // Create a new user with a first and last name
        Map<String, Object> animal = new HashMap<>();
        animal.put("cant_images", cant_images);
        animal.put("status", status);
        animal.put("type", type);
        animal.put("description", Objects.requireNonNull(description.getText()).toString());
        for(int i = 0; i < cant_images; i++){
            animal.put("image" + i, listPhotos.get(i));
        }

        // Add a new document with a generated ID
        db.collection("Animal")
                .add(animal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


}
