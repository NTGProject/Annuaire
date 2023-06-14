package com.example.annuaire.controller;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.annuaire.R;
import com.example.annuaire.model.Organisation;

import java.util.ArrayList;

public class OrganisationAdapter extends RecyclerView.Adapter<OrganisationAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Organisation> organisations;
    int selectedPosition = 0;
    Drawable drawable, drawable2;


    public OrganisationAdapter(ArrayList<Organisation> organisations, RecyclerViewInterface recyclerViewInterface) {
        this.organisations = organisations;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    public void setPositionToChange(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.vieworganisation, parent, false);
        return new ViewHolder(inflate, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.circle_shape);
        drawable2 = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.circle_shape_clicked);

        holder.TVTitre.setText(String.valueOf(organisations.get(position).getTitre()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(organisations.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.IVOrganisation);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                recyclerViewInterface.onItemClick(position);

                selectedPosition = position;

                notifyDataSetChanged();
            }
        });

        if(selectedPosition == position){
            holder.constraintLayout.setBackground(drawable2);
            holder.TVTitre.setTextColor(Color.rgb(6,129,81));
            holder.TVTitre.setTypeface(null, Typeface.BOLD);
        }
        else{
            holder.constraintLayout.setBackground(drawable);
            holder.TVTitre.setTextColor(Color.rgb(0,0,0));
            holder.TVTitre.setTypeface(null, Typeface.NORMAL);
        }
    }






    @Override
    public int getItemCount() {
        return organisations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TVTitre;
        ImageView IVOrganisation;
        ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            TVTitre = itemView.findViewById(R.id.TVTitre);
            IVOrganisation = itemView.findViewById(R.id.IVOrganisation);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int p = getAdapterPosition();
                        if( p != RecyclerView.NO_POSITION){
                            notifyDataSetChanged();
                            recyclerViewInterface.onItemClick(p);
                        }
                    }
                }
            });
        }





    }
}
