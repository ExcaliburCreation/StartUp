package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedItemActivity extends AppCompatActivity {


    private static final String TAG = SelectedItemActivity.class.getSimpleName();
    TextView txtSelectedItemName;
    TextView txtSelectedItemDesc;
    TextView txtItemCount;
    ImageView imgSelectedItem;

    Button btnAddToBasket;
    Button btnItemAdd;
    Button btnItemRemove;

    Intent intent;

    String name = "";
    String count = "";
    //String imageUrl= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        intent = getIntent();
        Bundle bundle = intent.getExtras();

        Log.d(TAG, Session.getItems().toString());

        btnAddToBasket = (Button) findViewById(R.id.btnAddToBasket);
        btnItemAdd = (Button) findViewById(R.id.btnItemAdd);
        btnItemRemove = (Button) findViewById(R.id.btnItemRemove);

        txtSelectedItemName = (TextView) findViewById(R.id.txtSelectedItemName);
        txtSelectedItemDesc = (TextView) findViewById(R.id.txtSelectedItemDesc);
        txtItemCount = (TextView) findViewById(R.id.txtItemCount);

        imgSelectedItem = (ImageView) findViewById(R.id.imgSelectedItem);

        final int id = bundle.getInt("item");
        final Item item = Session.items.get(id);

        Log.d(TAG + "Serialized", Session.items.toString());

        txtSelectedItemName.setText(item.getName());
        txtItemCount.setText(String.valueOf(item.getCount()));
        Log.d(TAG, item.toString());

        setTitle(item.getName());

        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedItemActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });

        btnItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectedItemActivity.this, "Add", Toast.LENGTH_LONG).show();
                int count = item.getCount();

                txtItemCount.setText(String.valueOf(++count));
                Session.items.get(id).setCount(count);

                if (!item.isAdded()) {
                    Session.basket.addItems(item);
                    Session.basket.getItem(item).setCount(count);
                    Session.basket.getItem(item).setAdded(true);
                    Session.items.get(id).setAdded(true);

                } else {
                    Session.basket.getItem(item).setCount(count);
                }
            }
        });

        btnItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectedItemActivity.this, "Remove", Toast.LENGTH_LONG).show();
                int count = item.getCount();

                if (count == 0) {

                    Session.items.get(id).setAdded(true);

                    Session.basket.removeItem(item);
                    Session.basket.getItem(item).setAdded(true);
                } else {

                    txtItemCount.setText(String.valueOf(--count));
                    Session.items.get(id).setCount(count);

                    if (!Session.items.get(id).isAdded()) {
                        Session.items.get(id).setAdded(true);
                        Session.basket.getItem(item).setCount(count);
                    }

                }
            }
        });
    }


}
