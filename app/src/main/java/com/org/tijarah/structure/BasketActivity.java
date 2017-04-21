package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = BasketActivity.class.getSimpleName();
    String[] basketItems = {"Basket 1", "Basket 2", "Basket 3", "Basket 4", "Basket 5", "Basket 6"};
    String[] basket;
    private int total = 0;
    Button btnFinalizeOrder;
    TextView txtBasketTotal;
    ArrayAdapter<String> adapter;
    List<Item> itemList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        listView = (ListView) findViewById(R.id.basket_list);

        itemList = Session.basket.getItems();

        Log.d(TAG, itemList.toString());

        if (itemList != null) {

            basket = new String[itemList.size()];

            for (int i = 0; i < itemList.size(); i++) {

                basket[i] = itemList.get(i).getName();
                total += itemList.get(i).getPrice() * itemList.get(i).getCount();
                Log.d(TAG, String.valueOf(total));
            }

            adapter = new ArrayAdapter<String>(this, R.layout.listview_item, basket);
            listView.setAdapter(adapter);

        } else {

            adapter = new ArrayAdapter<String>(this, R.layout.listview_item, basketItems);
            listView.setAdapter(adapter);
        }


        txtBasketTotal = (TextView) findViewById(R.id.txtBasketTotal);
        txtBasketTotal.setText(String.valueOf(total));

        btnFinalizeOrder = (Button) findViewById(R.id.btnFinalizeOrder);
      /*  btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BasketActivity.this, SelectedItemActivity.class);
                intent.putExtra("item", itemList.get(i).getId() - 1);
                Log.d(TAG, Session.getItems().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "On restart");
        itemList = Session.basket.getItems();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume");
        itemList.clear();
        itemList = Session.basket.getItems();
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.basket) {
            Intent intent = new Intent(getBaseContext(), BasketActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
