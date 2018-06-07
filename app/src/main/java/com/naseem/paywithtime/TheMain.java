package com.naseem.paywithtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TheMain extends AppCompatActivity implements View.OnClickListener {
    private Button btnLog10;
    private Button btnSign10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_main);
        btnLog10 = (Button) findViewById(R.id.btnLog10);
        btnSign10 = (Button) findViewById(R.id.btnSign10);
        btnSign10.setOnClickListener(this);
        btnLog10.setOnClickListener(this);
    }

    public void onClick(View view) {

        if (view == btnSign10) {
            Intent i = new Intent(this, SignUp.class);
            startActivity(i);
        }
        if (view == btnLog10) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
    }
}

