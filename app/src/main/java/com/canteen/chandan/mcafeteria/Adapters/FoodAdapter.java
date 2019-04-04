package com.canteen.chandan.mcafeteria.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.chandan.mcafeteria.Beans.FoodDataMap;
import com.canteen.chandan.mcafeteria.FragmentHolder;
import com.canteen.chandan.mcafeteria.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {


    private int type=1;
    private ArrayList<FoodDataMap> data;
    private boolean fav=false;
    private Context mCtx;
    private String dbcName;

    public FoodAdapter(Context ctx, ArrayList<FoodDataMap> data, int type, String dbcName) {
        this.data = data;
        this.type=type;
        if(type==4){
            this.type=3;
            this.fav=true;
        }
        this.dbcName=dbcName;
        mCtx=ctx;
    }



    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch(type){
            case 1:view= LayoutInflater.from(parent.getContext()).inflate(R.layout.holderlayout1,parent,false);
            break;
            case 2:view= LayoutInflater.from(parent.getContext()).inflate(R.layout.holderlayout2,parent,false);
            break;
            case 3:view= LayoutInflater.from(parent.getContext()).inflate(R.layout.holderlayout3,parent,false);
            break;
        }
            return new FoodViewHolder(view);

    }



    @Override
    public void onBindViewHolder(final FoodViewHolder holder, final int position) {
        //BINDING DATA WITH HOLDER

        switch(type){

            case 1 :
                holder.hl_one_name.setText(data.get(position).getName());
                holder.hl_one_desc.setText(data.get(position).getDesc());
                holder.hl_one_price.setText("Rs. " + data.get(position).getPrice());
                holder.hl_one_offer.setText(discount(Integer.parseInt(data.get(position).getPrice()),Integer.parseInt(data.get(position).getActualPrice())) + " off");
                holder.hl_one_rating.setRating(Float.parseFloat(data.get(position).getRating()));
                Picasso.with(mCtx).load(data.get(position).getImageUrl()).placeholder(R.drawable.load).into(holder.hl_one_image);
                break;

            case 2 :
                holder.hl_two_name.setText(data.get(position).getName());
                holder.hl_two_desc.setText(data.get(position).getDesc());
                holder.hl_two_price.setText("Rs. " + data.get(position).getPrice());
                holder.hl_two_offer.setText(discount(Integer.parseInt(data.get(position).getPrice()),Integer.parseInt(data.get(position).getActualPrice())) + " off");
                holder.hl_two_rating.setRating(Float.parseFloat(data.get(position).getRating()));
                Picasso.with(mCtx).load(data.get(position).getImageUrl()).placeholder(R.drawable.load).into(holder.hl_two_image);
                break;

            case 3:
                holder.hl_three_name.setText(data.get(position).getName());
                holder.hl_three_desc.setText(data.get(position).getDesc());
                holder.hl_three_price.setText("Rs. " + data.get(position).getPrice());
                holder.hl_three_offer.setText(discount(Integer.parseInt(data.get(position).getPrice()),Integer.parseInt(data.get(position).getActualPrice())) + " off");
                holder.hl_three_rating.setRating(Float.parseFloat(data.get(position).getRating()));
                Picasso.with(mCtx).load(data.get(position).getImageUrl()).placeholder(R.drawable.load).into(holder.hl_three_image);
                if(this.fav){
                    holder.hl_three_fav_in_favIv.setVisibility(View.VISIBLE);
                }
                break;
        }

    }


    private int discount(int sp,int op){
        float d=op-sp;
        d=d/op;
        d=d*100;
        Log.i("Dis",op+" "+sp+" "+d);
        return (int)Math.floor(d);

    }

    @Override
    public int getItemCount() {
        if(data!=null)
        return (data.size());
        return 0;
    }




    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView hl_two_name,hl_three_name,hl_one_name;
        private TextView hl_two_offer,hl_three_offer,hl_one_offer;
        private TextView hl_two_price,hl_three_price,hl_one_price;
        private TextView hl_two_desc,hl_three_desc,hl_one_desc;
        private RatingBar hl_two_rating,hl_three_rating,hl_one_rating;
        private Button hl_two_order_now_btn;
        private ImageView hl_three_fav_in_favIv,hl_one_image,hl_two_image,hl_three_image;
        View mView;

        private FoodViewHolder(View itemView) {

                super(itemView);
                mView = itemView;

                switch (type) {
                    case 1:
                        hl_one_name = itemView.findViewById(R.id.hl_one_name);
                        hl_one_desc = itemView.findViewById(R.id.hl_one_desc);
                        hl_one_price = itemView.findViewById(R.id.hl_one_price);
                        hl_one_offer = itemView.findViewById(R.id.hl_one_offer);
                        hl_one_rating = itemView.findViewById(R.id.hl_one_ratingbar);
                        hl_one_image=itemView.findViewById(R.id.hl_one_image);
                        break;

                    case 2:
                        hl_two_name = itemView.findViewById(R.id.hl_two_foodname);
                        hl_two_desc = itemView.findViewById(R.id.hl_two_fooddesc);
                        hl_two_price = itemView.findViewById(R.id.hl_two_price);
                        hl_two_offer = itemView.findViewById(R.id.hl_two_offer);
                        hl_two_rating = itemView.findViewById(R.id.hl_two_food_rating);
                        hl_two_order_now_btn = itemView.findViewById(R.id.hl_two_order_now);
                        hl_two_image=itemView.findViewById(R.id.hl_two_food_image);
                        hl_two_order_now_btn.setOnClickListener(this);
                        break;

                    case 3:
                        hl_three_name = itemView.findViewById(R.id.hl_three_name);
                        hl_three_desc = itemView.findViewById(R.id.hl_three_desc);
                        hl_three_price = itemView.findViewById(R.id.hl_three_price);
                        hl_three_rating = itemView.findViewById(R.id.hl_three_ratingBar);
                        hl_three_offer=itemView.findViewById(R.id.hl_three_offer);
                        hl_three_fav_in_favIv=itemView.findViewById(R.id.fif_iv);
                        hl_three_image=itemView.findViewById(R.id.hl_three_food_image);
                        break;

                }

                mView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if(data!=null){
                if(v.getId()==R.id.hl_two_order_now){
                    Toast.makeText(mCtx, "Button Pressed", Toast.LENGTH_SHORT).show();
                }
                Intent jump=new Intent(mCtx,FragmentHolder.class);
                FoodDataMap dataMap=data.get(getAdapterPosition());
                Toast.makeText(mCtx, ""+dataMap.getName(), Toast.LENGTH_SHORT).show();
                jump.putExtra("dbcName",dbcName);
                jump.putExtra("foodDataMap",dataMap);
                jump.putExtra("flow-id","%F%A%");
                mCtx.startActivity(jump);

            }
        }
    }

}
