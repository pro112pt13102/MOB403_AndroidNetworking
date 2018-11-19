package com.example.thucvuong.asm_mob403;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sutrix.model.Truyen;

import java.util.ArrayList;

public class MotaActivity extends AppCompatActivity {

    ImageButton imgbtn_back;
    TextView tv_title_mid, tv_TenTacGia, tv_TheLoai, tv_TrangThai, tv_SoChuong, tv_NgayUp,
            tv_NgayCapNhat, tv_NoiDung;


    ImageView img_chitiet;
    Button btn_Doctruyen, btn_Themtruyen;
    ArrayList<Truyen> truyens;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mota);

        anhxa();

        setData_inView();


    }

    private void anhxa(){
        tv_title_mid = findViewById(R.id.textViewTitle_mid);
        tv_TenTacGia = findViewById(R.id.tvTen_tacgia);
        tv_TrangThai = findViewById(R.id.tvTrangthai);
        tv_TheLoai = findViewById(R.id.tvTheloai);
        tv_SoChuong = findViewById(R.id.tvSochuong);
        tv_NgayUp = findViewById(R.id.tvNgayup);
        tv_NgayCapNhat = findViewById(R.id.tvNgaycapnhat);
        tv_NoiDung = findViewById(R.id.tvNoidung);
        img_chitiet = findViewById(R.id.img_hinhsp_chitiet);
        btn_Doctruyen = findViewById(R.id.btnDoc_truyen);
        btn_Themtruyen = findViewById(R.id.btnthem_truyen);
        imgbtn_back = findViewById(R.id.img_btn_back);



        //truyens = (ArrayList<Truyen>) getIntent().getSerializableExtra("truyens");


    }

    private void setData_inView(){
        Intent intent = getIntent();

        int indexValue = intent.getIntExtra("index", 0); //Nhận index của Arraylist từ OnClickListener Item RecycleView

        Bundle bundle = intent.getBundleExtra("BUNDLE");
        truyens = (ArrayList<Truyen>) bundle.getSerializable("truyens");

        Picasso.get().load(truyens.get(indexValue).getHinh()).into(img_chitiet);

        tv_title_mid.setText(truyens.get(indexValue).getTieuDe());
        tv_TenTacGia.setText(truyens.get(indexValue).getTacGia());
        //tv_TheLoai.setText(truyens.get(indexValue).getTheLoai());
        tv_NgayCapNhat.setText(truyens.get(indexValue).getNgayCapNhat());
        tv_NgayUp.setText(truyens.get(indexValue).getNgayUp());
        tv_TrangThai.setText(truyens.get(indexValue).getTrangThai());
        tv_SoChuong.setText(String.valueOf(truyens.get(indexValue).getSoChuong()));
        //tv_NoiDung.setText(truyens.get(indexValue).getNoiDung());

        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
