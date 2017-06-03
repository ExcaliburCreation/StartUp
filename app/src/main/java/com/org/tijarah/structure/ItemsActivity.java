package com.org.tijarah.structure;

import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
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

    ItemsAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<Item, ItemHolder> firebaseRecyclerAdapter;


    String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        final List<Item> keys = new ArrayList<Item>();

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


        final String items = getIntent().getStringExtra("Category");
        String catPosition = getIntent().getStringExtra("position");
        //  Log.d(TAG + "intent", items);


        databaseReference.child("Categories").child(catPosition).child("Items").addValueEventListener(new ValueEventListener() {
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

        Log.d(TAG + "keys", keys.toString());

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemHolder>(
                Item.class,
                R.layout.item_grid,
                ItemHolder.class,
                databaseReference.child("Categories").child(catPosition).child("Items").getRef()) {
            @Override
            protected void populateViewHolder(ItemHolder viewHolder, final Item model, int position) {

                viewHolder.textViewItemName.setText(model.getName());
                viewHolder.textViewItemPrice.setText("Rs: "+String.valueOf(model.getPrice()  ));

                Log.d(TAG + "model", model.toString());
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
                        intent.putExtra("0", model);
                        startActivity(intent);
                    }
                });


            }
        };

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle("Items");
        itemList = new ArrayList<Item>();


        itemList = Session.items;
        //  intent = getIntent();
        Log.d(TAG, Session.getItems().toString());

        recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewItems.setHasFixedSize(true);


        //  Session.basket.setItems(itemList);

        adapter = new ItemsAdapter(Session.items);

        recyclerViewItems.setLayoutManager(layoutManager);
        recyclerViewItems.setAdapter(firebaseRecyclerAdapter);

        Log.d(TAG, itemList.toString());


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
        itemList = new ArrayList<Item>();
        itemList.addAll(Session.getItems());
        adapter.notifyDataSetChanged();
        Log.d(TAG, "Restart" + itemList.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        itemList = new ArrayList<Item>();
        itemList.addAll(Session.getItems());
        adapter.notifyDataSetChanged();
        Log.d(TAG, "Resume" + itemList.toString());

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
