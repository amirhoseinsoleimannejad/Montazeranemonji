package com.example.amhso.montazeranemonji;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.amhso.montazeranemonji.otherclass.DBHelper;
import com.example.amhso.montazeranemonji.otherclass.G;
import com.example.amhso.montazeranemonji.otherclass.PlayerAdapter;
import com.example.amhso.montazeranemonji.otherclass.video;
import com.takusemba.spotlight.SimpleTarget;
import com.takusemba.spotlight.Spotlight;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class list_sokanrani extends AppCompatActivity {


    private ListView listView;


    private PlayerAdapter playerAdapter;
    private List<video> listnamevideo;
    private String image_sokhanran;
    private String name;
    public DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sokanrani);



        G.activity=this;
        mydb = new DBHelper(G.activity);



        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            image_sokhanran=bundle.getString("image");
            name=bundle.getString("name");

        }
        else{
            finish();
        }




        ImageView iconmarker=(ImageView)findViewById(R.id.icon_bookmarker);
        iconmarker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mydb.insertMarker(name,image_sokhanran);
                ImageView iconmarker2=(ImageView)findViewById(R.id.icon_bookmarker);
                DrawableCompat.setTint(iconmarker2.getDrawable(), ContextCompat.getColor(G.activity, R.color.orange));
//
            }
        });







        listView=(ListView) findViewById(R.id.listmedia);
        listnamevideo = new ArrayList<>();









        String tag_string_req = "string_req";

        final ProgressDialog pDialog = new ProgressDialog(G.activity);
        pDialog.setMessage("در حال بارگزاری...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,G.urlsokhanrani,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("rrrrrrr", "onResponse: "+response);


                        try {

                            JSONArray videoarray;
                            videoarray =new JSONArray(response);

                            for (int i = 0; i < videoarray.length(); i++) {

                                JSONObject c = videoarray.getJSONObject(i);

                                String title=c.getString("title");
                                String path=c.getString("path");

                                video v3=new video(path,title,"","20:50:80","id");
                                listnamevideo.add(v3);

                            }


                            playerAdapter = new PlayerAdapter(G.activity,listnamevideo);
                            listView.setAdapter(playerAdapter);

                        }
                        catch (Exception e){
                            Log.i("baner", "eeeeeeeeee: "+e.toString());
                        }



                        pDialog.hide();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.hide();
            }
        });


        G.getInstance().addToRequestQueue(strReq, tag_string_req);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("fffff", "onItemClick: "+position);

            }

        });





    }




























    @Override
    public void onWindowFocusChanged (boolean hasFocus) {


        if (G.first_start) {

            G.first_start=false;
            ImageView iconmarker = (ImageView) findViewById(R.id.icon_bookmarker);


            int[] oneLocation = new int[2];
            iconmarker.getLocationOnScreen(oneLocation);
            float oneX = oneLocation[0] + iconmarker.getWidth() / 2f;
            float oneY = oneLocation[1] + iconmarker.getWidth() / 2f;
            // make an target
            SimpleTarget firstTarget = new SimpleTarget.Builder(G.activity).setPoint(oneX, oneY)
                    .setRadius(50f)
                    .setTitle("نشانه")
                    .setDescription("در صورتی که بخواهید این مجموعه سخنرانی را انتخاب کنید کلیلک کنید.")
                    .build();


            Spotlight.with(G.activity)
                    .setOverlayColor(ContextCompat.getColor(G.activity, R.color.background))
                    .setDuration(500L)
                    .setAnimation(new DecelerateInterpolator(2f))
                    .setTargets(firstTarget)
                    .setClosedOnTouchedOutside(true)
                    .start();


        }

    }

}
