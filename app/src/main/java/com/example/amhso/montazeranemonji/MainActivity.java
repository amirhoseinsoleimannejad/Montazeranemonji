package com.example.amhso.montazeranemonji;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amhso.montazeranemonji.otherclass.DBHelper;
import com.example.amhso.montazeranemonji.otherclass.G;
import com.example.amhso.montazeranemonji.otherclass.PagerAdapter;


public class MainActivity extends AppCompatActivity {

    public DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        G.activity=this;

        if(!G.checknet()){
            Intent i = new Intent(G.activity, ErrorActivity.class);

            startActivity(i);

        }
        else {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


            ImageView about_us = (ImageView) findViewById(R.id.about_us);
            about_us.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(G.activity, AboutusActivity.class);
                    startActivity(i);
                }
            });


            mydb = new DBHelper(this);

            View tabView1 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            View tabView2 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            View tabView3 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

            TextView text = (TextView) tabView1.findViewById(R.id.text);
            ImageView icon = (ImageView) tabView1.findViewById(R.id.icon);


            TextView text3 = (TextView) tabView3.findViewById(R.id.text);
            ImageView icon3 = (ImageView) tabView3.findViewById(R.id.icon);

            Typeface font3 = Typeface.createFromAsset(getAssets(), "B Nazanin Bold_p30download.com.ttf");
            text3.setTypeface(font3);
            text3.setText("مجموعه ها");
            icon3.setBackgroundResource(R.drawable.ic_four_black_squares);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabView3));


            TextView text2 = (TextView) tabView2.findViewById(R.id.text);
            ImageView icon2 = (ImageView) tabView2.findViewById(R.id.icon);
            Typeface font2 = Typeface.createFromAsset(getAssets(), "B Nazanin Bold_p30download.com.ttf");
            text2.setTypeface(font2);
            text2.setText("اخیراً");
            icon2.setBackgroundResource(R.drawable.ic_recently);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabView2));


            Typeface font = Typeface.createFromAsset(getAssets(), "B Nazanin Bold_p30download.com.ttf");
            text.setTypeface(font);
            text.setText("نشانه ها");
            icon.setBackgroundResource(R.drawable.ic_location);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabView1));


            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final PagerAdapter adapter = new PagerAdapter
                    (getSupportFragmentManager(), tabLayout.getTabCount());

            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

        }

    }
}
