package com.org.tijarah.structure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.cast.framework.*;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentSelectActivity extends AppCompatActivity {

    private FirebaseDatabase fbdb;
    private DatabaseReference dbr;
    TextView txtCheckoutTotal;
    String[] basket;
    private int total = 0;
    Button btnCashOnDel;
    ListView listView;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_select);


        if (Session.basket.getItems() != null) {
            itemList = loadData();
        }


        fbdb = fbdb.getInstance();
        dbr = fbdb.getReference();

        listView = (ListView) findViewById(R.id.checkout_list);

        btnCashOnDel = (Button) findViewById(R.id.btnCashOnDel);
        final DatabaseReference usersRef = dbr.child("order").push();


        BasketAdapter adapter = new BasketAdapter(this, Session.basket.getItems());
        listView.setAdapter(adapter);
        txtCheckoutTotal = (TextView) findViewById(R.id.txtCheckoutTotal);

        btnCashOnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usersRef.setValue(Session.basket.getItems());

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

       /* Log.d(TAG, "On restart");
        itemList = Session.basket.getItems();
        // adapter.notifyDataSetChanged();
        Log.d(TAG, itemList.toString());*/

    }

    @Override
    protected void onResume() {
        super.onResume();

       /* Log.d(TAG, "On Resume");
        itemList = Session.basket.getItems();*/
        updateData();
        // adapter.notifyDataSetChanged();
       // Log.d(TAG, itemList.toString());
    }

    public List<Item> loadData() {

        for (Item i : Session.items) {
            if (i.getCount() > 0) {
                Session.basket.addItems(i);
            }
        }
        return Session.basket.getItems();
    }

    public void updateData() {
        total = 0;
        for (int i = 0; i < Session.basket.getItems().size(); i++) {
            basket = new String[itemList.size()];
            basket[i] = itemList.get(i).getName();
            total += itemList.get(i).getPrice() * itemList.get(i).getCount();
            txtCheckoutTotal.setText(String.valueOf(total));
            Session.basket.setTotal(total);

        }
    }

    private class Order{
        Date datetime;
        List<Item> basket;

        public Date getDatetime() {
            return datetime;
        }

        public void setDatetime(Date datetime) {
            this.datetime = datetime;
        }

        public List<Item> getBasket() {
            return basket;
        }

        public void setBasket(List<Item> basket) {
            this.basket = basket;
        }
    }
}
