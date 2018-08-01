package com.example.emobilis.firebase;

/**
 * Created by emobilis on 7/27/18.
 */

//CustomAdapter
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<user> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<user> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvnames = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvemail = (TextView) convertView.findViewById(R.id.tvMail);
            viewHolder.tvage = (TextView) convertView.findViewById(R.id.tvAge);
            viewHolder.delete = convertView.findViewById(R.id.btndel);
            //viewHolder.imageView=(ImageView) convertView.findViewById(R.id.imageView);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        final user person = data.get(position);//modify here
        viewHolder.tvnames.setText(person.getNames());//modify here
        viewHolder.tvemail.setText(person.getEmails());//modify here
        viewHolder.tvage.setText(person.getAges());//modify here
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/"+person.getUnique());
                ref.removeValue();
                Toast.makeText(mContext, person.getNames()+" Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        //viewHolder.imageView.setImageResource(person.getImage());
        return convertView;

    }
    static class ViewHolder {
        TextView tvnames;
        TextView tvemail;
        TextView tvage;
        Button delete;
        //These don't have to be same names as the Id's
        //MODIFY THIS BLOCK OF CODE
        //TextView textViewTitle;
        //ImageView imageView;
    }

}
