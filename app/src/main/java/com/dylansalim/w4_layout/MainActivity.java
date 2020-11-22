package com.dylansalim.w4_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mRelativeButton;
    private Button mConstraintButton;
    private Button mGridButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeButton = findViewById(R.id.btn_main_relative);
        mConstraintButton = findViewById(R.id.btn_main_constraint);
        mGridButton = findViewById(R.id.btn_main_grid);
        printName();
        mRelativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RelativeLayoutActivity.class);
                startActivity(intent);
            }
        });
        mConstraintButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ConstraintActivity.class);
            startActivity(intent);
        });
        mGridButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }

    private void printName() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name != null && name.length() > 0) {
            showToastMessage("Welcome back, " + name + "!");
        }
    }

    private void showToastMessage(String s) {
        if (s != null) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }
}