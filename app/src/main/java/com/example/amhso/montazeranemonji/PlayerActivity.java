package com.example.amhso.montazeranemonji;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

        try{
            mPlayer.pause();
        }
        catch (Exception e){

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
        setContentView(R.layout.activity_player);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = G.activity;


            if(!G.checknet()){
                Intent i = new Intent(G.activity, ErrorActivity.class);
                startActivity(i);

            }
            else {
                // Get the widget reference from xml layout
                mButtonPlay = findViewById(R.id.btn_play);
                mButtonStop = findViewById(R.id.btn_stop);


                mSeekBar = findViewById(R.id.seek_bar);
                mPass = findViewById(R.id.tv_pass);
                mDuration = findViewById(R.id.tv_duration);
                mDue = findViewById(R.id.tv_due);

                // Initialize the handler
                mHandler = new Handler();


                titleT = (TextView) findViewById(R.id.title);
                bodyT = (TextView) findViewById(R.id.body);
                dateT = (TextView) findViewById(R.id.date);
//        tageT=(TextView)findViewById(R.id.tage);
                pictureI = (ImageView) findViewById(R.id.picture);


                mydb = new DBHelper(G.activity);
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {

                    url = bundle.getString("url");
                    name_for_db = bundle.getString("name_for_db");

                } else {
                    finish();
                }


                String tag_string_req = "string_req";
                final ProgressDialog pDialog = new ProgressDialog(G.activity);
                pDialog.setMessage("صبر کنید...");
                try {

                    pDialog.show();
                } catch (Exception e) {

                }


                StringRequest strReq = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                Log.i("rrrrrrr", "onResponse: " + response);


                                try {

                                    JSONArray videoarray;
                                    videoarray = new JSONArray(response);

                                    for (int i = 0; i < videoarray.length(); i++) {

                                        JSONObject c = videoarray.getJSONObject(i);

                                        title = c.getString("title");
                                        tags = c.getString("tags");
                                        picture = c.getString("picture");
                                        date_content = c.getString("date_content");
                                        soundfile = c.getString("soundfile");
                                        body = c.getString("body");


                                    }


                                    mydb.insertMedia(name_for_db, url);
                                    titleT.setText(title);
                                    bodyT.setText(body);
                                    tageT.setText(tags);
                                    dateT.setText(date_content);
                                    String sp[] = picture.split("\"");

                                    picture = sp[1];
                                    Log.i("pppppppppppppp", "pppppppppppppp: " + picture);

                                    Picasso.with(G.activity)
                                            .load(picture)
                                            .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3)
                                            .into(pictureI);


                                    mPlayer = new MediaPlayer();
                                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                                    try {


                                        mPlayer.setDataSource(soundfile);
                                        mPlayer.prepare(); // might take long! (for buffering, etc)

                                    } catch (Exception e) {

                                    }

                                } catch (Exception e) {
                                    Log.i("baner", "eeeeeeeeee: " + e.toString());
                                }


                                try {
                                    pDialog.hide();

                                } catch (Exception e) {

                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                        try {
                            pDialog.hide();

                        } catch (Exception e) {

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

                        Toast.makeText(mContext, "Media Player is playing.", Toast.LENGTH_SHORT).show();

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
                        if (mPlayer != null && b) {
                    /*
                        void seekTo (int msec)
                            Seeks to specified time position. Same as seekTo(long, int)
                            with mode = SEEK_PREVIOUS_SYNC.

                        Parameters
                            msec int: the offset in milliseconds from the start to seek to

                        Throws
                            IllegalStateException : if the internal player engine has not been initialized
                    */
                            mPlayer.seekTo(i * 1000);
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

        }
        catch (Exception e){
            Toast.makeText(G.activity, "حافظه گوشی خود را مدیریت کنید با تشکر", Toast.LENGTH_LONG).show();
        }
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

        mPass.setText("" + pass + "رفته");
        mDuration.setText("" + duration + "مونده");
        mDue.setText("" + due + "مجموع");
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