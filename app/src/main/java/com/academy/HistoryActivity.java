package com.academy;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.academy.mbank.Application;
import com.academy.mbank.R;
import com.academy.mbank.database.DataBaseHelper;

public class HistoryActivity extends AppCompatActivity {
    private ImageView back;
    private RecyclerView rvHistory;
    private HistoryAdapter historyAdapter;

    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dataBaseHelper = new DataBaseHelper(this);

        back =findViewById(R.id.bgBack);

        back.setOnClickListener(view -> finish());
        rvHistory = findViewById(R.id.rvHistory);

        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        historyAdapter = new HistoryAdapter(this);

        historyAdapter.setData(dataBaseHelper.getHistorys(Application.userCurrent.getPhone()));
        rvHistory.setAdapter(historyAdapter);

    }
}