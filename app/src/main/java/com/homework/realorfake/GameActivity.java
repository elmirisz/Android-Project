package com.homework.realorfake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {


    SeekBar mSeekBar;
    TextView mTxtValue;
    String value;
    ImageView imageView;
    int photoNum = 1;
    FrameLayout frameLayout;
    Boolean hasBeenClicked = false;


    String imageName;
    String radioName ;
    int confidenceValue ;


    //image resources
    public static Integer [] photos = {

            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };



    // buttons
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        radioGroup = findViewById(R.id.radioGroup);
        mSeekBar = findViewById(R.id.seekbarExample);
        mTxtValue = findViewById(R.id.seekBarValueTxt);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mSeekBar.setProgress(25);

        value = String.valueOf(mSeekBar.getProgress());

        mTxtValue.setText(value);

        //Function for putting STAMP on photos
        putStamp();

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                value = String.valueOf(i);
                mTxtValue.setText(value);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Toast.makeText(GameActivity.this, "You Started Dragging", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(GameActivity.this, "You Stopped Dragging", Toast.LENGTH_SHORT).show();

            }
        });


        final ImageView imageview2 = findViewById(R.id.changePhoto);



        //buttons and what they do
        final Button buttonApply = findViewById(R.id.applyButton);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                imageName="image"+ (photoNum+1);
                radioName= radioButton.getText().toString();
                confidenceValue=mSeekBar.getProgress();
                hasBeenClicked = true;



                Intent i = new Intent(GameActivity.this, PopUp.class);




                photoNum += 1;
                if (photoNum == 5) {

                    photoNum = 0;

                }



                //Starting PopUp activity
                i.putExtra("variableNum", photoNum);
                startActivity(i);


                frameLayout = findViewById(R.id.frameLayoutMain);




                imageview2.setImageResource(photos[photoNum]);

                imageView.setVisibility(View.INVISIBLE);



            }



        });











    }

    //Method for adding FAKE and REAL stamps
    public void putStamp() {


        imageView = findViewById(R.id.stampPhoto);

        RadioButton button1 = findViewById(R.id.fakeButton);
        RadioButton button2 = findViewById(R.id.realButton);

        //Checking if FAKE button has been clicked
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                imageView.setImageResource(R.drawable.stamp1);
                imageView.setVisibility(View.VISIBLE);


            }
        });
        //Checking if REAL button has been clicked
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                imageView.setImageResource(R.drawable.stamp2);
                imageView.setVisibility(View.VISIBLE);


            }
        });



    }



    public void checkButton(View v) {

        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);


    }




}

