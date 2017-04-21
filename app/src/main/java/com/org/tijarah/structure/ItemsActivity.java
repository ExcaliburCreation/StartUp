package com.org.tijarah.structure;

import android.content.Intent;
import android.os.Parcelable;
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
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private static final String TAG = ItemsActivity.class.getSimpleName();
    List<Item> itemList;

    RecyclerView recyclerViewItems;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ItemsAdapter adapter;

    String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle("Items");
          itemList = new ArrayList<Item>();

        intent = getIntent();

        recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewItems.setHasFixedSize(true);

        for (int i = 1; i <= 10; i++) {

            Item item = new Item(i ,"Item " + i, 10, 120.00, "category");
            itemList.add(item);
        }

        Session.setItems(itemList);
      //  Session.basket.setItems(itemList);

        adapter = new ItemsAdapter(itemList);

        recyclerViewItems.setLayoutManager(layoutManager);
        recyclerViewItems.setAdapter(adapter);

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


}
