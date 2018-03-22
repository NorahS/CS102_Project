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

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

public class BothActivity extends Languages {

    private String chosen = "Random/";
    //set a getter
     static final String categories[] = {"7", "Shows", "Celebrities", "Movies", "Songs","5", "4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        String list[] = {"7", "Random","Shows", "Celebrities", "Movies", "Songs"};
        int[] ids= {R.drawable.eact, R.drawable.erandom, R.drawable.eshows, R.drawable.ecelebs, R.drawable.emovies, R.drawable.esongs};
        this.setBtns(new ButtonAdapter(this ,list,ids));
        setGridview((GridView) findViewById(R.id.menu3));
        this.setChosen("Random/");
        this.setTit("B O T H");
        this.setAdapter();
        this.setListner();





    }




}
