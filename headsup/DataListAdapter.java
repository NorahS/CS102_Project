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

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;


class DataListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mThumbIds;
    private String[] colors;

    DataListAdapter( Context c,String[] btns, String colors[]) {
        mContext = c;
        this.colors= colors;
        mThumbIds = btns;
    }


    public int getCount() {
        // TODO Auto-generated method stub
        return mThumbIds.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mThumbIds[arg0];
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;
        row = inflater.inflate(R.layout.button, parent, false);

        Button btn = (Button) row.findViewById(R.id.buttons);
        btn.setBackgroundColor(Color.parseColor(colors[position]));
        btn.setText(mThumbIds[position]);
        btn.setId(position);

        return (row);
    }


}