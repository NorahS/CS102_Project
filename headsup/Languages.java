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
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

public class Languages extends AppCompatActivity {

    private ButtonAdapter btns;
    private GridView gridview;
    private String chosen="";
    private String title;
    private MediaPlayer click;
    private Menu menu;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.cato_menu, menu);

        super.onCreateOptionsMenu(menu);
        this.menu =menu;
        updateMenu();

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.StatusBar));
        return true;


    }
    public void setBtns(ButtonAdapter btns) {
        this.btns = btns;
    }

    public void setGridview(GridView gridview) {
        this.gridview = gridview;
        click= MediaPlayer.create(this, R.raw.click);
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }

    public ButtonAdapter getBtns() {
        return btns;
    }

    public String getChosen() {
        return chosen;
    }

    public GridView getGridview() {
        return gridview;
    }

    public void setAdapter(){
        this.gridview.setAdapter(this.btns);
    }


    public void setListner(){
        this.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               String path = chosen + btns.getItem(position);
                click.start();
                Intent intent = new Intent(getApplication(), RoundsActivity.class)
                        .putExtra(Intent.EXTRA_TEXT,path);
                startActivity(intent);
            }
        });

    }
    public void updateMenu(){
        this.menu.findItem(R.id.catmen).setTitle(this.title);

    }
    public void setTit(String t){
      this.title=t;
    }
}



