package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class ItemsActivity extends AppCompatActivity {

    String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        int[] images = {R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,
                R.drawable.supermarkets,};

        GridViewAdapter adapter = new GridViewAdapter(this, images);

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

        if(item.getItemId() == R.id.basket){
            Intent intent = new Intent(ItemsActivity.this, BasketActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
