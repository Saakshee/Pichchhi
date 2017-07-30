package com.example.sakshi.shree;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BhajanFragment extends Fragment implements MainActivity.OnBackPressedListener {
    Button p1, p3, p5, p7, p11, p13;
    //ScrollView scrollView;

    MediaPlayer mp = null;
    String jainidance = "jainidance";
    String janmbhumi = "janmbhumi";
    String mereguru = "mereguru";
    String teertheshwar = "teerteshwar";
    // String terigaliyaparas="terigaliyaparas";
    String tumhiho = "tumhiho";
    String meresarpesada = "meresarpesada";

    public BhajanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_bhajan, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);

        p1 = (ToggleButton) view.findViewById(R.id.p1);

        p3 = (ToggleButton) view.findViewById(R.id.p3);

        p5 = (ToggleButton) view.findViewById(R.id.p5);

        p7 = (ToggleButton) view.findViewById(R.id.p7);
        p11 = (ToggleButton) view.findViewById(R.id.p11);

        p13 = (ToggleButton) view.findViewById(R.id.p13);


        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(jainidance);

                } else {
                    // Disable vibrate
                    if (mp != null) {
                        mp.pause();
                    }

                }
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(janmbhumi);

                } else {
                    // Disable vibrate
                    if (mp != null) {
                        mp.pause();
                    }

                }
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(mereguru);

                } else {
                    // Disable vibrate
                    if (mp != null) {
                        mp.pause();
                    }

                }
            }
        });
        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(teertheshwar);

                } else {
                    // Disable vibrate
                    if (mp != null) {
                        mp.pause();
                    }

                }
            }
        });

        p11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(tumhiho);

                } else {
                    // Disable vibrate
                    if (mp != null) {
                        mp.pause();
                    }

                }
            }
        });
        p13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(meresarpesada);

                } else {
                    // Disable vibrate
                    if (mp != null) {
                        mp.pause();
                    }

                }
            }
        });

        return view;
    }

    protected void managerofsound(String str) {
        if (mp != null) {
            mp.reset();
            mp.release();
        }
        if (str == jainidance)
            mp = MediaPlayer.create(getActivity(), R.raw.jainidance);
        else if (str == janmbhumi)
            mp = MediaPlayer.create(getActivity(), R.raw.janmbhumi);
        else if (str == mereguru)
            mp = MediaPlayer.create(getActivity(), R.raw.mereguru);
        else if (str == teertheshwar)
            mp = MediaPlayer.create(getActivity(), R.raw.teertheshwar);

        else if (str == tumhiho)
            mp = MediaPlayer.create(getActivity(), R.raw.tumhiho);
        else if (str == meresarpesada)
            mp = MediaPlayer.create(getActivity(), R.raw.meresarpesada);
        mp.start();
    }

    @Override
    public void doBack() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }
}


