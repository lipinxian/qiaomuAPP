package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.arborcommunity.Constants;
import com.arborcommunity.R;
import com.arborcommunity.adpter.PhotoAdapter;
import com.arborcommunity.adpter.RecyclerItemClickListener;
import com.arborcommunity.common.UploadUtil;
import com.arborcommunity.common.Utils;

import com.arborcommunity.net.BaseJsonRes;
import com.arborcommunity.view.BaseActivity;
import com.arborcommunity.widget.AbstractSpinerAdapter;
import com.arborcommunity.widget.CustemObject;
import com.arborcommunity.widget.CustemSpinerAdapter;
import com.arborcommunity.widget.SpinerPopWindow;
import com.juns.health.net.loopj.android.http.RequestParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * @Author: 李品先
 * @Date: 2019/1/28 15:55
 * @Description: 新增社区成员
 */
public class AddCommunityMemberActivity extends BaseActivity implements View.OnClickListener{

    private ImageView img_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;


    private TextView role_type;
    List<CustemObject> roleName = new ArrayList<>();
    private AbstractSpinerAdapter roleAdapter;
    private SpinerPopWindow roleSpinerPopWindow;

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    private RecyclerView identityImg;

    private RelativeLayout identity_layout;
    private Button add_community_button;//提交审核

    private String chooesShow = "1";
    private int chooceTypeResponCode = 10000;//用户选择信息公布返回参数Code

    String communityName =  "";
    String communityId = "";

    private EditText  add_member_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_community_member);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        img_back = (ImageView)findViewById(R.id.img_back);
        txt_title = (TextView)findViewById(R.id.txt_title);
        mess_type = (TextView)findViewById(R.id.mess_type);
        push_button = (Button)findViewById(R.id.push_button);
        img_back.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(R.string.add_community_number);
        mess_type.setVisibility(View.GONE);
        push_button.setVisibility(View.GONE);


        role_type = (TextView)findViewById(R.id.role_type);//社区角色
        identity_layout =(RelativeLayout)findViewById(R.id.identity_layout);
        identityImg = findViewById(R.id.identityImg);
        identityImg.setHasFixedSize(false);
        photoAdapter = new PhotoAdapter(this, selectedPhotos,1);
        identityImg.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        identityImg.setAdapter(photoAdapter);

        add_community_button = (Button)findViewById(R.id.add_community_button);
    }

    @Override
    protected void initControl() {
        roleAdapter = new CustemSpinerAdapter(this);
        roleSpinerPopWindow =  new SpinerPopWindow(this,250);
    }

    @Override
    protected void initData() {

        roleName.add(new CustemObject("管理员"));
        roleName.add(new CustemObject("成员"));
        roleAdapter.refreshData(roleName, 0);
        roleSpinerPopWindow.setAdatper(roleAdapter);
        role_type.setText(roleName.get(0).toString());

        //接收消息值
        Bundle bundle = this.getIntent().getExtras();
        communityName = bundle.getString("communityName");
        communityId = bundle.getString("communityId");
        add_member_name= (EditText)findViewById(R.id.add_member_name);

        add_member_name.setText(communityName);
    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);
        role_type.setOnClickListener(this);
        add_community_button.setOnClickListener(this);

        //角色选中点击
        roleSpinerPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener(){
            @Override
            public void onItemClick(View view, int pos, long arg3) {
                if (pos >= 0 && pos <= roleName.size()){
                    CustemObject value = roleName.get(pos);
                    role_type.setText(value.toString());
                    if(value.toString().equals("管理员")){
                        identity_layout.setVisibility(View.VISIBLE);
                    }else {
                        identity_layout.setVisibility(View.GONE);
                    }
                }
            }
        });


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
                                    .start(AddCommunityMemberActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(AddCommunityMemberActivity.this);
                        }
                    }
                })
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
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
                chooesShow = data.getStringExtra("type");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(AddCommunityMemberActivity.this);
                break;
            case R.id.role_type:
                showRoleSpinWindow();
                break;
            case R.id.add_community_button:
                verifyCommunityInfo();
                break;
            default:
                break;
        }

    }
    private void showRoleSpinWindow(){
        roleSpinerPopWindow.setWidth(role_type.getWidth());
        roleSpinerPopWindow.showAsDropDown(role_type);
    }

    private void verifyCommunityInfo(){
        //信息验证
        String roleName = role_type.getText().toString();
        if(roleName.equals("管理员")){
            if (selectedPhotos.size() == 0){
                Utils.showLongToast(AddCommunityMemberActivity.this, "请选择证件照片");
            }else {
                RequestParams params = new RequestParams();
                params.put("communityId",communityId);
                params.put("communityName",communityName);
                netClient.post(Constants.findCommunityAdministrator,params,new BaseJsonRes(){
                    @Override
                    public void onMySuccess(String data) {
                        System.out.println(data);
                     //   JSONObject jsonObject =  JSONObject.parseObject(data);
                        if(data.equals("ok")){
                            upCommunityInfo("1");
                        }else {
                            Utils.showLongToast(AddCommunityMemberActivity.this, data);
                        }
                    }
                    @Override
                    public void onMyFailure(String data) {

                    }
                });
            }

        }else {
            upCommunityInfo("2");
        }
    }


    public void upCommunityInfo(String type){
        EditText realName= (EditText)findViewById(R.id.realityName);
        if("".equals(realName.getText().toString())){
            Utils.showLongToast(AddCommunityMemberActivity.this, "请填写真实姓名");
        }else {

            RequestParams params = new RequestParams();

            if (type.equals("1")) {
                //上传图片
                String id = "";
                for (String path : selectedPhotos) {
                    try {
                        File file = new File(path);

                        id = UploadUtil.uploadImage(file, Constants.upFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                params.put("path", id);//图片路径
            }
            String name = Utils.getValue(context, Constants.User_ID);//用户名(手机号码)
            EditText identityInfo = (EditText)findViewById(R.id.identityInfo);
            EditText address = (EditText)findViewById(R.id.address);
            params.put("phone", name);
            params.put("communityId", communityId);
            params.put("roleType", role_type.getText().toString());
            params.put("realName", realName.getText().toString());
            params.put("address",address.getText().toString());
            params.put("identityInfo",identityInfo.getText().toString());
            netClient.post(Constants.addCommunityMember,params,new BaseJsonRes(){
                @Override
                public void onMySuccess(String data) {
                    System.out.println(data);
                    Utils.showLongToast(AddCommunityMemberActivity.this, "申请已提交，如有问题请联系社区管理员或平台客服");
                    Utils.finish(AddCommunityMemberActivity.this);
                }
                @Override
                public void onMyFailure(String data) {

                }
            });
        }
    }
}
