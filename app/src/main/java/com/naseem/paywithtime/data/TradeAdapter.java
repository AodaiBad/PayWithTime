package com.naseem.paywithtime.data;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naseem.paywithtime.MainPage;
import com.naseem.paywithtime.R;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by user on 11/03/2018.
 */

public class TradeAdapter extends ArrayAdapter<Trade> {

    public TradeAdapter(@NonNull Context context, int resource) {

        super(context, resource);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_trade, parent, false);
        TextView tvaddress = (TextView) view.findViewById(R.id.tvAdress);
        TextView tvHours = (TextView) view.findViewById(R.id.tvHours);
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        TextView tvSub = (TextView) view.findViewById(R.id.tvSub);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        Button btnAddres = (Button) view.findViewById(R.id.btnAddres);
        ImageButton btnDel = (ImageButton) view.findViewById(R.id.btnDel);


        final Trade h = getItem(position);


        btnAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = h.getAdress();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + h.getAdress());
                Intent i = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                i.setPackage("com.google.android.apps.maps");
                getContext().startActivity(i);

                del(h);

            }

        });
        tvaddress.setText(h.getAdress() + "");
        tvPrice.setText(h.getPrice() + "");
        tvHours.setText(h.getHours() + "");
        tvSub.setText(h.getSub() + "");
        tvName.setText(h.getName() + "");


        return view;
    }



        private void del ( final Trade t)
        {

            final String[] a = {"DELETE"};


            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Delete");
            builder.setCancelable(true);
            builder.setSingleChoiceItems(a, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                    Toast.makeText(getContext(), a[i], Toast.LENGTH_SHORT).show();
                    if (i == 0) {
                        DatabaseReference reference;
                        //todo לקבלת קישור למסד הנתונים שלנו
                        //todo  קישור הינו לשורש של המסד הנתונים

                        reference = FirebaseDatabase.getInstance().getReference();
                        reference.child("mylist").child(t.getKeyId()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError == null) {
                                    Toast.makeText(getContext(), "delete successful", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getCont
                                            ext(), "delete failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }


            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }



}