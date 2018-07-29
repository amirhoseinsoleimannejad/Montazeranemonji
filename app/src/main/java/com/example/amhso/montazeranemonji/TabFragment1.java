package com.example.amhso.montazeranemonji;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.amhso.montazeranemonji.otherclass.G;
import com.example.amhso.montazeranemonji.otherclass.Item;
import com.example.amhso.montazeranemonji.otherclass.ItemAdapter;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TabFragment1 extends Fragment {
    RecyclerView myRecycler ;
    List<Item> mItem = new ArrayList<>() ;
    ItemAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

 View v= inflater.inflate(R.layout.tab_fragment_1, container, false);


//        TextView txtyekan = (TextView)G.activity.findViewById(R.id.txt_message);
//        Typeface yekan_font = Typeface.createFromAsset(G.activity.getAssets(), "BTrafcBd_0.ttf");
//        txtyekan.setTypeface(yekan_font);

        myRecycler =(RecyclerView) v.findViewById(R.id.recycler_view);
        mAdapter = new ItemAdapter(mItem, G.activity);

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        RecyclerView.LayoutManager al = new LinearLayoutManager(G.activity, LinearLayoutManager.VERTICAL, false);
//          RecyclerView.LayoutManager gLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager gLayoutManager = new GridLayoutManager(G.activity, 2, LinearLayoutManager.VERTICAL, false);
        myRecycler.setLayoutManager(gLayoutManager);

//        RecyclerView.ItemDecoration iDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        myRecycler.addItemDecoration(iDecoration);

        myRecycler.setItemAnimator(new DefaultItemAnimator());

        myRecycler.setHasFixedSize(true);

        myRecycler.setAdapter(mAdapter);








        String tag_string_req = "string_req";
        final ProgressDialog pDialog = new ProgressDialog(G.activity);
        pDialog.setMessage("در حال بارگزاری...");
        try{

            pDialog.show();
        }
        catch (Exception e){

        }


        StringRequest strReq = new StringRequest(Request.Method.GET,G.urlcategory,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("rrrrrrr", "onResponse: "+response);


                        try {

                            JSONArray videoarray;
                            videoarray =new JSONArray(response);

                            for (int i = 0; i < videoarray.length(); i++) {

                                JSONObject c = videoarray.getJSONObject(i);

                                String image=c.getString("picture_tax");
                                String name=c.getString("name");

                                mItem.add(new Item(image, name, name, "12:14"));

                            }


                            mAdapter.notifyDataSetChanged();


                        }
                        catch (Exception e){
                            Log.i("baner", "eeeeeeeeee: "+e.toString());
                        }


                        try{
                            pDialog.hide();

                        }
                        catch (Exception e){

                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


                try{
                    pDialog.hide();

                }
                catch (Exception e){

                }

            }
        });

        // Adding request to request queue
        G.getInstance().addToRequestQueue(strReq, tag_string_req);




        return v;
    }




}