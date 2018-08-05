package com.example.amhso.montazeranemonji.otherclass;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.amhso.montazeranemonji.PlayerActivity;
import com.example.amhso.montazeranemonji.R;

import java.util.List;


public class PlayerAdapter extends ArrayAdapter<video> {

    private final Activity context;
    private final List<video> itemlabel;

    public PlayerAdapter(Activity context, List<video> itemname) {
        super(context, R.layout.activity_listplayer, itemname);
        this.context=context;
        this.itemlabel=itemname;
    }




    public View getView(int position,View view,ViewGroup parent) {



        View listItem = view;
        MyWrapper wrapper = null;


        try {




            if (listItem == null) {

                listItem = LayoutInflater.from(context).inflate(R.layout.activity_listplayer, parent, false);
                wrapper = new MyWrapper(listItem);
                listItem.setTag(wrapper);

            } else {
                wrapper = (MyWrapper) listItem.getTag();
            }


            String getnameseller = itemlabel.get(position).getTitle_video();
            wrapper.getName2().setText(getnameseller);











            wrapper.getIcon().setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    View parentRow = (View) v.getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int position = listView.getPositionForView(parentRow);


                    Log.i("ppppp", "ppppppppppppppppppppppp" + position);
                    Intent i = new Intent(G.activity, PlayerActivity.class);
                    i.putExtra("url", itemlabel.get(position).getUrl());
                    i.putExtra("name_for_db", itemlabel.get(position).getTitle_video());

                    G.activity.startActivity(i);



                }
            });


        }
        catch (Exception e){
            Log.i("eeeeee", "eeeeeeeeeeeeeeee"+e.toString());
        }



        return listItem;


    };







    class MyWrapper
    {
        private View base;
        private TextView name;
        private ImageView icon;



        public MyWrapper(View base)
        {
            this.base = base;
        }


        public TextView getName2(){
            if(name == null){
                name = (TextView) base.findViewById(R.id.label);
            }
            return name;
        }



        public ImageView getIcon(){
            if(icon == null){
                icon = (ImageView) base.findViewById(R.id.icon_movie);
            }
            return icon;
        }


    }
}
