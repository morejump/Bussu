package com.example.morejump.bussu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Touch extends AppCompatActivity implements View.OnTouchListener {
    TextView txtClick;
    float dX, dY;
    private GestureDetector gestureDetector;

    // Or: thử sử dụng action khác
    // Or: sử dụng tip and trick
    // Or: sử dụng gesture

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        gestureDetector = new GestureDetector(this, new ScaleListener());
        txtClick = (TextView) findViewById(R.id.txtClick);
        txtClick.setClickable(true);
        txtClick.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (gestureDetector.onTouchEvent(motionEvent)) {
            // single tap
            Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();
            Log.d("thaohandsome", "Tap: ");
            return true;
        } else {

            switch (motionEvent.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    Log.d("thaohandsome", "Down: ");

                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("thaohandsome", "Move: ");
                    view.animate()
                            .x(motionEvent.getRawX() + dX)
                            .y(motionEvent.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    break;
                case MotionEvent.ACTION_UP:
                    Toast.makeText(this, "Up", Toast.LENGTH_SHORT).show();
                    break;

            }
        }


        return false;
    }

    private class ScaleListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }
}
