package com.homework.realorfake;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class InstructionView extends Activity {


    //Here we define the uri, which we use for video instruction
    Uri uri;

    //Here we define the videov, which we use for video instruction
    VideoView videov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_view);


        //WE ARE ALSO MAKING INSTRUCTION VIEW AS A POP UP

        //Taking our devices screen size in pixels and creating new values according to them
        //We take phones resolution
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //window width
        int width = dm.widthPixels;

        //window height
        int height = dm.heightPixels;

        //here we set the percentage of pop-up window of phones resolution
        getWindow().setLayout((int)(width * .95), (int)(height * .75));


        //call the videoView we've created in instruction_view.xml
        videov = (VideoView) findViewById(R.id.videoView);

        //We call the resource where the video is located
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);

        //we connect the videoView and resource where the video is located
        videov.setVideoURI(uri);

        //a call to get the video started
        videov.start();



    }



}