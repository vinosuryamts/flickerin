package com.example.flickerin.Dashboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.flickerin.Dashboard.Models.DashboardModel;
import com.example.flickerin.R;

import java.util.List;

public class DashboardUserAdapter extends ArrayAdapter<DashboardModel> {

    private Context context;
    private List<DashboardModel> values;
    private int position;
    List<String> warehouseslist;

    public DashboardUserAdapter(Context context, List<DashboardModel> values) {
        super(context, android.R.layout.simple_list_item_1, values);

        this.context = context;
        this.values = values;
    }

    public String getFirstName(){
        String firstname = values.get(getPosition1()).getFirstname();
        return firstname;
    }

    public String getProfileImage(){
        return values.get(getPosition1()).getProfileimage();
    }

    private void setPosition(int currentposition) {
        this.position = currentposition;
    }

    public int getPosition1(){
        return this.position;
    }

    public List<String> getwarehouselist(){
        return values.get(getPosition1()).getWarehouses();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.dashboard_custom_spinner, parent, true);
        }

        TextView textView = (TextView) row.findViewById(R.id.text1);

        setPosition(position);
        DashboardModel item = values.get(position);
        warehouseslist = item.getWarehouses();

        return row;
    }
}

