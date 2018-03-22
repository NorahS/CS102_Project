package n.headsup;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Norah on 4/2/16 AD.
 */

public class ResultAdaptor extends BaseAdapter {


    private Context context;
    private ArrayList<String> playedlist, statelist;

    public ResultAdaptor(Context context, ArrayList<String> played, ArrayList<String> state) {
        super();
        this.context = context;
        this.playedlist = played;
        this.statelist = state;
    }

    @Override
    public int getCount() {
        return playedlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.text_adapter, parent, false);
        TextView t = (TextView)rowView.findViewById(R.id.result);
        t.setText(playedlist.get(position));
        if(statelist.get(position).equals("T"))
            t.setTextColor(Color.parseColor("#95c890"));

        else
            t.setTextColor(Color.parseColor("#beef7277"));
        return rowView;
    }

}
