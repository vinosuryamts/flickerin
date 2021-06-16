package com.example.flickerin.Dashboard.Retrofit;

import com.example.flickerin.Dashboard.Models.DashboardModel;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiInstance {

    //@GET("users/{user}/repos")
    //Call<List<SampleModel>> getHeroes(@Path("user")String user);

    @GET("cats/dashboard/assignedwarehouse.php")
    Call<List<DashboardModel>> getDashboard();
}
