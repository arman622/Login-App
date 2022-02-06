package com.example.logindatabase.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.logindatabase.R;
import com.example.logindatabase.model.User;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapterListView extends ArrayAdapter<String> {

    private int mResource;
    private Context mContext;


    public CustomAdapterListView(Context context, int resource, List<String> list){
        super(context,resource, list);
        this.mResource = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        String email = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView textView = convertView.findViewById(R.id.email_textView2);

        textView.setText(position+1 + ". " + email);

        return convertView;
    }

}
