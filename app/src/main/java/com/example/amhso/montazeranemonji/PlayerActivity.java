package com.example.amhso.montazeranemonji;

//import android.app.ProgressDialog;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.example.jalil.mahdi_s.otherclass.DBHelper;
//import com.example.jalil.mahdi_s.otherclass.G;
//import com.example.jalil.mahdi_s.otherclass.Item;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.concurrent.TimeUnit;
//
//
//public class PlayerActivity extends AppCompatActivity {
//
//
//    private Button b1,b2,b3,b4;
//    private ImageView iv;
//    private MediaPlayer mediaPlayer;
//
//    private double startTime = 0;
//    private double finalTime = 0;
//
//    private Handler myHandler = new Handler();;
//    private int forwardTime = 5000;
//    private int backwardTime = 5000;
//    private SeekBar seekbar;
//    private TextView tx1,tx2,tx3;
//    public static int oneTimeOnly = 0;
//
//    String url;
//
//    public String title;
//    public String tags;
//    public String picture;
//    public String date_content;
//    public String soundfile;
//    public String body;
//
//
//    public TextView titleT;
//    public TextView bodyT;
//    public TextView tageT;
//    public TextView dateT;
//    public ImageView pictureI;
//    private DBHelper mydb ;
//
//    private String name_for_db;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player);
//
//
//
//        G.activity=this;
//
//        mydb = new DBHelper(G.activity);
//
//        Bundle bundle = getIntent().getExtras();
//        if(bundle!=null){
//
//            url=bundle.getString("url");
//            name_for_db=bundle.getString("name_for_db");
//
//            Log.i("uuuuuuuuu", "uuuuuuuuuuuu: "+url);
//
//        }
//        else{
//            finish();
//        }
//
//
//
//        b1 = (Button) findViewById(R.id.button);
//        b2 = (Button) findViewById(R.id.button2);
//        b3 = (Button)findViewById(R.id.button3);
//        b4 = (Button)findViewById(R.id.button4);
//        iv = (ImageView)findViewById(R.id.imageView);
//
//        tx1 = (TextView)findViewById(R.id.textView2);
//        tx2 = (TextView)findViewById(R.id.textView3);
//        tx3 = (TextView)findViewById(R.id.textView4);
//        tx3.setText("Song.mp3");
//
//
//
//        titleT=(TextView)findViewById(R.id.title);
//        bodyT=(TextView)findViewById(R.id.body);
//        dateT=(TextView)findViewById(R.id.date);
//        tageT=(TextView)findViewById(R.id.tage);
//        pictureI=(ImageView) findViewById(R.id.picture);
//
//
//        seekbar = (SeekBar)findViewById(R.id.seekBar);
//        seekbar.setClickable(true);
//        b2.setEnabled(false);
//
//
//
//
//
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
//                mediaPlayer.start();
//
//                finalTime = mediaPlayer.getDuration();
//                startTime = mediaPlayer.getCurrentPosition();
//
//                if (oneTimeOnly == 0) {
//                    seekbar.setMax((int) finalTime);
//                    oneTimeOnly = 1;
//                }
//
//                tx2.setText(String.format("%d min, %d sec",
//                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                        finalTime)))
//                );
//
//                tx1.setText(String.format("%d min, %d sec",
//                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                        startTime)))
//                );
//
//                seekbar.setProgress((int)startTime);
//                myHandler.postDelayed(UpdateSongTime,100);
//                b2.setEnabled(true);
//                b3.setEnabled(false);
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
//                mediaPlayer.pause();
//                b2.setEnabled(false);
//                b3.setEnabled(true);
//            }
//        });
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int temp = (int)startTime;
//
//                if((temp+forwardTime)<=finalTime){
//                    startTime = startTime + forwardTime;
//                    mediaPlayer.seekTo((int) startTime);
//                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int temp = (int)startTime;
//
//                if((temp-backwardTime)>0){
//                    startTime = startTime - backwardTime;
//                    mediaPlayer.seekTo((int) startTime);
//                    Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//
//
//
//        String tag_string_req = "string_req";
//        final ProgressDialog pDialog = new ProgressDialog(G.activity);
//        pDialog.setMessage("در حال بارگزاری...");
//        try{
//
//            pDialog.show();
//        }
//        catch (Exception e){
//
//        }
//
//
//        StringRequest strReq = new StringRequest(Request.Method.GET,url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.i("rrrrrrr", "onResponse: "+response);
//
//
//                        try {
//
//                            JSONArray videoarray;
//                            videoarray =new JSONArray(response);
//
//                            for (int i = 0; i < videoarray.length(); i++) {
//
//                                JSONObject c = videoarray.getJSONObject(i);
//
//                                 title=c.getString("title");
//                                 tags=c.getString("tags");
//                                 picture=c.getString("picture");
//                                 date_content=c.getString("date_content");
//                                soundfile=c.getString("soundfile");
//                                body=c.getString("body");
//
//
//
//                            }
//
//
//                            mydb.insertMedia(name_for_db,url);
//                            titleT.setText(title);
//                            bodyT.setText(body);
//                            tageT.setText(tags);
//                            dateT.setText(date_content);
//
//
//                            Log.i("pppppppppppppp", "pppppppppppppp: "+picture);
//
//                            Picasso.with(G.activity)
//                                    .load(picture)
//                                    .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3 )
//                                    .into(pictureI);
//
//
//
//
//                            mediaPlayer = new MediaPlayer();
//                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                            try {
//
//                                Log.i("sssssssssssss", "ssssssssss: "+soundfile);
//                                mediaPlayer.setDataSource(soundfile);
//                                mediaPlayer.prepare(); // might take long! (for buffering, etc)
//
//                            }
//                            catch (Exception e){
//
//                            }
//
//
//
//                        }
//                        catch (Exception e){
//                            Log.i("baner", "eeeeeeeeee: "+e.toString());
//                        }
//
//
//                        try{
//                            pDialog.hide();
//
//                        }
//                        catch (Exception e){
//
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
//                try{
//                    pDialog.hide();
//
//                }
//                catch (Exception e){
//
//                }
//
//            }
//        });
//
//        // Adding request to request queue
//        G.getInstance().addToRequestQueue(strReq, tag_string_req);
//
//
//
//
//
//
//    }
//
//    private Runnable UpdateSongTime = new Runnable() {
//        public void run() {
//            startTime = mediaPlayer.getCurrentPosition();
//            tx1.setText(String.format("%d min, %d sec",
//                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
//                                    toMinutes((long) startTime)))
//            );
//            seekbar.setProgress((int)startTime);
//            myHandler.postDelayed(this, 100);
//        }
//    };
//
//
//    }
//


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.amhso.montazeranemonji.otherclass.DBHelper;
import com.example.amhso.montazeranemonji.otherclass.G;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;


