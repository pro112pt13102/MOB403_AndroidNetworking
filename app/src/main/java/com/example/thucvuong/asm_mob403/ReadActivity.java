package com.example.thucvuong.asm_mob403;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    TextView tvRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        tvRead = findViewById(R.id.tvNoi_dung_truyen);
        tvRead.setText("Giờ họa, cô giáo dạy các em học sinh lớp hai vẽ trái tim. Cô vẽ mẫu trên bảng xong rồi quay xuống:\n" +
                "\n" +
                "- Các em vẽ đi!\n" +
                "\n" +
                "Cả lớp bắt đầu vẽ. Riêng Vova không vẽ. Cô giáo hỏi:\n" +
                "\n" +
                "- Sao em không vẽ?\n" +
                "\n" +
                "- Thưa cô – Vova trả lời – Cô vẽ còn thiếu.\n" +
                "\n" +
                "- Thiếu cái gì?\n" +
                "\n" +
                "- Áo quần.\n" +
                "\n" +
                "- Sao vậy?\n" +
                "\n" +
                "- Ở nhà lúc ngủ dậy, em nghe ba em nói với mẹ: “Trái tim của anh ơi, anh mặc áo quần cho em nhé!”");
    }
}
