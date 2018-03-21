package com.example.sarveshj.multiuse;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Spanned;
import android.widget.TextView;

class MinMaxFilter implements InputFilter {

    private int mIntMin, mIntMax;

    public MinMaxFilter(int minValue, int maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
    }

    public MinMaxFilter(String minValue, String maxValue) {
        this.mIntMin = Integer.parseInt(minValue);
        this.mIntMax = Integer.parseInt(maxValue);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(mIntMin, mIntMax, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}

public class TimerActivity extends AppCompatActivity {

    EditText minText;
    EditText secText;
    CountDownTimer countDownTimer;
    Button startButton;
    boolean isActive=false;

    public void updateTimer(int secondsLeft){
        Log.e("hehe",Long.toString(secondsLeft));

        int min=(int)secondsLeft/60;
        int sec=secondsLeft%60;
        minText.setText(String.valueOf(min), TextView.BufferType.EDITABLE);
        if(sec<=9){
            String secString="0"+sec;
            secText.setText(secString, TextView.BufferType.EDITABLE);
        }else{
            secText.setText(String.valueOf(sec), TextView.BufferType.EDITABLE);
        }
        Log.e("hehe",Integer.toString(min)+Integer.toString(sec));

    }

    public void resetTimer(View view){
        Log.e("resetTimer","yes");
        countDownTimer.cancel();
        minText.setFocusableInTouchMode(true);
        secText.setFocusableInTouchMode(true);
        minText.setText(String.valueOf(10), TextView.BufferType.EDITABLE);
        secText.setText("00", TextView.BufferType.EDITABLE);
        isActive=false;
    }


    public void onBegin(View view){
        if(!isActive){
            Log.e("inside is active =false","yes");
            isActive=true;
            int min=Integer.parseInt(minText.getText().toString());
            int sec=Integer.parseInt(secText.getText().toString());
            int totalTime=(min*60)+sec;
            minText.setFocusable(false);
            secText.setFocusable(false);
            countDownTimer=new CountDownTimer(totalTime*1000+100,1000) {
                @Override
                public void onTick(long l) {
                    Log.e("ontick",Long.toString(l/1000));
                    updateTimer((int)l/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer(null);
                }
            }.start();
        }else{
            resetTimer(null);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        minText=(EditText)findViewById(R.id.minText);
        minText.setFilters(new InputFilter[]{new MinMaxFilter("0","60")});

        secText=(EditText) findViewById(R.id.secText);
        secText.setFilters(new InputFilter[]{new MinMaxFilter("0","60")});

        startButton=(Button)findViewById(R.id.startButton);

    }
}
