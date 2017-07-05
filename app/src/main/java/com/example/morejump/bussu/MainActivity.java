package com.example.morejump.bussu;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnGoogle, btnFB, btnShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private  void init(){
      /*  final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_download_dialog_actitivity);
        //
        btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

            }
        });*/
        float density = getResources().getDisplayMetrics().density;
        Toast.makeText(this, ""+density, Toast.LENGTH_SHORT).show();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnFB = (Button) findViewById(R.id.btnFb);
        btnGoogle = (Button) findViewById(R.id.btnGoogle);
    }
}
