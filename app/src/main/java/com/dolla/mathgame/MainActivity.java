package com.dolla.mathgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;

import java.util.Random;


public class MainActivity extends AppCompatActivity {


    TextView tvQuestion;
    EditText etAnswer;
    Button btnSkip;
    Button btnCheck;


    String[] operation = {"+", "-", "*"};
    int randomOperation;
    Random r = new Random();
    double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnSkip = findViewById(R.id.btnSkip);
        btnCheck = findViewById(R.id.btnCheck);

        tvQuestion.setVisibility(View.GONE);


        int num1 = r.nextInt(10) + 1;
        int num2 = r.nextInt(10) + 1;
        randomOperation = r.nextInt(3);
        String text = num1 + " " + operation[randomOperation] + " " + num2 + " = ?";
        switch (operation[randomOperation]) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / (double) num2;
                break;
            default:
        }

        tvQuestion.setText(text);
        tvQuestion.setVisibility(View.VISIBLE);


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvQuestion.setVisibility(View.GONE);


                int num1 = r.nextInt(10) + 1;
                int num2 = r.nextInt(10) + 1;
                randomOperation = r.nextInt(3);
                String text = num1 + " " + operation[randomOperation] + " " + num2 + " = ?";
                switch (operation[randomOperation]) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / (double) num2;
                        break;
                    default:
                }

                tvQuestion.setText(text);
                tvQuestion.setVisibility(View.VISIBLE);
                closeKeyboard();

            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                tvQuestion.setVisibility(View.GONE);

                try {
                    double answer = Double.parseDouble(etAnswer.getText().toString().trim());
                    String text;
                    if (answer == result)
                        text = "Correct!!";
                    else
                        text = "Wrong!";
                    tvQuestion.setText(text);
                } catch (Exception e) {
                    tvQuestion.setText(R.string.error);
                }


                tvQuestion.setVisibility(View.VISIBLE);
                etAnswer.setText("");
                closeKeyboard();

            }
        });


    }


    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

}