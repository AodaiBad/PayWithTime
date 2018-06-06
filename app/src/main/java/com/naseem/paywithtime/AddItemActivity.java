package com.naseem.paywithtime;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naseem.paywithtime.data.Trade;

import static com.naseem.paywithtime.R.string.Hours;

public class AddItemActivity extends AppCompatActivity {
    private EditText etAdress,etPrice,etHours,etSub,etName;
    private Button btnSave2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etAdress=(EditText)findViewById(R.id.etAdress1);
        etPrice=(EditText)findViewById(R.id.etPrice1);
        etHours=(EditText)findViewById(R.id.etHours1);
        btnSave2=(Button)findViewById(R.id.btnSave2);
        etName=(EditText)findViewById(R.id.etName);
        etSub=(EditText)findViewById(R.id.etSub);


        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();

            }
        });
    }
    public void dataHandler(){
        String stAdress=etAdress.getText().toString();
        String stprice=etPrice.getText().toString();
        String stHours=etHours.getText().toString();
        String stSub=etSub.getText().toString();
        String stName=etName.getText().toString();

        double price=Double.parseDouble(stprice);

        Trade t= new Trade();
        t.setSub(stSub);
        t.setName(stName);
        t.setAdress(stAdress);
        t.setHours(Integer.parseInt(stHours));
        t.setPrice(Double.parseDouble(stprice));
        t.setCompleted(false);

        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email=user.getEmail();
        email=email.replace('.','*');
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        t.setEmail(email);
        String key=reference.child("mylist").push().getKey();
        t.setKeyId(key);
        reference.child("mylist").child(key).setValue(t).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             if(task.isSuccessful())
             {
                 Toast.makeText(AddItemActivity.this, "Add Trade Successful", Toast.LENGTH_SHORT).show();
                finish();
             }
             else
             {
                 Toast.makeText(AddItemActivity.this, "Add Trade Failed", Toast.LENGTH_SHORT).show();
             }
            }
        });

    }
}
