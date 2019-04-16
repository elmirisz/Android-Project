package com.homework.realorfake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class InstructionActivity extends AppCompatActivity {

    /*An instructional activity that
    will help us to create a video
    tutorial for helping users */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);


    }

    //startGame view
    public void startGame(View view) {
        Intent intent = new Intent(InstructionActivity.this, GameActivity.class);
        startActivity(intent);

    }


}
