package Drawer.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.health.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SlideshowFragment extends Fragment {
    TextView tv;
    EditText id,nom,des,tam,cat,pre,uni;
    Button eliminar,buscar;
    RequestQueue requestQueue;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.tvborrar);
        buscar=(Button)root.findViewById(R.id.beliBuscar);
        id=(EditText)root.findViewById(R.id.etaeIDprod);
        eliminar=(Button)root.findViewById(R.id.BeliconfProd);
        nom=(EditText) root.findViewById(R.id.etaeNomProd);
        des=(EditText) root.findViewById(R.id.etaeDescProd);
        tam=(EditText) root.findViewById(R.id.etaeTamProd);
        cat=(EditText) root.findViewById(R.id.etaeCategoria);
        pre=(EditText) root.findViewById(R.id.etaePrecioProd);
        uni=(EditText) root.findViewById(R.id.etaeUnidades);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarproductos("http://192.168.1.72/health/buscarProducto.php?c="+id.getText()+"");
            }
        });
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarprod("http://192.168.1.72/health/deleteprod.php");
            }
        });
        return root;
    }

    private void eliminarprod(String url) {

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),"Producto Eliminado",Toast.LENGTH_SHORT).show();
                id.setText("");
                nom.setText("");
                des.setText("");
                tam.setText("");
                cat.setText("");
                pre.setText("");
                uni.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("i",id.getText().toString());

                return parametros;


            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);








    }


    private void buscarproductos(String url) {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        nom.setText(jsonObject.getString("NOMBRE"));
                        des.setText(jsonObject.getString("DESCRIPCION"));
                        tam.setText(jsonObject.getString("SIZE"));
                        cat.setText(jsonObject.getString("CATEGORIA"));
                        pre.setText(jsonObject.getString("PRECIO"));
                        uni.setText(jsonObject.getString("UNIDADES"));


                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error de Conexión",Toast.LENGTH_SHORT).toString();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);


    }
}

