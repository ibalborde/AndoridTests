package com.example.rosariorescue.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.R;
import com.example.rosariorescue.data.PhotoPackCreator;
import com.example.rosariorescue.viewHolders.PhotoPickerViewHolder;

public class PhotoPickerAdapter extends RecyclerView.Adapter<PhotoPickerViewHolder> {

    // MARK: - Adapter

    @NonNull
    @Override
    public PhotoPickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_picked_photo2, parent, false);
        return new PhotoPickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoPickerViewHolder holder, int position) {
        holder.setPhotoPickingItem(PhotoPackCreator.get().getPickedItem(position));
    }

    @Override
    public int getItemCount() {
        return PhotoPackCreator.get().getDiffPhotosCount();
    }

    // MARK: - Interface

    public void addLast() {
        this.notifyItemInserted(getItemCount() - 1);
    }

    public void remove(int index) {
        this.notifyItemRemoved(index);
    }

}
