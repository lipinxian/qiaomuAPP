package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arborcommunity.Constants;
import com.arborcommunity.R;
import com.arborcommunity.adpter.CommunithAdpter;
import com.arborcommunity.common.Page;
import com.arborcommunity.common.PageUtils;
import com.arborcommunity.common.Utils;
import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.LoadListView;
import com.arborcommunity.view.bean.Area;
import com.arborcommunity.view.bean.Community;
import com.arborcommunity.view.bean.ProvinceCityData;
import com.arborcommunity.widget.AbstractSpinerAdapter;
import com.arborcommunity.widget.CustemObject;
import com.arborcommunity.widget.CustemSpinerAdapter;
import com.arborcommunity.widget.SpinerPopWindow;
import com.juns.health.net.loopj.android.http.RequestParams;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2018/12/12.
 * 我的社区选择页面
 */
public class CommunityApplyActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener,
        LoadListView.ILoadListener {


    private ImageView image_back;
    private TextView lay_my_title;
    private LoadListView contact;

    private ImageView search_img;
    private EditText search_content;

    //存放省份名称
    List<CustemObject> provinceName = new ArrayList<>();
    List<CustemObject> cityName = new ArrayList<>();
    //接收数据
    List<ProvinceCityData> provinceCityDataList = new ArrayList<>();
    private AbstractSpinerAdapter provcinceAdapter;
    private SpinerPopWindow provcinceSpinerPopWindow;
    private AbstractSpinerAdapter cityAdapter;
    private SpinerPopWindow citySpinerPopWindow;

    private TextView province_text;
    private TextView city_text;

    private List<CustemObject> nameList = new ArrayList<CustemObject>();
    List<Community> deptList = new ArrayList<>();
    List<Area> areaList = new ArrayList<>();

    Map<String,CustemObject> cityMap = new HashMap<String,CustemObject>();

    String currPage = "1";
    int totalCount,totalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_community_apply);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initControl() {
        provcinceAdapter = new CustemSpinerAdapter(this);
        provcinceSpinerPopWindow =  new SpinerPopWindow(this,417);


        cityAdapter = new CustemSpinerAdapter(this);
        citySpinerPopWindow =  new SpinerPopWindow(this,417);
    }

    @Override
    protected void initView() {
        image_back = (ImageView)findViewById(R.id.img_back);
        image_back.setVisibility(View.VISIBLE);
        lay_my_title = (TextView)findViewById(R.id.txt_title);
        lay_my_title.setText("我的社区");
        contact = (LoadListView)findViewById(R.id.community_List);

        search_img = (ImageView)findViewById(R.id.search_img);
        search_content =(EditText)findViewById(R.id.search_content);

        province_text = (TextView)findViewById(R.id.province_text);//省份
        city_text = (TextView)findViewById(R.id.city_text);//chengs

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
                searchCommunity(currPage,"");
            }
            @Override
            public void onMyFailure(String data) {

            }
        });


    }

    @Override
    protected void setListener(){
        image_back.setOnClickListener(this);
        search_img.setOnClickListener(this);

        province_text.setOnClickListener(this);
        city_text.setOnClickListener(this);

        contact.setOnItemClickListener(this);
        contact.setInterface(this);

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
                    searchCommunity(currPage,value.toString());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(CommunityApplyActivity.this);
                break;
            case R.id.search_img:
                searchCommunity(currPage,"");
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
     * ListView选中事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Community dept = deptList.get(position);
        Intent intent = new Intent();
        intent.putExtra("community",JSON.toJSONString(dept));
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onLoad() {
        //获取更多数据
        if(!String.valueOf(totalPage).equals(currPage) ){
            searchCommunity(String.valueOf(Integer.valueOf(currPage)+1),city_text.getText().toString());
        }
        contact.loadComplete();

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
                searchCommunity(currPage,cityName.get(0).toString());
            }
            @Override
            public void onMyFailure(String data) {

            }
        });
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
     * 根据名称分页查询社区
     * @param page 当前页
     * @param cityName 城市名称
     */
    private void searchCommunity(String page,String cityName){
        String name= search_content.getText().toString();
        Map<String,String> params = new HashMap<>();
        params.put("limit","20");
        params.put("page",page);
        params.put("name",name);
        params.put("cityName",cityName);
        if(page.equals("1")){
            deptList.clear();
        }
        //查询社区
        if(name != "" && name != null){

            netClient.post(Constants.getCommunityURL,new RequestParams(params),new BaseJsonRes(){
                @Override
                public void onMySuccess(String data) {
                    System.out.println(data);
                    JSONObject jsonObject =  JSONObject.parseObject(data);
                    totalCount = Integer.valueOf(jsonObject.getString("totalCount"));
                    totalPage = Integer.valueOf(jsonObject.getString("totalPage"));
                    currPage  = jsonObject.getString("currPage");
                    deptList.addAll(JSON.parseArray(jsonObject.getString("list"),Community.class));
                    contact.setAdapter(new CommunithAdpter(deptList,CommunityApplyActivity.this));
                }
                @Override
                public void onMyFailure(String data) {

                }
            });

        }else {
            Utils.showLongToast(CommunityApplyActivity.this, "请输入社区名称");
        }

    }
}
