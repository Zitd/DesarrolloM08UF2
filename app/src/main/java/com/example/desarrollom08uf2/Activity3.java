package com.example.desarrollom08uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {
    MyService myService;
    boolean isBound = false;
Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        b1= findViewById(R.id.button7);
        b2= findViewById(R.id.button8);
        b3= findViewById(R.id.button9);
        b4= findViewById(R.id.button4);
        Toast.makeText(this,R.string.toast2,Toast.LENGTH_SHORT).show();//Aviso de pantalla

    }
    public void goActivityMain(View view) {//Boton de back
        Intent intent = new Intent(Activity3.this,MainActivity.class);
        startActivity(intent);//Volver a la principal y cerrar esta
        finish();//cerrar esta pantalla

    }

    public void playMusic(View view) {//empezar musica
        myService.musicPlayer();
    }

    public void stopMusic(View view) {//Parar musica
        myService.musicStopper();
    }

    public void freeze15(View view) { //SERVICIO DE BLOQUEO
        myService.musicFreeze();
    }

    public void serviceStart(View v) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        b1.setVisibility(View.VISIBLE);//Solo se muetran los botones mientras el servicio esta activo
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);


    }

    public void serviceStop(View v) {
        Intent intent = new Intent(this,MyService.class);
        myService.musicStopper();//Paramos la musica
        stopService(intent);
        b1.setVisibility(View.INVISIBLE);//Solo se muetran los botones mientras el servicio esta activo
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {//Conectar con el servicio
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService=binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        isBound =false;
        }
    };
}
