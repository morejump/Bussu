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

public class OrderWord extends AppCompatActivity implements View.OnTouchListener {
    // create a new flexbox
    float dX, dY;
    List<String> listButton= new ArrayList<>();
    TextView txt01;
    private GestureDetector gestureDetector;
    Map<String, Coordinates> coordiantesList;
    FlexboxLayout flexboxLayout;
   // FlexboxLayout flexboxLayoutGet;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_word);
        findView();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void findView() {
        listButton.add("Vi");
        listButton.add("Thao");
        listButton.add("Dep");
        listButton.add("Trai");
        listButton.add("Nhe");
        gestureDetector = new GestureDetector(this, new ScaleListener());
        flexboxLayout = (FlexboxLayout) findViewById(R.id.MyFlex);
        //flexboxLayoutGet = (FlexboxLayout) findViewById(R.id.MyFlexGet);
        flexboxLayout.removeAllViews();
        //flexboxLayoutGet.removeAllViews();
        for (int i = 0; i < 4; i++) {
            TextView txtview = new TextView(this);
            txtview.setText(listButton.get(i));
            txtview.setId(i);
            txtview.setClickable(true);
            txtview.setElevation(8);
            txtview.setOnTouchListener(this);
            txtview.setBackgroundColor(Color.WHITE);
            txtview.setPadding(48, 48, 48, 48);
            txtview.setTextColor(Color.BLACK);
            flexboxLayout.addView(txtview);
            View view = flexboxLayout.getChildAt(i);
            FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
            lp.setMargins(16, 16, 16, 0);
            view.setLayoutParams(lp);

        }
    }

    private void init() {
        coordiantesList = new HashMap<String, Coordinates>();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (gestureDetector.onTouchEvent(motionEvent)) {
            Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();
            // when tapping a button
            // saving a coordiantes of view
            Coordinates coordinates = new Coordinates();
            coordinates.X = view.getX();
            coordinates.Y = view.getY();
            //
            coordiantesList.put(Integer.toString(view.getId()), coordinates);
            // get first coordiantes
            dX = view.getX() - motionEvent.getRawX();
            dY = view.getY() - motionEvent.getRawY();

            //TextView txtView = new TextView(this);
            //txtView.setText("add more");
            //txtView= (TextView) view;
            // flexboxLayout.removeView(view);
            //flexboxLayoutGet.addView(txtView);
            view.animate()
                    .x(100)
                    .y(100)
                    .setDuration(0)
                    .start();
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
                    // when dropping , a view comback first location
                    Coordinates coordinates1 = coordiantesList.get(Integer.toString(view.getId()));
                    Log.d("moveup", "coordinates1.X: " + view.getId());
                    Log.d("moveup", "coordinates1.X: "+coordinates1.X);
                    Log.d("moveup", "coordinates1.Y: "+coordinates1.Y);
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