public class PlayerActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private Button mButtonPlay;
    private Button mButtonStop;
    

    private SeekBar mSeekBar;

    private TextView mPass;
    private TextView mDuration;
    private TextView mDue;

    private MediaPlayer mPlayer;
    private Handler mHandler;
    private Runnable mRunnable;
    private DBHelper mydb;
    private String url;
    private String name_for_db;

    public String title;
        public String tags;
    public String picture;
    public String date_content;
    public String soundfile;
    public String body;


    public TextView titleT;
    public TextView bodyT;
    public TextView tageT;
    public TextView dateT;
    public ImageView pictureI;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mPlayer.pause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = G.activity;

        // Get the widget reference from xml layout
        mButtonPlay = findViewById(R.id.btn_play);
        mButtonStop = findViewById(R.id.btn_stop);


        mSeekBar = findViewById(R.id.seek_bar);
        mPass = findViewById(R.id.tv_pass);
        mDuration = findViewById(R.id.tv_duration);
        mDue = findViewById(R.id.tv_due);

        // Initialize the handler
        mHandler = new Handler();



        titleT=(TextView)findViewById(R.id.title);
        bodyT=(TextView)findViewById(R.id.body);
        dateT=(TextView)findViewById(R.id.date);
        tageT=(TextView)findViewById(R.id.tage);
        pictureI=(ImageView) findViewById(R.id.picture);


        mydb = new DBHelper(G.activity);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            url=bundle.getString("url");
            name_for_db=bundle.getString("name_for_db");

        }
        else{
            finish();
        }


        String tag_string_req = "string_req";
        final ProgressDialog pDialog = new ProgressDialog(G.activity);
        pDialog.setMessage("صبر کنید...");
        try{

            pDialog.show();
        }
        catch (Exception e){

        }



        StringRequest strReq = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("rrrrrrr", "onResponse: "+response);


                        try {

                            JSONArray videoarray;
                            videoarray =new JSONArray(response);

                            for (int i = 0; i < videoarray.length(); i++) {

                                JSONObject c = videoarray.getJSONObject(i);

                                 title=c.getString("title");
                                 tags=c.getString("tags");
                                 picture=c.getString("picture");
                                 date_content=c.getString("date_content");
                                 soundfile=c.getString("soundfile");
                                 body=c.getString("body");



                            }


                            mydb.insertMedia(name_for_db,url);
                            titleT.setText(title);
                            bodyT.setText(body);
                            tageT.setText(tags);
                            dateT.setText(date_content);


                            Log.i("pppppppppppppp", "pppppppppppppp: "+picture);

                            Picasso.with(G.activity)
                                    .load(picture)
                                    .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3 )
                                    .into(pictureI);


                            mPlayer = new MediaPlayer();
                            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                            try {


                                mPlayer.setDataSource(soundfile);
                                mPlayer.prepare(); // might take long! (for buffering, etc)

                            }
                            catch (Exception e){

                            }

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



        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If media player another instance already running then stop it first
