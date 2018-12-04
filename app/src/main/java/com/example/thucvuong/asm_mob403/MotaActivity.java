package com.example.thucvuong.asm_mob403;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.sutrix.common.Common;
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.model.Id;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MotaActivity extends AppCompatActivity {

    String _oid;

    ArrayList<Truyen> truyens = new ArrayList<Truyen>();

    TextView tv_TieuDe, tvTen_tacgia, tvTheloai, tvTrangthai, tvSochuong, tvNgayup, tvNgaycapnhat, tvNoidung;
    ImageView img_hinhsp_chitiet;
    ImageButton img_btn_back;
    Button btnDoc_truyen;
    CircleImageView civHinhTruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mota);

        //DucNguyen init
        init();

        //DucNguyen
        new MotaActivity.GetData().execute(Common.getAddressSingle(_oid));

    }



    private void init() {
        if (getIntent().hasExtra("oid")){
//            Toast.makeText(this, getIntent().getStringExtra("oid")+"", Toast.LENGTH_SHORT).show();
            _oid = getIntent().getStringExtra("oid");
        }

        tv_TieuDe = findViewById(R.id.tv_TieuDe);
        tvTen_tacgia = findViewById(R.id.tvTen_tacgia);
        tvTheloai = findViewById(R.id.tvTheloai);
        tvTrangthai = findViewById(R.id.tvTrangthai);
        tvSochuong = findViewById(R.id.tvSochuong);
        tvNgayup = findViewById(R.id.tvNgayup);
        tvNgaycapnhat = findViewById(R.id.tvNgaycapnhat);
        tvNoidung = findViewById(R.id.tvNoidung);
        civHinhTruyen = findViewById(R.id.civHinhTruyen);
        img_hinhsp_chitiet = findViewById(R.id.img_hinhsp_chitiet);
        img_btn_back = findViewById(R.id.img_btn_back);
        img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        btnDoc_truyen = findViewById(R.id.btnDoc_truyen);
        btnDoc_truyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MotaActivity.this, ReadActivity.class);
                intent.putExtra("truyens", truyens);
                startActivity(intent);

            }
        });

    }

    private void _setChange() {

        ArrayList<String> tempTheLoai = (ArrayList<String>) truyens.get(0).getTheLoai();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tempTheLoai.size(); i++){
            stringBuilder.append(tempTheLoai.get(i)+", ");
        }

        tv_TieuDe.setText(truyens.get(0).getTieuDe());
        tvTen_tacgia.setText(truyens.get(0).getTacGia());
        tvTheloai.setText(stringBuilder);
        tvTrangthai.setText(truyens.get(0).getTrangThai());
        tvSochuong.setText(truyens.get(0).getSoChuong()+"");
        tvNgayup.setText(truyens.get(0).getNgayUp());
        tvNgaycapnhat.setText(truyens.get(0).getNgayCapNhat());
        tvNoidung.setText(truyens.get(0).getMoTa());

        Picasso.get().load(truyens.get(0).getHinh()).into(img_hinhsp_chitiet);
        Picasso.get().load(truyens.get(0).getHinh()).into(civHinhTruyen);

    }



    //DucNguyen getData
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(MotaActivity.this);

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
                JSONObject _json = new JSONObject(s);
                JSONObject _id = _json.getJSONObject("_id");
                $oid = _id.getString("$oid");

                String tieude = _json.getString("TieuDe");
                String tacgia = _json.getString("TacGia");
                String trangthai = _json.getString("TrangThai");
                int sochuong = Integer.parseInt(_json.getString("SoChuong"));
                String ngayup = _json.getString("NgayUp");
                String ngaycapnhat = _json.getString("NgayCapNhat");
                String nhomdich = _json.getString("NhomDich");
                String mota = _json.getString("MoTa");
                String hinh = _json.getString("Hinh");

                //Get The Loai
                JSONArray tempTheLoais = _json.getJSONArray("TheLoai");
                List<String> theloais = new ArrayList<>();
                for (int x = 0; x < tempTheLoais.length(); x++){
                    theloais.add(tempTheLoais.getString(x));
                }

                //Get Noi Dung
                JSONArray tempNoiDungs = _json.getJSONArray("NoiDung");
                List<String> noidungs = new ArrayList<>();
                for (int z = 0; z < tempNoiDungs.length(); z++){
                    noidungs.add(tempNoiDungs.getString(z));
                }


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


            } catch (JSONException e) {

                e.printStackTrace();

            }

            pd.dismiss();

            _setChange();

//            Log.d("DUCNGUYEN", truyens.get(0).getTacGia()+"");
        }
    }

}
