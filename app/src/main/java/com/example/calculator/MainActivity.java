package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    Button buttonAc,buttonDel;
//    AppCompatImageButton buttonDel;
    Button buttonPercent, buttonDiv, buttonMul, buttonMinus, buttonPlus, buttonEqual;
    Button button0, button1, button2, button3,button4, button5, button6, button7, button8, button9;
    Button buttonComa;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result);
        solutionTv = findViewById(R.id.solution);

        buttonAc = findViewById(R.id.Ac);
        buttonDel = findViewById(R.id.C);
        buttonPercent = findViewById(R.id.percent);
        buttonDiv = findViewById(R.id.div);
        buttonMul = findViewById(R.id.mul);
        buttonMinus = findViewById(R.id.minus);
        buttonPlus = findViewById(R.id.plus);
        buttonEqual = findViewById(R.id.equal);
        button0 = findViewById(R.id.zero);
        button1 = findViewById(R.id.one);
        button2 = findViewById(R.id.two);
        button3 = findViewById(R.id.three);
        button4 = findViewById(R.id.four);
        button5 = findViewById(R.id.five);
        button6 = findViewById(R.id.six);
        button7 = findViewById(R.id.seven);
        button8 = findViewById(R.id.eight);
        button9 = findViewById(R.id.nine);
        buttonComa = findViewById(R.id.coma);

        buttonAc.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonComa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        
        if (buttonText.equals("Ac")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult;
        finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }

}