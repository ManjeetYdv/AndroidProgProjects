package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false); //stops the popping default keyboard
        display.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(getString(R.string.show).equals(display.getText().toString())){ //show(fromstrings.xml )
                    display.setText(" ");
                }
            }
        });
    }
    private void updateText(String strToAdd){
        String oldstr = display.getText().toString();
        int cursorpos = display.getSelectionStart(); //returns cursor position eg. 123\456so index3 will be returned
        String leftStr =oldstr.substring(0 , cursorpos);
        String rightStr = oldstr.substring(cursorpos);
        if(getString(R.string.show).equals(display.getText().toString())){
            ;
            display.setText(String.format("%s%s" , "" ,strToAdd));
            display.setSelection(cursorpos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorpos + 1);
        }
    }
    public void zeroBtn(View view){
        updateText("0");
    }
    public void oneBtn(View view){
        updateText("1");
    }
    public void twoBtn(View view){
        updateText("2");
    }
    public void threeBtn(View view){
        updateText("3");
    }
    public void fourBtn(View view){
        updateText("4");
    }
    public void fiveBtn(View view){
        updateText("5");
    }
    public void sixBtn(View view){
        updateText("6");
    }
    public void sevenBtn(View view){
        updateText("7");
    }
    public void eightBtn(View view){
        updateText("8");
    }
    public void nineBtn(View view){
        updateText("9");
    }
    public void addBtn(View view){
        updateText("+");
    }
    public void subtractBtn(View view){
        updateText("-");
    }
    public void multiplyBtn(View view){
        updateText("×");
    }
    public void divideBtn(View view){
        updateText("/");
    }
    public void plusMinusBtn(View view){
        updateText("-");
    }
    public void pointBtn(View view){
        updateText(".");
    }
    public void exponentBtn(View view){
        updateText("^");
    }
    public void bracketsBtn(View view){
        int cursorpos =display.getSelectionStart();
        int openbracket =0;
        int closebracket =0;
        int textlength = display.getText().length();
        for(int i =0 ;i<cursorpos ;i++){
            if(display.getText().toString().substring(i , i+1).equals("(")){
                openbracket+=1;
            }
            if(display.getText().toString().substring(i , i+1).equals(")")){
                closebracket+=1;
            }
        }
        if(openbracket ==closebracket || display.getText().toString().substring(textlength-1, textlength).equals("(")){
            updateText("(");
            display.setSelection(cursorpos+1);
        }
        else if(closebracket<openbracket
                && !display.getText().toString().substring(textlength-1 , textlength).equals("(")){
            updateText(")");
            display.setSelection(textlength+1);
        }
    }
    public void clearBtn(View view){
        display.setText("");
    }
    public void backspaceBtn(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos!=0 && textLen!=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1 ,cursorPos , "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
    public void equalsBtn(View view){
        String userexp = display.getText().toString();
        userexp =userexp.replaceAll("÷" , "/");
        userexp =userexp.replaceAll("×" ,"*");
        Expression exp =new Expression(userexp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
}
