package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class CalculateActivity extends AppCompatActivity {

    private TextView feeTextView;
    private EditText in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        in = findViewById(R.id.weddingEditText);
        feeTextView = findViewById(R.id.weddingPrizeTextView);
        String s=String.valueOf(MenuActivity.favouriteItems);//Now it will return "10"

        in.setText(s);
    }

    public void szamolas(View view) {

        String dij = String.valueOf(in.getText());
        int szamitott = Integer.parseInt(dij) * 20000;
        feeTextView.setText("Ár: " + szamitott + " Ft");
        MenuActivity.favouriteItems = 0;

    }
}