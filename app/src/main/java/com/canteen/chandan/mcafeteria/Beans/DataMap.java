package com.canteen.chandan.mcafeteria.Beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataMap {



    @SerializedName("response")
    private String Response;
    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }

    public class DataList{

        @SerializedName("Server_Response")
        private ArrayList<FoodDataMap> serverResponse;

        public DataList(ArrayList<FoodDataMap> serverResponse) {
            this.serverResponse = serverResponse;
        }

        public ArrayList<FoodDataMap> getServerResponse() {
            return serverResponse;
        }
    }

    public class CommentList{

        @SerializedName("reviews")
        private ArrayList<Reviews> reviews;


        public CommentList(ArrayList<Reviews> reviews){
            this.reviews=reviews;
        }

        public ArrayList<Reviews> getReviews() {
            return reviews;
        }
    }




}