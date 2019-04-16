package com.arborcommunity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.view.fragment.FragmentFunctionKey;
import com.arborcommunity.view.fragment.Fragment_Home;
import com.arborcommunity.view.fragment.Fragment_Mine;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView txt_title;
    private ImageView img_right;
    protected static final String TAG = "MainActivity";

    private TextView btn_home;// 首页
    private TextView btn_function;// 功能
    private TextView btn_mine;// 我的
    private Fragment[] fragments;
    private ImageView[] imageViews;
    private TextView[] textViews;
    private ImageView img_back;
    private String connectMsg = "";;
    private int index;
    private int currentTabIndex=1;// 当前fragment的index
    public FragmentFunctionKey fragmentFunctionKey;
    public Fragment_Mine fragmentMine;
    public Fragment_Home fragmentHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initTabView();
    }
    private void initTabView() {
        View  topView = (View)findViewById(R.id.layout_top);//layout顶部
        //底部按钮
        imageViews = new ImageView[3];
        imageViews[0] = (ImageView) findViewById(R.id.btn_home);
        imageViews[1] = (ImageView) findViewById(R.id.btn_function);
        imageViews[2] = (ImageView) findViewById(R.id.btn_mine);
        imageViews[1].setSelected(true);
        imageViews[1].setImageResource(R.drawable.function_sel);
        //fragment功能界面
        fragmentHome = new Fragment_Home();
        fragmentFunctionKey = new FragmentFunctionKey();
        fragmentMine = new Fragment_Mine();
        fragments = new Fragment[] { fragmentHome,fragmentFunctionKey, fragmentMine};


        //顶部样式初始化

        img_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        //需要隐藏
        img_back.setVisibility(View.GONE);
        //需要显示
        topView.setVisibility(View.VISIBLE);//顶部显示
        txt_title.setText(R.string.app_name);//设置app标题

        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,fragmentHome)
                .add(R.id.fragment_container, fragmentFunctionKey)
                .add(R.id.fragment_container, fragmentMine)
                .hide(fragmentMine)
                .hide(fragmentHome)
                .show(fragmentFunctionKey).commit();

      //  updateUnreadLabel();
    }

    private void findViewById() {

        txt_title = (TextView) findViewById(R.id.txt_title);
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.img_right:
               *//* if (index == 0) {
                    titlePopup.show(findViewById(R.id.layout_bar));
                } else {
                    Utils.start_Activity(MainActivity.this, PublicActivity.class,
                            new BasicNameValuePair(Constants.NAME, "添加朋友"));
                }*//*
                break;
            default:
                break;
        }*/
    }

    public void onTabClicked(View view){
       // view.setVisibility(view.GONE);
        View  topView = (View)findViewById(R.id.layout_top);//layout顶部
        switch (view.getId()) {
            case R.id.btn_home://首页
                index=0;
                topView.setVisibility(View.VISIBLE);//顶部显示
               // iv_search.setVisibility(View.VISIBLE);//设置查询图标显示
                txt_title.setText(R.string.news);//设置最新资讯标题
                imageViews[index].setImageResource(R.drawable.home_sel);
                break;
            case R.id.btn_function://功能
                index =1;
                topView.setVisibility(View.VISIBLE);//顶部显示
                txt_title.setText(R.string.app_name);//设置app标题
                imageViews[index].setImageResource(R.drawable.function_sel);
                break;
            case R.id.btn_mine://我的
                index = 2;
                topView.setVisibility(View.GONE);//顶部隐藏
                imageViews[index].setImageResource(R.drawable.mine_sel);
                break;
        }

        switch (imageViews[currentTabIndex].getId()){
            case R.id.btn_home://首页
                if(index!=0) imageViews[currentTabIndex].setImageResource(R.drawable.home);
                break;
            case R.id.btn_function://功能
                if(index!=1) imageViews[currentTabIndex].setImageResource(R.drawable.function);
                break;
            case R.id.btn_mine://我的
                if(index!=2)  imageViews[currentTabIndex].setImageResource(R.drawable.mine);
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }

        imageViews[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imageViews[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

