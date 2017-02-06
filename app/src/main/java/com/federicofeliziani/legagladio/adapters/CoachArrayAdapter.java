package com.federicofeliziani.legagladio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.federicofeliziani.legagladio.R;
import com.federicofeliziani.legagladio.entities.Coach;
import com.federicofeliziani.legagladio.entities.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f3l1x on 1/31/2017.
 */

public class CoachArrayAdapter extends ArrayAdapter<Coach> {

    private ArrayList<Coach> objects;

    public CoachArrayAdapter(Context context, int resource, ArrayList<Coach> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

        Coach c = objects.get(position);
        if (c != null) {
            TextView mainTextView = (TextView) v.findViewById(R.id.listItemMainText);
            TextView secondaryTextView = (TextView) v.findViewById(R.id.listItemSecondaryText);
            if(mainTextView != null) {
                mainTextView.setText(c.getName());
            }
            if (secondaryTextView != null) {
                secondaryTextView.setText(c.getNafNick());
            }
        }
        return v;
    }
}
