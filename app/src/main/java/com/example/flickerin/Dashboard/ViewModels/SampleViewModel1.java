package com.example.flickerin.Dashboard.ViewModels;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.flickerin.Dashboard.Models.SampleModel1;
import com.example.flickerin.Dashboard.Retrofit.ApiInstance;
import com.example.flickerin.Dashboard.Retrofit.RetrofitInstance;
import com.example.flickerin.utilities.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class SampleViewModel1 extends ViewModel {

    private MutableLiveData<List<SampleModel1>> partList;
    public Context context;

    public SampleViewModel1(){
        partList = new MutableLiveData<>();
    }

    public void setContext(Context context){
        this.context = context;
    }

    public MutableLiveData<List<SampleModel1>> getHeroes() {
        //if the list is null
        if (partList == null) {
            partList = new MutableLiveData<List<SampleModel1>>();
            //we will load it asynchronously from server in this method
            loadParts();
        }

        //finally we will return the list
        return partList;
    }


    public void loadParts() {

        try {
            RetrofitInstance.setBaseUrl(Util.getProperty("url",context));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApiInstance client = RetrofitInstance.getRetrofitClient().create(ApiInstance.class);

        //this is where we call the method fromRetrofitClient.
        // I passed my ID from github for getting data. Try your own

        Call<List<SampleModel1>> call =client.getHeroes1();
        //Call<List<SampleModel1>> call =client.getHeroes("azemZejnil");

        call.enqueue(new Callback<List<SampleModel1>>() {
            @Override
            public void onResponse(Call<List<SampleModel1>> call, Response<List<SampleModel1>> response) {
                List<SampleModel1>repos= response.body();
                // this is where we handle the response ofc
                partList.setValue(response.body());
                //listView.setAdapter(new GitHubRepoAdapter(Hero.this,repos));
            }

            @Override
            public void onFailure(Call<List<SampleModel1>> call, Throwable t) {
                partList.setValue(null);
            }

        });
    }


}
