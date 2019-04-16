package com.arborcommunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.arborcommunity.bean.GroupInfo;
import com.arborcommunity.bean.User;
import com.arborcommunity.common.Utils;
import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.net.NetClient;
import com.arborcommunity.view.activity.LoginActivity;
import com.juns.health.net.loopj.android.http.RequestParams;


import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pc on 2018/11/11.
 */
public class SplashActivity  extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initData();
        int RunCount = Utils.getIntValue(this, "RUN_COUNT");
        if (RunCount == 0) {
            // TODO 引导页面
        } else {
            Utils.putIntValue(this, "RUN_COUNT", RunCount++);
        }
        Boolean isLogin = Utils.getBooleanValue(SplashActivity.this, Constants.LoginState);
        if (isLogin) {
            getLogin();
        } else {
            mHandler.sendEmptyMessage(0);
        }

    }

    private void initData() {

        GloableParams.UserInfos = new ArrayList<User>();
        GloableParams.Users = new HashMap<String, User>();
        GloableParams.ListGroupInfos = new ArrayList<GroupInfo>();
        GloableParams.GroupInfos = new HashMap<String, GroupInfo>();
        FinalDb db = FinalDb.create(this, Constants.DB_NAME, false);
        GloableParams.ListGroupInfos = db.findAll(GroupInfo.class);
        GloableParams.UserInfos = db.findAll(User.class);
    }

    private void getLogin() {
        String name = Utils.getValue(this, Constants.NAME);
        String pwd = Utils.getValue(this, Constants.PWD);
        Utils.RemoveValue(SplashActivity.this, Constants.LoginState);

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(name))
            getChatserive(name, pwd);
        else {
            Utils.RemoveValue(SplashActivity.this, Constants.LoginState);
            mHandler.sendEmptyMessageDelayed(0, 600);
        }
    }



    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Boolean isLogin = Utils.getBooleanValue(SplashActivity.this, Constants.LoginState);
            Intent intent = new Intent();
            if (isLogin) {
                intent.setClass(SplashActivity.this, MainActivity.class);
            } else {
                intent.setClass(SplashActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            finish();
        }
    };

    private void getChatserive(final String userName, final String password) {
        RequestParams params = new RequestParams();
        params.put("username", userName);
        params.put("password", password);
        NetClient netClient = new NetClient(this);


        netClient.post(Constants.Login_URL, params, new BaseJsonRes() {
            @Override
            public void onMySuccess(String data) {
                System.out.println(data);
                Utils.putBooleanValue(SplashActivity.this, Constants.LoginState, true);
                Utils.putValue(SplashActivity.this, Constants.NAME, userName);
                Utils.putValue(SplashActivity.this, Constants.PWD, password);
                Log.d("main", "登陆聊天服务器成功！");
                // 加载群组和会话
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onMyFailure(String data) {
                Log.d("main","登录失败");
            }

        });
    }

}
