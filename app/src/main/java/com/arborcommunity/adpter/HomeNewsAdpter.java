package com.arborcommunity.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.view.bean.NewsContent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by pc on 2018/8/29.
 */
public class HomeNewsAdpter extends BaseAdapter {
    private List<NewsContent> data;
    private Context context;

    public HomeNewsAdpter(List<NewsContent> data,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_news_msg,parent,false);
        }else {
            convertView = (View)convertView;
        }
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.newsImageView);
        final TextView newsName = (TextView)convertView.findViewById(R.id.newsName);
        final TextView newsAbstract = (TextView)convertView.findViewById(R.id.newsAbstract);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.__picker_ic_broken_image_black_48dp);
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(data.get(position).getNewsMessageUrl())
                .thumbnail( 0.5f )//先加载原图大小的十分之一
                .into(imageView);
        newsName.setText(data.get(position).getNewsName());
        newsAbstract.setText(data.get(position).getNewsAbstract());
        return convertView;
    }
}
