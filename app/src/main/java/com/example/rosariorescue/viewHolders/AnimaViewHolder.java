package com.example.rosariorescue.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rosariorescue.R;
import com.example.rosariorescue.adapters.AnimalAdapter;
import com.example.rosariorescue.models.Animal;

public class AnimaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // MARK: - UI Components

    private TextView titleTextView;

    private TextView statusTextView;

    private ImageView thumbnailImageView;

    private AnimalAdapter.OnAnimalCardListener onAnimalCardListener;

    // MARK: - Init

    public AnimaViewHolder(View itemView, AnimalAdapter.OnAnimalCardListener onAnimalCardListener) {
        super(itemView);

        this.titleTextView = itemView.findViewById(R.id.title);
        this.statusTextView = itemView.findViewById(R.id.count);
        this.thumbnailImageView = itemView.findViewById(R.id.thumbnail);

        this.onAnimalCardListener = onAnimalCardListener;

        this.itemView.setOnClickListener(this);
        this.thumbnailImageView.setOnClickListener(this);
    }

    public void setAnimal(Animal animal) {
        Context context = itemView.getContext();
        String name = animal.getName();
        String status = animal.getStatusString();
        int thumbnail = animal.getThumbnail();

        this.titleTextView.setText(name);
        this.statusTextView.setText(status);

        // loading album cover using Glide library
        Glide.with(context).load(thumbnail).into(thumbnailImageView);
    }

    @Override
    public void onClick(View v) {
        onAnimalCardListener.onAnimalCardClick(getAdapterPosition());
    }

}