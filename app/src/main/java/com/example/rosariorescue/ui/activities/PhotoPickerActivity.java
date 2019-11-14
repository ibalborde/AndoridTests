package com.example.rosariorescue.ui.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


import com.example.rosariorescue.BaseActivity;
import com.example.rosariorescue.R;
import com.example.rosariorescue.StaticAlbums;
import com.example.rosariorescue.Upload;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
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
    public List<Uri> imageUriList;
    private String mName;
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
    private static final int RESULT_LOAD_IMAGE = 1;
    private Uri[] datasUri = null;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private boolean statusForDates = false;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReferece;
    private ProgressBar mProgressBar;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Long referentName;


    // MARK: - Life Cycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.add_animal);

        mStorageReference = FirebaseStorage.getInstance().getReference("Animales");
        mDatabaseReferece = FirebaseDatabase.getInstance().getReference("Animales");


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
                Date cDate = new Date();
                String nameDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(cDate);

                uploadAnimals(nameDate);
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
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<PhotoPickingItem> photos = PhotoPickerUtil.onActivityResult(requestCode, resultCode, data);
        if (photos.isEmpty()) {
            return;
        }

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){
            if(data.getClipData() != null){
                int totalItemsSelected = data.getClipData().getItemCount();

                datasUri = new Uri[totalItemsSelected];
                for(int i = 0; i < totalItemsSelected; i++){
                    Uri fileUri = data.getClipData().getItemAt(i).getUri();
                    datasUri[i] =fileUri;
                    if (PhotoPackCreator.get().addImage(photos.get(i))) {
                        photoPickerAdapter.addLast();
                    }

                }
            } else if(data.getData() != null){
                datasUri = new Uri[1];
                    Uri fileUri = data.getData();
                    datasUri[1] =fileUri;
                    if (PhotoPackCreator.get().addImage(photos.get(0))) {
                        photoPickerAdapter.addLast();

                }
            }
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

    public void addAnimal( String nameDate) {
        // Create a new user with a first and last name
        Map<String, Object> animal = new HashMap<>();
        cant_images = datasUri.length;
        animal.put("cant_images", cant_images);
        animal.put("status", status);
        animal.put("type", type);
        animal.put("description", Objects.requireNonNull(description.getText()).toString());

        // Add a new document with a generated ID
        db.collection(nameDate)
                .add(animal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(PhotoPickerActivity.this, "Done!!", Toast.LENGTH_SHORT).show();
                        finish();
//                        Intent myIntent = new Intent(PhotoPickerActivity.this, MainActivity.class);
//                        startActivity(myIntent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding Animal :c", e);
                    }
                });
    }


    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

//    private void uploadAnimals(){
//        if(imageUriList != null){
//            referentName = System.currentTimeMillis();
//            for(Uri imageUri: imageUriList){
//                StorageReference fileReference = mStorageReference.child(referentName
//                        + "." + getFileExtension(imageUri));
//                fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                //add progress bar
//                                //mProgressBar.setProgress(0);
//                            }
//                        }, 500);
//                        Toast.makeText(PhotoPickerActivity.this, R.string.upload_success, Toast.LENGTH_SHORT).show();
//                        Date date = new Date();
//                        mName = referentName + "." + formatter.format(date);
//                        Upload upload = new Upload(mName, mStorageReference.getDownloadUrl().toString(), status, type, description.getText().toString().trim());
//                        String uploadId = mDatabaseReferece.push().getKey();
//                        mDatabaseReferece.child(uploadId).setValue(upload);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(PhotoPickerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                        //set progressBar
//                        //mProgressBar.setProgress((int) progress);
//                    }
//                });
//            }
//
//        }else{
//            Toast.makeText(this, R.string.no_photo_selected, Toast.LENGTH_SHORT).show();
//        }
//
//    }

    private void uploadAnimals(String nameDate) {

        for (int i = 0; i < datasUri.length; i++) {
            StorageReference fileToUpload = mStorageReference.child(nameDate).child(nameDate +" "+i);
            fileToUpload.putFile(datasUri[i]).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    statusForDates = true;
                }
            });

        }
        if(statusForDates){
            addAnimal(nameDate);
        }
    }




    public String getFileName(Uri uri){
        String result = null;
        if(uri.getScheme().equals("content")){
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try{
                if(cursor != null && cursor.moveToFirst()){
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
            if (result == null){
                result = uri.getPath();
                int cut = result.lastIndexOf('/');
                if(cut != 1){
                    result = result.substring(cut + 1);
                }
            }
            return result;

    }

}
