package com.windsing.androidskilltest;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.windsing.androidskilltest.tools.BlankFragment;
import com.windsing.androidskilltest.tools.BlankFragment2;

public class Main10Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

    }

    public void selectFragment(View view){
        Fragment fragment;
        if(view == findViewById(R.id.button17)){
            fragment = new BlankFragment();
        }else {
            fragment = new BlankFragment2();
        }

        android.app.FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place,fragment);
        fragmentTransaction.commit();
    }
}
