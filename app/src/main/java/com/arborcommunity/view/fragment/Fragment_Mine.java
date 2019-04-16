package com.arborcommunity.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.activity.AboutAppActivity;
import com.arborcommunity.view.activity.SettingActivity;
import com.arborcommunity.view.activity.VerifyIndetifyActivity;

/**
 * Created by pc on 2018/8/1.
 * 我的界面
 */
public class Fragment_Mine extends Fragment implements View.OnClickListener {

    private Activity ctx;
    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.activity_mine,null);
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
       // return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initViews() {
        // TODO Auto-generated method stub

    }

    private void initData() {
        // TODO Auto-generated method stub

    }

    private void setOnListener() {
        // TODO Auto-generated method stub

        layout.findViewById(R.id.mine_imge).setOnClickListener(this);//我的头像
        layout.findViewById(R.id.mineMoney).setOnClickListener(this);//我的积分
        layout.findViewById(R.id.mineApply).setOnClickListener(this);//我的申请
        layout.findViewById(R.id.mineInformation).setOnClickListener(this);//我的认证信息
        layout.findViewById(R.id.mineSafety).setOnClickListener(this);//安全
        layout.findViewById(R.id.aboutAppInfo).setOnClickListener(this);//安全
//        layout.findViewById(R.id.outApp).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_imge:
                //我的图片点击
                //Utils.start_Activity(getActivity(), LoginActivity.class);
                break;
            case R.id.mineMoney:
                //我的积分
                break;
            case R.id.mineApply:
                //Utils.start_Activity(getActivity(), MyApplyActivity.class);
                //我的申请
                break;
            case R.id.mineInformation:
                //信息认证
                Utils.start_Activity(getActivity(), VerifyIndetifyActivity.class);
                break;
            case R.id.mineSafety:
                //安全认证
                Utils.start_Activity(getActivity(), SettingActivity.class);
                break;
            case R.id.aboutAppInfo:
                //关于
                Utils.start_Activity(getActivity(), AboutAppActivity.class);
                break;
            default:
                break;
        }
    }

}
