package com.dylansalim.w4_layout;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConstraintActivity extends AppCompatActivity {
    private Button mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        mBackBtn = (Button) findViewById(R.id.btn_constraint_home);

        mBackBtn.setOnClickListener(view -> {
            finish();
        });
    }
}