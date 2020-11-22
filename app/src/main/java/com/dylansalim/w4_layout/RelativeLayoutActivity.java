package com.dylansalim.w4_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RelativeLayoutActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        mEditTextName = (EditText) findViewById(R.id.et_relative_name);
        mSubmitBtn = (Button) findViewById(R.id.btn_relative_back_to_home);

        mSubmitBtn.setOnClickListener(view -> {
            String name = mEditTextName.getText().toString();
            Intent intent = new Intent(RelativeLayoutActivity.this, MainActivity.class);
            intent.putExtra("name",name);
            startActivity(intent);
            finish();
        });
    }
}