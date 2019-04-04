package com.canteen.chandan.mcafeteria.Beans;

import com.google.gson.annotations.SerializedName;

public class Reviews {



    @SerializedName("f_id")
    private int f_id;

    @SerializedName("cus_id")
    private int cus_id;

    @SerializedName("ratings")
    private String ratings;

    @SerializedName("comment")
    private String comment;

    @SerializedName("c_datetime")
    private String c_datetime;

    @SerializedName("cus_name")
    private String cus_name;

    @SerializedName("cus_image_url")
    private String cus_image_url;








    public String getCus_image_url() {
        return cus_image_url;
    }

    public void setCus_image_url(String cus_image_url) {
        this.cus_image_url = cus_image_url;
    }



    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getC_datetime() {
        return c_datetime;
    }

    public void setC_datetime(String c_datetime) {
        this.c_datetime = c_datetime;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }
}
