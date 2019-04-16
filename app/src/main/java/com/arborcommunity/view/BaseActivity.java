package com.arborcommunity.view;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arborcommunity.App;
import com.arborcommunity.MainActivity;
import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.dialog.FlippingLoadingDialog;
import com.arborcommunity.net.NetClient;
import com.arborcommunity.net.utils.CommonUtils;
import com.arborcommunity.view.bean.NewsContent;
import com.easemob.util.EasyUtils;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by pc on 2018/8/31.
 */
public abstract class BaseActivity extends Activity {
    protected Activity context;
    protected NetClient netClient;
    protected FlippingLoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        App.getInstance2().addActivity(this);
        netClient = new NetClient(this);
        initView();
        initControl();
        initData();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Utils.finish(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定控件id
     */
    protected abstract void initControl();



    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 打开 Activity
     *
     * @param activity
     * @param cls
     * @param name
     */
    public void start_Activity(Activity activity, Class<?> cls,
                               BasicNameValuePair... name) {
        Utils.start_Activity(activity, cls, name);
    }

    /**
     * 关闭 Activity
     *
     * @param activity
     */
    public void finish(Activity activity) {
        Utils.finish(activity);
    }

    /**
     * 判断是否有网络连接
     */
    public boolean isNetworkAvailable(Context context) {
        return Utils.isNetworkAvailable(context);
    }

    public FlippingLoadingDialog getLoadingDialog(String msg) {
        if (mLoadingDialog == null) mLoadingDialog = new FlippingLoadingDialog(this, msg);
        return mLoadingDialog;
    }

    public FlippingLoadingDialog getLoadingDialog() {
          return mLoadingDialog;
    }

}
