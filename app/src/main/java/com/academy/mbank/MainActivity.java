package com.academy.mbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.academy.HistoryActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvName,tvSodu;

    ImageView imageView2;
    private LinearLayout chuyentien,tienan, tienhoc, tiennha, shopping, diennuoc,giaitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.username);
        tvSodu = findViewById(R.id.sodu);


        chuyentien = findViewById(R.id.chuyentien);
        tienan = findViewById(R.id.tienan);
        tienhoc = findViewById(R.id.tienhoc);
        tiennha = findViewById(R.id.tiennha);
        shopping = findViewById(R.id.shopping);
        diennuoc = findViewById(R.id.diennuoc);
        giaitri = findViewById(R.id.giaitri);

        imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        tvName.setText(Application.userCurrent.getUsername());

        tvSodu.setText("Số dư : " + Application.userCurrent.getMoney() + "VND");



        chuyentien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Chuyển khoản");
                intent.putExtra("MONEYMAX",10000000);
                startActivity(intent);
            }
        });


        tienan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Tiền ăn");
                intent.putExtra("MONEYMAX",4000000);
                startActivity(intent);
            }
        });

        tienhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Tiền học");
                intent.putExtra("MONEYMAX",8000000);
                startActivity(intent);
            }
        });


        tiennha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Tiền nhà");
                intent.putExtra("MONEYMAX",5000000);
                startActivity(intent);
            }
        });


        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Shopping");
                intent.putExtra("MONEYMAX",2000000);
                startActivity(intent);
            }
        });


        diennuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Điện nước");
                intent.putExtra("MONEYMAX",2000000);
                startActivity(intent);
            }
        });

        giaitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StepPayment.class);
                intent.putExtra("TYPE","Giải trí");
                intent.putExtra("MONEYMAX",3000000);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvSodu.setText("Số dư : " + Application.userCurrent.getMoney() + "VND");
    }
}