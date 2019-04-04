package com.canteen.chandan.mcafeteria;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.chandan.mcafeteria.Adapters.CommentAdapter;
import com.canteen.chandan.mcafeteria.Beans.DataMap;
import com.canteen.chandan.mcafeteria.Beans.OrdersMap;
import com.canteen.chandan.mcafeteria.Beans.Reviews;
import com.canteen.chandan.mcafeteria.WorkHandlers.FavHandler;
import com.canteen.chandan.mcafeteria.model.rest.ApiCall;
import com.canteen.chandan.mcafeteria.model.rest.ApiClient;
import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class FoodClickedFragment extends Fragment {

    private View view;
    private Button oBtn;
    private TextView cd_name;
    private TextView cd_pay;
    private TextView cd_size_txt;
    private long customer_id;
    private TextView cd_del;
    private Button cd_change;
    private Button cd_confirm;
    private Button cd_cancle;
    private double net_pay=1;
    private LinearLayout review;
    private RecyclerView rv;
    private ArrayList<Reviews> cmtData;



    private ImageView favIconIv;
    private TextView cdTotalAmt;
    private TextView nameTv, descTv, priceTv;
    private RatingBar ratingBar;
    private static ApiCall apiCall;
    private FoodDataMap dataMap;
    private String f_name,f_desc;
    private ImageView f_image;
    private long f_id,f_price;
    private float f_ratings;
    Dialog dialog;
    private Context ctx;
    private String dbcName;   // checking purpose
    private GetData getData;



    interface GetData {
        String getDBC();
        FoodDataMap getFoodDataMap();
    }




    public void initialise() {
        ctx = view.getContext();
        getData = (GetData) ctx;
        dbcName = getData.getDBC();

        rv=view.findViewById(R.id.comment_rv);
        f_image=view.findViewById(R.id.fc_image) ;
        nameTv = view.findViewById(R.id.fc_name);
        descTv = view.findViewById(R.id.fc_desc);
        priceTv=view.findViewById(R.id.fc_price);
        ratingBar=view.findViewById(R.id.fc_rating);
        favIconIv = view.findViewById(R.id.fc_fav_icon);
        favIconIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operateFav();
            }
        });
        oBtn = view.findViewById(R.id.fc_order_now);
        oBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void getComments(){

        //static id change it
        Call<DataMap.CommentList> call=apiCall.getComments(12);
        call.enqueue(new Callback<DataMap.CommentList>() {
            @Override
            public void onResponse(Call<DataMap.CommentList> call, Response<DataMap.CommentList> response) {
                DataMap.CommentList cmtList=response.body();
                cmtData=cmtList.getReviews();
                rv.setLayoutManager(new LinearLayoutManager(ctx));
                rv.setAdapter(new CommentAdapter(getContext(),cmtData));
                Toast.makeText(ctx, "Success"+cmtList.getReviews().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataMap.CommentList> call, Throwable t) {
                Toast.makeText(ctx, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void assignData(FoodDataMap foodDataMap) {

        f_name=foodDataMap.getName();
        f_desc=foodDataMap.getDesc();
        f_ratings=Float.parseFloat(foodDataMap.getRating());
        f_id=Long.valueOf(foodDataMap.getId());
        f_price=Long.valueOf(foodDataMap.getPrice());
        Picasso.with(ctx).load(foodDataMap.getImageUrl()).placeholder(R.drawable.load).into(f_image);
        customer_id=Integer.valueOf(new PreConfig(ctx).readCardId());


        descTv.setText(f_desc);
        nameTv.setText(f_name);
        priceTv.setText("Price: "+f_price);
        ratingBar.setRating(f_ratings);
        TextView rate=view.findViewById(R.id.review_rate);
        TextView rv_user=view.findViewById(R.id.rv_user_name);
        rv_user.setText("@"+new PreConfig(ctx).readName());
        rate.setText(""+f_ratings);
        if(!FavHandler.isFav(f_id,ctx)){
            favIconIv.setBackgroundResource(R.drawable.fav_unfilled_icon_24dp);
        }
    }


    void operateFav(){
        if (FavHandler.isFav(f_id, ctx)) {
            FavHandler.removeFav(favIconIv, f_id, ctx);
            Toast.makeText(ctx, ( ("Removed Successfuly with id" + f_id)), Toast.LENGTH_SHORT).show();
        } else {
            FavHandler.addToFav(favIconIv,getData.getFoodDataMap(), ctx);
            Toast.makeText(ctx, ( ("Added Successfuly with id" + f_id)), Toast.LENGTH_SHORT).show();

        }
    }

    private void showDialog() {

        NumberPicker cd_size_np;
        dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.confirm_order_popup);
        cd_name=dialog.findViewById(R.id.cd_fname);
        cd_pay=dialog.findViewById(R.id.cd_amount);
        cd_size_txt=dialog.findViewById(R.id.cd_size);
        cd_del=dialog.findViewById(R.id.cd_delivery);
        cd_change=dialog.findViewById(R.id.cd_change_addr);
        cd_confirm=dialog.findViewById(R.id.cd_confirm_btn);
        cd_cancle=dialog.findViewById(R.id.cancle);
        cd_size_np=dialog.findViewById(R.id.cd_total_dish);

        cd_name.setText("Food: "+f_name);
        cd_size_txt.setText("Size: "+ 1);
        cd_pay.setText("Pay: "+f_price);

        cd_size_np.setMaxValue(20);
        cd_size_np.setMinValue(1);
        cd_size_np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                net_pay =(int)(f_price*newVal);
                cd_pay.setText("Pay: " + (f_price*newVal));
                cd_size_txt.setText("Size: " +numberPicker.getValue());
            }
        });

        dialog.show();

        cd_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                initiateOrder(net_pay);
            }
        });

    }



    private void initiateOrder(double net_pay) {

        OrdersMap ordersMap=new OrdersMap();
        ordersMap.setFood_id((int)f_id);
        ordersMap.setCustomer_id(Integer.valueOf(new PreConfig(ctx).readCardId()));
        ordersMap.setNet_pay((int)net_pay);
        ordersMap.setDeliverStatus("ND");
        ordersMap.setPaymentStatus("COD");
        Toast.makeText(ctx, "order confirmed", Toast.LENGTH_SHORT).show();

        Call call= apiCall.addOrder(ordersMap.getFood_id(),ordersMap.getCustomer_id(),(int)(net_pay/f_price),ordersMap.getNet_pay(),ordersMap.getPaymentStatus(),ordersMap.getDeliverStatus());
        call.enqueue(new Callback<DataMap>() {
            @Override
            public void onResponse(Call<DataMap> call, Response<DataMap> response) {
                Log.i("resp",response.body().getResponse());
            }

            @Override
            public void onFailure(Call<DataMap> call, Throwable t) {
                Log.i("resp","Fail");
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_food_clicked, container, false);
        apiCall = ApiClient.getRetrofit().create(ApiCall.class);
        initialise();
        assignData(getData.getFoodDataMap());
        getComments();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
