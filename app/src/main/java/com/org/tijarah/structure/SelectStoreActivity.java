package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SelectStoreActivity extends AppCompatActivity {

    String[] stores = {"Store 1", "Store 2", "Store 3", "Store 4", "Store 5", "Store 6", "Store 7",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_store);

        Bundle bundle = this.getIntent().getExtras();

        String[] places = bundle.getStringArray("Places");
        ListView listView = (ListView) findViewById(R.id.storeslist);

        if(places != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_item, places);
            listView.setAdapter(adapter);
        }
        else{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_item, stores);
            listView.setAdapter(adapter);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SelectStoreActivity.this, CategoriesActivity.class);

                startActivity(intent);
            }
        });
    }
}
