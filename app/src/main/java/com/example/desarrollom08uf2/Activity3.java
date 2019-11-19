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
        Toast.makeText(this,R.string.toast2,Toast.LENGTH_SHORT).show();//Aviso de pantalla

    }
    public void goActivityMain(View view) {
        Intent intent = new Intent(Activity3.this,MainActivity.class);
        startActivity(intent);//Volver a la principal y cerrar esta
        finish();//cerrar esta pantalla

    }

    public void freeze15(View view) { //SERVICIO DE BLOQUEO
       try{
            Thread.sleep(150000);//Bloqueamos 150 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(View view) {//Reproducir musica
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    public void stopMusic(View view) {//Parar musica
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }
}
