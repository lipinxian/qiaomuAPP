package com.arborcommunity.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.adpter.PublicBankAdapter;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.bean.PublicBank;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/12/22.
 */
public class PublicBankActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ImageView img_back;
    private TabHost tabHost;
    private ListView family_service;
    private ListView pet_care;
    private ListView community_volunteer;
    private ImageView image_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_public_spirited);
        super.onCreate(savedInstanceState);
    }



    @Override
    protected void initView() {
        img_back = (ImageView)findViewById(R.id.img_back);
        txt_title = (TextView)findViewById(R.id.txt_title);
        family_service = (ListView)findViewById(R.id.family_service);
        pet_care = (ListView)findViewById(R.id.pet_care);
        community_volunteer = (ListView)findViewById(R.id.community_volunteer);
        mess_type = (TextView)findViewById(R.id.mess_type);//消息类型
        push_button= (Button)findViewById(R.id.push_button);//发帖按钮
        img_back.setVisibility(View.VISIBLE);
        txt_title.setText("公益银行");
        tabHost = (TabHost)findViewById(R.id.tbhost);
        tabHost.setup();
    }

    @Override
    protected void initControl() {
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("家庭服务").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("宠物看护").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("社区义工").setContent(R.id.tab03));
        reDefingTabwidget();
        tabHost.setCurrentTabByTag("tab1");
        updateTab(tabHost);
    }


    @Override
    protected void initData() {
        Glide.get(this).clearMemory();//清除图片缓存
        inintFamilyService();
    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);
        push_button.setOnClickListener(this);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                tabHost.setCurrentTabByTag(tabId);
                updateTab(tabHost);
                if (tabId.equals("tab1")) {   //第一个标签
                    inintFamilyService();
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    initPetCare();
                }
                if (tabId.equals("tab3")) {
                    initCommunityVolunteer();//第三个标签
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(PublicBankActivity.this);
                break;
            case R.id.push_button:
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void reDefingTabwidget(){
        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            View child = tabWidget.getChildAt(i);

            final TextView tv = (TextView)child.findViewById(android.R.id.title);
            tv.setTextSize(16);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0); //取消文字底边对齐
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); //设置文字居中对齐
            child.getLayoutParams().height=150;

        }
    }

    /**
     * 更改 背景颜色
     * @param tabHost
     */
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            //tv.setTextSize(16);
           // tv.setTypeface(Typeface.SERIF, 2); // 设置字体和风格
            if (tabHost.getCurrentTab() == i) {//选中
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_red2));//选中后的背景
               // tv.setTextColor(this.getResources().getColorStateList(android.R.color.black));
            } else {//不选中
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_gray2));//非选择的背景
               // tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
            }
        }
    }

    private void inintFamilyService(){
        List<PublicBank> list = new ArrayList<>();
        list.add(new PublicBank("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=662571b8962397ddc274905638ebd9d2/9a504fc2d5628535e6c09a7e97ef76c6a7ef6348.jpg","家具安装","5"));
        list.add(new PublicBank("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=662571b8962397ddc274905638ebd9d2/9a504fc2d5628535e6c09a7e97ef76c6a7ef6348.jpg","电器维修","5"));
        family_service.setAdapter(new PublicBankAdapter(list,PublicBankActivity.this));
    }

    private void initPetCare(){
        List<PublicBank> list = new ArrayList<>();
        list.add(new PublicBank("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=7d8f9c10751ed21b6dc426b7cc07b6a1/42166d224f4a20a4bc38312598529822720ed02a.jpg","宠物金毛散步","5"));
        list.add(new PublicBank("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545674014019&di=7bc642fd18bf3897f5f2eaac5f937b85&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F314e251f95cad1c8d09ef1ee753e6709c83d51d5.jpg","宠物猫打疫苗","5"));
        pet_care.setAdapter(new PublicBankAdapter(list,PublicBankActivity.this));
    }

    private void initCommunityVolunteer(){
        List<PublicBank> list = new ArrayList<>();
        list.add(new PublicBank("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=0953765f57fbb2fb202650402e234bc1/bd315c6034a85edfdd0c80214b540923dd54752b.jpg","照看老人","5"));
        list.add(new PublicBank("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=c5c46b3c8a13632701e0ca61f0e6cb89/8d5494eef01f3a29f9ee222e9925bc315c607c28.jpg","陪独居老人散心","5"));
        community_volunteer.setAdapter(new PublicBankAdapter(list,PublicBankActivity.this));
    }


}
