package com.canteen.chandan.mcafeteria.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.canteen.chandan.mcafeteria.Beans.Reviews;
import com.canteen.chandan.mcafeteria.R;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;




public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private ArrayList<Reviews> review_data;
    private Context ctx;

    public CommentAdapter(){

    }

    public CommentAdapter(Context ctx, ArrayList<Reviews> reviews){
        this.ctx=ctx;
        review_data=reviews;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comments,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.com_username.setText(review_data.get(position).getCus_name());
        holder.com_rating_bar.setRating(Float.valueOf(review_data.get(position).getRatings()));
        holder.com_comment.setText(review_data.get(position).getComment());
        holder.com_date.setText(review_data.get(position).getC_datetime());
    }

    @Override
    public int getItemCount() {
        return review_data.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView com_username;
        private CircleImageView com_pro;
        private TextView com_comment;
        private RatingBar com_rating_bar;
        private TextView com_date;


        public CommentViewHolder(View itemView) {
            super(itemView);
            com_username=itemView.findViewById(R.id.com_username);
            com_comment=itemView.findViewById(R.id.com_comment);
            com_rating_bar=itemView.findViewById(R.id.com_rating_bar);
            com_pro=itemView.findViewById(R.id.com_pro);
            com_date=itemView.findViewById(R.id.com_date);

        }
    }
}
