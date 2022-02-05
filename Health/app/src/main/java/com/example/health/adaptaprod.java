package com.example.health;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adaptaprod extends RecyclerView.Adapter<adaptaprod.ViewHolderProd> {
    private Context cntx;
    public List<prodsclass> prodsList;

    public adaptaprod( List<prodsclass> prodsList) {
                this.prodsList = prodsList;
    }

    @NonNull
    @Override
    public ViewHolderProd onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(cntx);
        View view=inflater.inflate(R.layout.list_prod, null );
        return new ViewHolderProd(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProd holder, int position) {
        prodsclass  prod=prodsList.get(position);

        holder.textViewnop.setText(prod.getNOMBRE());
        holder.textViewdesp.setText(prod.getDESCRIPCION());
        holder.textViewprep.setText(String.valueOf(prod.getPRECIO()));
        Glide.with(cntx)
                .load(prod.getFOTO())
                .into (holder.ipro);

    }

    @Override
    public int getItemCount() {
        return prodsList.size();
    }

    public class ViewHolderProd extends RecyclerView.ViewHolder {
        TextView textViewnop,textViewdesp,textViewprep;
        ImageView  ipro;
        public ViewHolderProd(@NonNull View itemView) {

            super(itemView);
            textViewnop=itemView.findViewById(R.id.textViewnopro);
            textViewdesp=itemView.findViewById(R.id.textViewdespro);
            textViewprep=itemView.findViewById(R.id.textViewprepro);
            ipro=itemView.findViewById(R.id.iprod);
        }
    }
}
