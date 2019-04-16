package com.homework.realorfake;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PopUp extends Activity {


    String responseJSON;
    int photoNum = 0;
    GameActivity gm = new GameActivity();
    ImageView image1;
    GameActivity gameActivity;
    JSONObject obj;
    String radio;
    String confidence;
    public static Integer[] answers = {0, 1, 0, 1, 1};


    //image resources
    public static Integer[] photos = {

            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };


    //here i will create global variables for textviews, all of them
    TextView gridText1;
    TextView gridText2;
    TextView gridText3;
    TextView gridText4;
    TextView gridText5;
    TextView gridText6;
    TextView gridText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this is code for row 1
        new RequestTask(1).execute("http://www.studenti.famnit.upr.si/~89161011/OLD/current.php");
        //this is code for row 2

        new RequestTask(2).execute("http://www.studenti.famnit.upr.si/~89161011/OLD/fake.php");

        //this is code for row 3
        new RequestTask(3).execute("http://www.studenti.famnit.upr.si/~89161011/OLD/real.php");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up);


        //Taking our devices screen size in pixels and creating new values according to them
        //We take phones resolution
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //defined width
        int width = dm.widthPixels;
        //defined height
        int height = dm.heightPixels;

        //here we set the percentage of pop-up window of phones resolution
        getWindow().setLayout((int) (width * .98), (int) (height * 1));


        addImages();
//        try{
//            obj = new JSONObject(responseJSON);
//            confidence=obj.getString("Confidence");
//            radio=obj.getString("Radio");
//            gridText1 = (TextView)findViewById(R.id.gridText1);
//            gridText2 = (TextView)findViewById(R.id.gridText2);
//            gridText1.setText("You  think this is "+radio);
//            gridText2.setText("With confidence: "+confidence);
//            Log.d("response", responseJSON);
//
//
//
//        }

//        catch (JSONException e){}


    }


    public void addImages() {


        Intent intent = getIntent();

        int temp = intent.getIntExtra("variableNum", 0); // here 0 is the default value
        photoNum = temp;
        Log.e("test photo temp num", " " + temp);


        //Adding images to PopUp activity
        image1 = findViewById(R.id.changePhotoPop);

        if (temp == 0) {
            image1.setImageResource(photos[4]);
        } else {
            image1.setImageResource(photos[temp - 1]);
        }


        final Button buttonNext = findViewById(R.id.nextButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });


    }

    public class RequestTask extends AsyncTask<String, String, String> {

        int row;


        public RequestTask(int i) {
            this.row = i;
        }



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



            responseJSON = responseString;


            Log.d("responseJSON", responseJSON);

            return responseString;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (row == 1) {


                try {
                    JSONObject obj = new JSONObject(result);
                    Log.d("AA", "BBBBBBBbbbbbbbbbbbbbbb: ");
                    JSONArray a = obj.getJSONArray("result");
                    JSONObject b = (JSONObject) a.get(0);
                    //ovdje je konacno proradilo
                    Log.d("HEJ RADI", b.getString("Radio"));
                    // konacno radi i tu
                    Log.d("HEJ RADI", b.getString("Confidence"));

                    //gridText1 is called on gridText1
                    gridText1 = (TextView) findViewById(R.id.gridText1);
                    //gridText2 is called on gridText2
                    gridText2 = (TextView) findViewById(R.id.gridText2);
                    gridText1.setText("You think this is " + b.getString("Radio"));
                    gridText2.setText("With confidence: " + b.getString("Confidence") + "%");


                } catch (JSONException e) {
                    e.printStackTrace();

                }
            } else if (row == 2) {
                try {
                    JSONObject obj = new JSONObject(result);

                    JSONArray a = obj.getJSONArray("result");

                    JSONObject b = (JSONObject) a.get(0);
                    //ovdje je konacno proradilo
                    Log.d("Drugi red people", b.getString("People"));
                    // konacno radi i tu
                    Log.d("Drugi red confidence", b.getString("Confidence"));

                    gridText3 = (TextView) findViewById(R.id.gridText3);
                    gridText4 = (TextView) findViewById(R.id.gridText4);

                    gridText3.setText(b.getString("People") + "% of people thinks this is fake");
                    gridText4.setText("With confidence: " + b.getString("Confidence") + "%");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (row == 3) {


                try {
                    JSONObject obj = new JSONObject(result);

                    JSONArray a = obj.getJSONArray("result");

                    JSONObject b = (JSONObject) a.get(0);
                    //ovdje je konacno proradilo
                    Log.d("Treci red people", b.getString("People"));
                    // konacno radi i tu
                    Log.d("Treci red confidence", b.getString("Confidence"));

                    //grid for text 5
                    gridText5 = (TextView) findViewById(R.id.gridText5);
                    //grid for text 6
                    gridText6 = (TextView) findViewById(R.id.gridText6);
                    //grid for text
                    gridText = (TextView) findViewById(R.id.textView);


                    gridText5.setText(b.getString("People") + "% of people thinks this is real");
                    gridText6.setText("With confidence: " + b.getString("Confidence") + "%");

                    Log.d("DA VIDIMO PASEL SLIKA", photoNum + "");
                    // konacno radi i tu
                    if (answers[photoNum] == 1) {
                        gridText.setText("THIS PICTURE IS REAL");
                    } else {
                        gridText.setText("THIS PICTURE IS FAKE");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }


        }

    }


}
