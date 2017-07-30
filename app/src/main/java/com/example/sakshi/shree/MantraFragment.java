package com.example.sakshi.shree;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MantraFragment extends Fragment implements MainActivity.OnBackPressedListener {
    Button b1;
    MediaPlayer mp = null;
    String namokar = "namokar";

    public MantraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mantra, container, false);
        b1 = (Button) view.findViewById(R.id.b1);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Play = ((ToggleButton) view).isChecked();

                if (Play) {
                    // Enable vibrate
                    managerofsound(namokar);

                } else {
                    // Disable vibrate
                    if(mp!=null){
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
        if (str == namokar)
            mp = MediaPlayer.create(getActivity(), R.raw.namokar);
        mp.start();
    }

    @Override
    public void doBack() {
        Intent intent=new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
}