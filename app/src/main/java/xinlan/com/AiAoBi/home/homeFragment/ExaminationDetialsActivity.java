package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.WaitExAdapter;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.enity.WaitExaminationInfo;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 代理审核
 * Created by Administrator on 2016/10/26.
 */
public class ExaminationDetialsActivity extends BaseActivty implements View.OnClickListener {
    Button agree, update;
    TextView time, name, id_number, recommend_name, re_top_name, level_name, wx_number, pay_text, phone;
    ImageView id_car_img1, id_car_img2, pay_img;
    Button btnPerformance;
    LinearLayout layoutIdCard;
    LinearLayout LayoutIvIdCard;
    WaitExaminationInfo waitExaminationInfo;
    Call<BaseModel> call;
    CommonUtils utils;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examination_detils);
        ButterKnife.bind(this);
        utils=new CommonUtils();
        waitExaminationInfo = (WaitExaminationInfo) getIntent().getSerializableExtra("waitExaminationInfo");
        initView();
        setData();
        String title1 = WaitExAdapter.checkflagArray[Integer.valueOf(waitExaminationInfo.getCheckflag())-1];
        String title2 = WaitExAdapter.slevelArray[Integer.valueOf(waitExaminationInfo.getSlevel())-1];
        setTitle(title1 + title2);
    }


    protected void initView() {
        layoutIdCard= (LinearLayout) findViewById(R.id.layout_incard);
        LayoutIvIdCard= (LinearLayout) findViewById(R.id.layout_iv_idcard);
        int slevel=Integer.valueOf(waitExaminationInfo.getSlevel());
        if (slevel>=5) {
            layoutIdCard.setVisibility(View.GONE);
            LayoutIvIdCard.setVisibility(View.GONE);
        }
        agree = (Button) findViewById(R.id.agree);
        update = (Button) findViewById(R.id.update);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAgree(true);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAgree(false);
            }
        });
        phone = (TextView) findViewById(R.id.phone);
        //查看最近业绩布局
        btnPerformance= (Button) findViewById(R.id.performance);
        pay_img = (ImageView) findViewById(R.id.pay_img);
        id_car_img1 = (ImageView) findViewById(R.id.id_car_img1);
        id_car_img2 = (ImageView) findViewById(R.id.id_car_img2);
        titileView = (TitileView) findViewById(R.id.title_view);
        time = (TextView) findViewById(R.id.time);
        name = (TextView) findViewById(R.id.name);
        //全系或单品
        level_name = (TextView) findViewById(R.id.level_name);
        id_number = (TextView) findViewById(R.id.id_number);
        //推荐人
        recommend_name = (TextView) findViewById(R.id.recommend_name);
        //申请人上级
        re_top_name = (TextView) findViewById(R.id.re_top_name);
        //微信
        wx_number = (TextView) findViewById(R.id.wx_number);
        //总裁审核text
        pay_text = (TextView) findViewById(R.id.pay_text);
        id_car_img1.setOnClickListener(this);
        id_car_img2.setOnClickListener(this);
        pay_img.setOnClickListener(this);
        btnPerformance.setOnClickListener(this);
    }


    void setData() {

        time.setText(waitExaminationInfo.getOperatdate());
        name.setText(waitExaminationInfo.getAgentname());
        //电话
        if (!TextUtils.isEmpty(waitExaminationInfo.getPhone())) {
            phone.setText(waitExaminationInfo.getPhone());
        } else {
            phone.setVisibility(View.GONE);
        }
        //微信
        if (!TextUtils.isEmpty(waitExaminationInfo.getWeixin())) {
            wx_number.setText(waitExaminationInfo.getWeixin());
        } else {
            wx_number.setVisibility(View.GONE);
        }
        //加入级别
        if (!TextUtils.isEmpty(waitExaminationInfo.getSlevel())) {
            int leve = Integer.valueOf(waitExaminationInfo.getSlevel());
            if (waitExaminationInfo.getIsmore().equals("1")) {
                level_name.setText(WaitExAdapter.slevelArray[leve - 1] + "/" + "全系");
            } else {
                level_name.setText(WaitExAdapter.slevelArray[leve - 1] + "/" + waitExaminationInfo.getBrand());
            }
        } else {
            level_name.setVisibility(View.GONE);
        }
        //推荐人
        if (TextUtils.isEmpty(waitExaminationInfo.getRefman())) {
            recommend_name.setVisibility(View.GONE);
        } else {
            recommend_name.setText(waitExaminationInfo.getRefman());
        }
        //上级
        if (TextUtils.isEmpty(waitExaminationInfo.getCurparentname())) {
            re_top_name.setVisibility(View.GONE);
        } else {
            re_top_name.setText(waitExaminationInfo.getCurparentname() + "/" +
                    waitExaminationInfo.getCurparenttel());
        }
        //身份证
        if (TextUtils.isEmpty(waitExaminationInfo.getCardno())) {
            id_number.setVisibility(View.GONE);
        } else {
            id_number.setText(waitExaminationInfo.getCardno());
        }
        //支付凭证
        if (TextUtils.isEmpty(waitExaminationInfo.getVoucher())) {
            pay_img.setVisibility(View.GONE);
        } else {
            Glide.with(context).load(waitExaminationInfo.getVoucher())
                    .placeholder(ResourcesCompat.getDrawable(getResources(),R.drawable.shape,null))
                    .error(ResourcesCompat.getDrawable(getResources(),R.mipmap.error200,null))
                    .into(pay_img);
        }
        //身份证正面
        if (TextUtils.isEmpty(waitExaminationInfo.getCardnoa())) {
            id_car_img1.setVisibility(View.GONE);
        } else {
            Glide.with(context).load(waitExaminationInfo.getCardnoa())
                    .placeholder(ResourcesCompat.getDrawable(getResources(),R.drawable.shape,null))
                    .error(ResourcesCompat.getDrawable(getResources(),R.mipmap.error200,null))
                    .into(id_car_img1);

        }
        //身份证反面
        if (TextUtils.isEmpty(waitExaminationInfo.getCardnob())) {
            id_car_img2.setVisibility(View.GONE);
        } else {
            Glide.with(context).load(waitExaminationInfo.getCardnob())
                    .placeholder(ResourcesCompat.getDrawable(getResources(),R.drawable.shape,null))
                    .error(ResourcesCompat.getDrawable(getResources(),R.mipmap.error200,null))
                    .into(id_car_img2);
        }
        //总代的最近业绩
        utils.LogUitls("waitExaminationInfo: "+waitExaminationInfo.toString());
        if (waitExaminationInfo.getSlevel().equals("3")&&"1".equals(user.getSlevel())) {
            btnPerformance.setVisibility(View.VISIBLE);
        } else {
            btnPerformance.setVisibility(View.GONE);
        }

    }

    /**
     * 同意加入或退回修改
     * @param b
     */
    void setAgree(boolean b) {
        showProgressDialog("正在处理中...");
        Map<String, String> map = new HashMap<>();
        map.put("curuserid", waitExaminationInfo.getPhone());//当前用户
        map.put("phone", waitExaminationInfo.getPhone());
        map.put("agentid", waitExaminationInfo.getAgentid());
        map.put("slevel", waitExaminationInfo.getSlevel());
        map.put("checkagentlevel", waitExaminationInfo.getCheckagentlevel());
        map.put("checkagentid", waitExaminationInfo.getCheckagentid());
        map.put("checkflag", waitExaminationInfo.getCheckflag());
        map.put("reftype", waitExaminationInfo.getReftype());
        map.put("slevel_up",waitExaminationInfo.getSlevel_up());
        if (b) {
            map.put("opt", "ok");
        } else {
            map.put("opt", "back");
        }
//        10 业务类别:reftype【apply:代理商加入；up：代理商升级】
//        11 代理商升后的级别：slevel_up，如果业务类别reftype = up，则slevel_up必填。
        Log.i("Log", "注册申请同意: "+map);
        call = super.netApi.examinationUpdate(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.i("log", "onResponse: res:"+response.body().getRes());
                    dimissProgressDialog();
                    handler=App.app.getHandler();
                    handler.sendEmptyMessage(0x001);
                    if (response.body().getRes().equals("1")) {
                        showMsgDialog("审批成功",1);
                    }
                    else{
                        dimissProgressDialog();
                        showToast(response.body().getMsg());
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(ExaminationDetialsActivity.this,DisplayVIew.class);
        switch (view.getId()){
            case  R.id.id_car_img1:
                if (waitExaminationInfo.getCardnoa()!=null) {
                    Log.i("Log","身份证正面"+waitExaminationInfo.getCardnoa());
                    intent.putExtra("url", waitExaminationInfo.getCardnoa());
                    startActivity(intent);
                }
                break;
            case  R.id.id_car_img2:
                if (waitExaminationInfo.getCardnob()!=null) {
                    intent.putExtra("url", waitExaminationInfo.getCardnob());
                    startActivity(intent);
                }
                break;
            case  R.id.pay_img:
                if (waitExaminationInfo.getVoucher()!=null) {
                    intent.putExtra("url", waitExaminationInfo.getVoucher());
                    startActivity(intent);
                }
                break;
            case R.id.performance://总代最近业绩
                Intent intent1=new Intent(ExaminationDetialsActivity.this,ActivityRecentResults.class);
                intent1.putExtra("parentId",waitExaminationInfo.getParentzdid());
                startActivity(intent1);
                break;
        }
    }
}
