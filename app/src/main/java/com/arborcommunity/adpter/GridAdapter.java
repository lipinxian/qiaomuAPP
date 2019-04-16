package com.arborcommunity.adpter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.arborcommunity.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by pc on 2018/12/27.
 * 图片浏览
 */
public class GridAdapter extends BaseAdapter {

    private ArrayList<String> listUrls;
    private LayoutInflater inflater;
    private int mMaxPosition;
    private Context context;
    public GridAdapter(ArrayList<String> listUrls,Context context) {
        this.listUrls = listUrls;
        this.context = context;
        if(listUrls.size() == 9){
            listUrls.remove(listUrls.size()-1);
        }
        inflater = LayoutInflater.from(context);
    }

    public int getCount(){
        /*if (listUrls.size() == 9) {
            mMaxPosition = listUrls.size()+1;
        } else {
            mMaxPosition = listUrls.size()+1;
        }
        return mMaxPosition;*/
        return  listUrls.size();
    }
    @Override
    public String getItem(int position) {
        return listUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item, parent,false);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final String path=listUrls.get(position);
        if (path.equals("paizhao")){
            holder.image.setImageResource(R.mipmap.find_add_img);
        }else {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.__picker_ic_photo_black_48dp)
                    .error(R.drawable.__picker_ic_broken_image_black_48dp)
                    .fallback(new ColorDrawable(Color.RED));
            Glide.with(context)
                    .load(path)
                    .apply(requestOptions)
                    .into(holder.image);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView image;
    }
}
