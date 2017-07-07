package com.example.morejump.bussu;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Using hastable to filter which existed
// Handling when a textview is moving, suddenly it is tapped

public class OrderWord extends AppCompatActivity implements View.OnTouchListener {
    // create a new flexbox
    float dX, dY;
    List<String> listButton= new ArrayList<>();
    TextView txtGuide;
    private GestureDetector gestureDetector;
    Map<String, Coordinates> coordiantesList;
    FlexboxLayout flexboxLayout;
    FlexboxLayout FillFlexBox;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_word);
        findView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void findView() {
        coordiantesList = new HashMap<String, Coordinates>();
        txtGuide= (TextView) findViewById(R.id.txtGuide);
        //
        listButton.add("Vi");
        listButton.add("Thao");
        listButton.add("Dep");
        listButton.add("Trai");
        listButton.add("Nhe");
        listButton.add("Vi");
        listButton.add("Thao");
        listButton.add("Dep");
        listButton.add("Trai");
        listButton.add("Nhe");
        gestureDetector = new GestureDetector(this, new ScaleListener());
        //
        flexboxLayout = (FlexboxLayout) findViewById(R.id.DisplayFlexBox);
        FillFlexBox = (FlexboxLayout) findViewById(R.id.FillFlexBox);
        //
        flexboxLayout.removeAllViews();
        FillFlexBox.removeAllViews();
        //
        for (int i = 0; i < 5; i++) {
            TextView txtview = new TextView(this);
            txtview.setText(listButton.get(i));
            txtview.setId(i);
            txtview.setClickable(true);
            txtview.setElevation(8);
            txtview.setOnTouchListener(this);
            txtview.setBackgroundColor(Color.WHITE);
            txtview.setPadding(16, 16, 16, 16);
            txtview.setTextColor(Color.BLACK);
            flexboxLayout.addView(txtview);
            View view = flexboxLayout.getChildAt(i);
            FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
            lp.setMargins(16, 16, 16, 0);
            view.setLayoutParams(lp);

        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (gestureDetector.onTouchEvent(motionEvent)) {
            // checking instance of child's parent
            if(view.getParent() == flexboxLayout ){
                Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();
                if (view != null) {
                    if(view.getParent()!=null){
                        ((ViewGroup)view.getParent()).removeView(view);
                        FillFlexBox.addView(view);
                    }
                    txtGuide.setVisibility(View.INVISIBLE);
                }

            }
            else {
                if (view != null) {
                    if(view.getParent()!=null){
                        ((ViewGroup)view.getParent()).removeView(view);
                        flexboxLayout.addView(view);
                    }
                    txtGuide.setVisibility(View.INVISIBLE);
                }

            }



            return true;
        } else {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Coordinates coordinates = new Coordinates();
                    coordinates.X = view.getX();
                    coordinates.Y = view.getY();
                    //
                    coordiantesList.put(Integer.toString(view.getId()), coordinates);
                    // get first coordiantes
                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start();
                    break;
                case MotionEvent.ACTION_UP:
                    Coordinates coordinates1 = coordiantesList.get(Integer.toString(view.getId()));
                    view.animate()
                            .x(coordinates1.X)
                            .y(coordinates1.Y)
                            .setDuration(0)
                            .start();
                    break;
            }

        }
        return false;
    }

    private class Coordinates {
        public float X;
        public float Y;

    }

    private class ScaleListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }
}
