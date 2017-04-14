package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedItemActivity extends AppCompatActivity {

    TextView txtSelectedItemName ;
    TextView txtSelectedItemDesc ;
    TextView txtItemCount ;
    ImageView imgSelectedItem;

    Button btnAddToBasket;
    Button btnItemAdd;
    Button btnItemRemove;

    Intent intent;
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

        imgSelectedItem = (ImageView) findViewById(R.id.imgSelectedItem);

        txtSelectedItemName.setText(bundle.getString("Name"));

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
                Toast.makeText(SelectedItemActivity.this,"Add", Toast.LENGTH_LONG).show();
          //      txtItemCount.setText("ADD");
            }
        });

        btnItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectedItemActivity.this,"Remove", Toast.LENGTH_LONG).show();
           //     txtItemCount.setText("REMOVE");
            }
        });
    }
}
