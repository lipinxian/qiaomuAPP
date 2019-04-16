package com.arborcommunity.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.arborcommunity.R;
import com.arborcommunity.view.BaseActivity;


/**
 * Created by pc on 2019/1/9.
 */
public class VisibleChooesActivity extends BaseActivity implements View.OnClickListener{

    private ImageView img_back;
    private TextView txt_title;
    private Button push_button;
    private RadioGroup radioGroup;

    private String type ="1";//1:公开，0：仅本社区

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_visible_choose);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        img_back = (ImageView)findViewById(R.id.img_back);//返回样式图片
        txt_title = (TextView)findViewById(R.id.txt_title);
        push_button = (Button) findViewById(R.id.push_button);
        txt_title.setVisibility(View.VISIBLE);
        img_back.setVisibility(View.VISIBLE);
        push_button.setVisibility(View.VISIBLE);
        txt_title.setText("谁可以看");
        push_button.setText("完成");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        img_back.setOnClickListener(this);
        push_button.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectRadioButton();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                chooesInfo();
                break;
            case R.id.push_button:
                chooesInfo();
                break;
            default:
                break;
        }
    }

    private void selectRadioButton(){
        RadioButton rb = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        String name = rb.getText().toString();
        if(name.equals("公开")){
            type = "1";
        }else if(name.equals("仅本社区")){
            type = "0";
        }
    }

    private void chooesInfo(){
        Intent intent = new Intent();
        intent.putExtra("type", type);
        setResult(RESULT_OK, intent);
        finish();
    }
}
