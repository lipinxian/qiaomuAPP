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
import com.arborcommunity.view.bean.FunctioInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by pc on 2018/9/12.
 */
public class MoneyManageAdpter extends BaseAdapter {
    private List<FunctioInfo> infoList;
    private Context context;
    private ImageView financeImg;
    private TextView financeInfo;
    private TextView financeNum;
    private TextView financeId;

    public MoneyManageAdpter(List<FunctioInfo> infoList, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_money,parent,false);
        }else {
            convertView = (View)convertView;
        }
        financeImg = (ImageView)convertView.findViewById(R.id.financeImg);
        financeInfo = (TextView)convertView.findViewById(R.id.financeInfo);
        financeNum = (TextView)convertView.findViewById(R.id.financeNum);
        financeId = (TextView)convertView.findViewById(R.id.financeId);
        financeInfo.setText(infoList.get(position).getName());
        financeNum.setText(infoList.get(position).getFinanceInfo());
        financeId.setText(infoList.get(position).getId().toString());
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.__picker_ic_broken_image_black_48dp);
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(infoList.get(position).getImgUrl())
                .thumbnail(0.5f)
                .into(financeImg);
        return convertView;
    }
}
