package com.canteen.chandan.mcafeteria;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;

public class FragmentHolder extends AppCompatActivity implements FoodClickedFragment.GetData {

    private FragmentTransaction ft;
    private FoodDataMap dataMap;
    private String dbcName;
    private String to_Go = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        this.to_Go = getIntent().getStringExtra("flow-id");
        mToGo(this.to_Go);
    }

    public void mToGo(String to_Go) {

        switch (to_Go) {

            case "%F%A%":
                dbcName = getIntent().getStringExtra("dbcName");
                dataMap = getIntent().getParcelableExtra("foodDataMap");
                doTransaction(new FoodClickedFragment());
                break;

        }

    }


    private void doTransaction(Fragment frag) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_holder, frag).commit();
    }



    @Override
    public String getDBC() {
        return dbcName;
    }

    public FoodDataMap getFoodDataMap() {
        return dataMap;
    }
}
