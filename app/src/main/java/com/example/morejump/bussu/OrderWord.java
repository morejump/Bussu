package com.example.morejump.bussu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class OrderWord extends AppCompatActivity implements View.OnTouchListener {
    // Each textview has an unique Id which is used to determine  x, y coordinate to come back
    // Generating a rectange dynamically ===>  keeping a space when removing bringToFront;
    // How to make textview get back first location//
    // Save first location and then ====>

    float dX, dY;
    TextView txt01;
    private GestureDetector gestureDetector;
    Map<String, Coordinates> coordiantesList = new HashMap<String, Coordinates>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_word);
        findView();
        init();

    }
    private  void findView(){

        FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.MyFlex);
        flexboxLayout.removeAllViews();
       // flexboxLayout.invalidate();
        //flexboxLayout.requestLayout();
        //
      /*  txt01= new TextView(this);
        txt01.setText("click me!!! plz");
        txt01.setTextColor(Color.BLACK);
        txt01.setBackgroundColor(Color.RED);
        txt01.setPadding(48,48,48,48);*/
       // flexboxLayout.addView(txt01);
        //
        for (int i=0; i<10;i++){
            TextView txtview = new TextView(this);
            txtview.setText("thao 0"+i);
            txtview.setId(i);
            txtview.setOnTouchListener(this);
            txtview.setBackgroundColor(Color.WHITE);
            txtview.setPadding(48,48,48,48);
            txtview.setTextColor(Color.BLACK);
            flexboxLayout.addView(txtview);
            View view = flexboxLayout.getChildAt(i);
            FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
            lp.setMargins(16,16,16,0);
            view.setLayoutParams(lp);
           // Log.d("thaohandsome", "getX: "+txtview.getX());
            //Log.d("thaohandsome", "getY: "+txtview.getY());

        }
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());
    }

    private void init() {
        // adding button dynamically
        /*txt01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                    view.animate()
                            .x(100)
                            .y(100)
                            .setDuration(100)
                            .start();
                    return true;
                }
                if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {
                   //txt01.bringToFront();
                    view.animate()
                            .x(motionEvent.getRawX() + dX)
                            .y(motionEvent.getRawY() + dY)
                            .setDuration(0)
                            .start();
                }
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                    //txt01.bringToFront();
                    view.animate()
                            .x(300)
                            .y(300)
                            .setDuration(100)
                            .start();
                }
                return false;
            }
        });*/
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - motionEvent.getRawX();
                dY = view.getY() - motionEvent.getRawY();
                // lúc touch xuống thì lấy tọa độ của view và id ==> gán vào dictionary==> tìm kiếm khi touch up


                Log.d("thaohandsome", "dX: "+view.getX());
                Log.d("thaohandsome", "dY: "+view.getY());
                Log.d("thaohandsome", "viewID: "+view.getId());


                break;

            case MotionEvent.ACTION_MOVE:
               // Toast.makeText(this, "move", Toast.LENGTH_SHORT).show();
                view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            case  MotionEvent.ACTION_UP:
                //lúc thả ra thì quay lại vị trí ban dầu
                // using id to determine that location view
                //


                break;


            default:
                return false;
        }
        return true;
    }
    private  class Coordinates{
        public  int X;
        public  int Y;
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

    }
}
