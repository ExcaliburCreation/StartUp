package com.org.tijarah.structure;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Excalibur Creations on 3/24/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private static final String TAG = GridViewAdapter.class.getSimpleName();

    private Context context;
    private List<Item> itemList;


    public GridViewAdapter(Context context, List<Item> itemList) {

        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

      /*  View grid;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Item item = itemList.get(i);

        Log.d(TAG,itemList.toString());
        if (view == null) {
            int count = 0;
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_item, null);



        } else {

            grid = view;
        }
        final ImageView itemImage = (ImageView) grid.findViewById(R.id.itemImage);
        //      ImageView imageViewOverlay = (ImageView) grid.findViewById(R.id.imageViewOverlay);
        Button btnRemove = (Button) grid.findViewById(R.id.btnRemove);
        Button btnAdd = (Button) grid.findViewById(R.id.btnAdd);
        TextView textViewItemName = (TextView) grid.findViewById(R.id.textViewItemName);
        final TextView textViewCount = (TextView) grid.findViewById(R.id.textViewCount);

        itemImage.setImageResource(R.drawable.supermarkets);

        textViewItemName.setText(getItem(i).getName());

        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item Image Clicked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, SelectedItemActivity.class);
                context.startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = item.getCount();
                itemImage.setVisibility(View.GONE);
                textViewCount.setVisibility(View.VISIBLE);
                textViewCount.setBackgroundColor(Color.DKGRAY);
                count++;
                textViewCount.setText(String.valueOf(count));
                if(Session.basket.getItems().contains(item)){
                    Session.basket.getItem(item).setCount(String.valueOf(count));
                }
                else {
                    Session.basket.addItems(item);
                    Session.basket.getItem(item).setCount(String.valueOf(count));
                }

                item.setAdded(true);

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (item.getCount() == 0) {
                    itemImage.setVisibility(View.VISIBLE);
                    itemImage.setImageResource(R.drawable.supermarkets);
                    textViewCount.setVisibility(View.GONE);
                    Session.basket.removeItem(item);

                } else if (item.getCount() > 0) {

                    int count = item.getCount();
                    count--;
                    textViewCount.setText(String.valueOf(count));
                    item.setCount(String.valueOf(count));

                    if (count == 0) {

                        itemImage.setVisibility(View.VISIBLE);
                        itemImage.setImageResource(R.drawable.supermarkets);
                        textViewCount.setVisibility(View.GONE);

                        if(Session.basket.getItems().contains(item)){
                            if(item.getCount() == 0){
                                Session.basket.removeItem(item);
                            }
                            else{
                                Session.basket.getItem(item).setCount(String.valueOf(count));

                            }
                        }
                        else {
                            Session.basket.removeItem(item);

                        }

                    }
                }
            }
        });*/
        return view;
    }

    @Override
    public Item getItem(int i) {

        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
