package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private ChildEventListener childEventListener;
    private FirebaseDatabase fbdb;
    private DatabaseReference dbr;


    private ListView catListview;
    private CategoryAdapter mycategoryAdapter;

    List<Category> cats = new ArrayList<>();

    Category category = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getSupportActionBar().setTitle("Categories");

        fbdb = fbdb.getInstance();
        dbr = fbdb.getReference().child("Categories");

        mycategoryAdapter = new CategoryAdapter(this,R.layout.categories,cats);


         catListview = (ListView) findViewById(R.id.categoriesList);



            catListview.setAdapter(mycategoryAdapter);



        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                todos = dataSnapshot.getValue(TODO.class);
//                todos.setKey(dataSnapshot.getKey());
//                mTODOADAPTER.add(todos);
//                todoLists.setAdapter(mTODOADAPTER);
//
                category = dataSnapshot.getValue(Category.class);
                mycategoryAdapter.add(category);
                catListview.setAdapter(mycategoryAdapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        };

        dbr.addChildEventListener(childEventListener);

//        childEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                    String catname = (String) dataSnapshot.getValue();
//                    adapter.add(catname);
//                    listView.setAdapter(adapter);
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//
//                dbr
//            }


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(CategoriesActivity.this, ItemsActivity.class);
//                startActivity(intent);
//            }
//        });

    catListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(CategoriesActivity.this, ItemsActivity.class);
            intent.putExtra("Category", cats.get(i).getName());
            startActivity(intent);

        }
    });

    }




}
