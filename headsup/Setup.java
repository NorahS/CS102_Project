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


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Setup extends Fragment {

    protected View rootView = null;
    private  static TextView text;
    private static ImageView randomIcon;
    protected Map langugae = new HashMap();
    private String colors[]= {"#be95c890","#beeed55e","#bef6c0a8","#beef7277","#beD88b7d"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.activity_rounds, container, false);
     //   rootView.setBackgroundColor(Color.parseColor(colors[(int) (Math.random() * colors.length)]));
        text = (TextView) rootView.findViewById(R.id.fullscreen_content);
        text.setBackgroundColor(Color.parseColor(colors[(int) (Math.random() * colors.length)]));
        randomIcon = (ImageView)rootView.findViewById(R.id.random);

        langugae.put("English", EnglishActivity.categories);
        langugae.put("Random", BothActivity.categories);
        langugae.put("Arabic", ArabicActivity.categories);

        return rootView;
    }

    protected static void setText(String s){
        text.setText(s);
    }

    protected void setBackground(){
        text.setBackgroundColor(Color.parseColor(colors[(int) (Math.random() * colors.length)]));
    }
    protected static void setCategories(int id){
        randomIcon.setImageResource(id);

    }
}