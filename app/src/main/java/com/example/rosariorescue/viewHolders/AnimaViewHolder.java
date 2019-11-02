package com.example.rosariorescue.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.R;
import com.example.rosariorescue.adapters.AnimalAdapter;

public class AnimaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // MARK: - UI Components

    public TextView title;
    public TextView status;
    public ImageView thumbnail;
    private ImageView overflow;
    private LinearLayout item_animal;
    AnimalAdapter.OnAnimalCardListener onAnimalCardListener;

    // MARK: - Init

    public AnimaViewHolder(View itemView, AnimalAdapter.OnAnimalCardListener onAnimalCardListener) {
        super(itemView);
        item_animal = itemView.findViewById(R.id.animal_item);
        title = itemView.findViewById(R.id.title);
        status = itemView.findViewById(R.id.count);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        overflow = itemView.findViewById(R.id.overflow);

        this.onAnimalCardListener = onAnimalCardListener;

        itemView.setOnClickListener(this);
        thumbnail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onAnimalCardListener.onAnimalCardClick(getAdapterPosition());
    }

}