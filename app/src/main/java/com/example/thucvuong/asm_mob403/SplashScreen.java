package com.example.thucvuong.asm_mob403;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sutrix.common.Common;
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity{
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;
    TextView textView;

    //DucNguyen ArrayList
    ArrayList<Truyen> truyens = new ArrayList<Truyen>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //DucNguyen
        new GetData().execute(Common.getAddressAPI());

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("");

        final long period = 100;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(i)+"%");
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);
    }

    //DucNguyen getData
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(SplashScreen.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Please Wait ...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String temp = null;

            String urlString = strings[0];

            HTTPDataHandler http = new HTTPDataHandler();

            temp = http.getHTTPData(urlString);

            return temp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jsonArray = null;

            Object temp = null;
            String $oid = null;

            try {

                jsonArray = new JSONArray(s);

                for (int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Truyen truyen = new Truyen();

                    JSONObject _id = jsonObject.getJSONObject("_id");
                    $oid = _id.getString("$oid");

//                    truyen.setTieuDe(jsonObject.getString("TieuDe"));
//                    truyen.setNoiDung(jsonObject.getString("NoiDung"));
//                    truyen.setMoTa(jsonObject.getString("MoTa"));

                    truyens.add(truyen);

                }


            }catch (Exception e){
                e.printStackTrace();
            }


//            Toast.makeText(SplashScreen.this, $oid+"", Toast.LENGTH_SHORT).show();

            pd.dismiss();
        }
    }

}