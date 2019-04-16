package com.arborcommunity.view.activity;

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
 * Created by pc on 2018/9/12.
 * 闲置分享
 */
public class IdleShareActivity  extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private TextView textView;
    private ImageView imageView;
    private TextView chiceText ;
    private Spinner spinner;
    private ArrayAdapter adapter;
    private EditText editText;
    private ImageView image_back;
    private ListView contact;
    private TextView txt_title;
    private Button push_button;
    private List<FunctioInfo> conversationList = new ArrayList<FunctioInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_policynotice);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initControl() {
      //  View  topView = (View)findViewById(R.id.policy_top);//layout顶部
        //返回图标显示、标题显示、搜索框隐藏、搜索图片隐藏
        //顶部样式初始化
        image_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);
        editText = (EditText)findViewById(R.id.editText);//该active输入框
        txt_title.setText(R.string.idleshare);//设置app标题
        image_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);

        push_button = (Button) findViewById(R.id.push_button);
        push_button.setVisibility(View.VISIBLE);

        creatSpinner();
    }

    @Override
    protected void initView() {
        contact = (ListView)findViewById(R.id.policListView);
    }

    @Override
    protected void initData() {
        Glide.get(this).clearMemory();//清除图片缓存
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FunctioInfo info = new FunctioInfo(Integer.valueOf(1),"婴儿床九成新半价出售",new Date(),"http://i1.umei.cc/uploads/tu/201807/9999/551a03416e.jpg","1");
        FunctioInfo info1 = new FunctioInfo(Integer.valueOf(2),"儿童餐桌免费送，自提",new Date(),"http://i1.umei.cc/uploads/tu/201608/72/mckdtwhbwfe.jpg","1");
        conversationList.add(info);
        conversationList.add(info1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                contact.setAdapter(new PolicyNoticeAdpter(conversationList,IdleShareActivity.this));
                /*if (GloableParams.ListGroupInfos != null && GloableParams.ListGroupInfos.size() > 0) {
                    mlistview.setAdapter(new MyGroupAdpter(
                            GroupListActivity.this, GloableParams.ListGroupInfos));
                } else {
                    TextView txt_nodata = (TextView) findViewById(R.id.txt_nochat);
                    txt_nodata.setText("暂时没有群聊");
                    txt_nodata.setVisibility(View.VISIBLE);
                }*/
            }
        }).start();
    }

    @Override
    protected void setListener() {
        image_back.setOnClickListener(this);
        push_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(IdleShareActivity.this);
                break;
            case R.id.push_button:
                Utils.start_Activity(IdleShareActivity.this, PushInfoActivity.class,new BasicNameValuePair("infoType","IdleShare"));
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void creatSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.data, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
    }
}
