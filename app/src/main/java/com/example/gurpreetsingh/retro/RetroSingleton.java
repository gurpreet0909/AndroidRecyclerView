package com.example.gurpreetsingh.retro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by gurpreetsingh on 03/01/18.
 */

public class RetroSingleton {

    public static String BASE_URL  = "https://api.github.com/";


   // public  static MyApitEndPoints myApitEndPoints = null;

    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return  retrofit;
    }


    /*
    public static MyApitEndPoints getMyApitEndPoints() {


        if (myApitEndPoints == null){



            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            myApitEndPoints = retrofit.create(MyApitEndPoints.class);
        }

        return  myApitEndPoints;
    }
     */

    public interface  MyApitEndPoints{

        @GET("users")
        Call<List<APITime>> getMyView();
    }

}
