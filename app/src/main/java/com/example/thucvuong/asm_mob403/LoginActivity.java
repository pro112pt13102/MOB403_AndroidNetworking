package com.example.thucvuong.asm_mob403;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button bt_dk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt_dk = (Button) findViewById(R.id.btn_Register);
        bt_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myDK = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(myDK);
            }
        });
    }
}
