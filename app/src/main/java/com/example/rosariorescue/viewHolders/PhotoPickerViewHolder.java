package com.example.rosariorescue.viewHolders;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.R;
import com.example.rosariorescue.event.PickedPhotoDeletionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class PhotoPickerViewHolder extends RecyclerView.ViewHolder {

    // MARK: - Data

    private Uri uri;

    // MARK: - Init

    public PhotoPickerViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.findViewById(R.id.delete_button).setOnClickListener(e -> this.deletePhotoPath(uri));
    }

    // MARK: - get set

    public void setPhotoPickingItem(Uri uri, int position, List<String> fileDoneList) {
        this.uri = uri;

        ImageView imageView = itemView.findViewById(R.id.thumbnail_photo);
        ImageView imageViewCheck = itemView.findViewById(R.id.upload_loading);
        imageView.setImageURI(uri);

        String fileDone = fileDoneList.get(position);

        if (fileDone.equals(String.valueOf(R.string.uploading))){
            imageViewCheck.setImageResource(R.drawable.progress);
        } else {
            imageViewCheck.setImageResource(R.drawable.checked);
        }
    }

    // MARK: - Internal

    private void deletePhotoPath(Uri uri) {
        PickedPhotoDeletionEvent event = new PickedPhotoDeletionEvent(uri);
        EventBus.getDefault().post(event);
    }

}
