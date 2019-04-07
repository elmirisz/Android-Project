package com.homework.realorfake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {


    SeekBar mSeekBar;
    TextView mTxtValue;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

    }
}

