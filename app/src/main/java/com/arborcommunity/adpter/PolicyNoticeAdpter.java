package com.arborcommunity.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.common.TransformationUtils;
import com.arborcommunity.net.NetClient;
import com.arborcommunity.view.bean.FunctioInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by pc on 2018/9/1.
 */
public class PolicyNoticeAdpter extends BaseAdapter {

    private List<FunctioInfo> infoList;
    private Context context;

    public PolicyNoticeAdpter(List<FunctioInfo> infoList, Context context){
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_function_info,parent,false);
        }else {
            convertView = (View)convertView;
        }
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.function_image);
        final TextView textView = (TextView)convertView.findViewById(R.id.function_text);
        final TextView info_id = (TextView)convertView.findViewById(R.id.info_id);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.__picker_ic_broken_image_black_48dp);
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(infoList.get(position).getImgUrl())
                .thumbnail(0.5f)
                .into(imageView);
        textView.setText(infoList.get(position).getName());
        info_id.setText(infoList.get(position).getId().toString());
        return convertView;
    }
}
