package com.org.tijarah.structure;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Excalibur Creations on 24-Apr-17.
 */

public class BasketAdapter extends ArrayAdapter<Item>{

    List<Item> basketItems;
    Context context;

    public BasketAdapter(@NonNull Context context,  @NonNull List<Item> basketItems) {
        super(context, 0, basketItems);
        this.context = context;
        this.basketItems = basketItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        }

        TextView textViewItemName = (TextView)convertView.findViewById(R.id.textViewItemName);
        TextView textViewItemQuantity = (TextView)convertView.findViewById(R.id.textViewItemQuantity);
        TextView textViewItemPrice = (TextView)convertView.findViewById(R.id.textViewItemPrice);

        Item item = Session.getItems().get(position);

        textViewItemName.setText(item.getName());
        textViewItemQuantity.setText(String.valueOf(item.getCount()));
        textViewItemPrice.setText(String.valueOf(item.getPrice()));

        return convertView;
    }

    @Override
    public int getCount() {
        return basketItems.size();
    }
}
