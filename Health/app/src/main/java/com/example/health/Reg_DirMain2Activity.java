package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Reg_DirMain2Activity extends AppCompatActivity {
    private TextView tvDir1;
    private EditText calle,col,numEx,Numint,cp, edo,mun,IDu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__dir_main2);
        tvDir1=(TextView) findViewById(R.id.textView7registro);
        calle=(EditText)findViewById(R.id.editText7calle);
        col=(EditText)findViewById(R.id.editText7col);
        numEx=(EditText)findViewById(R.id.editText7num);
        Numint=(EditText)findViewById(R.id.editText7numint);
        cp=(EditText)findViewById(R.id.editText7cp);
        edo=(EditText)findViewById(R.id.editText7edo);
        mun=(EditText)findViewById(R.id.editText7munic);
        IDu=(EditText)findViewById(R.id.editText7IDu);
        //instanciar resultado obtenido de activity anterior ID Usuario
        Intent dir_inicio = new Intent(this, Segundactivity.class);
        //startActivity(dir_inicio);
        String ID=getIntent().getStringExtra("ID");
        //asignar ID textView idusuario
        IDu.setText(ID);



    }
    //registrar direccion
    public void confirmardireccion(View View) {
        final String ca=calle.getText().toString();
        final String co=col.getText().toString();
        final String ne=numEx.getText().toString();
        final String  Ni=Numint.getText().toString();
        final String cop=cp.getText().toString();
        final String e=edo.getText().toString();
        final String m=mun.getText().toString();
        final String id=IDu.getText().toString();
        //final String idu=IDu.getText().toString();
        final Intent dir_princ=new Intent(this, Segundactivity.class);


        //Validar que los campos no estan vacios
        if (co.length()==0){
            Toast.makeText(this, "Debes ingresar una colonia",Toast.LENGTH_SHORT).show();
        }
        else if (ca.length()==0){
            Toast.makeText(this, "Debes ingresar una calle",Toast.LENGTH_SHORT).show();
        }
        else if (ne.length()==0){
            Toast.makeText(this, "Debes ingresar numero exterior",Toast.LENGTH_SHORT).show();
        }
        else if (Ni.length()==0){
            Toast.makeText(this, "Debes ingresar numero interior o descripción",Toast.LENGTH_SHORT).show();
        }
        else if (cop.length()==0){
            Toast.makeText(this, "Debes ingresar un código postal",Toast.LENGTH_SHORT).show();
        }
        else if (e.length()==0){
            Toast.makeText(this, "Debes ingresar un Estado",Toast.LENGTH_SHORT).show();
        }
        else if (m.length()==0){
            Toast.makeText(this, "Debes ingresar un Municipio",Toast.LENGTH_SHORT).show();
        }else {
            final String dire=getIntent().getStringExtra("regdirsolicitante");
            Toast.makeText(this, "Datos Registrados", Toast.LENGTH_SHORT).show();
            String url = "http://192.168.1.72/health/"+dire+"?calle=" + ca + "&col=" + co + "&numEx=" + ne + "&Numint=" + Ni + "&cp=" + cop + "&edo=" + e + "&mun=" + m + "&IDu=" + id;
            RequestQueue servicio = Volley.newRequestQueue(this);
            StringRequest respuesta=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response+dire,Toast.LENGTH_SHORT).show();

                 //   if (tipo.equals("logprov")){
                   //     Intent regpro=new Intent(Reg_DirMain2Activity.this,RegistroActivity.class);
                     //   startActivity(regpro);
                        finish();
                    //}else {
                      //  startActivity(dir_princ);
                        finish();
                   // }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error",Toast.LENGTH_SHORT).show();

                }
            });
            servicio.add(respuesta);
        }


    }


    //metodo regresar al inicio
    public void dir_main(View View){
        Intent dir_inicio=new Intent(this, MainActivity.class);
        startActivity(dir_inicio);
           }
}
