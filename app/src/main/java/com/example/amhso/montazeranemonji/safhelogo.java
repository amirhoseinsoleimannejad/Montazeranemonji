package com.example.amhso.montazeranemonji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.amhso.montazeranemonji.otherclass.G;


public class safhelogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safhelogo);

        G.activity=this;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(G.activity,safhevorod.class);
                startActivity(i);
                finish();

            }
        },5000);


    }
}
