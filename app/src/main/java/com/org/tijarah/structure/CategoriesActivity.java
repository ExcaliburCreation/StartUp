package com.org.tijarah.structure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private FirebaseRecyclerAdapter<Category, ViewHolder> adapter;
    private ChildEventListener cel;
//    private static ClickListener clickListener;

    private RecyclerView catRecyclerView;

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

        adapter = new FirebaseRecyclerAdapter<Category, ViewHolder>(
                Category.class,
                R.layout.categort_list_row,
                ViewHolder.class,
                dbr.child("Categories").getRef()) {

            @Override
            protected void populateViewHolder(ViewHolder viewHolder, final Category model, final int position) {

                viewHolder.textCatName.setText(model.getName());

                Log.d("Catego Populate View", model.toString());
                Log.d("Session", Session.getItems().toString());
                Log.d("Basket", Session.basket.getItems().toString());

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                int width = displayMetrics.widthPixels;
                viewHolder.textCatName.setTextSize( width / 20 );
                Log.d(TAG, "Recycler View :" + model.getName());



                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CategoriesActivity.this, ItemsActivity.class);
                        intent.putExtra("Category", model.getName());
                        intent.putExtra("position", model.getId());
                        startActivity(intent);
                        Toast.makeText(CategoriesActivity.this, "Category clicked" + model.getId(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Category clicked");
                    }
                });

            }
        };


        catRecyclerView.setLayoutManager(layoutManager);
        catRecyclerView.setAdapter(adapter);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        View mView;
        TextView textCatName;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            textCatName = (TextView) itemView.findViewById(R.id.textCatName);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.basket) {
            Intent intent = new Intent(getBaseContext(), BasketActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "On Resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "On Restart");
    }
}
