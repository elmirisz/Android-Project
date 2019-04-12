package com.homework.realorfake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
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

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.homework.realorfake.JSONParser;


public class GameActivity extends AppCompatActivity {


    SeekBar mSeekBar;
    TextView mTxtValue;
    String value;
    ImageView imageView;
    int photoNum = 1;
    FrameLayout frameLayout;
    Boolean hasBeenClicked = false;



    //ovaj dio je za bazu
    String imageName;
    String radioName ;
    int confidenceValue ;
    String confidenceValue1;
    //JSON
    JSONParser jsonParser = new JSONParser();
    private static String url_insert = "http://www.studenti.famnit.upr.si/~89161011/OLD/insert.php";

    // // JSON Node names
        private static final String TAG_SUCCESS = "success";


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


    //Resuming activity
    @Override
    protected void onResume() {
        super.onResume();


        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        //Delete dim background when activity resumed
        frameLayout.setBackgroundColor(getResources().getColor(R.color.fullTransparent));

        //Unchecking radio buttons when activity resumed
        radioGroup.clearCheck();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        radioGroup = findViewById(R.id.radioGroup);
        mSeekBar = findViewById(R.id.seekbarExample);
        mTxtValue = findViewById(R.id.seekBarValueTxt);
        frameLayout = findViewById(R.id.frameLayoutMain2);


        radioGroup = findViewById(R.id.radioGroup);

        mSeekBar.setProgress(50);

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
                hasBeenClicked = true;

                //Check if radiobuttons have been checked, if not print out Toast message
                if (radioId == R.id.fakeButton || radioId == R.id.realButton) {

                    imageName = "image" + (photoNum + 1);
                    radioName = radioButton.getText().toString();

                    confidenceValue = mSeekBar.getProgress();
                    confidenceValue1 = Integer.toString(confidenceValue);


                    Intent i = new Intent(GameActivity.this, PopUp.class);


                    photoNum += 1;
                    if (photoNum == 5) {

                        photoNum = 0;

                    }


                    //Starting PopUp activity
                    i.putExtra("variableNum", photoNum);
                    startActivity(i);

                    //Change image when button clicked, also dim GameActivity background, to 60% transparency
                    imageview2.setImageResource(photos[photoNum]);
                    frameLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
                    //Hide stamp from photos
                    imageView.setVisibility(View.INVISIBLE);


                } else {


                    Toast.makeText(GameActivity.this, "CHOOSE FAKE OR REAL !", Toast.LENGTH_SHORT).show();

                }


            }


        });

        //Function for help button (GIF)
        instructionView();



    }





    public void instructionView () {

        ImageView help = findViewById(R.id.helpButton);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentInstructionView = new Intent(GameActivity.this, InstructionView.class);

                startActivity(intentInstructionView);

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

    class Insert extends AsyncTask<String, String, String> {

        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Name", imageName));
            params.add(new BasicNameValuePair("Radio", radioName));
            params.add(new BasicNameValuePair("Confidence", confidenceValue1));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_insert,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                   // Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                    //startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }



    }
}





