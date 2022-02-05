package com.example.health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adaptadores.AdaptadorFragmentLista;
import constructoreslistas.Listanoimg;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLista extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerfragmentlist;
    ArrayList<Listanoimg>listaproductos;
    //ProgressBar progressBar;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentLista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLista.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLista newInstance(String param1, String param2) {
        FragmentLista fragment = new FragmentLista();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listaproductos=new ArrayList<>();
        View vista=inflater.inflate(R.layout.fragment_lista,container,false);

        recyclerfragmentlist= vista.findViewById(R.id.recyclefragmentlista);
        recyclerfragmentlist.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerfragmentlist.setHasFixedSize(true);
        request = Volley.newRequestQueue(getContext());
        cargarfrgmentlista();
        // Inflate the layout for this fragment
        return vista;
    }

    private void cargarfrgmentlista() {
       // progressBar= new ProgressBar(getContext());

        String url="http://localhost/health/listaproductos.php ";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se puede conectar"+error.toString(),Toast.LENGTH_SHORT);
        System.out.println();
        Log.d("Error",error.toString());

    }
    @Override
    public void onResponse(JSONObject response) {
        Listanoimg prod=null;
               JSONArray json=response.optJSONArray("productos");
        try {
        for (int i=0;i<json.length();i++) {
           prod = new Listanoimg();
            JSONObject jsonObject = null;

            jsonObject = json.getJSONObject(i);
            prod.setNOMBRE(jsonObject.optString("NOMBRE"));
            prod.setDESCRIPCION(jsonObject.optString("DESCRIPCION"));
            prod.setPRECIO(jsonObject.optInt("PRECIO"));
            listaproductos.add(prod);

        }
            AdaptadorFragmentLista adaptadorFragmentLista=new AdaptadorFragmentLista(listaproductos);
        recyclerfragmentlist.setAdapter(adaptadorFragmentLista);


            } catch (JSONException e) {
            Toast.makeText(getContext(),"Error de comunicacion con el servidor",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }



}