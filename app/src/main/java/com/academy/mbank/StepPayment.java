package com.academy.mbank;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.academy.mbank.database.DataBaseHelper;
import com.anton46.stepsview.StepsView;

import org.w3c.dom.Text;

import java.util.Date;

public class StepPayment extends AppCompatActivity {
    private StepsView stepsView;

    private TextView tvBack, tvNext, tvType, tvBackHome;

    private ImageView imageBack;


    private EditText etSotaikhoan, etSoTien, etContent,etOTP;


    private TextView tvSotaikhoan, tvSoTien, tvContent, tvcoursesspinner;

    private ConstraintLayout step1, step2, step3, step4;
    private Spinner spinner;

    private DataBaseHelper dataBaseHelper;

    String nameBank = "";


    String[] banks = {"Vietcombank", "Sacombank",
            "Eximbank", "Techcombank",
            "Viettinbank", "An Bình Bank"};

    final String[] labels = {"Infomation", "Confirm", "Payment"};

    int moneymax= 0;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_payment);

        stepsView = findViewById(R.id.stepsView);
        tvBack = findViewById(R.id.back);
        tvNext = findViewById(R.id.next);
        tvType = findViewById(R.id.tvType);
        imageBack = findViewById(R.id.bgBack);

        dataBaseHelper = new DataBaseHelper(this);


        etSotaikhoan = findViewById(R.id.etSoTaiKhoan);
        etSoTien = findViewById(R.id.etSoTien);
        etContent = findViewById(R.id.etContent);

        tvcoursesspinner = findViewById(R.id.tvcoursesspinner);
        tvSotaikhoan = findViewById(R.id.tvSoTaiKhoan);
        tvSoTien = findViewById(R.id.tvSoTien);
        tvContent = findViewById(R.id.tvContent);
        tvBackHome = findViewById(R.id.btnHome);
        etOTP=findViewById(R.id.etOTP);



        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);


        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        spinner = findViewById(R.id.coursesspinner);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                banks);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.setAdapter(ad);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameBank = banks[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        tvType.setText("Danh mục: "+ getIntent().getStringExtra("TYPE"));


        moneymax = getIntent().getIntExtra("MONEYMAX",0);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0)
                    finish();
                else {
                    position = position - 1;
                    setStep(position);
                }
            }
        });


        tvNext.setOnClickListener(view -> {
            if (position == 3){

            }

            else {
                if(position == 0) {
                    String txtStk = etSotaikhoan.getText().toString().trim();
                    String txtSotien = etSoTien.getText().toString().trim();
                    String txtContent = etContent.getText().toString().trim();
                    if(txtStk.isEmpty() || txtSotien.isEmpty() || txtContent.isEmpty()) {
                        Toast.makeText(StepPayment.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(Integer.parseInt(txtSotien) <1000) {
                        Toast.makeText(StepPayment.this,"Số tiền không được dưới 1000",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(Integer.parseInt(txtSotien) >Application.userCurrent.getMoney()) {
                        Toast.makeText(StepPayment.this,"Số dư tài khoản không đủ",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    position = 1;
                    setStep(position);
                    return;
                }

                if(position == 1) {
                    String txtStk = etSotaikhoan.getText().toString().trim();
                    String txtSotien = etSoTien.getText().toString().trim();
                    String txtContent = etContent.getText().toString().trim();
                    if(Integer.parseInt(txtSotien) <=moneymax) {
                        TypeSwapMoney typeSwapMoney = new TypeSwapMoney();
                        typeSwapMoney.setAccountTo(txtStk);
                        typeSwapMoney.setPrice(Integer.parseInt(txtSotien));
                        typeSwapMoney.setContent(txtContent);
                        typeSwapMoney.setDate(new Date().toString());
                        typeSwapMoney.setTypeSwap(getIntent().getStringExtra("TYPE"));
                        typeSwapMoney.setPhone(Application.userCurrent.getPhone());


                        dataBaseHelper.addHistory(typeSwapMoney);

                        dataBaseHelper.updateMoney(Application.userCurrent.getPhone(),Application.userCurrent.getMoney() - Integer.parseInt(txtSotien));
                        Application.userCurrent = dataBaseHelper.getUser(Application.userCurrent.getPhone());

                        position = 3;
                    } else {
                        position = 2;
                    }
                    setStep(position);
                    return;
                }

                if(position == 2) {
                    String txtOTP = etOTP.getText().toString().trim();
                    String txtStk = etSotaikhoan.getText().toString().trim();
                    String txtSotien = etSoTien.getText().toString().trim();
                    String txtContent = etContent.getText().toString().trim();
                    if(txtOTP.equals("111111")) {
                        TypeSwapMoney typeSwapMoney = new TypeSwapMoney();
                        typeSwapMoney.setAccountTo(txtStk);
                        typeSwapMoney.setPrice(Integer.parseInt(txtSotien));
                        typeSwapMoney.setContent(txtContent);
                        typeSwapMoney.setDate(new Date().toString());
                        typeSwapMoney.setTypeSwap(getIntent().getStringExtra("TYPE"));
                        typeSwapMoney.setPhone(Application.userCurrent.getPhone());

                        dataBaseHelper.addHistory(typeSwapMoney);

                        dataBaseHelper.updateMoney(Application.userCurrent.getPhone(),Application.userCurrent.getMoney() - Integer.parseInt(txtSotien));
                        Application.userCurrent = dataBaseHelper.getUser(Application.userCurrent.getPhone());

                        position = 3;
                        setStep(position);
                    } else {
                        Toast.makeText(StepPayment.this,"OTP sai",Toast.LENGTH_SHORT).show();
                    }
                }







            }
        });





        setStep(position);


        stepsView.setLabels(labels)
                .setBarColorIndicator(getApplicationContext().getResources().getColor(R.color.white))
                .setProgressColorIndicator(getApplicationContext().getResources().getColor(R.color.bluebole))
                .setLabelColorIndicator(getApplicationContext().getResources().getColor(R.color.bluebole))
                .setCompletedPosition(position)
                .drawView();

    }


    private void setStep(int position) {
        if(position != 3) {
            stepsView.setLabels(labels)
                    .setBarColorIndicator(getApplicationContext().getResources().getColor(R.color.white))
                    .setProgressColorIndicator(getApplicationContext().getResources().getColor(R.color.bluebole))
                    .setLabelColorIndicator(getApplicationContext().getResources().getColor(R.color.bluebole))
                    .setCompletedPosition(position)
                    .drawView();
        }



        switch (position) {
            case 0:

                step1.setVisibility(View.VISIBLE);
                step2.setVisibility(View.GONE);
                step3.setVisibility(View.GONE);
                step4.setVisibility(View.GONE);
                break;

            case 1:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.VISIBLE);
                step3.setVisibility(View.GONE);
                step4.setVisibility(View.GONE);
                tvcoursesspinner.setText(nameBank);
                tvSotaikhoan.setText(etSotaikhoan.getText().toString());
                tvContent.setText(etContent.getText().toString());
                tvSoTien.setText(etSoTien.getText().toString());

                break;
            case 2:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.GONE);
                step3.setVisibility(View.VISIBLE);
                step4.setVisibility(View.GONE);
                break;
            case 3:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.GONE);
                step3.setVisibility(View.GONE);
                step4.setVisibility(View.VISIBLE);
                stepsView.setVisibility(View.INVISIBLE);
                tvBack.setVisibility(View.INVISIBLE);
                tvNext.setVisibility(View.INVISIBLE);
                break;
        }
    }
}