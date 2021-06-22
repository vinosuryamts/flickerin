package com.example.flickerin.Dashboard.UI;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.flickerin.Dashboard.Adapters.DashboardUserAdapter;
import com.example.flickerin.Dashboard.Models.DashboardModel;
import com.example.flickerin.Dashboard.ViewModels.Dashboard_user_model;
import com.example.flickerin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DahboardActivity extends AppCompatActivity {

    AppCompatImageView  Logout;
    AppCompatImageView  inventorydashboardicon,shipmentdashboardicon,pickicon,shipicon;
    TextView            inventorydashboardmarker,shipmentdashboardmarker,pickmarker,shipmarker,username,blinkingimage,blinkingname,blinkimageview;
    AppCompatSpinner    warehousespinner;
    CircleImageView     userprofileicon;

    Dialog mydialog;


    List<DashboardModel> dashList;
    DashboardUserAdapter adapter1;
    ObjectAnimator objanim,objanim1,objanim2;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard_a_inventory_dashboard);

        Logout                      = (AppCompatImageView) findViewById(R.id.logout);
        inventorydashboardicon      = (AppCompatImageView) findViewById(R.id.inventorydashboardicon);
        shipmentdashboardicon       = (AppCompatImageView) findViewById(R.id.shipmentdashboardicon);
        pickicon                    = (AppCompatImageView) findViewById(R.id.pickicon);
        shipicon                    = (AppCompatImageView) findViewById(R.id.shipicon);
        userprofileicon             = (CircleImageView) findViewById(R.id.userprofileicon);
        blinkingimage               = (TextView) findViewById(R.id.blinkingimage);
        blinkingname                = (TextView) findViewById(R.id.blinkingname);
        blinkimageview              = (TextView) findViewById(R.id.blinkimageview);
        inventorydashboardmarker    = (TextView) findViewById(R.id.inventorydashboardmarker);
        shipmentdashboardmarker     = (TextView) findViewById(R.id.shipmentdashboardmarker);
        pickmarker                  = (TextView) findViewById(R.id.pickmarker);
        shipmarker                  = (TextView) findViewById(R.id.shipmarker);
        username                    = (TextView) findViewById(R.id.username);
        warehousespinner            = (AppCompatSpinner) findViewById(R.id.warehousespinner);


        //Setting blinking effects for waiting period time
        objanim = objanim.ofInt(blinkingimage,"backgroundColor",getColor(R.color.mildwhite),getColor(R.color.darkwhite));
        objanim.setDuration(1500);
        objanim.setEvaluator(new ArgbEvaluator());
        objanim.setRepeatMode(ValueAnimator.REVERSE);
        objanim.setRepeatCount(ValueAnimator.INFINITE);
        objanim.start();

        objanim1 = objanim.ofInt(blinkingname,"backgroundColor",getColor(R.color.mildwhite),getColor(R.color.darkwhite));
        objanim1.setDuration(1000);
        objanim1.setEvaluator(new ArgbEvaluator());
        objanim1.setRepeatMode(ValueAnimator.REVERSE);
        objanim1.setRepeatCount(ValueAnimator.INFINITE);
        objanim1.start();

        objanim2 = objanim.ofInt(blinkimageview,"backgroundColor",getColor(R.color.mildwhite),getColor(R.color.darkwhite));
        objanim2.setDuration(2000);
        objanim2.setEvaluator(new ArgbEvaluator());
        objanim2.setRepeatMode(ValueAnimator.REVERSE);
        objanim2.setRepeatCount(ValueAnimator.INFINITE);
        objanim2.start();


        Dashboard_user_model model = ViewModelProviders.of(this).get(Dashboard_user_model.class);
        model.setContext(this,this);
        model.getDashboard().observe(this, new Observer<List<DashboardModel>>() {
            @Override
            public void onChanged(@Nullable List<DashboardModel> dashwebList) {
                if(dashwebList != null) {
                    dashList = dashwebList;
                    adapter1 = new DashboardUserAdapter(DahboardActivity.this,dashList);
                    username.setText(adapter1.getFirstName());
                    Glide.with(DahboardActivity.this)
                            .load(adapter1.getProfileImage())
                            .apply(RequestOptions.skipMemoryCacheOf(true))
                            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                            .into(userprofileicon);

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
                        showPopup(DahboardActivity.this,"Application Error","Not a warehouse user. Please logout");
                    }

                }
            }
        });

        model.loadDashboard();



        inventorydashboardicon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(R.color.appfontcolor);
                shipmentdashboardmarker.setBackgroundResource(Color.TRANSPARENT);
                pickmarker.setBackgroundResource(Color.TRANSPARENT);
                shipmarker.setBackgroundResource(Color.TRANSPARENT);
            }
        });

        shipmentdashboardicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(Color.TRANSPARENT);
                shipmentdashboardmarker.setBackgroundResource(R.color.appfontcolor);
                pickmarker.setBackgroundResource(Color.TRANSPARENT);
                shipmarker.setBackgroundResource(Color.TRANSPARENT);
            }
        });

        pickicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(Color.TRANSPARENT);
                shipmentdashboardmarker.setBackgroundResource(Color.TRANSPARENT);
                pickmarker.setBackgroundResource(R.color.appfontcolor);
                shipmarker.setBackgroundResource(Color.TRANSPARENT);
            }
        });

        shipicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorydashboardmarker.setBackgroundResource(Color.TRANSPARENT);
                shipmentdashboardmarker.setBackgroundResource(Color.TRANSPARENT);
                pickmarker.setBackgroundResource(Color.TRANSPARENT);
                shipmarker.setBackgroundResource(R.color.appfontcolor);
            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(DahboardActivity.this,"Logout","Click on OK to Logout, else click on cancel to stay");
            }
        });

    }

    public void disableloaders(){
        objanim.cancel();
        objanim1.cancel();
        objanim2.cancel();
        blinkimageview.setVisibility(View.INVISIBLE);
        blinkingname.setVisibility(View.INVISIBLE);
        blinkingimage.setVisibility(View.INVISIBLE);
        username.setVisibility(View.VISIBLE);
        warehousespinner.setVisibility(View.VISIBLE);
        userprofileicon.setVisibility(View.VISIBLE);
    }

    public void showPopup(Context context, String titleString, String messageString){
        mydialog = new Dialog(context);
        TextView title,message;
        Button okbutton, cancelbutton;

        mydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mydialog.setContentView(R.layout.custom_message_popup);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        okbutton = (Button) mydialog.findViewById(R.id.okbutton);
        cancelbutton = (Button) mydialog.findViewById(R.id.cancelbutton);
        title = (TextView) mydialog.findViewById(R.id.title);
        message = (TextView) mydialog.findViewById(R.id.message);

        title.setText(titleString);
        message.setText(messageString);



        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });

        mydialog.show();
    }

}
