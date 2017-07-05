package com.example.morejump.bussu;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MachingSentenceActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView txt01Move, txt02Move, txt03Move, txt01, txt02, txt03, txt04, txt05,txt06;

    float xMove01, yMove01;
    float xMove02, yMove02;
    float xMove03, yMove03;
    //
    float xTxt04, yTxt04;
    float  xTxt05, yTxt05;
    float  xTxt06, yTxt06;
    //
    boolean firstTouchMove01, firstTouchMove02, firstTouchMove03,firstTouchTxt04, firstTouchTxt05, firstTouchTxt06 ;

    ViewGroup _root;
    Rect rectTxt04, rectTxt05, rectTxt06, rectMove01, rectMove02, rectMove03;
    private float _xDelta;
    private float _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maching_sentence);
        Init();

    }
    private  void Init(){
        rectTxt04= new Rect();
        rectTxt05= new Rect();
        rectTxt06 = new Rect();
        rectMove01= new Rect();
        rectMove02= new Rect();
        rectMove03 = new Rect();
        //

        firstTouchMove01 = false;
        firstTouchMove02 = false;
        firstTouchMove03 = false;
        firstTouchTxt04= false;
        firstTouchTxt05= false;
        firstTouchTxt06= false;

        _root = (ViewGroup)findViewById(R.id.root);

        txt01Move = (TextView) findViewById(R.id.txt01Move);
        txt02Move = (TextView) findViewById(R.id.txt02Move);
        txt03Move = (TextView) findViewById(R.id.txt03Move);
        //
        txt01Move.bringToFront();
        txt02Move.bringToFront();
        txt03Move.bringToFront();
        //
        txt03 = (TextView) findViewById(R.id.txt03);
        txt02 = (TextView) findViewById(R.id.txt02);
        txt01 = (TextView) findViewById(R.id.txt01);
        txt04= (TextView) findViewById(R.id.txt04);
        txt05= (TextView) findViewById(R.id.txt05);
        txt06= (TextView) findViewById(R.id.txt06);
        //

        txt01Move.setOnTouchListener(this);
        txt02Move.setOnTouchListener(this);
        txt03Move.setOnTouchListener(this);


    }
    private  TextView getShortestDistanceRect( Rect rect){
        double[] arr= new double[3];
        double SmallestDistance;
        //
        int xRect= rect.centerX();
        int yRect = rect.centerY();
        //
        int xRect04 = rectTxt04.centerX();
        int yRect04 = rectTxt04.centerY();
        //
        int xRect05 = rectTxt05.centerX();
        int yRect05 = rectTxt05.centerY();
        //
        int xRect06 = rectTxt06.centerX();
        int yRect06 = rectTxt06.centerY();

        double dis04 = getDistance(xRect, yRect, xRect04, yRect04);
        //
        double dis05 = getDistance(xRect, yRect, xRect05, yRect05);
        //
        double dis06 = getDistance(xRect, yRect, xRect06, yRect06);
        //
        arr[0]= dis04;
        arr[1]= dis05;
        arr[2]= dis06;
       //
        SmallestDistance= arr[0];
        for(int i=1; i< arr.length; i++)
        {

             if (arr[i] <SmallestDistance )
                SmallestDistance = arr[i];
        }
        if (SmallestDistance== arr[0]){

            return txt04;

        }
        if (SmallestDistance== arr[1]){

            return txt05;
        }
        if (SmallestDistance== arr[2]){

            return txt06;
        }

        return  null;
    }
    private void setDefaultBackground(){
        txt04.setBackgroundResource(R.color.colorNormalBtn);
        txt05.setBackgroundResource(R.color.colorNormalBtn);
        txt06.setBackgroundResource(R.color.colorNormalBtn);

    }
    private  double getDistance(int x1, int y1, int x2, int y2){
        double dis;
        dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        return dis;

    }
    private void hasCollisionMoveRect(Rect rect, View view){
        // view chính là 1 rectMove

        if (rect.intersect(rectMove01) && view.getId()!= txt01Move.getId()  ){
            moveToCoordinates(xMove01, yMove01, txt01Move);

        }
        if (rect.intersect(rectMove02) && view.getId()!=txt02Move.getId()  ){
            moveToCoordinates(xMove02, yMove02, txt02Move);

        }
        if (rect.intersect(rectMove03) && view.getId()!=txt03Move.getId() ){
            moveToCoordinates(xMove03, yMove03, txt03Move);

        }

    }
    private void changeBackgroundColor(Rect rect){
        if (rectTxt04.intersect(rect))
        {
            txt04.setBackgroundResource(R.color.colorHoverBtn);
            txt05.setBackgroundResource(R.color.colorNormalBtn);
            txt06.setBackgroundResource(R.color.colorNormalBtn);


        }
        else if(rectTxt05.intersect(rect)){
            txt04.setBackgroundResource(R.color.colorNormalBtn);
            txt05.setBackgroundResource(R.color.colorHoverBtn);
            txt06.setBackgroundResource(R.color.colorNormalBtn);

        }
        else if(rectTxt06.intersect(rect)) {
            txt04.setBackgroundResource(R.color.colorNormalBtn);
            txt05.setBackgroundResource(R.color.colorNormalBtn);
            txt06.setBackgroundResource(R.color.colorHoverBtn);

    }}
    private  boolean hasCollision040506(Rect rect){

        if (rectTxt04.intersect(rect) ||rectTxt05.intersect(rect)|| rectTxt06.intersect(rect))
            return true;
        return  false;
    }
    private  void getRectange(TextView txt04, TextView txt05, TextView txt06, TextView txt01Move, TextView txt02Move, TextView txt03Move){
        txt04.getHitRect(rectTxt04);
        txt05.getHitRect(rectTxt05);

        txt06.getHitRect(rectTxt06);

        txt01Move.getHitRect(rectMove01);

        txt02Move.getHitRect(rectMove02);

        txt03Move.getHitRect(rectMove03);


    }
    private void getCoordinates(){

            if ( firstTouchTxt04== false){
                xTxt04= txt04.getX();
                yTxt04= txt04.getY();
                firstTouchTxt04 = true;
            }



            if ( firstTouchTxt05== false){
                xTxt05= txt05.getX();
                yTxt05= txt05.getY();
                firstTouchTxt05 = true;
            }



            if ( firstTouchTxt06== false){
                xTxt06= txt06.getX();
                yTxt06= txt06.getY();
                firstTouchTxt06 = true;
            }

            if (firstTouchMove01 == false){
                xMove01= txt01Move.getX();
                yMove01= txt01Move.getY();
                firstTouchMove01 = true;
            }


        else
            if (firstTouchMove02 == false){
                xMove02= txt02Move.getX();
                yMove02= txt02Move.getY();
                firstTouchMove02 = true;
            }



            if (firstTouchMove03 == false){
                xMove03= txt03Move.getX();
                yMove03= txt03Move.getY();
                firstTouchMove03 = true;
            }

        }
    private void moveWithCollision(Rect rect, View view){
        if (getShortestDistanceRect(rect)!=null){
            if (getShortestDistanceRect(rect).getId()== R.id.txt04){
                // đang va chạm với txt04
                //  xem hình chữ nhật txt04 có va chạm với Move01, Move02, Move03 và đồng thời không phải hình chữ nhật đang xét?
                // nếu đúng thì move hình chữ nhật đó về vị trí ban đầu
                hasCollisionMoveRect(rectTxt04,view);


                view.animate()
                        .x(xTxt04)
                        .y(yTxt04)
                        .setDuration(0)
                        .start();
            }
            if (getShortestDistanceRect(rect).getId()== R.id.txt05){

                hasCollisionMoveRect(rectTxt05,view);
                view.animate()
                        .x(xTxt05)
                        .y(yTxt05)
                        .setDuration(0)
                        .start();
            }
            if (getShortestDistanceRect(rect).getId()== R.id.txt06){
                hasCollisionMoveRect(rectTxt06,view);

                view.animate()
                        .x(xTxt06)
                        .y(yTxt06)
                        .setDuration(0)
                        .start();
            }
    }}
    private void moveToCoordinates(float x, float y, View view){
        if (x!=view.getX()|| y!=view.getY()){
            view.animate()
                    .x(x)
                    .y(y)
                    .setDuration(0)
                    .start();
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        getCoordinates();
        getRectange(txt04, txt05, txt06, txt01Move, txt02Move, txt03Move);

        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:

                _xDelta = view.getX() - motionEvent.getRawX();
                _yDelta = view.getY() - motionEvent.getRawY();

                break;

            case  MotionEvent.ACTION_UP:
                if (view.getId()== R.id.txt01Move){// touching move 1
                    if (hasCollision040506(rectMove01)== false)
                    {
                        moveToCoordinates(xMove01, yMove01, view);
                    } else {
                        if (xMove01!=view.getX()|| yMove01!=view.getY()){
                            moveWithCollision(rectMove01, view);
                        }

                    }
                }
                else if (view.getId()== R.id.txt02Move){// touching move 2
                    if (hasCollision040506(rectMove02)== false){
                        moveToCoordinates(xMove02, yMove02, view);
                    }

                   if (hasCollision040506(rectMove02)== true){
                       if (xMove02!=view.getX()|| yMove02!=view.getY()){
                           moveWithCollision(rectMove02, view);
                       }
                   }


                }else if (view.getId()== R.id.txt03Move){ // touching mvoe3
                    if (hasCollision040506(rectMove03)== false){
                        moveToCoordinates(xMove03, yMove03, view);
                    }
                    else { // incase of hasing a collision
                        if (xMove03!=view.getX()|| yMove03!=view.getY()){
                            moveWithCollision(rectMove03, view);
                        }
                    }
                }
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_MOVE:
                if (view.getId()== R.id.txt01Move){// touching move 1
                    if (hasCollision040506(rectMove01)== false)
                    {
                        setDefaultBackground();

                    }
                    if (hasCollision040506(rectMove01)== true)
                    {
                        changeBackgroundColor(rectMove01);

                    }

                }
                else if (view.getId()== R.id.txt02Move){// touching move 2
                    if (hasCollision040506(rectMove02)== false){
                        setDefaultBackground();

                    }

                    if (hasCollision040506(rectMove02)== true){
                        changeBackgroundColor(rectMove02);


                    }


                }else if (view.getId()== R.id.txt03Move){ // touching mvoe3
                    if (hasCollision040506(rectMove03)== false){
                        setDefaultBackground();

                    }
                    if (hasCollision040506(rectMove03)== true){
                        changeBackgroundColor(rectMove03);
                    }

                }
                view.animate()
                        .x(motionEvent.getRawX() + _xDelta)
                        .y(motionEvent.getRawY() + _yDelta)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
        
    }
}
