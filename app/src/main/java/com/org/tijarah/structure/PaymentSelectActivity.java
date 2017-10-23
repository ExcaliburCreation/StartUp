package com.org.tijarah.structure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaymentSelectActivity extends AppCompatActivity {

    private FirebaseDatabase fbdb;
    private DatabaseReference dbr;
    Button btnCashOnDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_select);

        fbdb = fbdb.getInstance();
        dbr = fbdb.getReference();

        btnCashOnDel = (Button) findViewById(R.id.btnCashOnDel);
        final DatabaseReference usersRef = dbr.child("Orders").push();



        btnCashOnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Basket> order = new HashMap<String, Basket>();
                order.put(new Date().toString() + "-Rayan", Session.basket);
                usersRef.setValue(order);
            }
        });

    }
}
