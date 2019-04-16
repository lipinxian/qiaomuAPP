package com.arborcommunity.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.view.bean.Community;
import com.arborcommunity.view.bean.DeptEntity;

import java.util.List;

/**
 * Created by pc on 2018/12/15.
 */
public class CommunithAdpter extends BaseAdapter {
    private List<Community> data;
    private Context context;

    private Activity activity;

    private TextView name;
    private TextView id;
    private ImageView apply_into;
    public CommunithAdpter(List<Community> data, Context context){
        this.data = data;
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_adpter_community,parent,false);
        }else {
            convertView = (View)convertView;
        }
         name = (TextView)convertView.findViewById(R.id.community_name);
         id = (TextView)convertView.findViewById(R.id.community_id);
         apply_into =(ImageView)convertView.findViewById(R.id.apply_into);
         name.setText(data.get(position).getName());
        id.setText(String.valueOf(data.get(position).getId()));
        /*apply_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AlertDialog.class);
                intent.putExtra("msg", "是否加入社区？");
                intent.putExtra("title", "社区申请");
                intent.putExtra("cancel", true);
                intent.putExtra("ok", true);
                intent.putExtra("position", 2);
            }
        });*/
        return convertView;
    }

}
