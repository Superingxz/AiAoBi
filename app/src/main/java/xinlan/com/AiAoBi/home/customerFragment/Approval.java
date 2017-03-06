package xinlan.com.AiAoBi.home.customerFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import xinlan.com.AiAoBi.home.homeFragment.DisplayVIew;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/2.
 */
public class Approval extends BaseActivty {
    @BindView(R.id.title_view)
    TitileView titleView;
    @BindView(R.id.activity_approval_time)
    TextView activityApprovalTime;
    @BindView(R.id.activity_approval_name)
    TextView activityApprovalName;
    @BindView(R.id.activity_approval_slevel)
    TextView activityApprovalSlevel;
    @BindView(R.id.activity_approval_refman)
    TextView activityApprovalRefman;
    @BindView(R.id.activity_approval_superior)
    TextView activityApprovalSuperior;
    @BindView(R.id.activity_approval_proof)
    ImageView activityApprovalProof;
    @BindView(R.id.activity_approval_btn_agree)
    Button activityApprovalBtnAgree;
    @BindView(R.id.activity_approval_btn_return)
    Button activityApprovalBtnReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        info = (WaitExaminationInfo) getIntent().getExtras().getSerializable("info");
        /*if (info!=null){
            String title1 = WaitExAdapter.checkflagArray[Integer.valueOf(info.getCheckflag()) - 1];
            String title2 = WaitExAdapter.slevelArray[Integer.valueOf(info.getSlevel_up()) - 1];
            setTitle("申请升级为" + title2 + "-" + title1);
        }*/
        setTitle("详情");
        initEvent();
    }
    WaitExaminationInfo info;
    private void initEvent() {
        activityApprovalTime.setText(info.getOperatdate());
        int slevle=Integer.valueOf(info.getSlevel());
        int Slevel_up=Integer.valueOf(info.getSlevel_up());
        int curparentlevel=Integer.valueOf(info.getCurparentlevel());
        String strSlevel;
        if (slevle>0) {
             strSlevel = WaitExAdapter.slevelArray[slevle - 1];
        }else strSlevel="总裁";
        String strCurparentlevel;
        if (curparentlevel>0) {
            strCurparentlevel = WaitExAdapter.slevelArray[curparentlevel - 1];
        }else strCurparentlevel="总裁";
        String strSlevelUp = WaitExAdapter.slevelArray[Slevel_up - 1];
        activityApprovalName.setText(info.getAgentname()+"/"+info.getBrand()+"/"+strSlevel);
        activityApprovalSlevel.setText(info.getBrand_up()+"/"+strSlevelUp);
        activityApprovalRefman.setText(info.getRefman()+"/"+info.getRefmantel()+"/"+info.getRefmanlevel());
        activityApprovalSuperior.setText(info.getCurparentname()+"/"+info.getCurparenttel()+"/"+strCurparentlevel);
        if (info.getVoucher()!=null) {
            Glide.with(this).load(info.getVoucher())
                    .placeholder(ResourcesCompat.getDrawable(getResources(), R.drawable.shape, null))
                    .error(ResourcesCompat.getDrawable(getResources(), R.mipmap.error200, null))
                    .into(activityApprovalProof);
        }
    }

    @OnClick({R.id.activity_approval_btn_agree, R.id.activity_approval_btn_return,R.id.activity_approval_proof})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_approval_btn_agree:
                setAgree(true);
                break;
            case R.id.activity_approval_btn_return:
                setAgree(false);
                break;
            case R.id.activity_approval_proof:
                if (info.getVoucher()!=null) {
                    Intent intent = new Intent(Approval.this, DisplayVIew.class);
                    intent.putExtra("url", info.getVoucher());
                    Approval.this.startActivity(intent);
                }
                break;
        }
    }
    void setAgree(boolean b) {
        showProgressDialog("正在处理中...");
        Map<String, String> map = new HashMap<>();
        map.put("curuserid", info.getPhone());//当前用户
        map.put("phone", info.getPhone());
        map.put("agentid", info.getAgentid());
        map.put("slevel", info.getSlevel());
        map.put("checkagentlevel", info.getCheckagentlevel());
        map.put("checkagentid", info.getCheckagentid());
        map.put("checkflag", info.getCheckflag());
        map.put("reftype", info.getReftype());
        map.put("slevel_up",info.getSlevel_up());
        Log.i("log", "升级申请同意: "+map);
        if (b) {
            map.put("opt", "ok");
        } else {
            map.put("opt", "back");
        }
//        10 业务类别:reftype【apply:代理商加入；up：代理商升级】
//        11 代理商升后的级别：slevel_up，如果业务类别reftype = up，则slevel_up必填。
        Call<BaseModel> call = netApi.examinationUpdate(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    Log.i("Log","审核升级：res："+response.body().getRes());
                    App.getApp().getHandler().sendEmptyMessage(0x002);
                    if (response.body().getRes().equals("1")) {
                        dimissProgressDialog();
                        showMsgDialog("审批成功",1);
                    }else showMsgDialog(response.body().getMsg(),0);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败");
            }
        });
    }
}
