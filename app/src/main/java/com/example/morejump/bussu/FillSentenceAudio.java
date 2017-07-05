package com.example.morejump.bussu;

import android.animation.StateListAnimator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.Os;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.List;

public class FillSentenceAudio extends AppCompatActivity   {
    ImageView imgPlayBtn;
    TextView txtResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_sentence_audio);
        init();
    }
    private  void init(){
        String normalBefore= "First Part Not Bold ";
        String normalBOLD=  "BOLD ";
        String normalAfter= "rest not bold";
        String finalString= normalBefore+normalBOLD+normalAfter;
        Spannable sb = new SpannableString( finalString );

       /* sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), finalString.indexOf(normalBOLD)+ normalBOLD.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bold
        sb.setSpan(new AbsoluteSizeSpan(intSize), finalString.indexOf(normal*//*BOLD)+ normalBOLD.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*///resize size
        txtResult= (TextView) findViewById(R.id.txtTest);
        txtResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setEnabled(false);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    txtResult.setElevation(0);
                }
            }
        });
        imgPlayBtn = (ImageView) findViewById(R.id.imgPlayBtn);
        imgPlayBtn.isClickable();
        final Animation RotateAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.roteanim);
        final Animation RotateAnimBack = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.roteanimback);
        final Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);

        imgPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgPlayBtn.startAnimation(zoomIn);
            }
        });
        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgPlayBtn.startAnimation(RotateAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        RotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imgPlayBtn.setImageResource(R.drawable.ic_pause_btn);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                imgPlayBtn.startAnimation(RotateAnimBack);
                float second= 500;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        RotateAnimBack.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imgPlayBtn.setImageResource(R.drawable.ic_play_btn);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }



}
