package com.example.calculator_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class SimpleCalculatorActvity extends AppCompatActivity {

    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        result = findViewById(R.id.resultTV);
        result.setShowSoftInputOnFocus(false);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.default_display).equals(result.getText().toString())) {
                    result.setText("");
                }
            }
        });
    }

    private void updateResult(String newStr) {
        String currentStr = result.getText().toString();
        int cursorPos = result.getSelectionStart();
        String leftStr = currentStr.substring(0, cursorPos);
        String rightStr = currentStr.substring(cursorPos);
        if(getString(R.string.default_display).equals(result.getText().toString())) {
            result.setText(newStr);
            result.setSelection(cursorPos + 1);
        } else {
            result.setText(String.format("%s%s%s", leftStr, newStr, rightStr));
            result.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view) {
        updateResult("0");
    }

    public void oneBTN(View view) {
        updateResult("1");
    }

    public void twoBTN(View view) {
        updateResult("2");
    }

    public void threeBTN(View view) {
        updateResult("3");
    }

    public void fourBTN(View view) {
        updateResult("4");
    }

    public void fiveBTN(View view) {
        updateResult("5");
    }

    public void sixBTN(View view) {
        updateResult("6");
    }

    public void sevenBTN(View view) {
        updateResult("7");
    }

    public void eightBTN(View view) {
        updateResult("8");
    }

    public void nineBTN(View view) {
        updateResult("9");
    }

    public void addBTN(View view) {
        updateResult("+");
    }

    public void subtractBTN(View view) {
        updateResult("-");
    }

    public void multiplyBTN(View view) {
        updateResult("×");
    }

    public void divideBTN(View view) {
        updateResult("÷");
    }

    public void signChangeBTN(View view) {
        if(result.getSelectionStart() == 0) return;
        int cursorPos = result.getSelectionStart();
        String text = result.getText().toString();

        int l = 0;
        for(int i = cursorPos-1; i < result.length() && i >= 0; --i) {
            if(Character.isDigit(text.charAt(i)) || text.charAt(i) == '.'){
                ++l;
            }
            else if (text.charAt(i) == '+' || text.charAt(i) == '-') {
                ++l;
                break;
            }
            else break;
        }

        StringBuilder temp = new StringBuilder(result.getText().toString());
        if(cursorPos - l == 0) {
            if(result.getText().toString().charAt(0) == '-'){
                temp.deleteCharAt(0);
                result.setText(temp);
                result.setSelection(cursorPos-1);
            }
            else{
                temp.insert(0,'-');
                result.setText(temp);
                result.setSelection(cursorPos + 1);
            }
        }
        else if(result.getText().toString().charAt(cursorPos - l) == '+') {
            temp.setCharAt(cursorPos - l,'-');
            result.setText(temp);
            result.setSelection(cursorPos);
        }
        else if(Character.isDigit(text.charAt(cursorPos - l))) {
            temp.insert(cursorPos - l,'-');
            result.setText(temp);
            result.setSelection(cursorPos + 1);
        }
        else if (result.getText().toString().charAt(cursorPos - l) == '-') {
            if(Character.isDigit(result.getText().toString().charAt(cursorPos - l-1))){
                temp.setCharAt(cursorPos - l,'+');
                result.setText(temp);
                result.setSelection(cursorPos);
            }
            else {
                temp.deleteCharAt(cursorPos - l);
                result.setText(temp);
                result.setSelection(cursorPos-1);
            }
        }
    }

    public void backspaceBTN(View view) {
        int cursorPos = result.getSelectionStart();
        int strLen = result.getText().length();

        if (cursorPos != 0 && strLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) result.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            result.setText(selection);
            result.setSelection(cursorPos - 1);
        }
    }

    public void clearBTN(View view) {
        result.setText("");
    }

    public void pointBTN(View view) {
        updateResult(".");
    }

    public void equalsBTN(View view) {
        String strExpression = result.getText().toString();
        strExpression = strExpression.replaceAll("÷", "/");
        strExpression = strExpression.replaceAll("×", "*");

        Expression expression = new Expression(strExpression);
        String expressionResult = String.valueOf(expression.calculate());

        if(expressionResult.equals("NaN")) {
            Toast.makeText(this, "Expression is not valid", Toast.LENGTH_SHORT).show();
        }
        else {
            result.setText(expressionResult);
            result.setSelection(expressionResult.length());
        }
    }

}