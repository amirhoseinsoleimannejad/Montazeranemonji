package com.example.amhso.montazeranemonji;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amhso.montazeranemonji.otherclass.DBHelper;
import com.example.amhso.montazeranemonji.otherclass.G;
import com.example.amhso.montazeranemonji.otherclass.Item;
import com.example.amhso.montazeranemonji.otherclass.ItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class TabFragment3 extends Fragment {

    public View myview;

    RecyclerView myRecycler ;
    List<Item> mItem = new ArrayList<>() ;
    ItemAdapter mAdapter;
    public DBHelper mydb;






    @Override
    public void onStart() {
        super.onStart();


        mydb = new DBHelper(G.activity);



        myRecycler =(RecyclerView) myview.findViewById(R.id.recycler_view);
        mAdapter = new ItemAdapter(mItem, G.activity);

        RecyclerView.LayoutManager gLayoutManager = new GridLayoutManager(G.activity, 2, LinearLayoutManager.VERTICAL, false);
        myRecycler.setLayoutManager(gLayoutManager);
        myRecycler.setItemAnimator(new DefaultItemAnimator());
        myRecycler.setHasFixedSize(true);
        myRecycler.setAdapter(mAdapter);



        mItem=mydb.getAllMarker();

        mAdapter.notifyDataSetChanged();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        myview= inflater.inflate(R.layout.tab_fragment_3, container, false);
        mydb = new DBHelper(G.activity);


        myRecycler =(RecyclerView) myview.findViewById(R.id.recycler_view);
        mAdapter = new ItemAdapter(mItem, G.activity);

        RecyclerView.LayoutManager gLayoutManager = new GridLayoutManager(G.activity, 2, LinearLayoutManager.VERTICAL, false);
        myRecycler.setLayoutManager(gLayoutManager);
        myRecycler.setItemAnimator(new DefaultItemAnimator());
        myRecycler.setHasFixedSize(true);
        myRecycler.setAdapter(mAdapter);


//        Log.i("ttttttttttttttttttttttt", "tttttttttttttttt: "+mydb.numberOfRowsTableMarker());
        mItem=mydb.getAllMarker();

        mAdapter.notifyDataSetChanged();

        return myview;
    }
}
