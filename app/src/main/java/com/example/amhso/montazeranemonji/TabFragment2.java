package com.example.amhso.montazeranemonji;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.amhso.montazeranemonji.otherclass.DBHelper;
import com.example.amhso.montazeranemonji.otherclass.G;
import com.example.amhso.montazeranemonji.otherclass.PlayerAdapter;
import com.example.amhso.montazeranemonji.otherclass.video;

import java.util.ArrayList;
import java.util.List;


public class TabFragment2 extends Fragment {

    private DBHelper mydb ;

    private ListView listView;


    private PlayerAdapter playerAdapter;
    private List<video> listnamevideo;

    private View view;

    @Override
    public void onStart() {
        super.onStart();


        mydb = new DBHelper(G.activity);



        listView=(ListView) view.findViewById(R.id.listmedia);
        listnamevideo = new ArrayList<>();


        Log.i("ccccccccccccc", "count: "+mydb.numberOfRowsTableRecent());



        listnamevideo=mydb.getAllRecent();


        playerAdapter = new PlayerAdapter(G.activity,listnamevideo);
        listView.setAdapter(playerAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        mydb = new DBHelper(G.activity);


        view=  inflater.inflate(R.layout.tab_fragment_2, container, false);
        listView=(ListView) view.findViewById(R.id.listmedia);
        listnamevideo = new ArrayList<>();


        Log.i("ccccccccccccc", "count: "+mydb.numberOfRowsTableRecent());


//        video v3=new video(path,title,"","20:50:80","id");
//        listnamevideo.add(v3);

        listnamevideo=mydb.getAllRecent();


        playerAdapter = new PlayerAdapter(G.activity,listnamevideo);
        listView.setAdapter(playerAdapter);

        return view;


    }
}
