package com.canteen.chandan.mcafeteria.db.in.cafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;
import com.canteen.chandan.mcafeteria.Vars;

import java.util.ArrayList;

public class DBXXX extends SQLiteOpenHelper {

    private ArrayList<FoodDataMap> allFav;



    public DBXXX(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBXXX(@Nullable Context context) {
        super(context, Vars.DB_NAME, null, Vars.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Vars.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addFavID(FoodDataMap dataMap) {

        long result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {

                   ContentValues data = new ContentValues();
                   data.put(Vars.COLUMN_FID, dataMap.getId());
                   data.put(Vars.COLUMN_NAME, dataMap.getName());
                   data.put(Vars.COLUMN_PRICE, dataMap.getPrice());
                   data.put(Vars.COLUMN_DESC, dataMap.getDesc());
                   data.put(Vars.COLUMN_RATING, dataMap.getRating());
                   result = db.insert(Vars.TABLE_NAME, null, data);

        } catch (Exception e) {

        } finally {
            db.close();
        }

        if (result < 0) {
            return false;
        }
        return true;
    }


    public boolean removeFavId(long id) {
        long result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            //if(isFav(id)) {
                result = db.delete(Vars.TABLE_NAME, Vars.COLUMN_FID + "=" + id, null);
            //}
            if(result<0) {
                return false;
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
        return true;
    }

    public ArrayList<FoodDataMap> fetchAllFav(){
        SQLiteDatabase db=this.getReadableDatabase();
        allFav=new ArrayList<>();
        try{
            Cursor cr=db.rawQuery("SELECT * FROM "+ Vars.TABLE_NAME,null);
            if(cr.moveToFirst()){
                do{
                    FoodDataMap dataMap=new FoodDataMap();
                    dataMap.setId(String.valueOf(cr.getLong(cr.getColumnIndexOrThrow(Vars.COLUMN_FID))));
                    dataMap.setName(String.valueOf(cr.getString(cr.getColumnIndexOrThrow(Vars.COLUMN_NAME))));
                    dataMap.setDesc(String.valueOf(cr.getString(cr.getColumnIndexOrThrow(Vars.COLUMN_DESC))));
                    dataMap.setPrice(String.valueOf(cr.getFloat(cr.getColumnIndexOrThrow(Vars.COLUMN_PRICE))));
                    dataMap.setRating(String.valueOf(cr.getFloat(cr.getColumnIndexOrThrow(Vars.COLUMN_RATING))));
                    allFav.add(dataMap);

                }while(cr.moveToNext());
                return allFav;
            }else{
                return null;
            }

        }catch(Exception e){

        }
        return null;
    }


    public boolean isFav(long id){
        SQLiteDatabase db=this.getReadableDatabase();
        try{
            Cursor cr=db.rawQuery("SELECT * FROM "+ Vars.TABLE_NAME,null);
            if(cr.moveToFirst()){
                do{
                    long tempId=cr.getLong(cr.getColumnIndexOrThrow(Vars.COLUMN_FID));
                    if(id==tempId){
                        return true;
                    }

                }while(cr.moveToNext());

            }

        }catch(Exception e){

        }finally {
            db.close();
        }

        return false;
    }
}
