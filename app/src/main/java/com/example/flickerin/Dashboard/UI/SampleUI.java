package com.example.flickerin.Dashboard.UI;

import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.flickerin.Dashboard.Adapters.SampleListViewAdapter1;
import com.example.flickerin.Dashboard.Models.SampleModel1;
import com.example.flickerin.Dashboard.ViewModels.SampleViewModel1;
import com.example.flickerin.R;

import java.util.List;

public class SampleUI extends AppCompatActivity {

    private ListView listView;

    List<SampleModel1> partList;

    SampleListViewAdapter1 adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        listView= (ListView)findViewById(R.id.listView);


        SampleViewModel1 model = ViewModelProviders.of(this).get(SampleViewModel1.class);
        //model.setContext(this);
        model.getHeroes().observe(this, new Observer<List<SampleModel1>>() {
            @Override
            public void onChanged(@Nullable List<SampleModel1> partwebList) {
                if(partwebList != null) {
                    partList = partwebList;
                    adapter1 = new SampleListViewAdapter1(SampleUI.this,partList);
                    listView.setAdapter(adapter1);
                }else{
                    Toast.makeText(SampleUI.this, "Invalid Request", Toast.LENGTH_LONG).show();
                }
            }
        });

        model.loadParts();


    }
}