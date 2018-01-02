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
        final int position = intent.getIntExtra("position", 0);

//        final Item item = (Item) getIntent().getSerializableExtra("item");

        setTitle(name);
        Item item = Session.getItems().get(position);
        txtSelectedItemName.setText(item.getName());
        txtItemCount.setText(String.valueOf(item.getCount()));

//        Log.d("Add Item Button ", item.toString());
        Log.d("Session", Session.getItems().toString());
        Log.d("Basket", Session.basket.getItems().toString());

        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedItemActivity.this, BasketActivity.class);
                //     Session.basket.addItems(item);
                startActivity(intent);
            }
        });

        btnItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectedItemActivity.this, "Add", Toast.LENGTH_LONG).show();

                int count = Session.getItems().get(position).getCount();
                Session.getItems().get(position).setCount(String.valueOf(++count));

                txtItemCount.setText(String.valueOf(count));

                //   Log.d("Add Button Selected ", item.toString());
                Log.d("Session", Session.getItems().toString());
                Log.d("Basket", Session.basket.getItems().toString());
                Log.d("Session Length", String.valueOf(Session.getItems().size()));
                Log.d("Basket", String.valueOf(Session.basket.getItems().size()));
            }
        });

        btnItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Session.items.get(position).getCount();
                if (count > 0) {
                    Session.items.get(position).setCount(String.valueOf(--count));

                    txtItemCount.setText(String.valueOf(count));
                }
//                Log.d("Remove Button Selected ", item.toString());
                Log.d("Session", Session.getItems().toString());
                Log.d("Basket", Session.basket.getItems().toString());
                Log.d("Session Length", String.valueOf(Session.getItems().size()));
                Log.d("Basket", String.valueOf(Session.basket.getItems().size()));

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
