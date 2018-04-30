package com.naseem.paywithtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naseem.paywithtime.data.Trade;
import com.naseem.paywithtime.data.TradeAdapter;

import java.util.List;

public class TakeHours extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd;
    private ListView ListView;
    private TradeAdapter tradeadapter;

    protected View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_take_hours, container, false);
        setContentView(R.layout.activity_take_hours);
        btnAdd =(Button)findViewById(R.id.btnAdd);
        ListView ListView = (ListView) findViewById(R.id.ListView);
        tradeadapter = new TradeAdapter(getBaseContext(), R.layout.item_trade);
        ListView.setAdapter(tradeadapter);
        readAndListen();
        return view;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnItmAddIem:
                Intent i = new Intent(this, AddItemActivity.class);
                startActivity(i);
                break;
            case R.id.mnMap:
                Intent m = new Intent(this, MapsActivity.class);
                startActivity(m);
                break;
            case R.id.mnSignOut:
                Intent s = new Intent(this, Login.class);
                startActivity(s);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            Intent i = new Intent(this, AddItemActivity.class);
            startActivity(i);
        }

    }

    private void readAndListen() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        email = email.replace('.', '*');
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("mylist").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tradeadapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Trade t = ds.getValue(Trade.class);
                    Log.d("SL", t.toString());
                    tradeadapter.add(t);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}



