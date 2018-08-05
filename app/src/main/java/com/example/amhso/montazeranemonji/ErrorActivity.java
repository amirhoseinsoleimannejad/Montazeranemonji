package com.example.amhso.montazeranemonji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.amhso.montazeranemonji.otherclass.G;

public class ErrorActivity extends AppCompatActivity {


    String text_error="";

    @Override
    public void onBackPressed() {

        if (G.checknet()){
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);






        TextView te=(TextView) findViewById(R.id.text_error);
        te.setText("ارتباط خود را با شبکه برقرار کنید با تشکر");
    }
}
