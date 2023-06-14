package com.example.annuaire.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.annuaire.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class GeneralAdaptar<T> extends RecyclerView.Adapter<GeneralAdaptar.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<?> data;

    public GeneralAdaptar(ArrayList<?> data, RecyclerViewInterface recyclerViewInterface) {
        this.data = data;
        this.recyclerViewInterface = recyclerViewInterface;
    }



    @NonNull
    @Override
    public GeneralAdaptar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralAdaptar.ViewHolder holder, int position) {
        bindData(holder, position, (T) data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    protected abstract void bindData(GeneralAdaptar.ViewHolder holder, int position, T item);

    protected abstract int getLayoutId();
    protected abstract String getEmail(T item);

    protected abstract String getCall(T item);
    protected abstract void onClickTo(int p);
    protected abstract String getMoreDetails(T item);

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titre;
        TextView lien;
        TextView details;

        FloatingActionButton FABCall;
        FloatingActionButton FABMail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.titre);
            lien = itemView.findViewById(R.id.lien);
            details = itemView.findViewById(R.id.details);
            FABCall = itemView.findViewById(R.id.FABCall);
            FABMail = itemView.findViewById(R.id.FABMail);
            GeneralMethode.mailingAction(FABMail, itemView, data, ViewHolder.this, new Function<T, String>() {
                @Override
                public String apply(T service) {
                    return getEmail(service);
                }
            });


            GeneralMethode.ActionCall(FABCall, itemView, data, ViewHolder.this, new Function<T, String>() {
                @Override
                public String apply(T service) {
                    return getCall(service);
                }
            });



            GeneralMethode.ActionLinkTO(lien,
                    ViewHolder.this, GeneralAdaptar.this, p -> {
                        onClickTo(p);
                        return null;
                    });


            GeneralMethode.ActionMoreDetailsAnnuaire(details,data, ViewHolder.this, new Function<T, String>() {
                @Override
                public String apply(T item) {
                    return getMoreDetails(item);
                }
            });
        }
    }

}
