package com.homework.realorfake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {


    SeekBar mSeekBar;
    TextView mTxtValue;
    String value;
    ImageView imageView;
    int photoNum = 1;


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


        mSeekBar.setProgress(25);

        value = String.valueOf(mSeekBar.getProgress());

        mTxtValue.setText(value);

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

                Intent i = new Intent(GameActivity.this, PopUp.class);

                startActivity(i);

                photoNum += 1;
                if (photoNum == 5) {

                    photoNum = 0;

                }

                imageview2.setImageResource(photos[photoNum]);
                imageView.setVisibility(View.INVISIBLE);






            }
        });




    }



    public void changePhotos() {




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

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }





}

