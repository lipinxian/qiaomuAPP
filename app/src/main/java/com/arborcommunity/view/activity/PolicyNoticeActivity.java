package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arborcommunity.R;
import com.arborcommunity.adpter.PolicyNoticeAdpter;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.bean.FunctioInfo;
import com.bumptech.glide.Glide;

import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @Despcription: 政策公告
 * Created by pc on 2018/8/31.
 *
 */
public class PolicyNoticeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView textView;
    private ImageView imageView;
    private TextView chiceText ;
    private Spinner spinner;
    private ArrayAdapter  adapter;
    private ListView contact;

    private EditText editText;

    private ImageView image_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;

   // private View layout;
    private List<FunctioInfo> conversationList = new ArrayList<FunctioInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_policynotice);
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化样式
     */
    @Override
    protected void initView(){
        //ListView
        //layout = this.getLayoutInflater().inflate(R.layout.fragment_function,null);
        contact = (ListView)findViewById(R.id.policListView);
    }

    /**
     * 初始化下来框
     */
    private void creatSpinner(){
       // chiceText = (TextView) findViewById(R.id.chiceText);
        //关联控件
        spinner = (Spinner) findViewById(R.id.spinner);
        //将可选内容与ArrayAdapter连接起来，simple_spinner_item是android系统自带样式
        adapter = ArrayAdapter.createFromResource(this,R.array.data, android.R.layout.simple_spinner_item);

        //设置下拉列表的风格,simple_spinner_dropdown_item是android系统自带的样式，等会自定义修改
        // 第一个参数为Context对象
        // 第二个参数为要显示的数据源,也就是在string.xml配置的数组列表
        // 第三个参数为设置Spinner的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 设置Spinner数据来源适配器  将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        //spinner.setDropDownHorizontalOffset(14);
        //// 使用内部类形式来实现事件监听 添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
              //  Toast.makeText(getApplicationContext(),str, Toast.LENGTH_LONG) .show();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //initView();
    }

    /**
     * 初始化数据
     */
    protected void initData(){
        Glide.get(this).clearMemory();//清除图片缓存
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FunctioInfo info = new FunctioInfo(Integer.valueOf(1),"于2018-9-21日召开业主大会",new Date(),"http://i1.umei.cc/uploads/tu/201807/9999/551a03416e.jpg","1");
        FunctioInfo info1 = new FunctioInfo(Integer.valueOf(2),"开始缴纳停车费",new Date(),"http://i1.umei.cc/uploads/tu/201608/72/mckdtwhbwfe.jpg","1");
        conversationList.add(info);
        conversationList.add(info1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                contact.setAdapter(new PolicyNoticeAdpter(conversationList,PolicyNoticeActivity.this));

            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(PolicyNoticeActivity.this);
                break;
            case R.id.push_button:
                Utils.start_Activity(PolicyNoticeActivity.this, PushInfoActivity.class,new BasicNameValuePair("infoType","PolicyNotice"));
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.print(view.getId());
    }


    @Override
    protected void initControl() {
        View  topView = (View)findViewById(R.id.policy_top);//layout顶部
        //返回图标显示、标题显示、搜索框隐藏、搜索图片隐藏
        //顶部样式初始化

        image_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);
        editText = (EditText)findViewById(R.id.editText);//该active输入框

        push_button = (Button) findViewById(R.id.push_button);
        push_button.setVisibility(View.VISIBLE);
        txt_title.setText(R.string.policyNotice);//设置app标题

        image_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
        creatSpinner();
    }

    @Override
    protected void setListener() {
        image_back.setOnClickListener(this);
        push_button.setOnClickListener(this);

    }
}
