package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import Drawer.DrawerActivity;


public class Segundactivity extends AppCompatActivity {
     EditText etu,etp;
    TextView b,u;
     Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundactivity);
        etu=(EditText)findViewById(R.id.editTextUsuario);
        etp=(EditText)findViewById(R.id.editText2Password);
        b=(TextView)findViewById(R.id.textView2Bienvenidas);
        u=(TextView)findViewById(R.id.textView2usuario);
        boton=(Button)findViewById(R.id.button2);
        //guardar datos de login  usuario
        SharedPreferences preferences= getSharedPreferences("datos", Context.MODE_PRIVATE);
        //asignar usuario siempre que inicie la app
        etu.setText(preferences.getString("mail",""));
        //asignar el valor de preferences a text inicial
        u.setText(preferences.getString("mail",""));
        final String tip=getIntent().getStringExtra("TIPO");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar("http://192.168.1.72/health/"+tip+".php");
            }
        });

    }
    //Metodo para tercer activity
    private void validar(String URL){
        //obtener que tipo de usuario ingresó


        //obtener dato guardado del mail
        SharedPreferences preferencias= getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor=preferencias.edit();
        Obj_editor.putString("mail",etu.getText().toString());
        Obj_editor.commit();
 //String usuario=etu.getText().toString();
//String pas=etp.getText().toString();
         //metodo para cambiar a siguiente activity
       // final Intent iratercer=new Intent (this, Terceractivity.class);
        //guardar el nombre que ingresa el usuario
       // iratercer.putExtra("user", etu.getText().toString());

        //condicionales
     //   if (usuario.length()==0){
       //     Toast.makeText(this, "Debes ingresar un nombre",Toast.LENGTH_SHORT).show();
         //    }
        //else if(pas.length()==0) {
        //    Toast.makeText(this, "Debes Ingresar tu Contraseña", Toast.LENGTH_SHORT).show();
       // }
         //if(usuario.length()!=0 && pas.length()!=0) {
           // String url = "http://192.168.1.69/health/insert.php?u=" + etu + "&p="+etp;
            StringRequest log=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response){
                    final String tip=getIntent().getStringExtra("TIPO");
                    if(tip.equals("logsoli")){
                    if(!response.isEmpty()) {
                        Intent avanza = new Intent(getApplicationContext(), Terceractivity.class);
                        avanza.putExtra("user", etu.getText().toString());
                        startActivity(avanza);
                        //startActivity(iratercer);
                    } else{
                        Toast.makeText(Segundactivity.this, "Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                    }
                    } if(tip.equals("logprov")){
                        if(!response.isEmpty()) {
                            Intent redir = new Intent(Segundactivity.this, DrawerActivity.class);
                            startActivity(redir);
                            // Intent redir = new Intent(Segundactivity.this,Ver_productos.class);
                            //startActivity(redir);
                        } else{
                            Toast.makeText(Segundactivity.this, "Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Segundactivity.this, error.toString(),Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros=new HashMap<String,String>();
                    parametros.put("u", etu.getText().toString());
                    parametros.put("p", etp.getText().toString());
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(log);
            //iniciar intent cambio a tercera activity
           // startActivity(iratercer);
            //finish();

        }
        //else{Toast.makeText(this,"has ingresado credenciales erroneas",Toast.LENGTH_SHORT).show();}


    //Metodo para Ir a Registrarse Registro5Activity
    public void registrarse(View view) {
        final String tip=getIntent().getStringExtra("TIPO");
        ////metodo para  medico=a
        if (tip.equals("logsoli")){
            Intent iraregistro = new Intent(this, Registro5Activity.class);
            String tipo2="insert";
            iraregistro.putExtra("TIPO2",tipo2);
        startActivity(iraregistro);
    }////metodo para  paciente b
        if(tip.equals("logprov")){
            Intent iraregistro = new Intent(this, Registro5Activity.class);
            String tipo2="insertp";
            iraregistro.putExtra("TIPO2",tipo2);
            startActivity(iraregistro);
        }

    }
}
