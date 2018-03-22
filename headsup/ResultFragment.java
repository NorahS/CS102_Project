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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


public class ResultFragment extends Fragment{

    private Button replayBtn;
   // private Button exitBtn;

    //constructor
    public ResultFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_result, container, false);
        Intent intent = getActivity().getIntent();
        GridView list = (GridView)rootView.findViewById(R.id.gridViewres);
        TextView score = (TextView) rootView.findViewById(R.id.score);
        Player playerResult = new Player(RoundsFragment.sP);
       score.setText (score.getText()+Integer.toString(playerResult.getScore()));
        playerResult.addS("F");
        ResultAdaptor adpr = new ResultAdaptor(getContext(),playerResult.getPlayed(),playerResult.getState());
        list.setAdapter(adpr);
        if(playerResult.getBoth())
            list.setVisibility(View.GONE);


        MediaPlayer mp = new MediaPlayer();

        VideoView videoView = (VideoView)rootView.findViewById(R.id.RecVid);
        MediaController mc = new MediaController(getContext());
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);

        videoView.setVideoPath("/sdcard/"+playerResult.getVideoName()+".mp4");


        videoView.requestFocus();
        videoView.start();
        replayBtn = (Button) rootView.findViewById(R.id.replayBtn);

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
//        exitBtn = (Button) rootView.findViewById(R.id.exitBtn);
//        exitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });

        return rootView;

    }




}