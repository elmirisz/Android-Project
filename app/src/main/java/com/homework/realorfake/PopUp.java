package com.homework.realorfake;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopUp extends Activity {

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
        getWindow().setLayout((int)(width * .8), (int)(height * .6));


    }
}
