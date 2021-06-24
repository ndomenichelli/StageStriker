package com.example.t00562771.stagestriker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

/**
 * Created by T00562771 on 4/5/2018.
 */

public class startOptions extends Fragment{

    Button buttonStart;
    Switch switchGame, switchBestOf;
    CheckBox dsr;

    //default options
    int game;
    int bestOf;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.startoptions, container, false);

        buttonStart = (Button) v.findViewById(R.id.buttonStart);
        switchGame = (Switch) v.findViewById(R.id.switchGame);
        switchBestOf = (Switch) v.findViewById(R.id.switchBestOf);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                if(switchGame.isChecked()){
                    //smash 4 stagelist
                    game = 1;

                }
                else{
                    //melee stagelist
                    game  = 0;
                }
                if(switchBestOf.isChecked()){

                    bestOf = 5;
                }
                else{
                    bestOf = 3;
                }

                bundle.putInt("game", game);
                bundle.putInt("bestOf", bestOf);

                //something to do with rotation duplicating ui fix
                if(savedInstanceState == null){
                    FragmentManager FM = getFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();

                    setStart s1 = new setStart();
                    s1.setArguments(bundle);

                    FT.replace(R.id.screen, s1);
                    FT.addToBackStack("s1");
                    FT.commit();
                }
                else{

                }
            }
        });

        return v;

    }


}
