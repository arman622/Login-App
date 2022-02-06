package com.example.logindatabase.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.logindatabase.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterRecyclerView extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = "CustomAdapterRecyclerView";

    private List<String> mEmails;
    private Context mContext;

    public CustomAdapterRecyclerView(Context mContext, List<String> mEmails) {
        this.mEmails = mEmails;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_list_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: created " + (position+1) + " Holder");
        holder.textView.setText((position+1) + ". " + mEmails.get(position));
    }

    @Override
    public int getItemCount() {
        return mEmails.size();
    }



}
