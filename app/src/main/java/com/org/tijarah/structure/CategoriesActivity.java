package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.ls.LSException;

import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    String[] categories = {"Category 1","Category 2","Category 3","Category 4","Category 5","Category 6","Category 7"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_item, categories);

        ListView listView = (ListView) findViewById(R.id.categoriesList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoriesActivity.this, ItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}
