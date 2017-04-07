package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private static final String TAG = ItemsActivity.class.getSimpleName();
    List<Item> itemList;

    String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        itemList = new ArrayList<>();

        intent = getIntent();

        setTitle(intent.getStringExtra("Category"));

        for(int i=0 ; i< 10 ; i++){

            Item item = new Item("Item " + i, 10, 120.00, "category");
            itemList.add(item);
        }

        Log.d(TAG,itemList.toString());
        GridViewAdapter adapter = new GridViewAdapter(this, itemList);

        GridView itemsGridView = (GridView) findViewById(R.id.itemsGridView);

        itemsGridView.setAdapter(adapter);

        itemsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ItemsActivity.this, SelectedItemActivity.class);
                startActivity(intent);
            }
        });



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
}
