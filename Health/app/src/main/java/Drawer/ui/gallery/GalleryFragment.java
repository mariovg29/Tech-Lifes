package Drawer.ui.gallery;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.health.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Drawer.DrawerActivity;


public class GalleryFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    TextView tv;
    EditText nom,des,tam,cat,pre,uni;
    Button agregar;
    RequestQueue requestQueue;
    //JsonObjectRequest jsonObjectRequest;


    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceSttextViewate) {


        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.tvagregar);
         agregar=(Button)root.findViewById(R.id.BbregistrarProd);
        nom=(EditText) root.findViewById(R.id.etagregarNomProd);
        des=(EditText) root.findViewById(R.id.etagregar8DescProd);
        tam=(EditText) root.findViewById(R.id.etagregarTamProd);
        cat=(EditText) root.findViewById(R.id.etagregarCategoria);
        pre=(EditText) root.findViewById(R.id.etagregarPrecioProd);
        uni=(EditText) root.findViewById(R.id.etagregarUnidades);

        //request= Volley.newRequestQueue(getContext());
                agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addprod("http://192.168.1.72/health/insertProd.php");

            }
        });

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void addprod(String url) {


        //final String i = id.getText().toString().replaceAll(" ","%20");
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
                    //parametros.put("i",id.getText().toString());
                    parametros.put("n",nom.getText().toString());
                    parametros.put("d",des.getText().toString());
                    parametros.put("t",tam.getText().toString());
                    parametros.put("c",cat.getText().toString());
                    parametros.put("p",pre.getText().toString());
                    parametros.put("u",uni.getText().toString());
                    return parametros;


                }
            };
            requestQueue=Volley.newRequestQueue(getContext());
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
