package com.example.thucvuong.asm_mob403;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sutrix.common.Common;
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.model.Id;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity{

    //limit truyen;
    public static int limitTruyen = 10;


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
        new GetData().execute(Common.getAddressWithLimit(limitTruyen));

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
//                if (i<100){
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

                    //to MainActivity with data Truyen

                    Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                    intent.putExtra("truyens", truyens);
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

//            pd.setTitle("Please Wait ...");
//            pd.show();
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
                int length = jsonArray.length();
                for (int i=0; i<length; i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    JSONObject _id = jsonObject.getJSONObject("_id");
                    $oid = _id.getString("$oid");

                    String tieude = jsonObject.getString("TieuDe");
                    String tacgia = jsonObject.getString("TacGia");
                    String trangthai = jsonObject.getString("TrangThai");
                    int sochuong = Integer.parseInt(jsonObject.getString("SoChuong"));
                    String ngayup = jsonObject.getString("NgayUp");
                    String ngaycapnhat = jsonObject.getString("NgayCapNhat");
                    String nhomdich = jsonObject.getString("NhomDich");
                    String mota = jsonObject.getString("MoTa");
                    String hinh = jsonObject.getString("Hinh");

                    //Get The Loai
                    JSONArray tempTheLoais = jsonObject.getJSONArray("TheLoai");
                    List<String> theloais = new ArrayList<>();
                    for (int x = 0; x < tempTheLoais.length(); x++){
                        theloais.add(tempTheLoais.getString(x));
                    }

                    //Get Noi Dung
                    JSONArray tempNoiDungs = jsonObject.getJSONArray("NoiDung");
                    List<String> noidungs = new ArrayList<>();
                    for (int z = 0; z < tempNoiDungs.length(); z++){
                        noidungs.add(tempNoiDungs.getString(z));
                    }

//                    Log.d("zzdzdzdzdzd", noidungs.toString());

//                    truyen.setTieuDe(jsonObject.getString("TieuDe"));
//                    truyen.setNoiDung(jsonObject.getString("NoiDung"));
//                    truyen.setMoTa(jsonObject.getString("MoTa"));

                    Truyen truyen = new Truyen();
                    Id id = new Id();

                    id.setOid($oid);
                    truyen.set_id(id);
                    truyen.setTieuDe(tieude);
                    truyen.setTacGia(tacgia);
                    truyen.setTheLoai(theloais);
                    truyen.setTrangThai(trangthai);
                    truyen.setSoChuong(sochuong);
                    truyen.setNgayUp(ngayup);
                    truyen.setNgayCapNhat(ngaycapnhat);
                    truyen.setNhomDich(nhomdich);
                    truyen.setMoTa(mota);
                    truyen.setNoiDung(noidungs);
                    truyen.setHinh(hinh);

                    truyens.add(truyen);


                }


            }catch (Exception e){
                e.printStackTrace();
            }


//            Toast.makeText(SplashScreen.this, truyens.get(1).getHinh()+"", Toast.LENGTH_SHORT).show();

            pd.dismiss();
        }
    }

}