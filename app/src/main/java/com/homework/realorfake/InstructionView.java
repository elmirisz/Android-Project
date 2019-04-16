package com.homework.realorfake;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class InstructionView extends Activity {

    Uri uri;
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

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //here we set the percentage of pop-up window of phones resolution
        getWindow().setLayout((int)(width * .95), (int)(height * .75));



        videov = (VideoView) findViewById(R.id.videoView);

        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);

        videov.setVideoURI(uri);
        videov.start();



    }



}