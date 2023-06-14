package com.example.annuaire.controller;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire.R;
import com.example.annuaire.model.actualite;

import java.util.ArrayList;

public class actualiteAdapter extends RecyclerView.Adapter<actualiteAdapter.ViewHolder> {

    ArrayList<com.example.annuaire.model.actualite> actualite;

    public actualiteAdapter(ArrayList<actualite> actualite) {
        this.actualite = actualite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewactualite, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(String.valueOf(actualite.get(position).getTitle()));
        holder.desc.setText(String.valueOf(actualite.get(position).getDesc()));

        byte[] imageBytes = actualite.get(position).getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.imageView.setImageBitmap(bitmap);
        GeneralMethode.ActionMore(holder.more, holder.desc,holder.title);

    }

    @Override
    public int getItemCount() {
        return actualite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView desc;
        TextView more;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            imageView = itemView.findViewById(R.id.img);
            more = itemView.findViewById(R.id.more);
        }
    }
}
