package com.asocialfingers.kachestvo.Utils.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Utils.Database.CommentDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AllCommentsAdapter extends RecyclerView.Adapter<AllCommentsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<CommentDetails> commentDetailsArrayList;
    private static String today;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, comment, created_at;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtNameSurname);
            comment = (TextView) view.findViewById(R.id.txtComment);
            created_at = (TextView) view.findViewById(R.id.txtCommentCreatedAt);
        }
    }

    public AllCommentsAdapter(Context mContext, ArrayList<CommentDetails> commentDetailsArrayList) {
        this.mContext = mContext;
        this.commentDetailsArrayList = commentDetailsArrayList;

        Calendar calendar = Calendar.getInstance();
        today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comments_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentDetails commentDetails = commentDetailsArrayList.get(position);
        holder.name.setText(commentDetails.getNameSurname());
        holder.comment.setText(commentDetails.getComment());
        holder.created_at.setText(getTimeStamp(commentDetails.getCreated_at()));
    }

    @Override
    public int getItemCount() {
        return commentDetailsArrayList.size();
    }

    public static String getTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "";

        today = today.length() < 2 ? "0" + today : today;

        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat todayFormat = new SimpleDateFormat("dd");
            String dateToday = todayFormat.format(date);
            format = dateToday.equals(today) ? new SimpleDateFormat("hh:mm a") : new SimpleDateFormat("dd LLL, hh:mm a");
            String date1 = format.format(date);
            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}