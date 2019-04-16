package com.arborcommunity.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arborcommunity.R;
import com.arborcommunity.bean.RealtimeMessage;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.bean.NewsContent;

/**
 * @Author: 李品先
 * @Date: 2019/3/2 15:09
 * @Description: ${description}
 */
public class BrowsingInformationActivity extends BaseActivity implements View.OnClickListener{

    private ImageView img_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;

    private TextView brows_title;
    private TextView brows;
    private TextView people_num;
    private TextView heart_num;
    private TextView comment_num;
    private TextView transpond_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_browsing_information);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

        img_back = (ImageView)findViewById(R.id.img_back);
        txt_title = (TextView)findViewById(R.id.txt_title);
        mess_type = (TextView)findViewById(R.id.mess_type);
        push_button = (Button)findViewById(R.id.push_button);
        img_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText("我的社区");
        mess_type.setVisibility(View.GONE);
        push_button.setVisibility(View.GONE);

        brows = (TextView)findViewById(R.id.brows);
        people_num = (TextView)findViewById(R.id.people_num);
        heart_num = (TextView)findViewById(R.id.heart_num);
        comment_num = (TextView)findViewById(R.id.comment_num);
        transpond_num = (TextView)findViewById(R.id.transpond_num);
        brows_title = (TextView)findViewById(R.id.brows_title);
    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        //接收消息值
        String message = bundle.getString("message");
        JSONObject jsonObject= JSON.parseObject(message);

        NewsContent newsContent = JSON.parseObject(jsonObject.getString("message"),NewsContent.class);
      //  RealtimeMessage data = JSONObject.toJavaObject(jsonObject,RealtimeMessage.class);//JSON.parseArray(message,RealtimeMessage.class).get(0);
        brows_title.setText(newsContent.getNewsName());
        brows.setText("    "+newsContent.getNewsAbstract());
        people_num.setText(newsContent.getSeePeople()+"");
        heart_num.setText("20");
        comment_num.setText("35");
        transpond_num.setText("10");
    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(BrowsingInformationActivity.this);
                break;
            default:
                break;
        }
    }
}
