package com.example.thucvuong.asm_mob403;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.common.StringUtils;
import com.sutrix.adapter.SearchResultAdapter;
import com.sutrix.adapter.TruyenAdapter;
import com.sutrix.common.Common;
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.model.Id;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    String tieuDe;

    ArrayList<Truyen> truyens = new ArrayList<>();

    RecyclerView recyclerView;

    TextView tvSo_trang;

    SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();
    }

    private void init() {

        if (getIntent().hasExtra("searchName")){
            tieuDe = getIntent().getStringExtra("searchName");
            new GetData().execute(Common.getAddressWithTieuDe(tieuDe));
        }else{
            Toast.makeText(this, "Không tìm thấy truyện !", Toast.LENGTH_SHORT).show();
        }

    }

    private void setAdapter(){

        recyclerView = findViewById(R.id.rc_trangchuSearch);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        searchResultAdapter = new SearchResultAdapter(truyens, this);

        recyclerView.setAdapter(searchResultAdapter);


    }

    //DucNguyen getData
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ResultActivity.this);

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

            if(s.equalsIgnoreCase("") || s==null){
                Toast.makeText(ResultActivity.this, "Không tìm thấy!", Toast.LENGTH_SHORT).show();
            }else {
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
            } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            if (truyens.size() > 0){
                setAdapter();
            }else {
                Toast.makeText(ResultActivity.this, "Không tìm thấy truyện !", Toast.LENGTH_SHORT).show();
            }


            pd.dismiss();
        }
    }
}
