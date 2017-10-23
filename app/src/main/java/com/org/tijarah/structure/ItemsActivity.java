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
import com.google.firebase.database.ChildEventListener;
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
    //ItemsAdapter adapter;

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

        itemList = new ArrayList<Item>();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemHolder>(
                Item.class,
                R.layout.item_grid,
                ItemHolder.class,
                databaseReference.child("Categories").child(catPosition).child("Items").getRef()) {

            @Override
            protected void populateViewHolder(final ItemHolder viewHolder, final Item model, final int position) {

                itemList.add(model);

                Session.setItems(itemList);

                Log.d("Items Populate View", model.toString());
                Log.d("Session", Session.getItems().toString());
                Log.d("Basket", Session.basket.getItems().toString());

                viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ItemsActivity.this, "Added", Toast.LENGTH_SHORT).show();

                        int count = model.getCount();

                        viewHolder.textViewCount.setBackgroundColor(Color.DKGRAY);
                        viewHolder.textViewCount.setVisibility(View.VISIBLE);

                        count++;
                        viewHolder.textViewCount.setText(String.valueOf(count));
                        Session.items.get(position).setCount(String.valueOf(count));
                        Session.items.get(position).setAdded(true);

                        Log.d("Add Item Button ", model.toString());
                        Log.d("Session", Session.getItems().toString());
                        Log.d("Basket", Session.basket.getItems().toString());
                    }
                });
                
                viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ItemsActivity.this, "Remove", Toast.LENGTH_SHORT).show();
                        int count = Session.items.get(position).getCount();

                        if (count == 0) {

                            viewHolder.textViewCount.setVisibility(View.GONE);

                        } else if (count > 0) {
                            
                            count--;
                            viewHolder.textViewCount.setText(String.valueOf(count));
                            Session.items.get(position).setCount(String.valueOf(count));

                            if (count == 0) {
                                viewHolder.textViewCount.setVisibility(View.GONE);

                                if (Session.basket.getItems().contains(model)) {
                                    if (count == 0) {
                                    //    Session.basket.removeItem(model);
                                    } else {
                                        Session.items.get(position).setCount(String.valueOf(count));

                                    }
                                }

                            }
                        }

                        Log.d("Remove Item Button ", model.toString());
                        Log.d("Session", Session.getItems().toString());
                        Log.d("Basket", Session.basket.getItems().toString());
                    }
                });

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsActivity.this, SelectedItemActivity.class);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onBindViewHolder(ItemHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);

                Log.d(TAG, "BINDDDDD");
                Item item = Session.items.get(position);
                viewHolder.textViewItemName.setText(item.getName());
                viewHolder.textViewItemPrice.setText("Rs: "+String.valueOf(item.getPrice()  ));

                if (Session.items.get(position).getCount() > 0){
                    viewHolder.textViewCount.setBackgroundColor(Color.DKGRAY);
                    viewHolder.textViewCount.setVisibility(View.VISIBLE);
                    viewHolder.textViewCount.setText(String.valueOf(item.getCount()));
                }
                else{
                    viewHolder.textViewCount.setVisibility(View.GONE);
                }
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

        //adapter = new ItemsAdapter(Session.items);

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
        firebaseRecyclerAdapter.notifyDataSetChanged();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        View mView;
      //  ImageView itemImage;
        Button btnAdd;
        Button btnRemove;
        TextView textViewItemName;
        TextView textViewCount;
        TextView textViewItemPrice;

        public ItemHolder(View itemView) {
            super(itemView);

            mView = itemView;
           // itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            textViewItemName = (TextView) itemView.findViewById(R.id.textViewItemName);
            textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);
            textViewItemPrice = (TextView) itemView.findViewById(R.id.textViewItemPrice);
        }


    }
}
