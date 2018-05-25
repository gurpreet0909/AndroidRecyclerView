package com.example.gurpreetsingh.retro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gurpreetsingh on 04/01/18.
 */

public interface ApiInterface {


      @GET("users")
      Call<List<APITime>>getMyView();
}
