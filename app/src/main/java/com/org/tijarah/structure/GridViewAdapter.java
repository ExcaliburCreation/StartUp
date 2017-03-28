package com.org.tijarah.structure;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Excalibur Creations on 3/24/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private int imgUrl[];
    private Context context;

    public GridViewAdapter(Context context, int[] imgUrl) {

        this.context = context;
        this.imgUrl  = imgUrl;

    }

    @Override
    public int getCount() {
        return imgUrl.length;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View grid;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_item, null);

            ImageView itemImage = (ImageView) grid.findViewById(R.id.itemImage);
            Button btnRemove = (Button) grid.findViewById(R.id.btnRemove);
            Button btnAdd = (Button) grid.findViewById(R.id.btnAdd);
            EditText editTextQuantity = (EditText) grid.findViewById(R.id.editTextQuantity);
            itemImage.setImageResource(imgUrl[i]);

            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Item Image Clicked", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(context, SelectedItemActivity.class);
                    context.startActivity(intent);
                }
            });

        }
        else{

            grid = view;
        }

        return grid;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
