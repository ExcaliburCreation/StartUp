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


        btnAddToBasket = (Button) findViewById(R.id.btnAddToBasket);
        btnItemAdd = (Button) findViewById(R.id.btnItemAdd);
        btnItemRemove = (Button) findViewById(R.id.btnItemRemove);

        txtSelectedItemName = (TextView) findViewById(R.id.txtSelectedItemName);
        txtSelectedItemDesc = (TextView) findViewById(R.id.txtSelectedItemDesc);
        txtItemCount = (TextView) findViewById(R.id.txtItemCount);

        imgSelectedItem = (ImageView) findViewById(R.id.imgSelectedItem);


        final int id = intent.getIntExtra("item", 1);

        final Item item = (Item) getIntent().getSerializableExtra("itemsActivity");

     //   Log.d("Null object", item.toString());

        if (item.getCount() == 0) {
            btnAddToBasket.setEnabled(false);

        } else {
            btnAddToBasket.setEnabled(true);
        }
        txtSelectedItemName.setText(item.getName());
        txtItemCount.setText(String.valueOf(item.getCount()));
        //      Log.d(TAG, item.toString());
        btnAddToBasket.setEnabled(false);
        setTitle(name);

        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedItemActivity.this, BasketActivity.class);
                Session.basket.addItems(item);
                startActivity(intent);
            }
        });

        btnItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectedItemActivity.this, "Add", Toast.LENGTH_LONG).show();
                int count = item.getCount();

                txtItemCount.setText(String.valueOf(++count));
                Session.items.get(id).setCount(String.valueOf(count));

                if (!item.isAdded()) {
                    Session.basket.addItems(item);
                    Session.basket.getItem(item).setCount(String.valueOf(count));
                    Session.basket.getItem(item).setAdded(true);
//                    Session.items.get(id).setAdded(true);

                } else {
                     Session.basket.getItem(item).setCount(String.valueOf(count));
                }
                if (item.getCount() > 0) {
                    btnAddToBasket.setEnabled(true);

                } else {
                    btnAddToBasket.setEnabled(false);
                }
            }
        });

        btnItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectedItemActivity.this, "Remove", Toast.LENGTH_LONG).show();
                int count = item.getCount();

                if (count == 0) {

                    Session.items.get(id).setAdded(false);

                    Session.basket.removeItem(item);
//                    Session.basket.getItem(item).setAdded(false);
                } else {

                    txtItemCount.setText(String.valueOf(--count));
                    Session.items.get(id).setCount(String.valueOf(count));

                    if (count == 0) {
                        Session.basket.removeItem(item);
                        Session.items.get(id).setAdded(false);
                    }


                }

                if (item.getCount() <= 0) {
                    btnAddToBasket.setEnabled(false);

                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "On Restart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "On Resume");
    }
}
