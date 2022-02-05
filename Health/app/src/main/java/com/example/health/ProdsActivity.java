package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ProdsActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    RequestQueue requestQueue;
    private RecyclerView recyclerViewprod;
    private adaptaprod adaptadorProd;
    JsonObjectRequest jsonObjectRequest;
    List<prodsclass> prodsList =new ArrayList<>();

    private static final String url="http://192.168.1.72/health/buscarProducto.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prods);
        recyclerViewprod=(RecyclerView)findViewById(R.id.reciprod);
        recyclerViewprod.setHasFixedSize(true);
        recyclerViewprod.setLayoutManager(new LinearLayoutManager(this));
        adaptadorProd=new adaptaprod(prodsList);
        recyclerViewprod.setAdapter(adaptadorProd);






        listarproductos();



    }

    private void listarproductos() {
        String url="http://192.168.1.72/health/buscarProducto.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);




    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"no conecta"+error.toString(),Toast.LENGTH_SHORT);

    }

    @Override
    public void onResponse(JSONObject response) {
      

    }
}
