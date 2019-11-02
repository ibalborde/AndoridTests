package com.example.rosariorescue.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.R;
import com.example.rosariorescue.models.Animal;
import com.example.rosariorescue.viewHolders.AnimaViewHolder;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimaViewHolder> {

    // MARK: - Data

    private Context context;

    private LayoutInflater inflater;

    private List<Animal> animals;

    private OnAnimalCardListener mOnAnimalCardLister;

    // MARK: - Init

    public AnimalAdapter(Context context, List<Animal> animals, OnAnimalCardListener onAnimalCardListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.animals = animals;
        this.mOnAnimalCardLister = onAnimalCardListener;
    }

    // MARK: - Adapter

    @Override
    public int getItemCount() {
        return animals.size();
    }

    @NonNull
    @Override
    public AnimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View itemView = inflater.inflate(R.layout.animal_card, parent, false);

        return new AnimaViewHolder(itemView, mOnAnimalCardLister);
    }

    @Override
    public void onBindViewHolder(final AnimaViewHolder holder, final int position) {
        Animal animal = animals.get(position);
        holder.setAnimal(animal);
    }



    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {}

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    public interface OnAnimalCardListener{
        void onAnimalCardClick(int position);
    }
}