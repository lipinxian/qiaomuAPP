package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arborcommunity.Constants;
import com.arborcommunity.R;
import com.arborcommunity.adpter.GridAdapter;
import com.arborcommunity.adpter.PhotoAdapter;
import com.arborcommunity.adpter.RecyclerItemClickListener;
import com.arborcommunity.common.UploadUtil;
import com.arborcommunity.common.Utils;
import com.arborcommunity.view.BaseActivity;
import com.bumptech.glide.util.Util;

import com.juns.health.net.loopj.android.http.RequestParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


/**
 * Created by pc on 2018/12/30.
 * 发布信息
 */
public class PushInfoActivity extends BaseActivity implements View.OnClickListener{

    private Button submit_push;
    private EditText infoTile;
    private EditText et_usertel;

    private TextView txt_who;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    private RecyclerView recyclerView;

    private ImageView image_back;
    private TextView txt_title;
    private TextView mess_type;
    private Button push_button;

    private String infoType;//消息类型数据
    private String chooesShow = "1";
    private int chooceTypeResponCode = 10000;//用户选择信息公布返回参数Code
    private boolean verifyIndentityInfo = false;//用户认证信息判断


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_push_info);
        super.onCreate(savedInstanceState);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收消息值
        infoType = bundle.getString("infoType");

    }

    @Override
    protected void initView() {

        image_back = (ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);
        push_button = (Button) findViewById(R.id.push_button);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText("发帖");

        image_back.setVisibility(View.VISIBLE);
        push_button.setVisibility(View.GONE);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        photoAdapter = new PhotoAdapter(this, selectedPhotos,9);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        submit_push = (Button)findViewById(R.id.submit_push);
        infoTile = (EditText)findViewById(R.id.infoTile);
        et_usertel= (EditText)findViewById(R.id.et_usertel);
        txt_who = (TextView)findViewById(R.id.txt_who);
    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void initData() {
        String name = Utils.getValue(context, Constants.User_ID);
        if(name!=null){
            verifyIndentityInfo = Utils.verifyIndentity(name,netClient);
        }

    }

    @Override
    protected void setListener() {
        submit_push.setOnClickListener(this);
        infoTile.setOnClickListener(this);
        image_back.setOnClickListener(this);
        txt_who.setOnClickListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == 1) {
                            PhotoPicker.builder()
                                    .setPhotoCount(9)
                                    .setShowCamera(false)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(PushInfoActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(PushInfoActivity.this);
                        }
                    }
                })
        );
        submit_push.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(PushInfoActivity.this);
                break;
            case R.id.submit_push://发布信息
                pushInfo();
              //  Utils.finish(PushInfoActivity.this);
                break;
            case R.id.txt_who://谁可见
                Intent intent = new Intent(this, VisibleChooesActivity.class);
                this.startActivityForResult(intent,chooceTypeResponCode);
                this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            default:
                break;
        }
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

    private void pushInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //上传图片
                List<String> ids =new ArrayList<String>();
                for(String path : selectedPhotos){
                    try {
                        File file = new File(path);

                        ids.add(UploadUtil.uploadImage(file, Constants.upFile));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                RequestParams params = new RequestParams();
                String name = Utils.getValue(context, Constants.User_ID);
                System.out.println(name);
                if(null != name){
                    params.put("name",name);

                }else {
                    getLoadingDialog("请先通过身份验证").dismiss();
                }


            }
        }).start();
    }





}
