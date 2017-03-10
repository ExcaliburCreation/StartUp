package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectedItemActivity extends AppCompatActivity {

    Button btnAddToBasket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        btnAddToBasket = (Button) findViewById(R.id.btnAddToBasket);

        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedItemActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });

    }
}
