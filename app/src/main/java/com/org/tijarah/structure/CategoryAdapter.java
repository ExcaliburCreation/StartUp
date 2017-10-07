package com.org.tijarah.structure;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.List;

/**
 * Created by Excalibur Creations on 3/31/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList) {

        this.categoryList = categoryList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categort_list_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Category category = categoryList.get(position);
        holder.textCatName.setText(category.getName());
        //TODO set ImageView url

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textCatName;
        ImageView imageViewCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            textCatName = (TextView) itemView.findViewById(R.id.textCatName);
        }
    }

}
