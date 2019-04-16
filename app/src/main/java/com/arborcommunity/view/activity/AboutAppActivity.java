package com.arborcommunity.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;

/**
 * Created by pc on 2018/9/17.
 */
public class AboutAppActivity  extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView image_back;

    private TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about_app);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initControl() {
        View  topView = (View)findViewById(R.id.policy_top);//layout顶部
        //返回图标显示、标题显示、搜索框隐藏、搜索图片隐藏
        //顶部样式初始化

        image_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setText(R.string.aboutApp);//设置app标题
        image_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        image_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(AboutAppActivity.this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
