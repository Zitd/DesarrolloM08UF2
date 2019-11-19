package com.example.desarrollom08uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goActivity1(View view) {//Ir activity 1
        Intent intent = new Intent(MainActivity.this,Activity2.class);
        startActivity(intent);
        finish();//cerrar esta pantalla
    }

    public void goActivity2(View view) {//Ir activity 2
        Intent intent = new Intent(MainActivity.this,Activity3.class);
        startActivity(intent);
        finish();//cerrar esta pantalla
    }
}
