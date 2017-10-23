package com.org.tijarah.structure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.cast.framework.*;
import com.google.android.gms.games.request.OnRequestReceivedListener;
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

                Map<String, Order> order = new HashMap<String, Order>();
                Order o = new Order();
                o.setBasket(Session.basket);
                o.setDatetime(new Date());
                order.put("aa", o);
                usersRef.setValue(o);
            }
        });

    }

    private class Order{
        Date datetime;
        Basket basket;

        public Date getDatetime() {
            return datetime;
        }

        public void setDatetime(Date datetime) {
            this.datetime = datetime;
        }

        public Basket getBasket() {
            return basket;
        }

        public void setBasket(Basket basket) {
            this.basket = basket;
        }
    }
}
