/**
 * Wala Kalema
 *
 * Wala Kalema is  an Andriod game  (c) 2016-2017 held jointly by the individual
 * authors.
 *
 * Wala Kalema was implemented as a CS101 course project at Prince Sultan University by Norah Alsabti,Nora ALshaalan, Sara Hussain, Nouf Almoajel And Sara sweed.
 * Date:Spring 2016
 */
package n.headsup;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class MainFragment extends Fragment {


    //constructor
    public MainFragment(){}

    private Button arabicBtn;
    private Button englishBtn;
    private Button bothBtn;
    private MediaPlayer click;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);

        int width = getResources().getDisplayMetrics().widthPixels;
        int hei=getResources().getDisplayMetrics().heightPixels/3;
        click = MediaPlayer.create(getContext(), R.raw.click);

        arabicBtn = (Button) rootView.findViewById(R.id.arabicBtn);
       // arabicBtn.setBackgroundColor(Color.parseColor("#e6223b42"));
        arabicBtn.setHeight(hei);
        arabicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arabicBtn.setBackgroundColor(Color.parseColor("#e61e2d36"));
                click.start();
                Intent i = new Intent(getActivity(), ArabicActivity.class);
                startActivity(i);
                getActivity().recreate();

            }
        });


        englishBtn = (Button) rootView.findViewById(R.id.englishBtn);
        englishBtn.setHeight(hei);

        englishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                englishBtn.setBackgroundColor(Color.parseColor("#e61e2d36"));
                click.start();
                Intent i = new Intent(getActivity(), EnglishActivity.class);
                startActivity(i);
                getActivity().recreate();
            }
        });

        bothBtn = (Button) rootView.findViewById(R.id.bothBtn);
        bothBtn.setHeight(hei);
        bothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bothBtn.setBackgroundColor(Color.parseColor("#e61e2d36"));
                click.start();
                Intent i = new Intent(getActivity(), BothActivity.class);
                startActivity(i);
                getActivity().recreate();
            }
        });




        return rootView;


    }

}
