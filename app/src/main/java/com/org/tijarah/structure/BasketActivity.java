package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private static final String TAG = BasketActivity.class.getSimpleName();
    String[] basketItems = {"Basket 1", "Basket 2", "Basket 3", "Basket 4", "Basket 5", "Basket 6"};
    String[] basket;
    private int total = 0;
    Button btnFinalizeOrder;
    TextView txtBasketTotal;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ListView listView = (ListView) findViewById(R.id.basket_list);
        List<Item> itemList = Session.basket.items;

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
        btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }


}
