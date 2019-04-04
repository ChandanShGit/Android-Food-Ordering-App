package com.canteen.chandan.mcafeteria.Beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodDataMap implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("actual_price")
    @Expose
    private String actualPrice;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("desc")
    @Expose
    private String desc;

    public FoodDataMap(){

    }

    public FoodDataMap(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        actualPrice = in.readString();
        rating = in.readString();
        imageUrl = in.readString();
        desc = in.readString();
    }

    public static final Creator<FoodDataMap> CREATOR = new Creator<FoodDataMap>() {
        @Override
        public FoodDataMap createFromParcel(Parcel in) {
            return new FoodDataMap(in);
        }

        @Override
        public FoodDataMap[] newArray(int size) {
            return new FoodDataMap[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(actualPrice);
        parcel.writeString(rating);
        parcel.writeString(imageUrl);
        parcel.writeString(desc);
    }
}