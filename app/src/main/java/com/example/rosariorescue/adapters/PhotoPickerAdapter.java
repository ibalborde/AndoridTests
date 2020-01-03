package com.example.rosariorescue.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.R;
import com.example.rosariorescue.viewHolders.PhotoPickerViewHolder;

import java.util.List;

public class PhotoPickerAdapter extends RecyclerView.Adapter<PhotoPickerViewHolder> {

    private List<String> fileDoneList;

    private List<Uri> photos;

    public PhotoPickerAdapter(List<Uri> photos, List<String> fileDoneList){
        this.fileDoneList = fileDoneList;
        this.photos = photos;
    }

    // MARK: - Adapter

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @NonNull
    @Override
    public PhotoPickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_picked_photo, parent, false);
        return new PhotoPickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoPickerViewHolder holder, int position) {
        holder.setPhotoPickingItem(photos.get(position), position, fileDoneList);
    }

    // MARK: - Interface

    public void addLast() {
        this.notifyItemInserted(getItemCount() - 1);
    }

    public void remove(Uri uri) {
        int index = this.photos.indexOf(uri);
        if (index == -1) {
            return;
        }

        this.photos.remove(index);
        this.notifyItemRemoved(index);
    }

}
