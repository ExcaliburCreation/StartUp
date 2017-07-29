package com.org.tijarah.structure;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private static final String TAG = ItemsActivity.class.getSimpleName();
    List<Item> itemList;

    RecyclerView recyclerViewItems;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ItemsAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<Item, ItemHolder> firebaseRecyclerAdapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String catPosition = getIntent().getStringExtra("position");

       /* databaseReference.child("Categories").child(catPosition).child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                for (DataSnapshot i : data) {

                    Item item = i.getValue(Item.class);

                    Log.d(TAG + "RET", i.getValue().toString());
                    Log.d(TAG + "RETI", item.toString());
                    keys.add(item);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

*/

        itemList = new ArrayList<Item>();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemHolder>(
                Item.class,
                R.layout.item_grid,
                ItemHolder.class,
                databaseReference.child("Categories").child(catPosition).child("Items").getRef()) {
            @Override
            protected void populateViewHolder(final ItemHolder viewHolder, final Item model, int position) {
                Item item = model;
                itemList.add(item);

                Session.setItems(itemList);
                Session.basket.setItems(itemList);

           //     final Item item = Session.items.get(position);
                viewHolder.textViewItemName.setText(model.getName());
                viewHolder.textViewItemPrice.setText("Rs: "+String.valueOf(model.getPrice()  ));

                viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ItemsActivity.this, "Added", Toast.LENGTH_SHORT).show();

                        int count = model.getCount();

                        viewHolder.itemImage.setVisibility(View.GONE);
                        viewHolder.textViewCount.setBackgroundColor(Color.DKGRAY);
                        viewHolder.textViewCount.setVisibility(View.VISIBLE);

                        count++;
                        viewHolder.textViewCount.setText(String.valueOf(count));
                        if (Session.basket.getItems().contains(model)) {
                            Session.basket.getItem(model).setCount(String.valueOf(count));
                            Session.getItem(model).setCount(String.valueOf(count));
                        } else {
                            Session.basket.addItems(model);
                            Session.basket.getItem(model).setCount(String.valueOf(count));
                            Session.getItem(model).setCount(String.valueOf(count));
                        }
                        model.setAdded(true);
                    }
                });
                
                viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ItemsActivity.this, "Remove", Toast.LENGTH_SHORT).show();

                        if (model.getCount() == 0) {
                            viewHolder.itemImage.setVisibility(View.VISIBLE);
                            viewHolder.itemImage.setBackgroundColor(Color.DKGRAY);
                            viewHolder.textViewCount.setVisibility(View.GONE);
                            Session.basket.removeItem(model);

                        } else if (model.getCount() > 0) {

                            int count = model.getCount();
                            count--;
                            viewHolder.textViewCount.setText(String.valueOf(count));
                            model.setCount(String.valueOf(count));

                            if (count == 0) {

                                viewHolder.itemImage.setVisibility(View.VISIBLE);
                                viewHolder.itemImage.setBackgroundColor(Color.DKGRAY);
                                viewHolder.textViewCount.setVisibility(View.GONE);

                                if (Session.basket.getItems().contains(model)) {
                                    if (model.getCount() == 0) {
                                        Session.basket.removeItem(model);
                                    } else {
                                        Session.basket.getItem(model).setCount(String.valueOf(count));
                                        Session.getItem(model).setCount(String.valueOf(count));

                                    }
                                }

                            }
                        }
                    }
                });
                Log.d(TAG + "model", model.toString());
                Log.d(TAG + "Abc", Session.getItems().toString());
             /* if (model.getCount() > 0) {
                    viewHolder.itemImage.setVisibility(View.GONE);
                    viewHolder.textViewCount.setVisibility(View.VISIBLE);
                    viewHolder.textViewCount.setBackgroundColor(Color.DKGRAY);
                    viewHolder.textViewCount.setText(String.valueOf(model.getCount()));
                }*/

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsActivity.this, SelectedItemActivity.class);
                        intent.putExtra("itemsActivity", model);
                        startActivity(intent);
                    }
                });


            }
        };

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle("Items");

        //  intent = getIntent();
//        Log.d(TAG, Session.getItems().toString());

        recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewItems.setHasFixedSize(true);


        //  Session.basket.setItems(itemList);

        adapter = new ItemsAdapter(Session.items);

        recyclerViewItems.setLayoutManager(layoutManager);
        recyclerViewItems.setAdapter(firebaseRecyclerAdapter);

//        Log.d(TAG, itemList.toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.basket) {
            Intent intent = new Intent(ItemsActivity.this, BasketActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        View mView;
        ImageView itemImage;
        Button btnAdd;
        Button btnRemove;
        TextView textViewItemName;
        TextView textViewCount;
        TextView textViewItemPrice;

        public ItemHolder(View itemView) {
            super(itemView);

            mView = itemView;
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            textViewItemName = (TextView) itemView.findViewById(R.id.textViewItemName);
            textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);
            textViewItemPrice = (TextView) itemView.findViewById(R.id.textViewItemPrice);
        }


    }
}
