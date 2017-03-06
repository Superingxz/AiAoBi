package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.FindLogAdapter;
import xinlan.com.AiAoBi.entity.AgentpaymentaInfo;
import xinlan.com.AiAoBi.entity.AgentpaymentdInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ActivityRegister extends BaseActivty {
    @BindView(R.id.activity_register_title)
    TitileView title;
    @BindView(R.id.resgister_receive_man_edit)
    TextView resgisterReceiveManEdit;
    @BindView(R.id.resgister_receive_man)
    Button resgisterReceiveMan;
    @BindView(R.id.resgister_receive_money)
    EditText resgisterReceiveMoney;
    @BindView(R.id.resgister_receive_yes)
    Button resgisterReceiveYes;
    @BindView(R.id.resgister_remark)
    EditText remarkEdit;
    @BindView(R.id.register_find_btn)
    Button findBtn;
    @BindView(R.id.register_find_lv)
    ListView registerFindLv;
    @BindView(R.id.register_find_tvmsg)
    TextView registerFindTvmsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        title.setTitle("首款登记");
        initEvent();
    }

    private String moneny;//金额
    private String remark;//备注
    private View view;
    private void initEvent() {
        view = LayoutInflater.from(this).inflate(R.layout.adapter_register_foot, null, false);
        resgisterReceiveMoney.addTextChangedListener(monTextWatch);
        remarkEdit.addTextChangedListener(remarkTextWatch);
    }

    private MTextWatch monTextWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            moneny = resgisterReceiveMoney.getText().toString().trim();
        }
    };
    private MTextWatch remarkTextWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            remark = remarkEdit.getText().toString().trim();
        }
    };

    @OnClick({R.id.resgister_receive_man, R.id.resgister_receive_yes, R.id.register_find_btn})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.resgister_receive_man:
                intent.setClass(this, CheckMyCustomer.class);
                startActivityForResult(intent, 0x020);
                break;
            case R.id.resgister_receive_yes:
                //确认收款
                if (TextUtils.isEmpty(resgisterReceiveManEdit.getText().toString().trim())) {
                    showToast("付款人不能为空");
                } else if (TextUtils.isEmpty(moneny)) {
                    showToast("请输入金额");
                } else agentpaymentc();
                break;
            case R.id.register_find_btn://查看到账历史记录
                agentpaymentd(user.getAgentid());
                break;
        }
    }

    private void agentpaymentd(String agentid) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<AgentpaymentdInfo>> call = netApi.agentpaymentd(agentid);
        call.enqueue(new Callback<BaseModel<AgentpaymentdInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentpaymentdInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<AgentpaymentdInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成功
                        registerFindLv.setVisibility(View.VISIBLE);
                        registerFindTvmsg.setVisibility(View.GONE);
                        TextView amt= (TextView) view.findViewById(R.id.adapter_register_foot_amtmoney);
                        TextView surplus= (TextView) view.findViewById(R.id.adapter_register_foot_surplus);
                        float amts = 0;
                        float surpluses = 0;
                        for (AgentpaymentdInfo info:body.getData()){
                            amts+=Float.parseFloat(info.getAmt());
                            surpluses+=Float.parseFloat(info.getBalance());
                        }
                        amt.setText(amts+"");
                        surplus.setText(surpluses+"");
                        if (registerFindLv.getFooterViewsCount()>0){
                            registerFindLv.removeFooterView(view);
                        }
                        registerFindLv.addFooterView(view);
                        FindLogAdapter adapter=new FindLogAdapter(ActivityRegister.this,R.layout.adapter_register,body.getData());
                        registerFindLv.setAdapter(adapter);

                    } else {
                        showToast(body.getMsg());
                        registerFindLv.setVisibility(View.GONE);
                        registerFindTvmsg.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
                registerFindLv.setVisibility(View.GONE);
                registerFindTvmsg.setVisibility(View.VISIBLE);
            }
        });
    }

    //确认收款
    private void agentpaymentc() {
        showProgressDialog("正在提交...");
        Map<String, String> map = new HashMap<>();
        map.put("outagentid", agentpaymentaInfo.getAgentid());
        map.put("inagentid", user.getAgentid());
        map.put("inagentname", user.getAgentname());
        map.put("inagentlevel", user.getSlevel());
        map.put("amt", moneny);
        map.put("outagentname", agentpaymentaInfo.getAgentname());
        map.put("outagentlevel", agentpaymentaInfo.getSlevel());
        map.put("remark", remark);
        Call<BaseModel> call = netApi.agentpaymentc(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel body = response.body();
                    showMsgDialog(body.getMsg(),0);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });

    }

    private static final String TAG = "ActivityRegister";
    private AgentpaymentaInfo agentpaymentaInfo;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x020 && resultCode == 0x021) {
            agentpaymentaInfo = (AgentpaymentaInfo) data.getSerializableExtra("agentpaymentaInfo");
            if (agentpaymentaInfo != null) {
                resgisterReceiveManEdit.setText(agentpaymentaInfo.getAgentname());
                Log.i(TAG, "onActivityResult: " + agentpaymentaInfo.toString());
            } else resgisterReceiveManEdit.setText("");
        }
    }
}
