package com.homework.realorfake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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



    //radio buttons
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        radioGroup = findViewById(R.id.radioGroup);
        mSeekBar = (SeekBar) findViewById(R.id.seekbarExample);
        mTxtValue = (TextView) findViewById(R.id.seekBarValueTxt);

        mSeekBar.setProgress(25);

        value = String.valueOf(mSeekBar.getProgress());

        mTxtValue.setText(value);

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

        //buttons and what they do
        Button buttonApply = findViewById(R.id.applyButton);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);


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

