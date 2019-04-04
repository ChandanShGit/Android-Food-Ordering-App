package com.canteen.chandan.mcafeteria;

import android.content.Context;
import android.content.SharedPreferences;

public class PreConfig {

    private SharedPreferences sp;
    private Context ctx;
    private SharedPreferences.Editor editor;

    public PreConfig(Context ctx){
        this.ctx=ctx;
        sp=ctx.getSharedPreferences(ctx.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    String readName(){
        return sp.getString(ctx.getString(R.string.pref_name),"user");
    }

    public void writeName(String name){
        editor=sp.edit();
        editor.putString(ctx.getString(R.string.pref_name),name);
        editor.apply();
    }

    public void writeLoginStatus(boolean status){
        editor=sp.edit();
        editor.putBoolean(ctx.getString(R.string.pref_login_status),status);
        editor.apply();
    }

    boolean readLoginStatus(){
        return sp.getBoolean(ctx.getString(R.string.pref_login_status),false);
    }

    public void writeCardId(String Name){
        editor=sp.edit();
        editor.putString(ctx.getString(R.string.pref_user_name),Name);
        editor.apply();
    }

    public String readCardId(){
        return sp.getString(ctx.getString(R.string.pref_user_name),"");
    }

}
