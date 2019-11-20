package com.example.desarrollom08uf2;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
public class MyService extends Service {

    private static MediaPlayer player;

    private final IBinder iBinder = new LocalBinder();

    class LocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//Aqui ponemos la logica
        Toast.makeText(this,R.string.serviceStarted,Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    public void musicPlayer(){//Reproduce la musica al ser llamado
        Toast.makeText(this,R.string.playing,Toast.LENGTH_SHORT).show();

        if (player !=null && player.isPlaying())//detecta su estado para no crear varios
            player.stop(); //Si ya está funcionando lo para
        player = MediaPlayer.create(this, R.raw.song);//Reproduce la canción
        player.setLooping(true);//La repite
        player.start();
    }

    public void musicStopper(){//Para la musica si se llama
        Toast.makeText(this,R.string.stop,Toast.LENGTH_SHORT).show();
        if(player != null) {//Si esta funcionando
            player.stop();//Lo para
        }
    }
    public void musicFreeze(){//Congelña la aplicación si se llama
        try{
            Thread.sleep(150000);//Bloqueamos 150 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {//La terminamos bien
        super.onDestroy();//aqui destruimos el servicio
        player.release();
        stopSelf();//Se para a si mismo


    }


}
