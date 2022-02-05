package com.example.health;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {
    public List<Productosclass> listaproductos;

    public AdaptadorProducto(List<Productosclass> listaproductos) {
        this.listaproductos = listaproductos;
    }

    @NonNull
    @Override
    public AdaptadorProducto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProducto.ViewHolder holder, int position) {
        holder.Nombre.setText(listaproductos.get(position).getNombre());
        holder.Descripcion.setText(listaproductos.get(position).getDescripcion());
        holder.imgproducto.setImageResource(listaproductos.get(position).getImgproducto());

    }

    @Override
    public int getItemCount() {
        return listaproductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Nombre, Descripcion;
        ImageView imgproducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre=(TextView)itemView.findViewById(R.id.textView9nomprod);
            Descripcion=(TextView)itemView.findViewById(R.id.textView9desprod);
            imgproducto=(ImageView)itemView.findViewById(R.id.imageView9imgprod);
        }


    }
}
