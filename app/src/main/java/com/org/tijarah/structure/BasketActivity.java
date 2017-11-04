package com.org.tijarah.structure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = BasketActivity.class.getSimpleName();

    private FirebaseDatabase fbdb;
    private DatabaseReference dbr;

    String[] basketItems = {"Basket 1", "Basket 2", "Basket 3", "Basket 4", "Basket 5", "Basket 6"};
    String[] basket;

    private int total = 0;
    Button btnFinalizeOrder;
    TextView txtBasketTotal;
    ArrayAdapter<String> adapter;
    List<Item> itemList = new ArrayList<Item>();
    ListView listView;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        fbdb = fbdb.getInstance();
        dbr = fbdb.getReference();

        listView = (ListView) findViewById(R.id.basket_list);

        if (Session.getItems() != null) {
            itemList = loadData();
        }
        Log.d(TAG, itemList.toString());
        List<String> items = new ArrayList<String>();
        if (itemList != null) {

            for (Item i : itemList) {
                items.add(i.getName());
            }

            adapter = new ArrayAdapter<String>(this, R.layout.listview_item, items);
            listView.setAdapter(adapter);

        }


        txtBasketTotal = (TextView) findViewById(R.id.txtBasketTotal);

        final DatabaseReference usersRef = dbr.child("Orders").push();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Log.d("USER", email);
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
        btnFinalizeOrder = (Button) findViewById(R.id.btnFinalizeOrder);
        btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Log.d("USER", FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
                }
                Map<String, Order> order = new HashMap<String, Order>();
                Order o = new Order();
                o.setUser("User");
                o.setBasket(Session.basket);
                o.setDatetime(new Date());
                order.put("aa", o);
                usersRef.setValue(o);


                final AlertDialog.Builder dialog = new AlertDialog.Builder(BasketActivity.this);
                dialog.setTitle("THANK YOU");
                dialog.setMessage("Your order has been successfully placed");
                dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Session.basket.getItems().clear();
                        Session.basket.setTotal(0);
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BasketActivity.this, SelectedItemActivity.class);
                intent.putExtra("itemsActivity", itemList.get(i));
                Log.d(TAG, Session.getItems().toString());
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "On restart");
        itemList = Session.basket.getItems();
        adapter.notifyDataSetChanged();
        Log.d(TAG, itemList.toString());

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "On Resume");
        itemList = Session.basket.getItems();
        updateData();
        adapter.notifyDataSetChanged();
        Log.d(TAG, itemList.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                adapter.notifyDataSetChanged();
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
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
        for (int i = 0; i < itemList.size(); i++) {
            basket = new String[itemList.size()];
            basket[i] = itemList.get(i).getName();
            total += itemList.get(i).getPrice() * itemList.get(i).getCount();
            Log.d(TAG, String.valueOf(total));
            txtBasketTotal.setText(String.valueOf(total));
            Session.basket.setTotal(total);

        }
    }

    private class Order {
        String user;
        Date datetime;
        Basket basket;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

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
