package com.example.amhso.montazeranemonji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.amhso.montazeranemonji.otherclass.G;


public class safhevorod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safhevorod);

        G.activity=this;
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent i = new Intent(G.activity,MainActivity.class);
//                startActivity(i);
//                finish();
//
//            }
//        },2000);


        ImageView imageView=(ImageView)findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(G.activity,MainActivity.class);
                startActivity(i);
                finish();

            }
        });





    }
}
