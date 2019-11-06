package com.example.rosariorescue.util;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rosariorescue.data.LocalPhotoPickingItem;
import com.example.rosariorescue.data.PhotoPickingItem;

import java.util.ArrayList;
import java.util.List;

public class PhotoPickerUtil {

    private static final int GALLERY_REQUEST_CODE = 1;

    public static void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull int[] grantResults) {
        if (requestCode != GALLERY_REQUEST_CODE) {
            return;
        }

        if (grantResults.length <= 0) {
            // Cancelled
            // TODO
        }
        else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            doPickFromGallery(activity);
        }
        else {
            // Permission denied.
            // TODO
        }

    }

    /**
     * Intenta abrir el picker de la galería
     */
    public static void tryPickFromGallery(Activity activity) {
        if (checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            doPickFromGallery(activity);
        } else {
            String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(activity, permissions, GALLERY_REQUEST_CODE);
        }
    }

    public static List<PhotoPickingItem> onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK || requestCode != GALLERY_REQUEST_CODE) {
            return new ArrayList<>();
        }

        return getPhotosFromIntent(data);
    }

    // MARK: - Internal

    /**
     * Comprueba si se tiene permiso para utilizar un recurso
     * @return True si se tiene el permiso para utilizar el recurso
     */
    private static boolean checkPermission(Context context, String permission) {
        int status = ContextCompat.checkSelfPermission(context, permission);
        return status == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Abre el picker de la galería
     */
    private static void doPickFromGallery(Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent,"Seleccionar Imágenes"), GALLERY_REQUEST_CODE);
    }

    private static List<PhotoPickingItem> getPhotosFromIntent(Intent intentData) {
        ArrayList<PhotoPickingItem> result = new ArrayList<>();

        ClipData clipData = intentData.getClipData();
        Uri data = intentData.getData();

        if(clipData != null) {
            int count = clipData.getItemCount();
            for(int i = 0; i < count; i++) {
                Uri imageUri = clipData.getItemAt(i).getUri();
                PhotoPickingItem photoPickingItem = new LocalPhotoPickingItem(imageUri);
                result.add(photoPickingItem);
            }
        } else if(data != null) {
            PhotoPickingItem photoPickingItem = new LocalPhotoPickingItem(data);
            result.add(photoPickingItem);
        }

        return result;
    }

}
