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
import com.canteen.chandan.mcafeteria.model.rest.ApiCall;
import com.canteen.chandan.mcafeteria.model.rest.ApiClient;
import com.canteen.chandan.mcafeteria.Beans.DataMap;
import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabbedInnerThreeFragment extends Fragment {

    private View view;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<FoodDataMap> data;
    private static ApiCall apiCall;
    String dbcName="lunch";
    private FoodAdapter adapter;




    public TabbedInnerThreeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tabbed_inner_three, container, false);
        context=this.getContext();

        recyclerView=view.findViewById(R.id.t_three_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        apiCall= ApiClient.getRetrofit().create(ApiCall.class);
        Call<DataMap.DataList> call =apiCall.getJson(dbcName,0);
        call.enqueue(new Callback<DataMap.DataList>() {
            @Override
            public void onResponse(Call<DataMap.DataList> call, Response<DataMap.DataList> response) {
                DataMap.DataList sResponse=response.body();
                data=sResponse.getServerResponse();
                adapter=new FoodAdapter(getContext(),data,3,dbcName);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<DataMap.DataList> call, Throwable t) {

            }
        });


        return view;
    }

}
