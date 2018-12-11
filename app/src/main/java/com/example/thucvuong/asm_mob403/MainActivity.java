package com.example.thucvuong.asm_mob403;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.sutrix.common.Common;
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.model.Id;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    ArrayList<Truyen> truyens = new ArrayList<Truyen>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DucNguyen init()
        init();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void init() {

        if (getIntent().hasExtra("truyens")){
            truyens = (ArrayList<Truyen>) getIntent().getSerializableExtra("truyens");
            loadFragment(new HomeFragment(truyens));
        }else {

            new MainActivity.GetData().execute(Common.getAddressWithLimit(SplashScreen.limitTruyen));

            new CountDownTimer(4000, 1000) {
                ProgressDialog pd;
                @Override
                public void onTick(long l) {
                    pd = new ProgressDialog(MainActivity.this);
                    pd.setTitle("Please Wait ...");
                    pd.show();
                    pd.dismiss();
                }

                @Override
                public void onFinish() {

                    loadFragment(new HomeFragment(truyens));
                }
            }.start();
        }
//        Toast.makeText(this, truyens.get(0).getTieuDe()+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment(truyens, true);
                break;

            case R.id.navigation_theloai:
                fragment = new TheloaiFragment();
                break;

            case R.id.navigation_library:
                fragment = new LibraryFragment();
                break;

            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    //DucNguyen getData
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(MainActivity.this);

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