//                stopPlaying();



                mPlayer.start();

                Toast.makeText(mContext,"Media Player is playing.",Toast.LENGTH_SHORT).show();

                // Get the current audio stats
                getAudioStats();
                // Initialize the seek bar
                initializeSeekBar();

            }
        });

        // Set a click listener for top playing button
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
            }
        });




        /*
            SeekBar.OnSeekBarChangeListener
                A callback that notifies clients when the progress level has been changed. This
                includes changes that were initiated by the user through a touch gesture or
                arrow key/trackball as well as changes that were initiated programmatically.
        */

        // Set a change listener for seek bar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
                void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
                    Notification that the progress level has changed. Clients can use the fromUser
                    parameter to distinguish user-initiated changes from those that occurred programmatically.

                Parameters
                    seekBar SeekBar : The SeekBar whose progress has changed
                    progress int : The current progress level. This will be in the range min..max
                                   where min and max were set by setMin(int) and setMax(int),
                                   respectively. (The default values for min is 0 and max is 100.)
                    fromUser boolean : True if the progress change was initiated by the user.
            */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mPlayer!=null && b){
                    /*
                        void seekTo (int msec)
                            Seeks to specified time position. Same as seekTo(long, int)
                            with mode = SEEK_PREVIOUS_SYNC.

                        Parameters
                            msec int: the offset in milliseconds from the start to seek to

                        Throws
                            IllegalStateException : if the internal player engine has not been initialized
                    */
                    mPlayer.seekTo(i*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void stopPlaying(){
        // If media player is not null then try to stop it
        if(mPlayer!=null){
            mPlayer.pause();
//            mPlayer.release();
//            mPlayer = null;
            Toast.makeText(mContext,"Stop playing.",Toast.LENGTH_SHORT).show();
            if(mHandler!=null){
                mHandler.removeCallbacks(mRunnable);
            }
        }
    }







    /*
        int getDuration ()
            Gets the duration of the file.

        Returns
            int the duration in milliseconds, if no duration is available
            (for example, if streaming live content), -1 is returned.
    */

    /*
        int getCurrentPosition ()
            Gets the current playback position.

        Returns
            int the current position in milliseconds
    */

    protected void getAudioStats(){
        int duration  = mPlayer.getDuration()/1000; // In milliseconds
        int due = (mPlayer.getDuration() - mPlayer.getCurrentPosition())/1000;
        int pass = duration - due;

        mPass.setText("" + pass + " seconds");
        mDuration.setText("" + duration + " seconds");
        mDue.setText("" + due + " seconds");
    }

    protected void initializeSeekBar(){
        mSeekBar.setMax(mPlayer.getDuration()/1000);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if(mPlayer!=null){
                    int mCurrentPosition = mPlayer.getCurrentPosition()/1000; // In milliseconds
                    mSeekBar.setProgress(mCurrentPosition);
                    getAudioStats();
                }
                mHandler.postDelayed(mRunnable,1000);
            }
        };
        mHandler.postDelayed(mRunnable,1000);
    }
}