package com.dylansalim.w4_layout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBackBtn;
    private TextView mEquation;
    private TextView mCalculatorDisplay;

    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorService mCalculatorService;
    private static final String DIGITS = "0123456789.";
    DecimalFormat df = new DecimalFormat("@###########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mCalculatorService = new CalculatorService();
        initializeUI();
    }

    private void initializeUI() {
        findViewById(R.id.btn_clr).setOnClickListener(this);
        findViewById(R.id.btn_invert_sign).setOnClickListener(this);
        findViewById(R.id.btn_percentage).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_cal).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_decimal).setOnClickListener(this);
        mBackBtn = (Button) findViewById(R.id.btn_calculator_back);
        mEquation = (TextView) findViewById(R.id.tv_calculator_equation);
        mCalculatorDisplay = (TextView) findViewById(R.id.tv_calculator_result);

        mBackBtn.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();
        Log.d("buttonPressed", buttonPressed);

        if (DIGITS.contains(buttonPressed)) {

            // digit was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // Eliminate entering multiple decimals
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    // Avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {
                // Enable continuous calculation after an equal
                if (mEquation.getText().toString().length() > 0 && mEquation.getText().charAt(mEquation.length() - 1) == '=') {
                    mEquation.setText(mEquation.getText().toString().substring(0, mEquation.length() - 1));
                } else {
                    mEquation.append(mCalculatorDisplay.getText().toString());
                }

                mCalculatorService.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                mEquation.append(buttonPressed);
                userIsInTheMiddleOfTypingANumber = buttonPressed.equals("=");
            }

            if (buttonPressed.equals("C")) {
                mEquation.setText("");
            }


            mCalculatorService.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mCalculatorService.getResult()));

        }
    }
}