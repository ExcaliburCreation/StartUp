package com.org.tijarah.structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private static final String TAG = CategoriesActivity.class.getSimpleName();
    private ChildEventListener childEventListener;
    private FirebaseDatabase fbdb;
    private DatabaseReference dbr;


    private RecyclerView catRecyclerView;
    private CategoryAdapter mycategoryAdapter;

   List<Category> cats = new ArrayList<Category>();

    Category category = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getSupportActionBar().setTitle("Categories");

        fbdb = fbdb.getInstance();
        dbr = fbdb.getReference();

        catRecyclerView = (RecyclerView) findViewById(R.id.catRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        catRecyclerView.setHasFixedSize(true);
        generateData();

        mycategoryAdapter = new CategoryAdapter(cats);

        FirebaseRecyclerAdapter<Category,ViewHolder> adapter = new FirebaseRecyclerAdapter<Category, ViewHolder>(
                Category.class,
                R.layout.categort_list_row,
                ViewHolder.class,
                dbr.child("Categories").getRef()) {

            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Category model, int position) {
                viewHolder.textCatName.setText(model.getName());
                Log.d(TAG,"Recycler View :"+ model.getName());
            }
        };


        catRecyclerView.setLayoutManager(layoutManager);
        catRecyclerView.setAdapter(adapter);


    }


    private void generateData(){

        for(int i=0 ; i< 10 ; i++){
            Category category = new Category("Category "+ i);
            cats.add(category);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textCatName;
        ImageView imageViewCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);
            textCatName = (TextView) itemView.findViewById(R.id.textCatName);
        }
    }
}
