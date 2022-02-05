package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Terceractivity extends AppCompatActivity {
    private TextView tv1;
    private ListView LV1;
    private String nombre [] = {"Calcular IMC", "Categorías", "Pendiente", "Pendiente", "Pendiente", "Pendiente", "Pendiente"};
    private String nombre2 [] = {"Calcular IMC", "Categorías", "Pendiente", "Pendiente", "Pendiente", "Pendiente", "Pendiente"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceractivity);
        String op=getIntent().getStringExtra("nom");
        //conectar parte logica con grafica

        tv1=(TextView) findViewById(R.id.editTextA3Usuario);
        //instanciar metodo de segunda activity
        String user=getIntent().getStringExtra("user");
        //Asigunar el nombre guardado en segundo activity a text de tercer activity
        tv1.setText("Hola "+ user + "\nElige una Opción");
        LV1=(ListView)findViewById(R.id.ListV1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.list_opciones_health, nombre);
        LV1.setAdapter(adapter);
        LV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv1.setText(" Has elegido "+LV1.getItemAtPosition(position)+ " como "+ nombre2[position]+ " para accesar ");
                if(nombre2[position].equals("Categorías")){
                    Intent iracat=new Intent(Terceractivity.this, listacatecorias.class);
                    startActivity(iracat);
                }



                    }


        });

    }
    //metodo para ir a cuarta activity imc
    public void iraimc(View view){
        Intent iraimc=new Intent(this, cuartaActivity.class);
        startActivity(iraimc);
    }


    //Metodo para ir a main activity
    public void terceramain(View View){
        Intent terceramain=new Intent (this, MainActivity.class);

        startActivity(terceramain);

    }
}
