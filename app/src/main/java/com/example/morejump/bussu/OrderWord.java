package com.example.morejump.bussu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderWord extends AppCompatActivity implements View.OnTouchListener {
    // relative layout by forcing
    // Using button ===> how to break line when hasing many buttons
    // Rethink above layout
    float dX, dY;
    TextView txt01;
    boolean isMove;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_word);
        init();

    }

    private void init() {
        // adding button dynamically
        //
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());
        txt01 = (TextView) findViewById(R.id.textview1);
        txt01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                    return true;
                }
                if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {
                    view.animate()
                            .x(motionEvent.getRawX() + dX)
                            .y(motionEvent.getRawY() + dY)
                            .setDuration(0)
                            .start();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //view.bringToFront();
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
               // isMove = true;
                dX = view.getX() - motionEvent.getRawX();
                dY = view.getY() - motionEvent.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
              //  isMove = false;
                Toast.makeText(this, "move", Toast.LENGTH_SHORT).show();
                view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            //return super.onDown(e);
            return true;
        }

    }
}
