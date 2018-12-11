package com.example.thucvuong.asm_mob403;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.sutrix.adapter.TruyenAdapter;
import com.sutrix.adapter.TruyenTranhAdapter;
import com.sutrix.model.Truyen;

import java.util.ArrayList;

public class ReadTruyenTranhActivity extends AppCompatActivity {

    int index = 0;

    ArrayList<Truyen> truyens = new ArrayList<Truyen>();

    TextView tv_TieuDeTruyenTranh;
    ImageButton img_btn_backTruyenTranh;
    Button btn_previous_truyentranh, btn_next_truyentranh;

    RecyclerView recyclerView;

    TruyenTranhAdapter truyenTranhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_truyen_tranh);

        init();

        _showTruyen();
    }

    private void init() {

        if (getIntent().hasExtra("truyens")){
            truyens = (ArrayList<Truyen>) getIntent().getSerializableExtra("truyens");
        }

        tv_TieuDeTruyenTranh = findViewById(R.id.tv_TieuDeTruyenTranh);
        img_btn_backTruyenTranh = findViewById(R.id.img_btn_backTruyenTranh);


        recyclerView = findViewById(R.id.rc_truyentranh);

        img_btn_backTruyenTranh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void _showTruyen(){

        if (truyens.size() > 0){

            tv_TieuDeTruyenTranh.setText(truyens.get(0).getTieuDe());

            /////////////////////////////////////////

            recyclerView.setHasFixedSize(true);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReadTruyenTranhActivity.this, LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(linearLayoutManager);

            truyenTranhAdapter = new TruyenTranhAdapter(truyens.get(0).getNoiDung(), getApplicationContext());

            recyclerView.setAdapter(truyenTranhAdapter);



        }

    }
}
