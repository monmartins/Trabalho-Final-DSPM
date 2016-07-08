package br.ufc.dc.dspm.balancobrasil.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndpointInterface {
    String BASE_URL = "http://192.168.1.8:8000/";

    /* @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);*/


    @POST("/query")
    Call<Integer> getQuery(@Body QueryTransference queryTransferenceuery);
    @POST("/feed")
    Call<Integer> getFeed(@Body QueryFeed queryFeed);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
}

