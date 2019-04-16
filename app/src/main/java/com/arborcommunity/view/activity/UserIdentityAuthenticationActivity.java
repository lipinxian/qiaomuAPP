package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arborcommunity.Constants;
import com.arborcommunity.R;
import com.arborcommunity.adpter.PhotoAdapter;
import com.arborcommunity.adpter.RecyclerItemClickListener;
import com.arborcommunity.common.CheckBoxUtil;
import com.arborcommunity.common.UploadUtil;
import com.arborcommunity.common.UploadUtilThread;
import com.arborcommunity.common.Utils;
import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.view.bean.Community;
import com.juns.health.net.loopj.android.http.RequestParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * @Author: 李品先
 * @Date: 2019/4/9 23:21
 * @Description: 用户身份认证
 */
public class UserIdentityAuthenticationActivity extends BaseActivity implements View.OnClickListener{

    private ImageView img_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;

    private TextView realityName;//真实姓名
    private String identityCode;//身份证
    private TextView address;
    private TextView communityChose;

    private CheckBox checkBox1;
    private CheckBox checkBox2;

    private Button add_community_button;//提交审核

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    private RecyclerView identityImg;
    private List<CheckBox> radios = new ArrayList<CheckBox>(); // 单选组
    private List<String> photos =  new ArrayList<String>();
    private Community community =  new Community();
    private int chooceTypeResponCode = 10000;//用户选择信息公布返回参数Code

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_user_identity_authentication);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        img_back = (ImageView)findViewById(R.id.img_back);
        txt_title= (TextView)findViewById(R.id.txt_title);
        push_button = (Button)findViewById(R.id.push_button);
        mess_type = (TextView)findViewById(R.id.mess_type);

        checkBox1 = (CheckBox) findViewById(R.id.radio1);
        checkBox2  = (CheckBox) findViewById(R.id.radio2);
        radios.add(checkBox1);
        radios.add(checkBox2);
        communityChose = (TextView)findViewById(R.id.communityChose);
        add_community_button = (Button)findViewById(R.id.add_community_button);

        identityImg = findViewById(R.id.identityImg);

    }

    @Override
    protected void initControl() {
        img_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(R.string.add_community_number);
        mess_type.setVisibility(View.GONE);
        push_button.setVisibility(View.GONE);


        identityImg.setHasFixedSize(false);
        photoAdapter = new PhotoAdapter(this, selectedPhotos,1);
        identityImg.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        identityImg.setAdapter(photoAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);

        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);

        communityChose.setOnClickListener(this);
        add_community_button.setOnClickListener(this);

        //图片
        identityImg.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == 1) {
                            PhotoPicker.builder()
                                    .setPhotoCount(1)
                                    .setShowCamera(false)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(UserIdentityAuthenticationActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(UserIdentityAuthenticationActivity.this);
                        }
                    }
                })
        );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(UserIdentityAuthenticationActivity.this);
                break;
            case R.id.radio1:
                checkBox(v);
                break;
            case R.id.radio2:
                checkBox(v);
                break;
            case R.id.communityChose:
                Intent intent = new Intent(this, CommunityApplyActivity.class);
                this.startActivityForResult(intent,chooceTypeResponCode);
                this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.add_community_button:
                verifyCommunityInfo();
                break;
            default:
                break;
        }
    }

    //单选
    public void checkBox(View v){
        CheckBox checkBox = (CheckBox) v;
        CheckBoxUtil.unCheck(radios);
        checkBox.setChecked(true);
        /*if(checkBox.getId() == R.id.radio1){
            checkBox1.setChecked(true);
            checkBox2.setChecked(false);
        }else {
            checkBox1.setChecked(false);
            checkBox2.setChecked(true);
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }else if(resultCode == RESULT_OK && requestCode == chooceTypeResponCode){
            if(data != null){
                community = JSONObject.parseObject(data.getStringExtra("community"),Community.class);
                communityChose.setText("社区选择        "+community.getName());
            }
        }
    }


    private void verifyCommunityInfo(){
        //信息验证
        realityName = (TextView)findViewById(R.id.realityName);
        if("".equals(realityName.getText().toString())){
            Utils.showLongToast(UserIdentityAuthenticationActivity.this, "请输入真实姓名");
            return;
        }
        TextView indent_10 = (TextView)findViewById(R.id.left_10);
        TextView right_4 = (TextView)findViewById(R.id.right_4);
        String indenty = indent_10.getText().toString()+"****"+right_4.getText().toString();
        if(indenty.length()<18){
            Utils.showLongToast(UserIdentityAuthenticationActivity.this, "请确认身份证信息");
            return;
        }
        EditText address = (EditText)findViewById(R.id.address);
        if(address.getText().toString() == null || "".equals(address.getText().toString())){
            Utils.showLongToast(UserIdentityAuthenticationActivity.this, "请填写地址");
            return;
        }
        String sex = CheckBoxUtil.getOne(radios);
        if("".equals(sex) || null == sex){
            Utils.showLongToast(UserIdentityAuthenticationActivity.this, "请选择性别");
            return;
        }

        if(community ==null){
            Utils.showLongToast(UserIdentityAuthenticationActivity.this, "请选择社区");
            return;
        }
        if(selectedPhotos.size()==0){
            Utils.showLongToast(UserIdentityAuthenticationActivity.this, "请选择图片");
            return;
        }
        RequestParams params = new RequestParams();
        UploadUtilThread uploadUtilThread = new UploadUtilThread(selectedPhotos.get(0), Constants.upFile);
        Thread thread = new Thread(uploadUtilThread);
        thread.start();
        try {
            thread.join();
            params.put("pathId",uploadUtilThread.pathId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = Utils.getValue(context, Constants.NAME);//用户名(手机号码)
        params.put("realName",realityName.getText().toString());
        params.put("identityInfo",indenty);
        params.put("address",address.getText().toString());
        params.put("sex",sex);
        params.put("communityId",community.getId().toString());
        params.put("phone", name);

        netClient.post(Constants.addCommunityMember,params,new BaseJsonRes(){
            @Override
            public void onMySuccess(String data) {
                System.out.println(data);
                Utils.showLongToast(UserIdentityAuthenticationActivity.this, "申请已提交");
                Utils.finish(UserIdentityAuthenticationActivity.this);
            }
            @Override
            public void onMyFailure(String data) {
                System.out.println("UserIdentityAuthenticationActivity.onMyFailure"+ data);
            }
        });
    }

}
