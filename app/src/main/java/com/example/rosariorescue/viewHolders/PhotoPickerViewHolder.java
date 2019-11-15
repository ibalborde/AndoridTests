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

import java.util.List;
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

    public void setPhotoPickingItem(PhotoPickingItem photoPickingItem, int position, List<String> fileDoneList) {
        this.photoPickingItem = photoPickingItem;

        ImageView imageView = itemView.findViewById(R.id.thumbnail_photo);
        ImageView imageViewCheck = itemView.findViewById(R.id.upload_loading);
        imageView.setImageURI(photoPickingItem.getUri());

        String fileDone = fileDoneList.get(position);

        if(fileDone.equals(String.valueOf(R.string.uploading))){

            imageViewCheck.setImageResource(R.drawable.progress);

        } else {

            imageViewCheck.setImageResource(R.drawable.checked);

        }
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



    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ImageView fileDoneView;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            fileDoneView = (ImageView) mView.findViewById(R.id.upload_loading);


        }

    }
}
