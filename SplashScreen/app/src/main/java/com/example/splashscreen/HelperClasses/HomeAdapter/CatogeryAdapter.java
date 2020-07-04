package com.example.splashscreen.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.R;

import java.util.ArrayList;

public class CatogeryAdapter extends RecyclerView.Adapter<CatogeryAdapter.CatogeryViewHolder> {

    ArrayList<CatogeryHelperClass> catogeryLocations;

    public CatogeryAdapter(ArrayList<CatogeryHelperClass> catogeryLocations) {
        this.catogeryLocations = catogeryLocations;
    }

    @NonNull
    @Override
    public CatogeryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catogery_card_design,parent,false);
        CatogeryViewHolder catogeryViewHolder = new CatogeryViewHolder(view);
        return catogeryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatogeryViewHolder holder, int position) {

        CatogeryHelperClass catogeryHelperClass = catogeryLocations.get(position);
        holder.imageView.setImageResource(catogeryHelperClass.getImage());
        holder.textView.setText(catogeryHelperClass.getTitle());
    }

    @Override
    public int getItemCount() {
        return catogeryLocations.size();
    }


    public static class CatogeryViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public CatogeryViewHolder(@NonNull View itemView) {
            super(itemView);
            //hooks
            imageView = itemView.findViewById(R.id.catogery_image);
            textView = itemView.findViewById(R.id.catogery_title);
        }
    }
}
