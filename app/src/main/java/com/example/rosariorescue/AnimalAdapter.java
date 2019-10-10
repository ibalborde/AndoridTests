package com.example.rosariorescue;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {

    private Context mContext;
    private List<Animal> AnimalsList;
    private Dialog mDialog;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, count, description;
        private ImageView thumbnail, overflow;
        private LinearLayout item_animal;
        public CardView mCardView;


        public MyViewHolder(View view) {
            super(view);
            mCardView = view.findViewById(R.id.card_view);
            item_animal = view.findViewById(R.id.animal_item);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
            overflow = view.findViewById(R.id.overflow);
            description = view.findViewById(R.id.description);
        }

    }

    public AnimalAdapter(Context mContext, List<Animal> albumList) {
        this.mContext = mContext;
        this.AnimalsList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.animal_card, parent, false);

            final MyViewHolder viewHolder = new MyViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Animal animal = AnimalsList.get(position);
        holder.title.setText(animal.getName());
        holder.count.setText(animal.getNumOfSongs() + " games");
        holder.mCardView.setTag(position);

        // loading album cover using Glide library
        Glide.with(mContext).load(animal.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
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
}