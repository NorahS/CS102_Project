/**
 * Wala Kalema
 *
 * Wala Kalema is an Andriod game (c) 2016-2017 held jointly by the individual
 * authors.
 *
 * Wala Kalema was implemented as a CS101 course project at Prince Sultan University by Norah Alsabti
 * ,Nora ALshaalan, Sara Hussain, Nouf Almoajel And Sara sweed.
 * Date:Spring 2016
 */
package n.headsup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

public class ArabicActivity extends Languages {




    static final String categories[] ={"7", "2", "6", "1", "3","5","4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arabic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTit("");
        setSupportActionBar(toolbar);


        String list[] ={"5","Random", "4", "6", "2", "3","7","1"};
        int ids[] ={R.drawable.ptrial, R.drawable.rtrial, R.drawable.pltrial,R.drawable.ctrial,R.drawable.tvtrial, R.drawable.strial,R.drawable.actrial,R.drawable.mtrial};
        this.setBtns(new ButtonAdapter(this, list, ids));
        this.setGridview((GridView) findViewById(R.id.menu3));
        this.setChosen("Arabic/");
        this.setTit("A r a b i c");
        this.setAdapter();
        this.setListner();


    }


}
