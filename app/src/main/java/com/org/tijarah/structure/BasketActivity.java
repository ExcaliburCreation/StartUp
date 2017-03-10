package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class BasketActivity extends AppCompatActivity {

    String[] basketItems = {"basket 1" ,"basket 2","basket 3","basket 4","basket 5","basket 6"};

    Button btnFinalizeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        ListView listView = (ListView) findViewById(R.id.basket_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_item, basketItems);
        listView.setAdapter(adapter);

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
