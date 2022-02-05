package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class cuartaActivity extends AppCompatActivity {
    private EditText p, t;
    private TextView res, m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarta);
        p = (EditText) findViewById(R.id.editTextA4peso);
        t = (EditText) findViewById(R.id.editTextA4talla);
        res = (TextView) findViewById(R.id.textViewA4Resultadoimc);
        m = (TextView) findViewById(R.id.textViewA4Mensaje);
    }
        public void cuartatercera (View view){
            Intent cuartatercera = new Intent(this, Terceractivity.class);
            startActivity(cuartatercera);
        }
        //Metodo Boston inicio
        public void cuartamain (View view){
            Intent cuartamain = new Intent(this, MainActivity.class);
            startActivity(cuartamain);

        }
        //Metodo boton Regresar
        public void regesar (View view){


            Intent regresar = new Intent(this, Terceractivity.class);
            startActivity(regresar);

        }
        //Metodo Calcular IMC
        public void calcularimc (View view) {
            if (p.length() == 0) {
                Toast.makeText(this, "Debes ingresar tu peso", Toast.LENGTH_SHORT).show();
            } else if (t.length() == 0) {
                Toast.makeText(this, "Debes ingresar tu peso", Toast.LENGTH_SHORT).show();
            } else {
                String valorpeso = p.getText().toString();
                String valortalla = t.getText().toString();
                double peso = Double.parseDouble(valorpeso);
                double talla = Double.parseDouble(valortalla);
                //formula imc
                double resultado = peso / ((talla / 100) * (talla / 100));
                String Resultadoimc = String.valueOf(resultado);
                res.setText(Resultadoimc);
                String estado = "null";
                if (resultado < 18.5) {
                    estado = "Bajo Peso ó Delgado ";
                    m.setText("De acuerdo a la tabla de la OMS  su clasificacion es  " + estado);
                }
                if (resultado >= 18.5 && (resultado <= 24.9)) {
                    estado = "Adecuado o Aceptable";
                    m.setText("De acuerdo a la tabla de la OMS  su clasificacion es  " + estado);
                }
                if (resultado >= 25 && (resultado <= 29.9)) {
                    estado = "Sobrepeso ";
                    m.setText("De acuerdo a la tabla de la OMS  su clasificacion es  " + estado);
                }
                if (resultado >= 30 && (resultado <= 34.9)) {
                    estado = "Obresidad Grado 1 u Obesidad ";
                    m.setText("De acuerdo a la tabla de la OMS  su clasificacion es  " + estado);
                }
                if (resultado >= 35 && (resultado <= 39.9)) {
                    estado = "Obresidad Grado 2 u Obesidad ";
                    m.setText("De acuerdo a la tabla de la OMS  su clasificacion es  " + estado);
                }
                if (resultado >= 40) {
                    estado = "Obresidad Grado 2 u Obesidad ";
                    m.setText("De acuerdo a la tabla de la OMS  su clasificacion es  " + estado);
                }

                Toast.makeText(this, "Este boton ya sirve DON PENDEJO", Toast.LENGTH_SHORT).show();
            }
        }
    }







