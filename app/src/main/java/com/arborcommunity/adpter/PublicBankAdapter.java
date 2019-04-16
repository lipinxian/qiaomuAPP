package com.arborcommunity.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.view.bean.PublicBank;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by pc on 2018/12/23.
 * 公益银行adapter
 */
public class PublicBankAdapter extends BaseAdapter {

    private List<PublicBank> data;
    public Context context;

    public PublicBankAdapter(List<PublicBank> data,Context context){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_adpter_family_services, parent, false);
        }else {
            convertView = (View)convertView;
        }

        ViewGroup parents = (ViewGroup) convertView.getParent();
        if (parents != null) {
            parents.removeView(convertView);
        }


        final ImageView family_img = (ImageView)convertView.findViewById(R.id.family_img);
        TextView family_content= (TextView)convertView.findViewById(R.id.family_content);
        TextView family_value= (TextView)convertView.findViewById(R.id.family_value);
        Button family_button = (Button)convertView.findViewById(R.id.family_button);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.__picker_ic_broken_image_black_48dp);
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(data.get(position).getImgUrl())
                .thumbnail( 0.5f )//先加载原图大小的十分之一
                .into(family_img);

        family_content.setText(data.get(position).getContent());
        family_value.setText(data.get(position).getValue()+"/次");
        family_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}
