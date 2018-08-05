package com.example.amhso.montazeranemonji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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


        if(!G.checknet()){
            Intent i = new Intent(G.activity, ErrorActivity.class);

            startActivity(i);

        }
        else {
            mydb = new DBHelper(G.activity);


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {

                image_sokhanran = bundle.getString("image");
                name = bundle.getString("name");

            } else {
                finish();
            }


            ImageView iconmarker = (ImageView) findViewById(R.id.icon_bookmarker);
            iconmarker.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    mydb.insertMarker(name, image_sokhanran);

//                DrawableCompat.setTint(iconmarker2.getDrawable(), ContextCompat.getColor(G.activity, R.color.orange));
//  public void jalil(View view) {
                    String imagName = "iiconzeddddd";
                    int res = getResources().getIdentifier(imagName, "drawable", getPackageName());
                    ImageView iconmarker2 = (ImageView) findViewById(R.id.icon_bookmarker);
                    iconmarker2.setImageResource(res);

//    }
                }
            });


            listView = (ListView) findViewById(R.id.listmedia);
            listnamevideo = new ArrayList<>();


            String tag_string_req = "string_req";

            final ProgressDialog pDialog = new ProgressDialog(G.activity);
            pDialog.setMessage("در حال بارگزاری...");
            pDialog.show();

            StringRequest strReq = new StringRequest(Request.Method.GET, G.urlsokhanrani,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                            Log.i("rrrrrrr", "onResponse: " + response);


                            try {

                                JSONArray videoarray;
                                videoarray = new JSONArray(response);

                                for (int i = 0; i < videoarray.length(); i++) {

                                    JSONObject c = videoarray.getJSONObject(i);

                                    String title = c.getString("title");
                                    String path = c.getString("path");

                                    video v3 = new video(path, title, "", "20:50:80", "id");
                                    listnamevideo.add(v3);

                                }


                                playerAdapter = new PlayerAdapter(G.activity, listnamevideo);
                                listView.setAdapter(playerAdapter);

                            } catch (Exception e) {
                                Log.i("baner", "eeeeeeeeee: " + e.toString());
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

                    Log.i("fffff", "onItemClick: " + position);

                }

            });

        }

    }

}
