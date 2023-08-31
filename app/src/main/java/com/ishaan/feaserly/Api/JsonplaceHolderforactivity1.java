package com.ishaan.feaserly.Api;

import androidx.core.app.ComponentActivity;

import com.ishaan.feaserly.Files.PostforViewpager;
import com.ishaan.feaserly.Signup;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonplaceHolderforactivity1 {
    @GET("guest/token")
    Call<PostforViewpager> getViewpager(@Header("Authorization") String Token);

    @POST("/auth/registration")
    Call<PostforViewpager> signup(@Header("Authorization") String Token, @Body RequestBody requestBody);

}
