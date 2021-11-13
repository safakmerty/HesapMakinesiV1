package com.example.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText goruntule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goruntule = findViewById(R.id.input);
        goruntule.setShowSoftInputOnFocus(false);


        goruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.goruntule).equals(goruntule.getText().toString())) {
                    goruntule.setText("");
                }
            }
        });
    }


    private void updateText(String strToAdd){
        String oldStr = goruntule.getText().toString();
        int cursorPos = goruntule.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.goruntule).equals(goruntule.getText().toString())){
            goruntule.setText(strToAdd);
            goruntule.setSelection(cursorPos + 1);
        }
        else{
            goruntule.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
            goruntule.setSelection(cursorPos + 1);
        }



    }

    public void sifirBTN(View view){
        updateText("0");

    }

    public void birBTN(View view){
        updateText("1");

    }

    public void ikiBTN(View view){
        updateText("2");

    }

    public void ucBTN(View view){
        updateText("3");

    }

    public void dortBTN(View view){
        updateText("4");
    }

    public void besBTN(View view){
        updateText("5");
    }

    public void altiBTN(View view){
        updateText("6");
    }

    public void yediBTN(View view){
        updateText("7");
    }

    public void sekizBTN(View view){
        updateText("8");
    }

    public void dokuzBTN(View view){
        updateText("9");
    }

    public void nokta(View view){
        updateText(".");
    }

    public void temizle(View view){
        goruntule.setText("");
    }

    public void artieksi(View view){
        updateText("-");
    }

    public void parantez(View view){
        int cursorPos = goruntule.getSelectionStart();
        int acikPar = 0;
        int kapaliPar = 0;
        int textLen = goruntule.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if(goruntule.getText().toString().substring(i, i+1).equals("(")){
                acikPar +=1;
            }
            if(goruntule.getText().toString().substring(i, i+1).equals(")")){
                kapaliPar +=1;
            }
        }
        if(acikPar == kapaliPar || goruntule.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }
        else if(kapaliPar < acikPar && !goruntule.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        goruntule.setSelection(cursorPos + 1);
    }

    public void cikar(View view){
        updateText("-");
    }

    public void carp(View view){
        updateText("×");
    }

    public void topla(View view){
        updateText("+");
    }

    public void bol(View view){
        updateText("÷");
    }

    public void esit(View view){
        String userExp = goruntule.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String sonuc = String.valueOf(exp.calculate());

        goruntule.setText(sonuc);
        goruntule.setSelection(sonuc.length());



    }

    public void geri(View view){
        int cursorPos = goruntule.getSelectionStart();
        int textLen = goruntule.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) goruntule.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            goruntule.setText(selection);
            goruntule.setSelection(cursorPos - 1);
        }

    }

}