package com.canteen.chandan.mcafeteria.model.rest;


import com.canteen.chandan.mcafeteria.Beans.DataMap;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCall {


    @FormUrlEncoded
    @POST("login.php")
    Call<DataMap> Login(@Field("card_id") String CardId, @Field("pass") String Password);

    @FormUrlEncoded
    @POST("register.php")
    Call<DataMap> Register(@Field("name") String Name, @Field("id") String Id, @Field("contact") String Contact, @Field("username") String Username, @Field("password") String Password);

    @FormUrlEncoded
    @POST("json_food_map.php")
    Call<DataMap.DataList> getJson(@Field("dataName") String name, @Field("f_id") int id);

    @FormUrlEncoded
    @POST("orders.php")
    Call<DataMap> addOrder(@Field("f_id") long f_id,@Field("c_id") long c_id, @Field("size") int size, @Field("net_pay") double net_pay, @Field("pay_status") String p_status,@Field("del_status") String d_status);

    @FormUrlEncoded
    @POST("fetch_comments.php")
    Call<DataMap.CommentList> getComments(@Field("f_id") long f_id);


}
