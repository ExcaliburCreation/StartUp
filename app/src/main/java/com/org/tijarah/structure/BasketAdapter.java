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

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories,parent,false);


        }

        TextView name = (TextView)convertView.findViewById(R.id.CatNames);

        Item item = getItem(position);

        name.setText(item.getName());



        return convertView;
    }

    @Override
    public int getCount() {
        return basketItems.size();
    }
}
