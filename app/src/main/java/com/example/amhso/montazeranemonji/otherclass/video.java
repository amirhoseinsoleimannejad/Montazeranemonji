package com.example.amhso.montazeranemonji.otherclass;

/**
 * Created by amhso on 02/06/2018.
 */

public class video {

    public String url;
    public String title_video;
    public String description;
    public String date;
    public String id;

    public video(String url, String title_video, String description, String date, String id){

        this.date=date;
        this.description=description;
        this.title_video=title_video;
        this.url=url;
        this.id=id;

    }



    public String getUrl(){
        return this.url;
    }



    public String getDescription(){
        return this.description;
    }



    public String getTitle_video(){
        return this.title_video;
    }

}
