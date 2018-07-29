package com.example.amhso.montazeranemonji.otherclass;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class G extends Application {

    public static Context context;
    public static Activity activity;

    public static String urlcategory="http://montazer.ir/api/app_vas_taxonomy";
    public static String urlsokhanrani="http://montazer.ir/api/app_vas_list_subject";




    public static final String TAG = VolleySingleton.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static G mInstance;

//    public static String urlserver="http://192.168.43.186/android/";
//    public static String ServerImg="http://192.168.43.186/uploads/";
//    public static String urlwebview="http://192.168.43.186/";

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String id_sick = "id_sick";
    public static final String code = "code";
    public static final String lang = "lang";


    public static Boolean first_start=true;





//    public static final String CheckLoginUrl= G.urlserver+"auth";
//    public static final String FetchlistCity= G.urlserver+"fetchlistcity";
//    public static final String FetchlistOstan= G.urlserver+"fetchlistostan";
//    public static final String FetchlistShahrestan= G.urlserver+"fetchlistshahrestan";
//    public static final String FetchlistPart= G.urlserver+"fetchlistpart";
//    public static final String FetchlistDoctor= G.urlserver+"fetchlistdoctor";
//    public static final String FetchlistTurn= G.urlserver+"fetchlistturn";
//    public static final String FetchlistDoctorAll= G.urlserver+"fetchlistdoctorall";
//
//    public static final String Turn= G.urlserver+"turn";
//    public static final String FetchListMmpi= G.urlserver+"fetchlistmmpi";
//    public static final String FetchListVisit= G.urlserver+"fetchlistvisit";
//    public static final String FetchListVisitMmpi= G.urlserver+"fetchlistvisitmmpi";
//    public static final String FetchListExpertise= G.urlserver+"fetchlistexpertise";




















    @Override
    public void onCreate() {

        context = getApplicationContext();
        super.onCreate();
        mInstance = this;


//
        FontsOverride.setDefaultFont(context, "DEFAULT", "fonts/IRANSansWeb(FaNum).ttf");
        FontsOverride.setDefaultFont(context, "MONOSPACE", "fonts/IRANSansWeb(FaNum).ttf");
        FontsOverride.setDefaultFont(context, "SERIF", "fonts/IRANSansWeb(FaNum).ttf");
        FontsOverride.setDefaultFont(context, "SANS_SERIF", "fonts/IRANSansWeb(FaNum).ttf");



    }





    public static synchronized G getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}