package com.example.calculator_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        openSimpleCalculatorActivity();
        openAdvancedCalculatorActivity();
        openAboutActivity();
        exit();
    }

    public void openSimpleCalculatorActivity() {
        Button simpleBTN = (Button) findViewById(R.id.simpleBTN);
        simpleBTN.setOnClickListener((view -> { startActivity(new Intent(this, SimpleCalculatorActvity.class));}));
    }

    public void openAdvancedCalculatorActivity() {
        Button advancedBTN = (Button) findViewById(R.id.advancedBTN);
        advancedBTN.setOnClickListener((view -> { startActivity(new Intent(this, AdvancedCalculatorActvity.class));}));
    }

    public void openAboutActivity() {
        Button aboutBTN = (Button) findViewById(R.id.aboutBTN);
        aboutBTN.setOnClickListener((view -> { startActivity(new Intent(this, AboutActivity.class));}));
    }

    public void exit() {
        Button exitBTN = findViewById(R.id.exitBTN);
        exitBTN.setOnClickListener((view -> finish()));
    }
}