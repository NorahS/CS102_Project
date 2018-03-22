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
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RoundsFragment extends CameraEx{


    private AssetManager mngr;
    private long startTime = 0;
    private final int timePeroid=50+3;
    private TextView timer,counter, condition;
    static Player sP;
    private boolean recording =false;
    private boolean isSong= false;
    //constructor
    public RoundsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        rootView = super.onCreateView(inflater, container, savedInstanceState);

        Intent intent = getActivity().getIntent();
        startTime =System.currentTimeMillis();
        timer = (TextView)rootView.findViewById(R.id.Timer);
        counter = (TextView)rootView.findViewById(R.id.counter);
        condition= (TextView)rootView.findViewById(R.id.condition);
        condition.setVisibility(View.GONE);



        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String category = intent.getStringExtra(Intent.EXTRA_TEXT);
            if(category.split("/")[0].equals("Random"))
                super.getPlayer().setBoth(true);
            if (category.equals("English/Songs") || category.equals("Random/Songs")|| category.equals("Arabic/3")){
                isSong = true;
                condition.setText("You Can Hmmm The Song!");
            }
            else if(category.equals("English/Celebrities")||category.equals("Random/Celebrities")|| category.equals("Arabic/6")){
                isSong = true;
                condition.setText("You Can use 3 Words!");
            }
            this.mngr = getContext().getAssets();
            HelperFunctions.play(this, category, mngr);


        }

        timerHandler.postDelayed(timerRunnable, 0);

        return rootView;

    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            int sec  = (int)((System.currentTimeMillis() - startTime)/1000);
            sec = timePeroid-sec;
            if(sec >=timePeroid-3){
                String str = (sec-50==0)?"Start":""+(sec-50);
                counter.setText(str);
            }


           if(!recording && sec == timePeroid-4){
               counter.setText("");
               startRoc();
                recording=true;
               if( isSong ){
                   condition.setVisibility(View.VISIBLE);
               }
            }
            else if(sec== 0|| HelperFunctions.Done ){
                timerHandler.removeCallbacks(timerRunnable);
                setSP();
                Intent i = new Intent(getActivity(), ResultActivity.class);
                startActivity(i);
            }
            timer.setText(String.format("%d", sec));
            if(isSong && sec <= timePeroid-11){
                condition.setVisibility(View.GONE);
            }
            timerHandler.postDelayed(this, 500);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        unsetIsSet();
        timerHandler.removeCallbacks(timerRunnable);
    }
public void setSP(){
    sP = new Player(super.getPlayer());
    setPlayer();
    super.stoprRecording();
    recording=false;
}
private  void startRoc(){
    this.addToP(HelperFunctions.next());
    super.setIsSet();
    super.startRecorder();
}

}
