package com.example.t00562771.stagestriker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by T00562771 on 4/5/2018.
 */

public class setStart extends Fragment{

    Button buttonP1Win;
    Button buttonP2Win;
    boolean p1Win;
    TextView top;
    ImageView image;
    boolean counterpick;
    int p1Score;
    int p2Score;
    int game;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){

        //pass view fragment
        View v = inflater.inflate(R.layout.setstart, container, false);

        //layout variables
        buttonP1Win = (Button) v.findViewById(R.id.buttonP1Win);
        buttonP2Win = (Button) v.findViewById(R.id.buttonP2Win);
        top = (TextView) v.findViewById(R.id.textViewPhase);
        image = (ImageView) v.findViewById(R.id.imageViewImage);

        //bundle variables
        Bundle bundle = getArguments();
        game = bundle.getInt("game");
        final int bestOf = bundle.getInt("bestOf");
        p1Score = bundle.getInt("p1Score");
        p2Score = bundle.getInt("p2Score");

        int stage = bundle.getInt("stage", 0);

        if(stage == 1){
            top.setText("Battlefield");
            if(game == 0){
                image.setImageResource(R.drawable.bf_m);
            }
            else{
                image.setImageResource(R.drawable.bf_s4);
            }
            image.setVisibility(View.VISIBLE);
        }
        else if(stage == 2){
            if(game == 0){
                top.setText("Fountain of Dreams");
                image.setImageResource(R.drawable.fod);
            }
            else{
                top.setText("Smashviile");
                image.setImageResource(R.drawable.smashville);
            }
            image.setVisibility(View.VISIBLE);
        }
        else if(stage == 3){
            if(game == 0){
                top.setText("Dreamland");
                image.setImageResource(R.drawable.dl_4);
            }
            else{
                top.setText("Lylat Cruise");
                image.setImageResource(R.drawable.lylat);
            }
            image.setVisibility(View.VISIBLE);
        }
        else if(stage == 4){
            top.setText("Final Destination");
            if(game == 0){
                image.setImageResource(R.drawable.fd_m);
            }
            else{
                image.setImageResource(R.drawable.fd_4);
            }
            image.setVisibility(View.VISIBLE);
        }
        else if(stage == 5){
            if(game == 0){
                top.setText("Yoshis Story");
                image.setImageResource(R.drawable.yoshis);
            }
            else{
                top.setText("Town and City");
                image.setImageResource(R.drawable.tandc);
            }
            image.setVisibility(View.VISIBLE);
        }
        else if(stage == 6){
                if(game == 0){
                    top.setText("Pokemon Stadium");
                    image.setImageResource(R.drawable.staduim);
                }
                else{
                    top.setText("Dreamland");
                    image.setImageResource(R.drawable.dl_4);
                }
                image.setVisibility(View.VISIBLE);
        }

        buttonP1Win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //player 1 wins
                p1Win = true;

                if(image.getVisibility() == View.VISIBLE){

                    counterpick = true;
                    p1Score++;

                }

                Bundle bundle = new Bundle();

                bundle.putBoolean("counterpick", counterpick);
                bundle.putBoolean("p1Win", p1Win);
                bundle.putInt("p1Score", p1Score);
                bundle.putInt("p2Score", p2Score);
                bundle.putInt("bestOf", bestOf);
                bundle.putInt("game", game);

                if(savedInstanceState == null) {
                    FragmentManager FM = getFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();
                    setPhase1 s1 = new setPhase1();
                    s1.setArguments(bundle);
                    FT.replace(R.id.screen, s1);
                    FT.addToBackStack("s1");
                    FT.commit();
                }
                else{

                }
            }
        });

        buttonP2Win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //player 2 wins
                p1Win = false;

                if(image.getVisibility() == View.VISIBLE){

                    counterpick = true;
                    p2Score++;

                }

                Bundle bundle = new Bundle();

                bundle.putBoolean("counterpick", counterpick);
                bundle.putBoolean("p1Win", p1Win);
                bundle.putInt("p1Score", p1Score);
                bundle.putInt("p2Score", p2Score);
                bundle.putInt("bestOf", bestOf);
                bundle.putInt("game", game);

                if(savedInstanceState == null) {
                    FragmentManager FM = getFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();
                    setPhase1 s1 = new setPhase1();
                    s1.setArguments(bundle);
                    FT.replace(R.id.screen, s1);
                    FT.addToBackStack("s1");
                    FT.commit();
                }

            }
        });

        return v;

    }

}
