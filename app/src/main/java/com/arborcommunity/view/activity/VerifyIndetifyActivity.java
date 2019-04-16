package com.arborcommunity.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;

/**
 * @Author: 李品先
 * @Date: 2019/1/18 11:33
 * @Description: 信息认证页面
 */
public class VerifyIndetifyActivity extends BaseActivity implements View.OnClickListener{
    private ImageView img_back;
    private TextView txt_title;
    private Button push_button;
    //private TextView verify_community;
    private TextView verify_identity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_verify_indentity);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        img_back = (ImageView)findViewById(R.id.img_back);
        txt_title= (TextView)findViewById(R.id.txt_title);
        push_button = (Button)findViewById(R.id.push_button);
        img_back.setVisibility(View.VISIBLE);
        txt_title.setText("信息认证");
        push_button.setVisibility(View.GONE);
        //verify_community = (TextView)findViewById(R.id.verify_community);//社区认证
        verify_identity = (TextView)findViewById(R.id.verify_identity);//身份认证


    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);
      //  verify_community.setOnClickListener(this);
        verify_identity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(VerifyIndetifyActivity.this);
                break;
           /* case R.id.verify_community:
                Utils.start_Activity(this, CommunityApplyActivity.class);
                break;*/
            case R.id.verify_identity:
                Utils.start_Activity(this, UserIdentityAuthenticationActivity.class);
                break;
            default:break;

        }

    }
}
