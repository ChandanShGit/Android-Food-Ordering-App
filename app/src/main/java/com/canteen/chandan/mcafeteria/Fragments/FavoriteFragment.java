package com.canteen.chandan.mcafeteria.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canteen.chandan.mcafeteria.Adapters.FoodAdapter;
import com.canteen.chandan.mcafeteria.R;
import com.canteen.chandan.mcafeteria.Vars;
import com.canteen.chandan.mcafeteria.WorkHandlers.FavHandler;
import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private View view=null;
    ArrayList<FoodDataMap> dataMaps=null;
    Context ctx;

    public FavoriteFragment() {

            }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view= inflater.inflate(R.layout.fragment_favorite, container, false);
        ctx=view.getContext();
        recyclerView=view.findViewById(R.id.favorite_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new FoodAdapter(ctx,FavHandler.allFav(ctx),4, Vars.DB_NAME));
        return view;
    }

}
