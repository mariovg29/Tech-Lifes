package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class listacatecorias extends AppCompatActivity {
    private RecyclerView recyclerViewproductos;
    private AdaptadorProducto adaptadorProducto;
    List<Productosclass>productos =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);
               recyclerViewproductos=(RecyclerView)findViewById(R.id.recyclerProducto);
        recyclerViewproductos.setLayoutManager(new LinearLayoutManager(this));
        adaptadorProducto=new AdaptadorProducto(listaproductos());
        recyclerViewproductos.setAdapter(adaptadorProducto);

    }
    public List<Productosclass>listaproductos(){


        productos.add(new Productosclass
                ("Mario Valadez","eres un hijo de tu chingada madre cabron hijo de la gaga",R.drawable.siete));
        productos.add(new Productosclass
                ("Dante Valadez","eres un hijo de tu chingada madre cabron hijo de la gaga",R.drawable.uno));
        productos.add(new Productosclass
                ("Brissa Valadez","eres un hijo de tu chingada madre cabron hijo de la gaga",R.drawable.siete));
        productos.add(new Productosclass
                ("Fabiola Valadez","eres un hijo de tu chingada madre cabron hijo de la gaga",R.drawable.uno));
        productos.add(new Productosclass
                ("Manuela Valadez","eres un hijo de tu chingada madre cabron hijo de la gaga",R.drawable.siete));
        return productos;


    }

}
