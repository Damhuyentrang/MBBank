package com.academy.mbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.academy.mbank.database.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {
    private TextView btnDangNhap;
    private EditText etPhone, etPassword;

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataBaseHelper = new DataBaseHelper(this);

        btnDangNhap = findViewById(R.id.btnDangNhap);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textPhone = etPhone.getText().toString();
                String textPassword = etPassword.getText().toString();



                if(dataBaseHelper.checkLogin(textPhone,textPassword)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Application.userCurrent = dataBaseHelper.getUser(textPhone);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"Tài khoản và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}