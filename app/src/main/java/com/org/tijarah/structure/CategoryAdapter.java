package com.org.tijarah.structure;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Excalibur Creations on 3/31/2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    public CategoryAdapter(Context context, int resource, List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.categories,parent,false);


        }

        TextView name = (TextView)convertView.findViewById(R.id.CatNames);

        Category category = getItem(position);

        name.setText(category.getName());



        return convertView;
    }


}
