package com.example.morejump.bussu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//  viết hàm thay thế chuỗi dựa vào index hoặc sử build - in methoe in java
//  lay chuoi clicked ra
//  tìm vị trí của "miss" trong chuỗi ban dầu==> index
// thay chuỗi clicked vào chỗ miss

public class MySpanable extends AppCompatActivity {
    String ClickedText ;
    String  strDisplay;
    TextView txtTest;
    TextView txtDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spanable);
        txtTest= (TextView) findViewById(R.id.txtTest);
        //txtDisplay= (TextView) findViewById(R.id.txtDisplayText);
        strDisplay = "How's it going it lol thao";
        txtDisplay.setText(strDisplay); // passing this view later
        findView();

    }
    private  void findView(){
        // Getting spannable string and assigning to str test
        //Spanned s;
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        String between = "";
        stringBuilder.append("");
        for (String tag : "thao dep trai nhe nhe hihih ihihih ihi hihi thao hand osme hihihi thaothao".split(" ")) {
           stringBuilder.append(between);
            if (between.length() == 0) between = "  ";
            String thisTag = "  "+tag+"  ";
            stringBuilder.append(thisTag);
            //stringBuilder.setSpan(new TouchableSpan(getApplicationContext(),txtDisplay, strDisplay,Color.BLACK ,Color.WHITE,Color.BLACK,Color.parseColor("#CFEAFC")), stringBuilder.length() - thisTag.length(), stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MySpanable.this, "hihi", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            }, stringBuilder.length() - thisTag.length(), stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // stringBuilder.setSpan(new RoundedBackgroundSpan(this), stringBuilder.length() - thisTag.length(), stringBuilder.length() - thisTag.length() + thisTag.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        txtTest.setText(stringBuilder);
        txtTest.setMovementMethod(LinkMovementMethod.getInstance());

}



}
 class RoundedBackgroundSpan extends ReplacementSpan {

    private static int CORNER_RADIUS = 8;
    private int backgroundColor = 0;
    private int textColor = 0;

    public RoundedBackgroundSpan(Context context) {
        super();
        backgroundColor = context.getResources().getColor(R.color.green);
        textColor = context.getResources().getColor(R.color.yellow);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), bottom);
        paint.setColor(Color.WHITE);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(text, start, end, x, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}


  class TouchableSpan extends ClickableSpan {
      private  TextView txtDisplay;
      private  StringBuffer StrDisplay;
    private Context context;
    private boolean mIsPressed;
    private int mPressedBackgroundColor;
    private int mNormalTextColor;
    private int mPressedTextColor;
      private  int normalBackgroundColor;

    public TouchableSpan(Context context, TextView txtDisplay,String strDisplay , int normalTextColor, int normalBackgroundColor, int pressedTextColor, int pressedBackgroundColor) {
        this.context= context;
        this.txtDisplay= txtDisplay;
        this.StrDisplay= new StringBuffer(strDisplay);
        mNormalTextColor = normalTextColor;
        mPressedTextColor = pressedTextColor;
        mPressedBackgroundColor = pressedBackgroundColor;
        this.normalBackgroundColor= normalBackgroundColor;
    }

    public void setPressed(boolean isSelected) {
        mIsPressed = isSelected;
    }

      @Override
      public void onClick(View view) {
          TextView tv = (TextView) view;
          Spanned s = (Spanned) tv.getText();
          int start = s.getSpanStart(this);
          int end = s.getSpanEnd(this);
          int a = StrDisplay.indexOf("it");
         String clickedTxt= s.subSequence(start, end).toString();
          if (a!=-1){
              StrDisplay.replace(a,a+2,clickedTxt);
          }
          txtDisplay.setText(StrDisplay);
         // Toast.makeText(GetApplicationContext, "", Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, "clickedTxt: "+clickedTxt+"  StrDisplay: " +StrDisplay+ "index: "+a, Toast.LENGTH_SHORT).show();

      }

      @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
          ds.setColor(mIsPressed ? mPressedTextColor : mNormalTextColor);
          ds.bgColor = mIsPressed ? mPressedBackgroundColor : normalBackgroundColor;
          ds.setUnderlineText(false);

    }
}


//
 class LinkTouchMovementMethod extends LinkMovementMethod {
    private TouchableSpan mPressedSpan;

    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mPressedSpan = getPressedSpan(textView, spannable, event);
            if (mPressedSpan != null) {
                mPressedSpan.setPressed(true);
                Selection.setSelection(spannable, spannable.getSpanStart(mPressedSpan),
                        spannable.getSpanEnd(mPressedSpan));
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            TouchableSpan touchedSpan = getPressedSpan(textView, spannable, event);
            if (mPressedSpan != null && touchedSpan != mPressedSpan) {
                mPressedSpan.setPressed(false);
                mPressedSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (mPressedSpan != null) {
                mPressedSpan.setPressed(false);
                super.onTouchEvent(textView, spannable, event);
            }
            mPressedSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    private TouchableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        TouchableSpan[] link = spannable.getSpans(off, off, TouchableSpan.class);
        TouchableSpan touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;
    }

}


