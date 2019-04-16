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
 * Created by pc on 2018/9/13.
 * 爱好兴趣交流
 */
public class InterestActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
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
        setContentView(R.layout.activity_interest);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initControl() {
        //顶部样式初始化
        image_back =(ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);
        editText = (EditText)findViewById(R.id.editText);//该active输入框
        txt_title.setText(R.string.shareInformation);//设置app标题
        image_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);

        push_button = (Button) findViewById(R.id.push_button);
        push_button.setVisibility(View.VISIBLE);

        creatSpinner();
    }

    @Override
    protected void initView() {
        contact = (ListView)findViewById(R.id.shareInfo);
    }

    @Override
    protected void initData() {
        Glide.get(this).clearMemory();//清除图片缓存
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FunctioInfo info = new FunctioInfo(Integer.valueOf(1),"广场舞聚集地",new Date(),"http://i1.umei.cc/uploads/tu/201807/9999/551a03416e.jpg","1");
        FunctioInfo info1 = new FunctioInfo(Integer.valueOf(2),"儿童绘画兴趣组团",new Date(),"http://i1.umei.cc/uploads/tu/201608/72/mckdtwhbwfe.jpg","1");
        conversationList.add(info);
        conversationList.add(info1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                contact.setAdapter(new PolicyNoticeAdpter(conversationList,InterestActivity.this));
            }
        }).start();
    }

    @Override
    protected void setListener() {
        findViewById(R.id.fithessDance).setOnClickListener(this);
        findViewById(R.id.chess).setOnClickListener(this);
        findViewById(R.id.musical).setOnClickListener(this);
        findViewById(R.id.chat).setOnClickListener(this);
        findViewById(R.id.walk).setOnClickListener(this);
        findViewById(R.id.travel).setOnClickListener(this);
        push_button.setOnClickListener(this);
        image_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(InterestActivity.this);
                break;
            case R.id.fithessDance:

                break;
            case R.id.chess:

                break;
            case R.id.musical:
                Utils.start_Activity(InterestActivity.this, MoneyActivity.class);
                break;
            case R.id.chat:

                break;
            case R.id.walk:

                break;
            case R.id.travel:

                break;
            case R.id.push_button:
                Utils.start_Activity(InterestActivity.this, PushInfoActivity.class,new BasicNameValuePair("infoType","Interest"));
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
               // Toast.makeText(getApplicationContext(),str, Toast.LENGTH_LONG) .show();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}
