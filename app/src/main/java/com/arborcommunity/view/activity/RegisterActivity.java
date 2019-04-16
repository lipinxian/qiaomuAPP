package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.arborcommunity.Constants;
import com.arborcommunity.MainActivity;
import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.bean.Community;

import com.juns.health.net.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by pc on 2018/10/9.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private ImageView image_back;

    private TextView txt_title;
    private Spinner spinner;

    private TextView communityId;

    private ArrayAdapter<String>  adapter;
    private Button registerButton;
    private EditText registerPhone;
    private EditText registerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initControl() {
        //返回图标显示、标题显示、搜索框隐藏、搜索图片隐藏
        //顶部样式初始化

        image_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);

        registerButton = (Button)findViewById(R.id.registerButton);
        registerPhone = (EditText) findViewById(R.id.registerPhone);
        registerPassword = (EditText) findViewById(R.id.registerPassword);

        txt_title.setText(R.string.register);//设置app标题

        image_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
       /* */


    }

    @Override
    protected void setListener() {
        image_back.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        registerPhone.addTextChangedListener(new TextChange());
        registerPassword.addTextChangedListener(new TextChange());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(RegisterActivity.this);
                break;
            case  R.id.registerButton:
                pushRegisterInfo();
                break;
            default:
                break;
        }
    }

    private void pushRegisterInfo(){
        final String phone = registerPhone.getText().toString();
        final String password = registerPassword.getText().toString();
        if (!Utils.isMobileNO(phone)) {
            Utils.showLongToast(RegisterActivity.this, "请使用手机号码注册账户！ ");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Utils.showLongToast(RegisterActivity.this, "请填写密码！");
            return;
        }

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Utils.showLongToast(RegisterActivity.this, "请填写核心信息！");
            return;
        }
        getLoadingDialog("正在注册...  ").show();
        registerButton.setEnabled(false);

        RequestParams params = new RequestParams();
        params.put("phone",phone);
        params.put("password",password);
        netClient.post(Constants.RegistURL, params, new BaseJsonRes() {

            @Override
            public void onMySuccess(String data) {
                Utils.putValue(RegisterActivity.this, Constants.UserInfo, data);
                Utils.putValue(RegisterActivity.this, Constants.NAME, phone);
                Utils.putValue(RegisterActivity.this, Constants.PWD, password);
                Utils.putBooleanValue(RegisterActivity.this,
                Constants.LoginState, true);
                getChatserive(phone, password);
            }

            @Override
            public void onMyFailure(String data) {
                getLoadingDialog().setText(data);

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        getLoadingDialog().dismiss();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 2000);
                registerButton.setEnabled(true);
            }
        });
    }

    private void getChatserive(final String userName, final String password) {
        runOnUiThread(new Runnable() {
            public void run() {
              /*  Utils.putBooleanValue(RegisterActivity.this, Constants.LoginState, true);
                Utils.putValue(RegisterActivity.this, Constants.User_ID, userName);
                Utils.putValue(RegisterActivity.this, Constants.PWD, password);
                Log.d("main", "登陆聊天服务器成功！");*/
                getLoadingDialog("正在登录...").dismiss();
                Utils.showLongToast(RegisterActivity.this, "注册成功！");
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
    }


    // EditText监听器
    class TextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {
            boolean Sign2 = registerPhone.getText().length() > 0;
            boolean Sign3 = registerPassword.getText().length() > 4;
            if (Sign2 & Sign3) {
                registerButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_green));
                registerButton.setEnabled(true);
            } else {
                registerButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_enable_green));
                registerButton.setTextColor(0xFFD0EFC6);
                registerButton.setEnabled(false);
            }
        }
    }
}
