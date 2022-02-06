package com.example.logindatabase.Adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.logindatabase.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView textView;
    LinearLayout parentLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.email_textView2);
        parentLayout = itemView.findViewById(R.id.parent_Layout);


    }
}
