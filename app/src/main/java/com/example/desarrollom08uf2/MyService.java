package com.example.desarrollom08uf2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//Aqui ponemos la logica
        Toast.makeText(this,R.string.playing,Toast.LENGTH_SHORT).show();
        if (player !=null && player.isPlaying())//detecta su estado para no crear varios
            player.stop(); //Si ya está funcionando lo para
        player = MediaPlayer.create(this,R.raw.song);//Reproduce la canción
        player.setLooping(true);//La repite
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//La terminamos bien
        Toast.makeText(this,R.string.stop,Toast.LENGTH_SHORT).show();
        if(player != null)//Si esta funcionando
            player.release();//Lo para
        stopSelf();//Se para a si mismo
        super.onDestroy();
        //aqui destruimos el servicio
    }
}
