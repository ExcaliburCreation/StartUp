package com.org.tijarah.structure;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by Excalibur Creations on 3/22/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_navigation_drawer, null);

        RelativeLayout relativeLayout = (RelativeLayout) drawerLayout.findViewById(R.id.content_navigation_drawer);
        getLayoutInflater().inflate(layoutResID, relativeLayout, true);

        super.setContentView(drawerLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public boolean showNavDrawer(){
        return true;
    }
}
