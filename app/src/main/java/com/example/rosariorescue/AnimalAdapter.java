package com.example.rosariorescue;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {

    private Context mContext;
    private List<Animal> AnimalsList;
    private Dialog mDialog;
    private OnAnimalCardListener mOnAnimalCardLister;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title, status;
        private ImageView thumbnail, overflow;
        private LinearLayout item_animal;
        OnAnimalCardListener onAnimalCardListener;



        public MyViewHolder(View itemView, OnAnimalCardListener onAnimalCardListener) {
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

    public AnimalAdapter(Context mContext, List<Animal> animalList, OnAnimalCardListener onAnimalCardListener) {
        this.mContext = mContext;
        this.AnimalsList = animalList;
        this.mOnAnimalCardLister = onAnimalCardListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.animal_card, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(itemView, mOnAnimalCardLister);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
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


    @Override
    public int getItemCount() {
        return AnimalsList.size();
    }

    public interface OnAnimalCardListener{
        void onAnimalCardClick(int position);
    }
}