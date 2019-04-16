package com.arborcommunity.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.App;
import com.arborcommunity.Constants;
import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;

/**
 * Created by pc on 2018/12/8.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TextView txt_title;
    private ImageView image_back;
    private Button button;
    private TextView txt_usersafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_safety_setting);
        super.onCreate(savedInstanceState);
        findViewById();
    }

    private void findViewById(){

    }


    @Override
    protected void initControl() {
        image_back = (ImageView)findViewById(R.id.img_back);
        image_back.setVisibility(View.VISIBLE);

        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setText(R.string.securitySet);
        button = (Button) findViewById(R.id.btnexit);

        txt_usersafe =(TextView)findViewById(R.id.txt_usersafe);

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
        button.setOnClickListener(this);
        txt_usersafe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(SettingActivity.this);
                break;
            case R.id.txt_usersafe:
                Utils.finish(SettingActivity.this);
                break;
           /* case R.id.txt_about:
                Utils.start_Activity(SettingActivity.this, WebViewActivity.class,
                        new BasicNameValuePair(Constants.Title, "关于微信"),
                        new BasicNameValuePair(Constants.URL,
                                "https://github.com/motianhuo/wechat"));
                break;*/

            case R.id.btnexit:
                Utils.RemoveValue(context, Constants.LoginState);
                Utils.RemoveValue(context, Constants.UserInfo);
                Utils.RemoveValue(context, Constants.NAME);
                Utils.RemoveValue(context, Constants.PWD);
                App.getInstance2().exit();
                Utils.start_Activity(this, LoginActivity.class);
                break;
            default:
                break;
        }
    }
}
