package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    private TextView tv1;
    private EditText id,nom,des,tam,cat,pre,uni;
    private Button b8,b8B,b8E,b8A;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tv1=(TextView) findViewById(R.id.editText8NomProd);
        id=(EditText)findViewById(R.id.editText8IDprod);
        nom=(EditText)findViewById(R.id.editText8NomProd);
        des=(EditText)findViewById(R.id.editText8DescProd);
        tam=(EditText)findViewById(R.id.editText8TamProd);
        cat=(EditText)findViewById(R.id.editText8Categoria);
        pre=(EditText)findViewById(R.id.editText8PrecioProd);
        uni=(EditText)findViewById(R.id.editText8Unidades);
        b8=(Button) findViewById(R.id.B8registrarProd);
        b8B=(Button) findViewById(R.id.button8Buscar);
        b8E=(Button) findViewById(R.id.B8eliminarProd);
        b8A=(Button) findViewById(R.id.button8ActualizarDatos);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar("http://192.168.1.72/health/insertProd.php");

            }
        });
        b8B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarproductos("http://192.168.1.72/health/buscarProducto.php?c="+id.getText()+"");
            }
        });
        b8A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar("http://192.168.1.72/health/Actualizproducto.php");
            }
        });
        b8E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar("http://192.168.1.72/health/deleteprod.php");
            }
        });

        //String url = "http://192.168.1.72/health/insertProd.php";
    }

    //metodo confirmar registro de producto
    public void registrar (String url){

        final String i = id.getText().toString().replaceAll(" ","%20");
        final String n = nom.getText().toString().replaceAll(" ","%20");
        final String d = des.getText().toString().replaceAll(" ","%20");
        final String t = tam.getText().toString().replaceAll(" ","%20");
        final String c = cat.getText().toString().replaceAll(" ","%20");
        final String p = pre.getText().toString();
        final String u = uni.getText().toString();


        //validar que los campos no esten vacios
        if (nom.length() == 0) {
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
        } else if (des.length() == 0) {
            Toast.makeText(this, "Debes ingresar una descripcion", Toast.LENGTH_SHORT).show();
        } else if (tam.length() == 0) {
            Toast.makeText(this, "Debes ingresar el tamaño", Toast.LENGTH_SHORT).show();
        } else if (cat.length() == 0) {
            Toast.makeText(this, "Debes ingresar la categoría", Toast.LENGTH_SHORT).show();
        } else if (pre.length() == 0) {
            Toast.makeText(this, "Debes ingresar el precio", Toast.LENGTH_SHORT).show();
        }else if (uni.length() == 0) {
            Toast.makeText(this, "Debes ingresar las unidades disponibles", Toast.LENGTH_SHORT).show();
        } else{
            StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),"Operación Exitosa",Toast.LENGTH_SHORT).show();
                   limpiar();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

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
            requestQueue=Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

    }



            private void buscarproductos(String url){
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
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de Conexión",Toast.LENGTH_SHORT).toString();
            }
        }
        );
                requestQueue=Volley.newRequestQueue(this);
                requestQueue.add(jsonArrayRequest);


            }

            public void limpiar(){
                id.setText("");
                nom.setText("");
                des.setText("");
                tam.setText("");
                cat.setText("");
                pre.setText("");
                uni.setText("");
            }
    public void eliminar (String url){


            StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),"Producto Eliminado",Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros=new HashMap<String,String>();
                    parametros.put("i",id.getText().toString());

                    return parametros;


                }
            };
            requestQueue=Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);








}

}
