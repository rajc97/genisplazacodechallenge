package com.rajchandan.codingchallenge;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<serverInfo>
{
    private Activity context;
    private int resource;
    private List<serverInfo> listImage;

    public ListViewAdapter(Context context, int resource, List<serverInfo> objects)
    {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.resource = resource;
        listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if(null == v)
        {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item,null);
        }
        serverInfo serverInfo = getItem(position);
        ImageView avatar = (ImageView) v.findViewById(R.id.avatarImg);
        TextView firstName =(TextView) v.findViewById(R.id.firstNameTxt);
        TextView lastname =(TextView) v.findViewById(R.id.lastNameTxt);

        Glide.with(context).load(listImage.get(position).getAvatar()).into(avatar);
        firstName.setText(serverInfo.getFirstName());
        lastname.setText(serverInfo.getLastName());

        return v;
    }
}
