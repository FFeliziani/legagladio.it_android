package com.federicofeliziani.legagladio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.federicofeliziani.legagladio.R;
import com.federicofeliziani.legagladio.entities.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f3l1x on 1/31/2017.
 */

public class TeamArrayAdapter extends ArrayAdapter<Team> {

    private ArrayList<Team> objects;

    public TeamArrayAdapter(Context context, int textViewResourceId, ArrayList<Team> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

        Team t = objects.get(position);
        if (t != null) {
            TextView mainTextView = (TextView) v.findViewById(R.id.listItemMainText);
            TextView secondaryTextView = (TextView) v.findViewById(R.id.listItemSecondaryText);
            if(mainTextView != null) {
                mainTextView.setText(t.getName());
            }
            if (secondaryTextView != null) {
                secondaryTextView.setText(t.getCoachName());
            }
        }
        return v;
    }
}
