package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class ItemsActivity extends AppCompatActivity {

    String[] items = {"Item 1","Item 2","Item 3","Item 4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.grid_item, items);

        GridView gridView = (GridView)findViewById(R.id.itemsgridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ItemsActivity.this, SelectedItemActivity.class);
                startActivity(intent);
            }
        });


    }
}
