package com.org.tijarah.structure;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Excalibur Creations on 17-Apr-17.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    List<Item> itemsList;

    public ItemsAdapter(List<Item> itemsList) {

        this.itemsList = itemsList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Item item = itemsList.get(position);

        holder.textViewItemName.setText(item.getName());

        if (item.getCount() > 0) {
            holder.itemImage.setVisibility(View.GONE);
            holder.textViewCount.setVisibility(View.VISIBLE);
            holder.textViewCount.setBackgroundColor(Color.DKGRAY);
            holder.textViewCount.setText(String.valueOf(item.getCount()));
        }

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();

                int count = item.getCount();

                holder.itemImage.setVisibility(View.GONE);
                holder.textViewCount.setVisibility(View.VISIBLE);
                holder.textViewCount.setBackgroundColor(Color.DKGRAY);
                count++;
                holder.textViewCount.setText(String.valueOf(count));
                if (Session.basket.getItems().contains(item)) {
                    Session.basket.getItem(item).setCount(String.valueOf(count));
                    Session.getItem(item).setCount(String.valueOf(count));
                } else {
                    Session.basket.addItems(item);
                    Session.basket.getItem(item).setCount(String.valueOf(count));

                    Session.getItem(item).setCount(String.valueOf(count));
                }

                item.setAdded(true);

            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Remove", Toast.LENGTH_SHORT).show();

                if (item.getCount() == 0) {
                    holder.itemImage.setVisibility(View.VISIBLE);
                    holder.itemImage.setBackgroundColor(Color.DKGRAY);
                    holder.textViewCount.setVisibility(View.GONE);
                    Session.basket.removeItem(item);

                } else if (item.getCount() > 0) {

                    int count = item.getCount();
                    count--;
                    holder.textViewCount.setText(String.valueOf(count));
                    item.setCount(String.valueOf(count));

                    if (count == 0) {

                        holder.itemImage.setVisibility(View.VISIBLE);
                        holder.itemImage.setBackgroundColor(Color.DKGRAY);
                        holder.textViewCount.setVisibility(View.GONE);

                        if (Session.basket.getItems().contains(item)) {
                            if (item.getCount() == 0) {
                                Session.basket.removeItem(item);
                            } else {
                                Session.basket.getItem(item).setCount(String.valueOf(count));
                                Session.getItem(item).setCount(String.valueOf(count));

                            }
                        }

                    }
                }

            }
        });

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SelectedItemActivity.class);
                intent.putExtra("item", itemsList.get(position).getId() - 1);
                view.getContext().startActivity(intent);

            }
        });

        holder.textViewCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SelectedItemActivity.class);
                intent.putExtra("item", itemsList.get(position).getId() - 1);
                view.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        Button btnAdd;
        Button btnRemove;
        TextView textViewItemName;
        TextView textViewCount;

        public ViewHolder(View itemView) {
            super(itemView);

         //   itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            textViewItemName = (TextView) itemView.findViewById(R.id.textViewItemName);
            textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);
        }


    }
}
