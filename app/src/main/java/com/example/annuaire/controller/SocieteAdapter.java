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
import com.example.annuaire.model.general;
import com.example.annuaire.model.societe;
import java.util.ArrayList;

public class SocieteAdapter extends RecyclerView.Adapter<SocieteAdapter.ViewHolder> {

    ArrayList<com.example.annuaire.model.societe> societes;

    public SocieteAdapter(ArrayList<societe> societe) {
        this.societes = societe;
    }

    @NonNull
    @Override
    public SocieteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewsocietes, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(String.valueOf(societes.get(position).getLabel()));
        byte[] imageBytes = societes.get(position).getImg();
        if(imageBytes!=null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imageView.setImageBitmap(bitmap);
        }

       GeneralMethode.ConsulterSociete(holder.more,holder.itemView.getContext(),holder,SocieteAdapter.this,p->{
           general.setIdEntreprise(societes.get(position).getId());
           general.setSociete(societes.get(position).getLabel());

           general.setFacebook(societes.get(position).getFacebook());
           general.setLinked_in(societes.get(position).getLinked_in());
           general.setInstagram(societes.get(position).getInstagram());




           return null;
     });


    }

    @Override
    public int getItemCount() {
        return societes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView more;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.img);
            more = itemView.findViewById(R.id.more);
        }
    }
}
