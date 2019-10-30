package com.example.rosariorescue;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;


public class SharePhotos extends AppCompatActivity {

    Button buttonSharedPhoto;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ImageView photoToShare;
    List<Animal> auxAnimalList;
    Animal auxAnimal;
    int auxAnimalImage;
    int animal_position;
    String animal_types;

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();
            if (shareDialog.canShow(SharePhotoContent.class)) {
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                shareDialog.show(content);
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.share_photo);

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

        buttonSharedPhoto = findViewById(R.id.button_share_photo);
        photoToShare = findViewById(R.id.image_to_share);


        if (getIntent().hasExtra("animal_type") && getIntent().hasExtra("animal_position")) {
            animal_types = getIntent().getStringExtra("animal_type");
            animal_position = getIntent().getIntExtra("animal_position", 0);

            switch (animal_types) {
                case "cats":
                    auxAnimalList = StaticAlbums.AnimalsListCats;
                    break;
                case "dogs":
                    auxAnimalList = StaticAlbums.AnimalsListDogs;
                    break;
                case "others":
                    auxAnimalList = StaticAlbums.AnimalsListOthers;
                    break;
                default:
                    break;
            }

            auxAnimal = auxAnimalList.get(animal_position);
            auxAnimalImage = auxAnimal.getThumbnail();
            Picasso.with(getBaseContext())
                    .load(auxAnimalImage)
                    .into(photoToShare);
        }


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        buttonSharedPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(getApplicationContext(), "Shared successful!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Shared cancel!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                Picasso.with(getBaseContext())
                        .load(auxAnimalImage)
                        .into(target);
            }
        });
    }

}
