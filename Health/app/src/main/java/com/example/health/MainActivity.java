package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //colocarel icono en el accion bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }
//Metodo para segunda activity
    public void irasegunda(View View){
        Intent irasegunda=new Intent (this, Segundactivity.class);
        //identificar si es medico=a
        String tipo="logsoli";
       irasegunda.putExtra("TIPO", tipo);
        startActivity(irasegunda);


    }
    //Metodo para paciente
    public void paciente(View View){
        Intent irasegunda=new Intent (this, Segundactivity.class);
        //identificar si es  paciente=b
        String tipo="logprov";
        irasegunda.putExtra("TIPO", tipo);
        startActivity(irasegunda);


        Toast.makeText(this, "Has Ingresado como Pacinte", Toast.LENGTH_SHORT).show();

    }
}
