package com.homework.realorfake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PopUp extends Activity {


    int photoNum = 0;
    GameActivity gm = new GameActivity();
    ImageView imageView;

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

        ImageView image1;

        //Taking our devices screen size in pixels and creating new values according to them
        //We take phones resolution
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //here we set the percentage of pop-up window of phones resolution
        getWindow().setLayout((int)(width * .98), (int)(height * .75));


        Intent intent = getIntent();
        int temp = intent.getIntExtra("variableNum", 0); // here 0 is the default value

        Log.e("sfas", " " + temp);


        //Adding images to PopUp activity
        image1 = findViewById(R.id.changePhotoPop);
        image1.setImageResource(photos[temp]);



        final Button buttonNext = findViewById(R.id.nextButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePhotos(buttonNext);



            }
        });


    }




    public void changePhotos(Button button1) {



        button1 = findViewById(R.id.nextButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                photoNum += 1;
                if (photoNum == 5) {

                    photoNum = 0;

                }







            }
        });


    }


}
