package com.org.tijarah.structure;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 1/3/2018.
 */

public class RemoteServer {


    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    private static boolean categoryFlag = false;

    private static boolean itemFlag = false;

    private static List<Item> itemsList = new ArrayList<Item>();
    private static List<Category> categoryList = new ArrayList<Category>();

    public static List<Category> getRemoteCategories(final Context context) {

        final ArrayList<Category> categoryList = new ArrayList<Category>();
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Categories");

        // db.deleteAllCategory();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot c : dataSnapshot.getChildren()) {

                    Category category = c.getValue(Category.class);

                    Log.d("Category", category.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("RR", categoryList.toString());
        return categoryList;
    }

    public static List<Item> getRemoteItems(final Context context) {

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot c : dataSnapshot.child("Categories").getChildren()) {
                    int count = 0;
                    Log.d("shotItem1", c.toString());

                    for (DataSnapshot cc : c.child("Items").getChildren()) {

                        try {

                            Log.d("shotItem2", cc.toString());
                            Item item = cc.getValue(Item.class);
                            item.setCategory(c.getValue(Category.class).getName());

                            itemsList.add(item);

                            Log.d("ttem", item.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Intent intent = new Intent(context, MapsActivity.class);
                    context.startActivity(intent);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RemoteServer.itemFlag = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Session.items = itemsList;
        return itemsList;
    }
}
