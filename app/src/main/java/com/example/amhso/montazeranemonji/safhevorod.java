package com.example.amhso.montazeranemonji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.amhso.montazeranemonji.otherclass.G;


public class safhevorod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safhevorod);

        G.activity=this;
        if(!G.checknet()){
            Intent i = new Intent(G.activity, ErrorActivity.class);

            startActivity(i);

        }
        else {


            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    YoYo.with(Techniques.Pulse)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.imageView2));


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent i = new Intent(G.activity, MainActivity.class);
                            startActivity(i);
                            finish();


                        }
                    }, 1000);


                }
            });


        }


    }
}
