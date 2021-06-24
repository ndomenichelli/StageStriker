package com.example.t00562771.stagestriker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by T00562771 on 4/5/2018.
 */

public class setPhase1 extends Fragment{

    ImageButton ib_bf;
    ImageButton ib_fod;
    ImageButton ib_fd;
    ImageButton ib_yoshis;
    ImageButton ib_dl;
    ImageButton ib_stadium;
    TextView player;
    TextView ban;
    TextView TFp1Score, TFp2Score;
    TextView TFbestOf;
    int bestOf;
    boolean p1Win = true;
    boolean p1Start = true;
    int p1Count = 0;
    int p2Count = 0;
    int switched = 0;
    int stage = 0;
    boolean counterpick = false;
    int p1Score, p2Score;
    int game;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.setphase1, container, false);

        ib_bf = (ImageButton) v.findViewById(R.id.imageButtonBf);
        ib_fod = (ImageButton) v.findViewById(R.id.imageButtonFod);
        ib_fd = (ImageButton) v.findViewById(R.id.imageButtonFd);
        ib_yoshis = (ImageButton) v.findViewById(R.id.imageButtonYoshis);
        ib_dl = (ImageButton) v.findViewById(R.id.imageButtonDreamland);
        ib_stadium = (ImageButton) v.findViewById(R.id.imageButtonStadium);
        player = (TextView) v.findViewById(R.id.textViewPlayer);
        ban = (TextView) v.findViewById(R.id.textViewBan);
        TFp1Score = (TextView) v.findViewById(R.id.textViewP1Score);
        TFp2Score = (TextView) v.findViewById(R.id.textViewP2Score);
        TFbestOf = (TextView) v.findViewById(R.id.textViewBestOf);

        Bundle bundle = getArguments();
        p1Win = bundle.getBoolean("p1Win");
        p1Start = bundle.getBoolean("p1Win");
        counterpick = bundle.getBoolean("counterpick");
        p1Score = bundle.getInt("p1Score");
        p2Score = bundle.getInt("p2Score");
        bestOf = bundle.getInt("bestOf");
        game = bundle.getInt("game");

        int wins = 2;

        //best of conditions
        if(bestOf == 3){
            TFbestOf.setText("Best of 3");
        }
        else if(bestOf == 5){
            TFbestOf.setText("Best of 5");
            //no bans in best of 5
            if(counterpick == true && game == 0){
                switchPlayer();
                ban.setText("pick stage to play on");
            }
        }
        if(bestOf == 3){

            wins = 2;
        }
        else if(bestOf == 5){

            wins = 3;
        }

        //player order
        if(p1Win == true){

            player.setText("Player 1");
        }
        else{
            player.setText("Player 2");
        }

        if(counterpick == true){

            if(game == 0){
                ib_stadium.setImageResource(R.drawable.staduim);
            }
            else{
                ib_stadium.setImageResource(R.drawable.dl_4);
            }
            ib_stadium.setVisibility(View.VISIBLE);

            TFp1Score.setText(p1Score + "");
            TFp2Score.setText(p2Score + "");

        }

        //set ended
        if(p1Score == wins){

            Toast.makeText(getActivity(), "Player 1 Wins", Toast.LENGTH_LONG).show();

            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            startOptions s1 = new startOptions();
            FT.replace(R.id.screen, s1);
            FT.addToBackStack("s1");
            FT.commit();

        }
        else if(p2Score == wins){
            Toast.makeText(getActivity(), "Player 2 Wins", Toast.LENGTH_LONG).show();

            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            startOptions s1 = new startOptions();
            FT.replace(R.id.screen, s1);
            FT.addToBackStack("s1");
            FT.commit();
        }

        if(game == 0){
            //melee stages off 1-2-1
            ib_bf.setImageResource(R.drawable.bf_m);
            ib_fod.setImageResource(R.drawable.fod);
            ib_fd.setImageResource(R.drawable.fd_m);
            ib_yoshis.setImageResource(R.drawable.yoshis);
            ib_dl.setImageResource(R.drawable.dl_4);
        }
        else{
            ib_bf.setImageResource(R.drawable.bf_4);
            ib_fod.setImageResource(R.drawable.smashville);
            ib_fd.setImageResource(R.drawable.fd_s4);
            ib_yoshis.setImageResource(R.drawable.tandc);
            ib_dl.setImageResource(R.drawable.lylat);
        }

        ib_bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counterpick == false){

                    if(switched == 2){
                        ban.setText("pick stage to play on");
                        stage = 1;
                        gotoStage(savedInstanceState);
                    }

                    if(p1Win == true){
                        if(game == 0){
                            ib_bf.setImageResource(R.drawable.bf_m_b);
                        }
                        else{
                            ib_bf.setImageResource(R.drawable.bf_s4_b);
                        }
                        p1Count++;
                        if(p1Start == true){

                            if(p1Count == 1){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p1Count == 2){
                                switchPlayer();
                            }
                        }
                    }
                    else{
                        if(game == 0){
                            ib_bf.setImageResource(R.drawable.bf_m_b2);
                        }
                        else{
                            ib_bf.setImageResource(R.drawable.bf_s4_b2);
                        }
                        p2Count++;
                        if(p1Start == true){

                            if(p2Count == 2){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p2Count == 1){
                                switchPlayer();
                            }
                        }
                    }

                }
                else{
                    //counterpick phase

                    //check bo3/bo5
                    //check melee/smash 4
                    if(switched == 1){

                        stage = 1;
                        gotoStage(savedInstanceState);

                    }

                    if(p1Win == true){

                        if(game == 0){
                            ib_bf.setImageResource(R.drawable.bf_m_b);
                        }
                        else{
                            ib_bf.setImageResource(R.drawable.bf_s4_b);
                        }
                        p1Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                    else{
                        if(game == 0){
                            ib_bf.setImageResource(R.drawable.bf_m_b2);
                        }
                        else{
                            ib_bf.setImageResource(R.drawable.bf_s4_b2);
                        }
                        p2Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                }

                ib_bf.setClickable(false);
            }
        });
        ib_fod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counterpick == false){

                    if(switched == 2){
                        ban.setText("pick stage to play on");
                        stage = 2;
                        gotoStage(savedInstanceState);
                    }

                    if(p1Win == true){
                        if(game == 0){
                            ib_fod.setImageResource(R.drawable.fod_b);
                        }
                        else{
                            ib_fod.setImageResource(R.drawable.smashville_b);
                        }
                        p1Count++;
                        if(p1Start == true){

                            if(p1Count == 1){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p1Count == 2){
                                switchPlayer();
                            }
                        }
                    }
                    else{
                        if(game == 0){
                            ib_fod.setImageResource(R.drawable.fod_b2);
                        }
                        else{
                            ib_fod.setImageResource(R.drawable.smashville_b2);
                        }
                        p2Count++;
                        if(p1Start == true){

                            if(p2Count == 2){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p2Count == 1){
                                switchPlayer();
                            }
                        }
                    }
                }
                else{
                    //counterpick phase
                    if(switched == 1){

                        stage = 2;
                        gotoStage(savedInstanceState);

                    }

                    if(p1Win == true){

                        if(game == 0){
                            ib_fod.setImageResource(R.drawable.fod_b);
                        }
                        else{
                            ib_fod.setImageResource(R.drawable.smashville_b);
                        }
                        p1Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                    else{
                        if(game == 0){
                            ib_fod.setImageResource(R.drawable.fod_b);
                        }
                        else{
                            ib_fod.setImageResource(R.drawable.smashville_b2);
                        }
                        p2Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                }

                ib_fod.setClickable(false);
            }
        });
        ib_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counterpick == false){

                    if(switched == 2){
                        ban.setText("pick stage to play on");
                        stage = 3;
                        gotoStage(savedInstanceState);
                    }

                    if(p1Win == true){
                        if(game == 0){
                            ib_dl.setImageResource(R.drawable.dreamland_b);
                        }
                        else{
                            ib_dl.setImageResource(R.drawable.lylat_b);
                        }
                        p1Count++;
                        if(p1Start == true){

                            if(p1Count == 1){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p1Count == 2){
                                switchPlayer();
                            }
                        }
                    }
                    else{
                        if(game == 0){
                            ib_dl.setImageResource(R.drawable.dreamland_b2);
                        }
                        else{
                            ib_dl.setImageResource(R.drawable.lylat_b2);
                        }
                        p2Count++;
                        if(p1Start == true){

                            if(p2Count == 2){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p2Count == 1){
                                switchPlayer();
                            }
                        }
                    }

                }
                else{
                    //counterpick
                    if(switched == 1){

                        stage = 3;
                        gotoStage(savedInstanceState);

                    }

                    if(p1Win == true){

                        if(game == 0){
                            ib_dl.setImageResource(R.drawable.dreamland_b);
                        }
                        else{
                            ib_dl.setImageResource(R.drawable.lylat_b);
                        }
                        p1Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                    else{
                        if(game == 0){
                            ib_dl.setImageResource(R.drawable.dreamland_b2);
                        }
                        else{
                            ib_dl.setImageResource(R.drawable.lylat_b2);
                        }
                        p2Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                }

                ib_dl.setClickable(false);
            }
        });
        ib_fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counterpick == false){

                    if(switched == 2){
                        ban.setText("pick stage to play on");
                        stage = 4;
                        gotoStage(savedInstanceState);
                    }

                    if(p1Win == true){
                        if(game == 0){
                            ib_fd.setImageResource(R.drawable.fd_m_b);
                        }
                        else{
                            ib_fd.setImageResource(R.drawable.fd_s4_b);
                        }
                        p1Count++;
                        if(p1Start == true){

                            if(p1Count == 1){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p1Count == 2){
                                switchPlayer();
                            }
                        }
                    }
                    else{
                        if(game == 0){
                            ib_fd.setImageResource(R.drawable.fd_m_b2);
                        }
                        else{
                            ib_fd.setImageResource(R.drawable.fd_s4_b2);
                        }
                        p2Count++;
                        if(p1Start == true){

                            if(p2Count == 2){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p2Count == 1){
                                switchPlayer();
                            }
                        }

                    }
                }
                else{
                    //counterpick

                    if(switched == 1){

                        stage = 4;
                        gotoStage(savedInstanceState);

                    }

                    if(p1Win == true){

                        if(game == 0){
                            ib_fd.setImageResource(R.drawable.fd_m_b);
                        }
                        else{
                            ib_fd.setImageResource(R.drawable.fd_s4_b);
                        }
                        p1Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                    else{
                        if(game == 0){
                            ib_fd.setImageResource(R.drawable.fd_m_b2);
                        }
                        else{
                            ib_fd.setImageResource(R.drawable.fd_s4_b2);
                        }
                        p2Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                }

                ib_fd.setClickable(false);
            }
        });
        ib_yoshis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counterpick == false){

                    if(switched == 2){
                        ban.setText("pick stage to play on");
                        stage = 5;
                        gotoStage(savedInstanceState);
                    }

                    if(p1Win == true){
                        if(game == 0){
                            ib_yoshis.setImageResource(R.drawable.yoshis_b);
                        }
                        else{
                            ib_yoshis.setImageResource(R.drawable.tandc_b);
                        }
                        p1Count++;
                        if(p1Start == true){

                            if(p1Count == 1){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p1Count == 2){
                                switchPlayer();
                            }
                        }
                    }
                    else{
                        if(game == 0){
                            ib_yoshis.setImageResource(R.drawable.yoshis_b2);
                        }
                        else{
                            ib_yoshis.setImageResource(R.drawable.tandc_b2);
                        }
                        p2Count++;
                        if(p1Start == true){

                            if(p2Count == 2){
                                switchPlayer();
                            }
                        }
                        else{
                            if(p2Count == 1){
                                switchPlayer();
                            }
                        }

                    }
                }
                else{
                    //counterpick
                    if(switched == 1){

                        stage = 5;
                        gotoStage(savedInstanceState);
                    }

                    if(p1Win == true){

                        if(game == 0){
                            ib_yoshis.setImageResource(R.drawable.yoshis_b);
                        }
                        else{
                            ib_yoshis.setImageResource(R.drawable.tandc_b);
                        }
                        p1Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                    else{
                        if(game == 0){
                            ib_yoshis.setImageResource(R.drawable.yoshis_b2);
                        }
                        else{
                            ib_yoshis.setImageResource(R.drawable.tandc_b2);
                        }
                        p2Count++;
                        switchPlayer();
                        ban.setText("pick stage to play on");

                    }
                }

                ib_yoshis.setClickable(false);
            }
        });

        ib_stadium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //only counterpick

                    if(counterpick == true){

                        if(switched == 1){

                            stage = 6;
                            gotoStage(savedInstanceState);
                        }

                        if(p1Win == true){
                            if(game == 0){
                                ib_stadium.setImageResource(R.drawable.stadium_b);
                            }
                            else{
                                ib_stadium.setImageResource(R.drawable.dreamland_b2);
                            }
                            p1Count++;
                            switchPlayer();
                            ban.setText("pick stage to play on");
                        }
                        else{
                            if(game == 0){
                                ib_stadium.setImageResource(R.drawable.stadium_b2);
                            }
                            else{
                                ib_stadium.setImageResource(R.drawable.dreamland_b2);
                            }
                            p2Count++;
                            switchPlayer();
                            ban.setText("pick stage to play on");
                        }
                    }

                    ib_stadium.setClickable(false);

            }
        });

        return v;
    }

    public void switchPlayer(){

        if(p1Win == true){
            p1Win = false;
        }
        else{
            p1Win = true;
        }

        if(p1Win == true){

            player.setText("Player 1");
        }
        else{
            player.setText("Player 2");
        }
        p1Count = 0;
        p2Count = 0;
        switched++;

        if(switched == 2){
            ban.setText("pick stage to play on");
        }
    }

    public void gotoStage(Bundle savedInstanceState){

        Bundle bundle = new Bundle();

        bundle.putInt("stage", stage);
        bundle.putInt("p1Score", p1Score);
        bundle.putInt("p2Score", p2Score);
        bundle.putInt("game", game);
        bundle.putInt("bestOf", bestOf);

        //rotate thing
        if(savedInstanceState == null){
            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            setStart s1 = new setStart();
            s1.setArguments(bundle);
            FT.replace(R.id.screen, s1);
            FT.addToBackStack("s1");
            FT.commit();
        }

    }

}
