package com.example.flickerin.Dashboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.flickerin.Dashboard.Models.SampleModel1;
import com.example.flickerin.R;

import java.util.List;

public class SampleListViewAdapter1 extends ArrayAdapter<SampleModel1> {

    private Context context;
    private List<SampleModel1> values;

    public SampleListViewAdapter1(Context context, List<SampleModel1> values) {
        super(context, android.R.layout.simple_list_item_1, values);

        this.context = context;
        this.values = values;
    }

    public void setValues (List<SampleModel1> values){
        this.values =values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_sample_listview_items, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.textView);

        SampleModel1 item = values.get(position);
        String message = item.getpartcode();
        textView.setText(message);

        return row;
    }
}

