package com.example.cronometro;


import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int segundos = 0;
    private boolean running;
    public String time ="0:00:00";
    String textoVueltas="";
    int vueltas=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiempo();
    }
    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickRestart(View view) {

        running = false;
        segundos = 0;
        time ="0:00:00";
        textoVueltas="";
        vueltas=0;
        final TextView textNumero= (TextView) findViewById(R.id.textNumero);
        textNumero.setText("####");
    }
    public void onClickVueltas(View view){
        final TextView textNumero = (TextView) findViewById(R.id.textNumero);
        if(vueltas<10) {
            vueltas++;
            textoVueltas=textoVueltas+"Vuelta" +vueltas+" : "+time+"\n";
            textNumero.setText(textoVueltas);
        }
    }

    private void tiempo(){

        final TextView timeView = (TextView) findViewById(R.id.time);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = segundos / 3600;
                int minutes = (segundos % 3600) / 60;
                int secs = segundos % 60;

                time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if (running) {
                    segundos++;
                }
                handler.postDelayed(this,1);
            }
        });
    }

}