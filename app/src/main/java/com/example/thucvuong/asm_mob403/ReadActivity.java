package com.example.thucvuong.asm_mob403;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sutrix.model.Truyen;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    int index = 0;

    ArrayList<Truyen> truyens = new ArrayList<Truyen>();

    TextView tv_Title_truyen, tvNoi_dung_truyen, tv_TieuDeTruyen;
    ImageButton img_btn_back;
    Button btn_previous_truyen, btn_next_truyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        init();

        _showTruyen();

        _doiChuong();
    }

    private void init() {
        if (getIntent().hasExtra("truyens")){
            truyens = (ArrayList<Truyen>) getIntent().getSerializableExtra("truyens");
        }

        tv_Title_truyen = findViewById(R.id.tv_Title_truyen);
        tvNoi_dung_truyen = findViewById(R.id.tvNoi_dung_truyen);
        tv_TieuDeTruyen = findViewById(R.id.tv_TieuDeTruyen);

        img_btn_back = findViewById(R.id.img_btn_back);

        btn_previous_truyen = findViewById(R.id.btn_previous_truyen);
        btn_next_truyen = findViewById(R.id.btn_next_truyen);

    }

    private void _showTruyen(){

        if (truyens.size() > 0){

            tv_TieuDeTruyen.setText(truyens.get(0).getTieuDe());

            tv_Title_truyen.setText("Chương "+(index+1)+": ");

            tvNoi_dung_truyen.setText(truyens.get(0).getNoiDung().get(index));

        }

    }

    private void _doiChuong(){

        btn_previous_truyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (index > 0){

                    index--;


                    _showTruyen();

                }else{
                    Toast.makeText(ReadActivity.this, "Đây là chương đầu tiền rồi !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_next_truyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (index < truyens.get(0).getNoiDung().size()-1){

                    index++;


                    _showTruyen();

                }else {
                    Toast.makeText(ReadActivity.this, "Rất tiếc đây là chương cuối rồi !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
