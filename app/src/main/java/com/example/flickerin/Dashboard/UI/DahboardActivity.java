package com.example.flickerin.Dashboard.UI;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.motion.widget.Animatable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.flickerin.Dashboard.Adapters.DashboardUserAdapter;
import com.example.flickerin.Dashboard.Adapters.SampleListViewAdapter1;
import com.example.flickerin.Dashboard.Models.DashboardModel;
import com.example.flickerin.Dashboard.Models.SampleModel1;
import com.example.flickerin.Dashboard.ViewModels.SampleViewModel1;
import com.example.flickerin.R;

import java.util.ArrayList;
import java.util.List;

public class DahboardActivity extends AppCompatActivity {

    AppCompatImageView  Logout;
    AppCompatImageView  inventorydashboardicon,shipmentdashboardicon,pickicon,shipicon;
    TextView            inventorydashboardmarker,shipmentdashboardmarker,pickmarker,shipmarker,username,blinkingimage;
    AppCompatSpinner    warehousespinner;


    List<DashboardModel> dashList;
    DashboardUserAdapter adapter1;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard_a_inventory_dashboard);

        Logout                      = (AppCompatImageView) findViewById(R.id.logout);
        inventorydashboardicon      = (AppCompatImageView) findViewById(R.id.inventorydashboardicon);
        shipmentdashboardicon       = (AppCompatImageView) findViewById(R.id.shipmentdashboardicon);
        pickicon                    = (AppCompatImageView) findViewById(R.id.pickicon);
        shipicon                    = (AppCompatImageView) findViewById(R.id.shipicon);
        blinkingimage               = (TextView) findViewById(R.id.blinkingimage);
        inventorydashboardmarker    = (TextView) findViewById(R.id.inventorydashboardmarker);
        shipmentdashboardmarker     = (TextView) findViewById(R.id.shipmentdashboardmarker);
        pickmarker                  = (TextView) findViewById(R.id.pickmarker);
        shipmarker                  = (TextView) findViewById(R.id.shipmarker);
        username                    = (TextView) findViewById(R.id.username);
        warehousespinner            = (AppCompatSpinner) findViewById(R.id.warehousespinner);


        objectAnimator = ObjectAnimator.ofInt(blinkingimage,"backgroundColor",getColor(R.color.mildwhite),getColor(R.color.white));
        objectAnimator.setDuration(1500);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();



        SampleViewModel1 model = ViewModelProviders.of(this).get(SampleViewModel1.class);
        model.setContext(this,this);
        model.getDashboard().observe(this, new Observer<List<DashboardModel>>() {
            @Override
            public void onChanged(@Nullable List<DashboardModel> dashwebList) {
                if(dashwebList != null) {
                    dashList = dashwebList;
                    adapter1 = new DashboardUserAdapter(DahboardActivity.this,dashList);
                    username.setText(adapter1.getFirstName());

                    List<String> spinnerArray = new ArrayList<>();
                    if (adapter1.getwarehouselist().size() > 0) {
                        for (int i = 0; i < adapter1.getwarehouselist().size(); i++) {
                            spinnerArray.add(adapter1.getwarehouselist().get(i));
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                DahboardActivity.this,
                                R.layout.dashboard_custom_spinner,
                                spinnerArray
                        );

                        warehousespinner.setAdapter(adapter);

                    }else {
                        Toast.makeText(DahboardActivity.this, "Not a warehouse user. Please logout", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(DahboardActivity.this, "Invalid Request", Toast.LENGTH_LONG).show();
                }
            }
        });

        model.loadDashboard();


        inventorydashboardicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(R.color.applightcolor);
                shipmentdashboardmarker.setBackgroundResource(R.color.appthemecolot);
                pickmarker.setBackgroundResource(R.color.appthemecolot);
                shipmarker.setBackgroundResource(R.color.appthemecolot);
            }
        });

        shipmentdashboardicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(R.color.appthemecolot);
                shipmentdashboardmarker.setBackgroundResource(R.color.applightcolor);
                pickmarker.setBackgroundResource(R.color.appthemecolot);
                shipmarker.setBackgroundResource(R.color.appthemecolot);
            }
        });

        pickicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(R.color.appthemecolot);
                shipmentdashboardmarker.setBackgroundResource(R.color.appthemecolot);
                pickmarker.setBackgroundResource(R.color.applightcolor);
                shipmarker.setBackgroundResource(R.color.appthemecolot);
            }
        });

        shipicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(R.color.appthemecolot);
                shipmentdashboardmarker.setBackgroundResource(R.color.appthemecolot);
                pickmarker.setBackgroundResource(R.color.appthemecolot);
                shipmarker.setBackgroundResource(R.color.applightcolor);
            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DahboardActivity.this,"Clicked on Logout",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void disableloaders(){
        objectAnimator.cancel();
        blinkingimage.setVisibility(View.INVISIBLE);
        warehousespinner.setVisibility(View.VISIBLE);
    }

}
