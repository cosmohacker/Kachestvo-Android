package com.asocialfingers.kachestvo.Utils.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Utils.Database.CommentDetails;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<CommentDetails> {

    public CustomListViewAdapter(Context context , ArrayList<CommentDetails> comments){
        super(context,0,comments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CommentDetails commentDetails = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_comment_list_view, parent, false);
        }

        TextView _nameSurname = (TextView) convertView.findViewById(R.id.txtNameSurname);
        TextView _created_at = (TextView) convertView.findViewById(R.id.txtCommentCreatedAt);
        TextView _comment = (TextView) convertView.findViewById(R.id.txtComment);
        _nameSurname.setText(commentDetails.getNameSurname());
        _created_at.setText(commentDetails.getCreated_at());
        _comment.setText(commentDetails.getComment());
        return convertView;
    }

    @Nullable
    @Override
    public CommentDetails getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable CommentDetails item) {
        return super.getPosition(item);
    }
}
