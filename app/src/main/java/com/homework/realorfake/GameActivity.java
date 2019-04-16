package com.homework.realorfake;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

import android.os.AsyncTask;
import android.widget.VideoView;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import android.widget.VideoView;

public class GameActivity extends AppCompatActivity {

    //here we have defined:

    //imageView
    ImageView help;
    //SeekBar as mSeekBar
    SeekBar mSeekBar;
    //TextView as mTxtValue
    TextView mTxtValue;
    //String as value
    String value;
    //ImageView as imageView
    ImageView imageView;
    //int photoNum as 1
    int photoNum = 1;
    //FrameLayout as FrameLayout
    FrameLayout frameLayout;
    //Boolean hasBeenClicked as false
    Boolean hasBeenClicked = false;


    //ovaj dio je za bazu
    //imageName we defined it as string
    String imageName;
    //radioName we defined it as string
    String radioName;
    //confidenceValue we defined it as int
    int confidenceValue;
    //condfidenceValuer we defined it as
    String confidenceValue1;
    //JSON

    //Some url endpoint that you may have
    String myUrl = "http://www.studenti.famnit.upr.si/~89161011/OLD/insert.php";
    //String to place our result in
    String result;


    JSONParser jsonParser = new JSONParser();
    private static String url_insert = "http://www.studenti.famnit.upr.si/~89161011/OLD/insert.php";

    // // JSON Node names
    private static final String TAG_SUCCESS = "success";


    //image resources
    public static Integer[] photos = {

            //the name of the photo in the resource
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

        //Hiding stamp when resumed
        ImageView stamp1 = findViewById(R.id.stampPhoto);
        stamp1.setVisibility(View.INVISIBLE);

        mSeekBar.setProgress(50);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        //radioGroup is called id value radioGroup
        radioGroup = findViewById(R.id.radioGroup);
        // mSeekBar is called id value seekbarExample
        mSeekBar = findViewById(R.id.seekbarExample);
        //mTxtValue is called id value seekBarValueTxt
        mTxtValue = findViewById(R.id.seekBarValueTxt);
        //frameLayout is called id value frameLayoutMain2
        frameLayout = findViewById(R.id.frameLayoutMain2);
        //radioGroup is called id value radioGroup
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


                    Intent i = new Intent(GameActivity.this, PopUp.class);


                    photoNum += 1;
                    if (photoNum == 5) {

                        photoNum = 0;

                    }


                    //Starting PopUp activity
                    i.putExtra("variableNum", photoNum);
                    startActivity(i);


                    imageName = "image" + (photoNum);
                    radioName = radioButton.getText().toString();

                    //confidenceVlue we call on mSeekBar
                    confidenceValue = mSeekBar.getProgress();
                    confidenceValue1 = Integer.toString(confidenceValue);
                    //myUrl = myUrl+"?Name=image"+photoNum+"&Radio="+radioName+"&Confidence="+confidenceValue1;


                    //Change image when button clicked, also dim GameActivity background, to 60% transparency

                    imageview2.setImageResource(photos[photoNum]);
                    frameLayout.setBackgroundColor(getResources().getColor(R.color.transparent));


                    //Hide stamp from photos
                    imageView.setVisibility(View.INVISIBLE);


                    //myUrl = myUrl+"?Name=image"+photoNum+"&Radio="+radioName+"&Confidence="+confidenceValue1;
                    Log.d("LINK", myUrl);
                    Log.d("photoName", photoNum + "");
                    new RequestTask().execute(myUrl + "?Name=image" + photoNum + "&Radio=" + radioName + "&Confidence=" + confidenceValue1);


                } else {


                    Toast.makeText(GameActivity.this, "CHOOSE FAKE OR REAL !", Toast.LENGTH_SHORT).show();

                }


            }


        });


        //Function for help button (GIF)
        /*In function instructionView we have defined the values ​​and
        methods for the video gif */
        instructionView();


    }



    @Override
    protected void onStop() {
        super.onStop();



//        frameLayout.setBackgroundColor(getResources().getColor(R.color.transparent));



    }

    @Override
    protected void onPause() {
        super.onPause();


    }


    public void instructionView() {


        ImageView help = findViewById(R.id.helpButton);

        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                onPause();

                Intent intentInstructionView = new Intent(GameActivity.this, InstructionView.class);

                startActivity(intentInstructionView);

            }
        });


    }


    //Method for adding FAKE and REAL stamps
    public void putStamp() {


        imageView = findViewById(R.id.stampPhoto);
        //button for fake
        RadioButton button1 = findViewById(R.id.fakeButton);
        //button for real
        RadioButton button2 = findViewById(R.id.realButton);


        //Checking if FAKE button has been clicked
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calling the picture out of the resource
                imageView.setImageResource(R.drawable.stamp1);
                //setting to be visible
                imageView.setVisibility(View.VISIBLE);


            }

        });


        //Checking if REAL button has been clicked
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //calling the picture out of the resource
                imageView.setImageResource(R.drawable.stamp2);
                //setting to be visible
                imageView.setVisibility(View.VISIBLE);


            }

        });


    }

    //method for checkButton
    public void checkButton(View v) {

        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);


    }


//   //request to db

    public class RequestTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..

        }

    }


}






