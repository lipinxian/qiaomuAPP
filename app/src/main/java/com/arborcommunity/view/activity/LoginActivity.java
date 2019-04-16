package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.Constants;
import com.arborcommunity.MainActivity;
import com.arborcommunity.R;
import com.arborcommunity.common.AESUtil;
import com.arborcommunity.common.SecretUtils;
import com.arborcommunity.common.Utils;
import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.view.BaseActivity;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.juns.health.net.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pc on 2018/9/4.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private ImageView image_back;
    private TextView txt_title;
    private TextView register;
    private TextView tv_wenti;
    /*private TextView loginInfo;*/
    private Button btn_login;
    private EditText et_usertel, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            setContentView(R.layout.activity_login);
            super.onCreate(savedInstanceState);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void initControl() {
        View  topView = (View)findViewById(R.id.policy_top);//layout顶部
        //返回图标显示、标题显示、搜索框隐藏、搜索图片隐藏
        //顶部样式初始化

        image_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);

       /* loginInfo = (TextView)findViewById(R.id.loginInfo);//登陆信息*/

        et_usertel = (EditText) findViewById(R.id.et_usertel);//用户名
        et_password = (EditText) findViewById(R.id.et_password);//密码
        btn_login = (Button)findViewById(R.id.btn_login);//登陆
        txt_title.setText(R.string.login);//设置app标题

        image_back.setVisibility(View.GONE);
        txt_title.setVisibility(View.VISIBLE);
        //getLoadingDialog("正在登录...").show();
    }

    @Override
    protected void initView() {
        register = (TextView)findViewById(R.id.register);//注册按钮
        tv_wenti = (TextView)findViewById(R.id.tv_wenti);//遇到找回密码按钮
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
       // image_back.setOnClickListener(this);
        tv_wenti.setOnClickListener(this);
        register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        et_usertel.addTextChangedListener(new TextChange());
        et_password.addTextChangedListener(new TextChange());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Utils.start_Activity(LoginActivity.this, RegisterActivity.class);
                break;
            case R.id.tv_wenti:
              //  Utils.start_Activity(GroupListActivity.this, AddGroupChatActivity.class);
                break;
            case R.id.img_back:
                Utils.finish(LoginActivity.this);
                break;
            case R.id.btn_login:
                getLogin();
                break;
            default:
                break;
        }
    }

    private void getLogin() {
        String userName = et_usertel.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        getLogin(userName, password);
    }


    private void getLogin(final String userName, final String password) {
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            RequestParams params = new RequestParams();
            params.put("username", userName);
            //params.put("password", AESUtil.AESencrypt(password));
            params.put("password", password);

            Utils.showLongToast(LoginActivity.this, "正在登录...");
            netClient.post(Constants.Login_URL, params, new BaseJsonRes() {

                @Override
                public void onMySuccess(String data) {
                    Utils.showLongToast(LoginActivity.this, "登陆成功.");
                    Utils.putValue(LoginActivity.this, Constants.UserInfo, data);
                    Utils.putBooleanValue(LoginActivity.this, Constants.LoginState, true);
                    Utils.putValue(LoginActivity.this, Constants.NAME, userName);
                    Utils.putValue(LoginActivity.this, Constants.PWD, password);
                    // 加载群组和会话
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    finish();
                   // getChatserive(userName, password);
                }

                @Override
                public void onMyFailure(String data) {
                    getLoadingDialog("登录失败").dismiss();
                }

            });
        } else {
            Utils.showLongToast(LoginActivity.this, "请填写账号或密码！");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void getChatserive(final String userName, final String password) {
        Utils.putBooleanValue(LoginActivity.this, Constants.LoginState, true);
        Utils.putValue(LoginActivity.this, Constants.User_ID, userName);
        Utils.putValue(LoginActivity.this, Constants.PWD, password);
        Log.d("main", "登陆成功！");
        // 加载群组和会话
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();


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
            boolean Sign2 = et_usertel.getText().length() > 0;
            boolean Sign3 = et_password.getText().length() > 4;
            if (Sign2 & Sign3) {
                btn_login.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.btn_bg_green));
                btn_login.setEnabled(true);
            } else {
                btn_login.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.btn_enable_green));
                btn_login.setTextColor(0xFFD0EFC6);
                btn_login.setEnabled(false);
            }
        }
    }


}
