package com.homework.realorfake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUp extends Activity {


    int photoNum = 0;
    GameActivity gm = new GameActivity();
    ImageView image1;
    GameActivity gameActivity;

    //image resources
    public static Integer [] photos = {

            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up);


        //Taking our devices screen size in pixels and creating new values according to them
        //We take phones resolution
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //here we set the percentage of pop-up window of phones resolution
        getWindow().setLayout((int)(width * .98), (int)(height * .94));

        addImages();




    }


    public void addImages() {




        Intent intent = getIntent();
        int temp = intent.getIntExtra("variableNum", 0); // here 0 is the default value

        Log.e("test photo temp num", " " + temp);


        //Adding images to PopUp activity
        image1 = findViewById(R.id.changePhotoPop);

        if(temp==0){
            image1.setImageResource(photos[4]);
        }else{
            image1.setImageResource(photos[temp-1]);}



        final Button buttonNext = findViewById(R.id.nextButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                finish();

            }
        });





    }










}
