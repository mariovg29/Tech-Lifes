package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class Registro5Activity extends AppCompatActivity  {
    private TextView tv1;
    private EditText nom,ap_p,ap_m,us,pass, tel1,tel2,correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro5);
        tv1=(TextView) findViewById(R.id.textView6registro);
        nom=(EditText)findViewById(R.id.editText6nombre);
        ap_p=(EditText)findViewById(R.id.editText6ap_pat);
        ap_m=(EditText)findViewById(R.id.editText6ap_mat);
        correo=(EditText)findViewById(R.id.editText6correo);
        us=(EditText)findViewById(R.id.editText6usuario);
        pass=(EditText)findViewById(R.id.editText6contraseña);
        tel1=(EditText)findViewById(R.id.editText6tel1);
        tel2=(EditText)findViewById(R.id.editText6tel2);




    }
    //metodo confirmar

    public void confirmar(View View) {
        final String tabla = getIntent().getStringExtra("TIPO2");
        Intent regdireccion = new Intent(Registro5Activity.this, Reg_DirMain2Activity.class);


        if(tabla.equals("insertp")){
            String dir="Insert_dir_prov.php";
            regdireccion.putExtra("regdirsolicitante", dir);

        }


        //final String tabla="insert";
        //if(tip.equals("logprov")){tabla="insertp";}


            final String n = nom.getText().toString();
            final String ap = ap_p.getText().toString();
            final String am = ap_m.getText().toString();
            final String corr = correo.getText().toString();
            final String u = us.getText().toString();
            final String p = pass.getText().toString();
            final String t1 = tel1.getText().toString();
            final String t2 = tel2.getText().toString();

            //validar que los campos no esten vacios
            if (n.length() == 0) {
                Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
            } else if (ap.length() == 0) {
                Toast.makeText(this, "Debes ingresar tu Apellido Paterno", Toast.LENGTH_SHORT).show();
            } else if (am.length() == 0) {
                Toast.makeText(this, "Debes ingresar tu Apellido Materno", Toast.LENGTH_SHORT).show();
            } else if (corr.length() == 0) {
                Toast.makeText(this, "Debes ingresar tu Correo", Toast.LENGTH_SHORT).show();
            } else if (u.length() == 0) {
                Toast.makeText(this, "Debes ingresar un nombre de usuario", Toast.LENGTH_SHORT).show();
            } else if (p.length() == 0) {
                Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT).show();
            } else if (t1.length() == 0) {
                Toast.makeText(this, "Debes ingresar un numero de telefono", Toast.LENGTH_SHORT).show();
            } else {


                    String url = "http://192.168.1.72/health/"+tabla+".php?n=" + n + "&ap=" + ap + "&am=" + am + "&corr=" + corr + "&u=" + u + "&p=" + p + "&t1=" + t1 + "&t2=" + t2;
                RequestQueue servicio = Volley.newRequestQueue(this);
                StringRequest respuesta = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()) {
                            //si accedio como solicitante(paciente)(A), registrar direccion en tabla solicitante
                            if(tabla.equals("insert")) {
                                String dire="Insert_dir_sol.php";
                                Toast.makeText(getApplicationContext(), "ID asignado: " + response, Toast.LENGTH_LONG).show();
                                String id = response;
                                //tel2.setText(response);
                                Intent dir = new Intent(Registro5Activity.this, Reg_DirMain2Activity.class);
                                dir.putExtra("ID", id);
                                dir.putExtra("regdirsolicitante", dire);
                                startActivity(dir);
                                finish();
                            }
                            //si accedio como proveedor,(Medico),(B) registrar direccion en tabla proveedor
                            if(tabla.equals("insertp")) {
                                String dire="Insert_dir_prov.php";
                                Toast.makeText(getApplicationContext(), "ID asignado: " + response, Toast.LENGTH_LONG).show();
                                String id = response;
                                //tel2.setText(response);
                                Intent dir = new Intent(Registro5Activity.this, Reg_DirMain2Activity.class);
                                dir.putExtra("ID", id);
                                dir.putExtra("regdirsolicitante", dire);
                                startActivity(dir);
                                finish();
                            }
                        } else {
                            Intent registrado = new Intent(Registro5Activity.this, Registro5Activity.class);
                            Toast.makeText(getApplicationContext(), "Correo ya Registrado", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registro5Activity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
                servicio.add(respuesta);
            }



    }
    //metodo ir a registrar direccion
    public void regdireccion(View View){
        Intent intent=new Intent(this, Reg_DirMain2Activity.class);
        startActivity(intent);

    }
}
