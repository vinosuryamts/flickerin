package com.example.flickerin.Dashboard.Retrofit;

import com.example.flickerin.Dashboard.Models.DashboardModel;
import com.example.flickerin.Dashboard.Models.SampleModel1;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ApiInstance {

    //@GET("users/{user}/repos")
    //Call<List<SampleModel>> getHeroes(@Path("user")String user);

    @GET("cats/db.php")
    Call<List<SampleModel1>> getHeroes1();

    @GET("cats/dashboard/assignedwarehouse.php")
    Call<List<DashboardModel>> getDashboard();
}
