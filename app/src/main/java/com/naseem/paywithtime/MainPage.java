package com.naseem.paywithtime;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naseem.paywithtime.data.Trade;
import com.naseem.paywithtime.data.TradeAdapter;

public class MainPage extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ListView listView;
    private TradeAdapter tradeadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
         listView = (ListView) findViewById(R.id.listview);
        tradeadapter = new TradeAdapter(this, R.layout.item_trade);
        mAuth=FirebaseAuth.getInstance();
       listView.setAdapter(tradeadapter);
       readAndListen();
        listView.setOnItemClickListener(this);






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
            case R.id.mnSignOut:
                Intent s = new Intent(this, Login.class);
                startActivity(s);
                break;
        }
        return true;
    }



    private void readAndListen() {

        FirebaseUser user = mAuth.getCurrentUser();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String[] a={"DELETE"};
        final Trade w= (Trade) parent.getItemAtPosition(position);


         AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
        builder.setTitle("nnnn");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(a, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainPage.this, a[i], Toast.LENGTH_SHORT).show();
                if(i==0) {
                    DatabaseReference reference;
                    //todo לקבלת קישור למסד הנתונים שלנו
                    //todo  קישור הינו לשורש של המסד הנתונים

                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("mylist").child(w.getKeyId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(MainPage.this, "delete successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainPage.this, "delete failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                }



            });
        AlertDialog dialog=builder.create();
        dialog.show();


    }
}


