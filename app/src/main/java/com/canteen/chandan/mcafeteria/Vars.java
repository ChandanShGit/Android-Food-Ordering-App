package com.canteen.chandan.mcafeteria;

public class Vars {
    public static final String DB_NAME="Fav_DB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "fav_list";
    public static final String COLUMN_FID = "food_id";
    public static final String COLUMN_NAME = "food_name";
    public static final String COLUMN_DESC = "food_desc";
    public static final String COLUMN_PRICE = "food_price";
    public static final String COLUMN_RATING = "food_rating";



    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "("
            + COLUMN_FID + " LONG PRIMARY KEY,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " FLOAT,"
            + COLUMN_DESC + " TEXT,"
            + COLUMN_RATING + " REAL"
            + ")";

}