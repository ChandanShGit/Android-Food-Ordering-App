package com.canteen.chandan.mcafeteria.WorkHandlers;

import android.content.Context;
import android.widget.ImageView;

import com.canteen.chandan.mcafeteria.R;
import com.canteen.chandan.mcafeteria.db.in.cafe.DBXXX;
import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;

import java.util.ArrayList;

public class FavHandler {

    public static boolean addToFav(ImageView im,FoodDataMap dataMap, Context ctx){
        boolean result=false;

        if(im!=null){

            DBXXX dbxxx=new DBXXX(ctx);
            result=dbxxx.addFavID(dataMap);
            im.setBackgroundResource(R.drawable.fav_filled_icon_24dp);
        }
        return result;
    }


    public static boolean removeFav(ImageView im,long id,Context ctx){
        boolean result=false;

        if(im!=null){
            DBXXX dbxxx=new DBXXX(ctx);
            result=dbxxx.removeFavId(id);
            im.setBackgroundResource(R.drawable.fav_unfilled_icon_24dp);

        }
        return result;
    }


    public static boolean isFav(long id,Context ctx){
        DBXXX dbxxx=new DBXXX(ctx);
        return dbxxx.isFav(id);
    }

    public static ArrayList<FoodDataMap> allFav(Context ctx){
        return new DBXXX(ctx).fetchAllFav();
    }



}
