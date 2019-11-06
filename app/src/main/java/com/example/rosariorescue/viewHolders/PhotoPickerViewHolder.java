package com.example.rosariorescue.viewHolders;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.R;
import com.example.rosariorescue.data.PhotoPackCreator;
import com.example.rosariorescue.data.PhotoPickingItem;

import java.util.Timer;
import java.util.TimerTask;

public class PhotoPickerViewHolder extends RecyclerView.ViewHolder {

    // MARK: - Data

    private PhotoPickingItem photoPickingItem;

    // MARK: - Init

    public PhotoPickerViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.findViewById(R.id.delete_button).setOnClickListener(e -> {
            PhotoPackCreator.get().removeImage(this.photoPickingItem);
        });

    }

    // MARK: - get set

    public void setPhotoPickingItem(PhotoPickingItem photoPickingItem) {
        this.photoPickingItem = photoPickingItem;

        ImageView imageView = itemView.findViewById(R.id.thumbnail_photo);
        imageView.setImageURI(photoPickingItem.getUri());
    }

    // MARK: - Internal



    private void decrementIfPossible() {
        if (photoPickingItem.getCount() == 1) { return; }

        PhotoPackCreator photoPackCreator = PhotoPackCreator.get();
        boolean canAdd = photoPackCreator.canAdd(-1);
        if (!canAdd) {
            return;
        }

        int currentCount = photoPickingItem.getCount();
        photoPickingItem.setCount(currentCount - 1);

        photoPackCreator.applyDelta(-1);

    }

}
