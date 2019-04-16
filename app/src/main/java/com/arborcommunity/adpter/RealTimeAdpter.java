package com.arborcommunity.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.bean.RealtimeMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/8/28.
 */
public class RealTimeAdpter extends BaseAdapter {

    private List<RealtimeMessage> data;
    private Context context;

   public RealTimeAdpter(List<RealtimeMessage> data,Context context){
       this.data = data;
       this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.realtime_message,parent,false);
        }else {
            convertView = (View)convertView;
        }
        //初始化item的各个组件
        final TextView message = (TextView)convertView.findViewById(R.id.message);
        TextView message_id = (TextView)convertView.findViewById(R.id.message_id);
        message.setText(data.get(position).getMessage().getNewsName()+":"+data.get(position).getMessage().getNewsAbstract());
        //设置按钮的监听，实现点击后跳转到消息详细内容的界面，并且传递数据
        message_id.setText(data.get(position).getMessage().getId().toString());
        return convertView;
    }


}
