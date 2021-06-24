package com.example.t00562771.stagestriker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        startOptions s1 = new startOptions();

        FT.add(R.id.screen, s1);
        FT.commit();

        }
}
