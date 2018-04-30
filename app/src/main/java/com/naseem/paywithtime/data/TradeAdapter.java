package com.naseem.paywithtime.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.naseem.paywithtime.R;

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
        Trade h = getItem(position);
        tvaddress.setText(h.getAdress() + "");
        tvPrice.setText(h.getPrice() + "");
        tvHours.setText(h.getHours() + "");
        return view;
    }
}

