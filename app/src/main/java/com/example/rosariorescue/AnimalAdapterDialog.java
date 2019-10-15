package com.example.rosariorescue;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class AnimalAdapterDialog extends PagerAdapter {

    private Context mContext;
    private List<Animal> AnimalsList;
    private Dialog mDialog;
    private LayoutInflater layoutInflater;

    public AnimalAdapterDialog(List<Animal> AnimalsList, Context context){
        this.AnimalsList = AnimalsList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return AnimalsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem (@NonNull ViewGroup container, int position){
        layoutInflater = layoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.dialog_animal_pager, container, false);

        ImageView imageView;
        TextView status, description;

        imageView = view.findViewById(R.id.image_dialog);
        status = view.findViewById(R.id.status);
        description = view.findViewById(R.id.description_dialog);

        imageView.setImageResource(AnimalsList.get(position).getThumbnail());
        if (AnimalsList.get(position).getStatus() == 0){
            status.setText("Buscado");
        }
        else{
            status.setText("Encontrado");
        }
        description.setText(AnimalsList.get(position).getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
