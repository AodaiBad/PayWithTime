package com.naseem.paywithtime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmail;
    private EditText etPass;
    private Button btnLogin;
    private Button btnSiginUp;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


    }

    private void dataHandler() {
        String email = etEmail.getText().toString();
        String passw = etPass.getText().toString();
        signIn(email, passw);
    }

    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "signIn Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), MainPage.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Login.this, "signIn failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    public void onClick(View view) {
        if (view == btnLogin) {
            dataHandler();
        }


//        switch (view.getId()) {
//            case R.id.cbRem:
//                if (cbRem.isChecked()) {
//                    if (firebaseUser != null && firebaseUser.getEmail() != null && firebaseUser.getEmail().length() > 0) {
//                        Intent activtyIntent = new Intent(getBaseContext(), MainPage.class);
//                        startActivity(activtyIntent);
//                    }
//                    break;
//                }
//        }

//        if (((CheckBox) view).isChecked()) {
//            if (firebaseUser != null && firebaseUser.getEmail() != null && firebaseUser.getEmail().length() > 0) {
//                Intent activtyIntent = new Intent(getBaseContext(), MainPage.class);
//                startActivity(activtyIntent);
//            }
//        }
//
    }
}
