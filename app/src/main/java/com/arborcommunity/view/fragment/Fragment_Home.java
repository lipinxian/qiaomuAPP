package com.arborcommunity.view.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arborcommunity.MainActivity;
import com.arborcommunity.R;
import com.arborcommunity.adpter.HomeNewsAdpter;
import com.arborcommunity.view.bean.NewsContent;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2018/8/2.
 * 开始消息界面
 */
public class Fragment_Home extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener {

    private Activity ctx;
    private View layout;
    private ListView contact;
    private MainActivity parentActivity;
    public RelativeLayout errorItem;
    public TextView errorText;
    private ListView lvContact;
    private List<NewsContent> conversationList = new ArrayList<NewsContent>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Glide.get(getActivity()).clearMemory();
        if (layout == null) {
            ctx = this.getActivity();
            parentActivity = (MainActivity) getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_home,null);
            lvContact = (ListView) layout.findViewById(R.id.home_listview);
            errorItem = (RelativeLayout) layout.findViewById(R.id.home_rl_error_item);
            errorText = (TextView) errorItem.findViewById(R.id.tv_connect_errormsg);
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

      //  return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setOnListener() {
        lvContact.setOnItemClickListener(this);
        errorItem.setOnClickListener(this);
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        conversationList.clear();
        initViews();
    }

    /**
     * 初始化界面
     */
    public void initViews(){
        contact = (ListView) layout.findViewById(R.id.home_listview);

    }

    /**
     * 获取所有新闻
     * @return
     */
    public void initData(){
       // NewsContent news = new NewsContent("http://n1.itc.cn/img8/wb/smccloud/fetch/2015/05/19/80576146016943551.JPEG","你好,世界!",12, "\"你好,世界!\"全球旅行摄影大赛",new Date());
      //  conversationList.add(news);
        NewsContent news2 = new NewsContent("http://i1.umei.cc/uploads/tu/201809/9999/bae280abba.jpg","最后的“大漠隐者”",14, "在互联网等现代科技已经普及的中国，新疆塔克拉玛干沙漠深处，依然居住着原始牧民克里雅人。");
        conversationList.add(news2);
        contact.setAdapter(new HomeNewsAdpter(conversationList,getActivity()));
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
