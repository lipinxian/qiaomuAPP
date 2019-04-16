package com.arborcommunity.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.arborcommunity.Constants;
import com.arborcommunity.R;
import com.arborcommunity.common.Utils;
import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.bean.Area;
import com.arborcommunity.view.bean.ProvinceCityData;
import com.arborcommunity.widget.AbstractSpinerAdapter;
import com.arborcommunity.widget.CustemObject;
import com.arborcommunity.widget.CustemSpinerAdapter;
import com.arborcommunity.widget.SpinerPopWindow;
import com.juns.health.net.loopj.android.http.RequestParams;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 李品先
 * @Date: 2019/1/21 16:35
 * @Description: 新增社区
 */
public class AddCommunityActivity extends BaseActivity implements View.OnClickListener {


    private ImageView img_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;


    private Button add_community_button;
    private TextView province_text;
   // private ImageButton province_relative_img;
    private TextView city_text;
   // private ImageButton city_relative_img;
    private EditText add_community_name;
    private EditText add_community_address;


    //存放省份名称
    List<CustemObject> provinceName = new ArrayList<>();
    List<CustemObject> cityName = new ArrayList<>();

    //接收数据
    List<ProvinceCityData> provinceCityDataList = new ArrayList<>();
    private AbstractSpinerAdapter provcinceAdapter;
    private SpinerPopWindow provcinceSpinerPopWindow;
    private AbstractSpinerAdapter cityAdapter;
    private SpinerPopWindow citySpinerPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_community);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        //top的view
        img_back = (ImageView)findViewById(R.id.img_back);
        txt_title = (TextView)findViewById(R.id.txt_title);
        mess_type = (TextView)findViewById(R.id.mess_type);
        push_button = (Button)findViewById(R.id.push_button);
        img_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText("新增社区");
        mess_type.setVisibility(View.GONE);
        push_button.setVisibility(View.GONE);

        //
        add_community_button = (Button)findViewById(R.id.add_community_button);//新增社区按钮
        province_text = (TextView)findViewById(R.id.province_text);//省份
        city_text = (TextView)findViewById(R.id.city_text);//chengs
        add_community_name = (EditText)findViewById(R.id.add_community_name);//社区名称
        add_community_name.clearFocus();
        add_community_address = (EditText)findViewById(R.id.add_community_address);
        add_community_address.clearFocus();
   //


    }

    @Override
    protected void initControl() {
        provcinceAdapter = new CustemSpinerAdapter(this);
        provcinceSpinerPopWindow =  new SpinerPopWindow(this,417);


        cityAdapter = new CustemSpinerAdapter(this);
        citySpinerPopWindow =  new SpinerPopWindow(this,417);
    }

    @Override
    protected void initData() {
        //初始化省份数据
        netClient.post(Constants.getProvince,null,new BaseJsonRes(){
            @Override
            public void onMySuccess(String data) {
                provinceCityDataList = JSON.parseArray(data,ProvinceCityData.class);
                for(int i = 0; i < provinceCityDataList.size(); i++){
                    CustemObject object = new CustemObject();
                    object.data = provinceCityDataList.get(i).getProvinceName();
                    provinceName.add(object);
                }
                provcinceAdapter.refreshData(provinceName, 0);
                provcinceSpinerPopWindow.setAdatper(provcinceAdapter);
                province_text.setText(provinceName.get(0).toString());

            }
            @Override
            public void onMyFailure(String data) {

            }
        });
    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);
        add_community_button.setOnClickListener(this);
        province_text.setOnClickListener(this);
        city_text.setOnClickListener(this);

        //省份下拉框点击选中事件
        provcinceSpinerPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener(){
            @Override
            public void onItemClick(View view, int pos, long arg3) {
                if (pos >= 0 && pos <= provinceName.size()){
                    CustemObject value = provinceName.get(pos);
                    province_text.setText(value.toString());
                    getCityData(value.toString());
                }
            }
        });
        //城市下拉框选中点击
        citySpinerPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener(){
            @Override
            public void onItemClick(View view, int pos, long arg3) {
                if (pos >= 0 && pos <= cityName.size()){
                    CustemObject value = cityName.get(pos);
                    city_text.setText(value.toString());

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(AddCommunityActivity.this);
                break;
            case R.id.add_community_button:
                addCommunityInfo();
                break;
            case R.id.province_text:
                showProvinceSpinWindow();
                break;
            case R.id.city_text:
                showCitySpinWindow();
            default:
                break;
        }
    }


    /**
     * 省份下拉框选中
     */
    private void showProvinceSpinWindow(){
        provcinceSpinerPopWindow.setWidth(province_text.getWidth());
        provcinceSpinerPopWindow.showAsDropDown(province_text);
    }

    /**
     * 城市下拉框选中
     */
    private void showCitySpinWindow(){
        citySpinerPopWindow.setWidth(city_text.getWidth());
        citySpinerPopWindow.showAsDropDown(city_text);
    }

    /**
     * 获取城市信息
     * @param name
     */
    private void getCityData(String name){
        RequestParams params = new RequestParams();
        params.put("provinceName",name);
        netClient.post(Constants.getCityByProvince,params,new BaseJsonRes(){
            @Override
            public void onMySuccess(String data) {
                cityName.clear();
                List<ProvinceCityData> cityDataList = JSON.parseArray(data,ProvinceCityData.class);
                for(int i = 0; i < cityDataList.size(); i++){
                    CustemObject object = new CustemObject();
                    object.data = cityDataList.get(i).getCityName();
                    cityName.add(object);

                }
                cityAdapter.refreshData(cityName, 0);
                citySpinerPopWindow.setAdatper(cityAdapter);
                city_text.setText(cityName.get(0).toString());
            }
            @Override
            public void onMyFailure(String data) {

            }
        });
    }

    private void addCommunityInfo(){
        if(add_community_name.getText().toString()!= null && !"".equals(add_community_name.getText().toString())){
          //  getLoadingDialog("社区名称不为空").dismiss();
            RequestParams params = new RequestParams();
            params.put("cityName",city_text.getText().toString());
            params.put("provinceName",province_text.getText().toString());
            params.put("communityName",add_community_name.getText().toString());
            params.put("address",add_community_address.getText().toString());
            netClient.post(Constants.addCommunityInfo,params,new BaseJsonRes(){
                @Override
                public void onMySuccess(String data) {
                    if(data.equals("OK")){
                        Utils.finish(AddCommunityActivity.this);
                    }
                }
                @Override
                public void onMyFailure(String data) {

                }
            });
        }else {
            Utils.showLongToast(AddCommunityActivity.this, "社区名称不为空");
        }
    }


}
