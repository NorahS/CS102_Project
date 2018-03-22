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

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class HelperFunctions {

    static protected ArrayList<String> holder = new ArrayList<String>();
    static protected int[] usedInd;
    static protected ArrayList<Integer> randomIcon= new ArrayList<Integer>();
    protected static boolean isRandom;
    private static  int drawbleId[] = {R.drawable.act, R.drawable.tv, R.drawable.celeb, R.drawable.movie, R.drawable.music,
    R.drawable.quote, R.drawable.play};
    static boolean Done = false;
    private  static int n=1;

    static protected String next() {

        if (n == holder.size()) {
            Done = true;
        } else {
            int ranIndex;
            do {
                ranIndex = (int) (Math.random() * holder.size());
            } while (usedInd[ranIndex] == 1);
            usedInd[ranIndex] = 1;
            String str = holder.get(ranIndex);
            Setup.setText(str);
            n+=1;
            if (isRandom) {
                Setup.setCategories(randomIcon.get(ranIndex));
            }
            return str;
        }
        return "";
    }

    protected static void play(Setup obj,String category, AssetManager mngr) {

        BufferedReader cat = null;

        if ((category.split("/")[1]).equals("Random")) {
            try {
                Random(obj,category,mngr);
                isRandom=true;
            } catch (IOException e) {
            }

        } else {
            isRandom=false;
            try {

                cat = new BufferedReader(
                        new InputStreamReader(mngr.open(category + ".txt")));
            } catch (IOException e) {
                System.out.println("Error 1:\nFile Not Found");
            }
            try {
                //catogry = cat.readLine();}
                while (cat.ready()) {
                    HelperFunctions.holder.add(cat.readLine());
                }
            } catch (IOException e) {
                System.out.println(e);
            }

        }

       usedInd = new int[holder.size()];
    }

    protected static void Random(Setup obj,String path , AssetManager mngr) throws IOException {

        //  String list[] = EnglishActivity.catogries;
        path= path.split("/")[0];
        String[] list = (String[])obj.langugae.get(path);
        //i till we add actit out for english
        String folder = path.split("/")[0];
        BufferedReader cat=null;
        for (int i = 0; i != list.length; i++) {



            try {

                cat = new BufferedReader(
                        new InputStreamReader(mngr.open(path+"/"+list[i] + ".txt")));
            } catch (IOException e) {
                System.out.println("Error 1:\nFile Not Found");
            }
            try {

                while (cat.ready()) {
                    HelperFunctions.holder.add(cat.readLine());
                    randomIcon.add(drawbleId[i]);
                }
            } catch (IOException e) {
                System.out.println("Error101");
            }
            cat.close();
        }

    }

    protected static void reset(){
        holder.clear();
        randomIcon.clear();
        n =1;
        Done= false;
    }

}
