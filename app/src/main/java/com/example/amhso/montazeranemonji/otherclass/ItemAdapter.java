package com.example.amhso.montazeranemonji.otherclass;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.amhso.montazeranemonji.R;
import com.example.amhso.montazeranemonji.list_sokanrani;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
List<Item>itemList;

    public DBHelper mydb;

    public ItemAdapter(List<Item> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
    }

    Context mContext;



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View aView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_item,parent,false);
//        View aView3= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_item3,parent,false);
        return new MyViewHolder(aView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

    Item aItem=itemList.get(position);

        mydb = new DBHelper(G.activity);

        Picasso.with(G.activity)
                .load(aItem.getuAvatar())
                .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3 )
                .into(holder.aAvatar);



        holder.aMessage.setText(aItem.getuMessage());
        holder.aItem.setOnClickListener(new  View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {


                                        Intent i = new Intent(G.activity,list_sokanrani.class);
                                        i.putExtra("image",itemList.get(position).getuAvatar());
                                        i.putExtra("name",itemList.get(position).getuMessage());

                                        G.activity.startActivity(i);


                                    }
                                });







//        holder.icon_bookmarker.setOnClickListener(new  View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                mydb.insertMarker(itemList.get(position).getuMessage(),itemList.get(position).getuAvatar());
//                DrawableCompat.setTint(holder.icon_bookmarker.getDrawable(), ContextCompat.getColor(G.activity, R.color.orange));
//
//            }
//        }) ;






    }




    @Override
    public int getItemCount() {
        return itemList.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView aAvatar;
//        public TextView aName;
        public TextView aMessage;
//        public ImageView icon_bookmarker;
//        public TextView aTime;

        public LinearLayout aItem;
        public MyViewHolder(View itemView) {
            super(itemView);

            aAvatar =(ImageView) itemView.findViewById(R.id.img_avatar);
//            aName =(TextView) itemView.findViewById(R.id.txt_name);
            aMessage =(TextView) itemView.findViewById(R.id.txt_message);
//            aTime =(TextView) itemView.findViewById(R.id.txt_time);
            aItem= itemView.findViewById(R.id.item_layout);

//            icon_bookmarker= itemView.findViewById(R.id.icon_bookmarker);

        }





    }


}