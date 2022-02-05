package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Esplash2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esplash2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Inicio=new Intent(Esplash2Activity.this, MainActivity.class);
                startActivity(Inicio);
                finish();


            }
        },3000);

    }
}
