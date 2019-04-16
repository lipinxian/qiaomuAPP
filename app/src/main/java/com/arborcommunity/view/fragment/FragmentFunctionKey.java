package com.arborcommunity.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.arborcommunity.R;
import com.arborcommunity.adpter.RealTimeAdpter;
import com.arborcommunity.bean.RealtimeMessage;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.LoadListView;
import com.arborcommunity.view.activity.BrowsingInformationActivity;
import com.arborcommunity.view.activity.HomeworkDiscussActivity;
import com.arborcommunity.view.activity.IdleShareActivity;
import com.arborcommunity.view.activity.InterestActivity;
import com.arborcommunity.view.activity.PolicyNoticeActivity;
import com.arborcommunity.view.activity.PublicBankActivity;
import com.arborcommunity.view.bean.NewsContent;
import com.arborcommunity.widght.SideBar;


import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2018/7/30.
 * 功能界面
 */
public class FragmentFunctionKey  extends Fragment implements OnClickListener,OnItemClickListener {

    private Activity ctx;
    private View layout;
    private ListView contact;
    private SideBar indexBar;
    private TextView mDialogText;
    private WindowManager mWindowManager;
    private List<RealtimeMessage> messageList = new ArrayList<RealtimeMessage>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_function,null);
            initViews();
            initData();
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }


    private void initViews() {
        // TODO Auto-generated method stub
        contact = (ListView) layout.findViewById(R.id.realitimeMessageListView);

    }

    private void initData() {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取信息

        RealtimeMessage message =  new RealtimeMessage(new NewsContent("","通知",102,"今天海尚菊小区20：00-明天早上5：00停水，请居民做好准备",1L),sdf.format(new Date()));
        RealtimeMessage message2 =  new RealtimeMessage(new NewsContent("","信息",102,"2018年9月份幼儿园开学，在8月30号报名，请个适龄儿童家长做好报名准备",2L),sdf.format(new Date()));
        messageList.add(message);
        messageList.add(message2);
        contact.setAdapter(new RealTimeAdpter(messageList,getActivity()));
    }

    private void setOnListener() {
        // TODO Auto-generated method stub
        layout.findViewById(R.id.policyNotice).setOnClickListener(this);//政策公告
        layout.findViewById(R.id.homeworkDiscuss).setOnClickListener(this);//家事讨论
        layout.findViewById(R.id.exposure).setOnClickListener(this);//七彩生活
        layout.findViewById(R.id.shareInformation).setOnClickListener(this);//信息共享
        layout.findViewById(R.id.advertis).setOnClickListener(this);//广告灌水
        layout.findViewById(R.id.public_spirited).setOnClickListener(this);//公益银行
        contact.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.policyNotice://政策公告
                Utils.start_Activity(getActivity(), PolicyNoticeActivity.class);
                break;
            case R.id.homeworkDiscuss://家事讨论
                Utils.start_Activity(getActivity(), HomeworkDiscussActivity.class);
                break;
            case R.id.exposure://曝光台
               // Utils.start_Activity(getActivity(), exposureActivity.class);
                break;
            case R.id.shareInformation://兴趣交流
                //Utils.start_Activity(getActivity(), InterestActivity.class);
                break;
            case R.id.advertis://闲置分享
                Utils.start_Activity(getActivity(), IdleShareActivity.class);
                break;
            case R.id.public_spirited://公益银行
                Utils.start_Activity(getActivity(), PublicBankActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(position);
        //获取该消息内容
        RealtimeMessage message = messageList.get(position);

        //Intent intent = new Intent(getActivity(), ChatActivity.class);
        Utils.start_Activity(getActivity(), BrowsingInformationActivity.class,new BasicNameValuePair("message", JSON.toJSONString(message)));


    }
}
