package com.example.pial.newovilist;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Pial on 06-Apr-17.
 */

public interface ApiInterface {



    @POST("login.php")
    @FormUrlEncoded
    Call<Signin>loginPost(@Field("email_token") String email_token,@Field("pass") String pass);

    @POST("signup.php")
    @FormUrlEncoded
    Call<Signin>signUpPost(@Field("email_token") String email_token,
                           @Field("pass") String pass,
                           @Field("name") String name,
                           @Field("gender") String gender,
                           @Field("birth_date") String birth_date,
                           @Field("phone") String phone,
                           @Field("alt_phone") String alt_phone,
                           @Field("addr") String addr);

}
