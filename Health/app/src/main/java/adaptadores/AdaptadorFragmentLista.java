package adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health.R;

import java.util.List;

import constructoreslistas.Listanoimg;

public class AdaptadorFragmentLista extends RecyclerView.Adapter<AdaptadorFragmentLista.listaprodsHolder> {




    @NonNull
    @Override
    public listaprodsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prod,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT);
       vista.setLayoutParams(layoutParams);
        //listaprodsHolder hold=new listaprodsHolder(vista);
        //return hold;
        return new listaprodsHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull listaprodsHolder holder, int position) {
        holder.nom.setText(listaproductos.get(position).getNOMBRE());
        holder.des.setText(listaproductos.get(position).getDESCRIPCION());
        holder.pre.setText(listaproductos.get(position).getPRECIO());

    }

    @Override
    public int getItemCount() {
        return listaproductos.size();
    }

    public class listaprodsHolder extends RecyclerView.ViewHolder {
          TextView nom,des,pre;
        public listaprodsHolder(@NonNull View itemView) {
            super(itemView);
            nom=(TextView)itemView.findViewById(R.id.TVfragmentlistanmobre);
            des=(TextView)itemView.findViewById(R.id.TVfragmentlistadescripcion);
            pre=(TextView)itemView.findViewById(R.id.TVfragmentlistaprecio);

        }

    }
    public List<Listanoimg>listaproductos;

    public AdaptadorFragmentLista(List<Listanoimg> listaproductos) {
        this.listaproductos = listaproductos;
    }
}
