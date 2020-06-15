package com.asocialfingers.kachestvo.Tabs.Comments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asocialfingers.kachestvo.Main.MainActivity;
import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Utils.Adapter.CustomListViewAdapter;
import com.asocialfingers.kachestvo.Utils.Button.CustomAnimatedButton;
import com.asocialfingers.kachestvo.Utils.Database.CommentDao;
import com.asocialfingers.kachestvo.Utils.Database.CommentDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CommentsFragment extends Fragment {

    private CommentDao dao = new CommentDao(getActivity());

    private final ArrayList<CommentDetails> list = dao.getAllComments();
    private String selectedNameSurname, selectedComment, created_at;
    private EditText _overviewNameSurname, _overviewComment;
    private TextView _nameSurnameCounter, _commentCounter;
    private TextView _nameSurname;
    private ImageButton _close;
    private ListView _comments;
    private Button _delete;
    private Dialog _dialog;
    int selectedId;

    public CommentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comments, container, false);

        utils(root);
        listViewEvents();

        return root;
    }

    private void utils(View view) {
        _comments = (ListView) view.findViewById(R.id.commentsList);
        _nameSurname = (TextView) view.findViewById(R.id.txtNameSurname);

        dao = new CommentDao(getActivity());
        _dialog = new Dialog(getActivity());
    }

    private void listViewEvents() {
        final ArrayList<CommentDetails> list = dao.getAllComments();
        String[] items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = list.get(i).getNameSurname();
        }

        CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), list);

        _comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                selectedNameSurname = list.get(i).getNameSurname();
                selectedComment = list.get(i).getComment();
                selectedId = i;
                showPopUp();
            }
        });
        _comments.setAdapter(adapter);
    }

    private void showPopUp() {

        _dialog.setContentView(R.layout.custom_comment_overview_modal);

        _overviewNameSurname = (EditText) _dialog.findViewById(R.id.txtOverviewNameSurname);
        _nameSurnameCounter = (TextView) _dialog.findViewById(R.id.txtNameSurnameCounter);
        _overviewComment = (EditText) _dialog.findViewById(R.id.txtOverviewComment);
        _commentCounter = (TextView) _dialog.findViewById(R.id.txtCommentCounter);
        _close = (ImageButton) _dialog.findViewById(R.id.imgClose);
        _delete = (Button) _dialog.findViewById(R.id.btnDelete);

        _overviewNameSurname.setText(selectedNameSurname);
        _overviewComment.setText(selectedComment);

        _overviewComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _commentCounter.setVisibility(View.VISIBLE);

                int maxCommentLength = 350;
                int resultCommentLength;
                int commentLength;

                commentLength = _overviewComment.getText().length();

                resultCommentLength = maxCommentLength - commentLength;
                _commentCounter.setText(String.valueOf(resultCommentLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _overviewNameSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _nameSurnameCounter.setVisibility(View.VISIBLE);

                int maxNameSurnameLength = 25;
                int resultNameSurnameLength;
                int nameSurnameLength;

                nameSurnameLength = _overviewNameSurname.getText().length();

                resultNameSurnameLength = maxNameSurnameLength - nameSurnameLength;
                _nameSurnameCounter.setText(String.valueOf(resultNameSurnameLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _dialog.dismiss();
            }
        });

        _delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Silmek istediğinizden emin misiniz?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dao.deleteComment(list.get(selectedId).getId());
                        _dialog.dismiss();
                        Intent i = new Intent(getActivity(),MainActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        Objects.requireNonNull(_dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _dialog.show();
    }
}