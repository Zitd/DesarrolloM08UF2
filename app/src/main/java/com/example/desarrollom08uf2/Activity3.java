package com.example.desarrollom08uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toast.makeText(this,R.string.toast2,Toast.LENGTH_SHORT).show();

    }
    public void goActivityMain(View view) {
        Intent intent = new Intent(Activity3.this,MainActivity.class);
        startActivity(intent);
        finish();//cerrar esta pantalla

    }
}
