package com.example.rosariorescue.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rosariorescue.R;
import com.example.rosariorescue.models.Animal;
import com.example.rosariorescue.viewHolders.AnimaViewHolder;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimaViewHolder> {

    // MARK: - Data

    private Context mContext;
    public List<Animal> AnimalsList;
    private OnAnimalCardListener mOnAnimalCardLister;

    // MARK: - Init

    public AnimalAdapter(Context mContext, List<Animal> animalList, OnAnimalCardListener onAnimalCardListener) {
        this.mContext = mContext;
        this.AnimalsList = animalList;
        this.mOnAnimalCardLister = onAnimalCardListener;
    }

    // MARK: - Adapter

    @Override
    public int getItemCount() {
        return AnimalsList.size();
    }

    @Override
    public AnimaViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.animal_card, parent, false);

        return new AnimaViewHolder(itemView, mOnAnimalCardLister);
    }

    @Override
    public void onBindViewHolder(final AnimaViewHolder holder, final int position) {
        Animal animal = AnimalsList.get(position);
        holder.title.setText(animal.getName());

        if (AnimalsList.get(position).getStatus() == 0){
            holder.status.setText("Buscado");
        }
        else{
            holder.status.setText("Encontrado");
        }
        // loading album cover using Glide library
        Glide.with(mContext).load(animal.getThumbnail()).into(holder.thumbnail);
    }



    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
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