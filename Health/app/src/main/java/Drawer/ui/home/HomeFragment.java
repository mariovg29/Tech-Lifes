package Drawer.ui.home;

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


public class HomeFragment extends Fragment implements Response.Listener <JSONObject>,Response.ErrorListener {
    TextView tv;
    EditText id,nom,des,tam,cat,pre,uni;
    Button actualizar,buscar;
    RequestQueue requestQueue;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.tvactualizar);
        buscar=(Button)root.findViewById(R.id.bacBuscar);
        id=(EditText)root.findViewById(R.id.etacIDprod);
        actualizar=(Button)root.findViewById(R.id.BacconfProd);
        nom=(EditText) root.findViewById(R.id.etacNomProd);
        des=(EditText) root.findViewById(R.id.etacDescProd);
        tam=(EditText) root.findViewById(R.id.etacTamProd);
        cat=(EditText) root.findViewById(R.id.etacCategoria);
        pre=(EditText) root.findViewById(R.id.etacPrecioProd);
        uni=(EditText) root.findViewById(R.id.etacUnidades);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar("http://192.168.1.72/health/Actualizproducto.php");
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarproductos("http://192.168.1.72/health/buscarProducto.php?c="+id.getText()+"");
            }
        });
        return root;
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
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);


    }



    private void actualizar(String s) {
        String url="http://192.168.1.72/health/Actualizproducto.php";
        final String i = id.getText().toString().replaceAll(" ","%20");
        final String n = nom.getText().toString().replaceAll(" ","%20");
        final String d = des.getText().toString().replaceAll(" ","%20");
        final String t = tam.getText().toString().replaceAll(" ","%20");
        final String c = cat.getText().toString().replaceAll(" ","%20");
        final String p = pre.getText().toString();
        final String u = uni.getText().toString();


        //validar que los campos no esten vacios
        if (nom.length() == 0) {
            Toast.makeText(getContext(), "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
        } else if (des.length() == 0) {
            Toast.makeText(getContext(), "Debes ingresar una descripcion", Toast.LENGTH_SHORT).show();
        } else if (tam.length() == 0) {
            Toast.makeText(getContext(), "Debes ingresar el tamaño", Toast.LENGTH_SHORT).show();
        } else if (cat.length() == 0) {
            Toast.makeText(getContext(), "Debes ingresar la categoría", Toast.LENGTH_SHORT).show();
        } else if (pre.length() == 0) {
            Toast.makeText(getContext(), "Debes ingresar el precio", Toast.LENGTH_SHORT).show();
        }else if (uni.length() == 0) {
            Toast.makeText(getContext(), "Debes ingresar las unidades disponibles", Toast.LENGTH_SHORT).show();
        } else{

            StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getContext(),"Operación Exitosa",Toast.LENGTH_SHORT).show();
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
                    parametros.put("n",nom.getText().toString());
                    parametros.put("d",des.getText().toString());
                    parametros.put("t",tam.getText().toString());
                    parametros.put("c",cat.getText().toString());
                    parametros.put("p",pre.getText().toString());
                    parametros.put("u",uni.getText().toString());
                    return parametros;


                }
            };
            requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);

        }


    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
