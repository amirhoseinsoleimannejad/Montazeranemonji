package com.example.amhso.montazeranemonji;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.amhso.montazeranemonji.otherclass.NetworkJSONLoader;


public class VolleyActivity extends Activity {

    String urlJSON = "http://montazer.ir/api/app_vas_taxonomy";
    Button jsonLoaderBtn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        tv = (TextView) findViewById(R.id.textView1);


        jsonLoaderBtn = (Button) findViewById(R.id.buttonJSONRequest);
        jsonLoaderBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                NetworkJSONLoader j = new NetworkJSONLoader(VolleyActivity.this,
                        tv);
                j.requestJSON(urlJSON);
            }
        });
    }
}
