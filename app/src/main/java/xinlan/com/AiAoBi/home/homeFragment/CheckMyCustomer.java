package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import xinlan.com.AiAoBi.adapter.CheckMyCustomerAdapter;
import xinlan.com.AiAoBi.entity.AgentpaymentaInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/29.
 */
public class CheckMyCustomer extends BaseActivty {
    @BindView(R.id.check_my_customer_title)
    TitileView checkMyCustomerTitle;
    @BindView(R.id.check_my_customer_lv)
    ListView checkMyCustomerLv;
    @BindView(R.id.check_my_customer_linear)
    LinearLayout checkMyCustomerLinear;
    @BindView(R.id.check_my_customer_tvmsg)
    TextView checkMyCustomerTvmsg;
    @BindView(R.id.check_my_customer_edit)
    EditText checkMyCustomerEdit;
    @BindView(R.id.check_my_customer_btn)
    Button checkMyCustomerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_customer);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        checkMyCustomerTitle.setTitle("选择我的客户");
        initEvent();
    }
    private int flags;
    private Map<String,String> map;
    private String startdate;
    private String enddate;
    private void initEvent() {
        map=new HashMap<>();
        Intent intent=getIntent();
        flags=intent.getFlags();
        if (flags==3){
            startdate= intent.getStringExtra("startdate");
            enddate= intent.getStringExtra("enddate");
            map.put("agentid",user.getAgentid());
            map.put("startdate",startdate);
            map.put("enddate",enddate);
            mysendgoodsman(map);
        }
        else {
            agentpaymenta(user.getAgentid());
        }
        checkMyCustomerEdit.addTextChangedListener(textWatch);
        checkMyCustomerTitle.setRightText("确定", new TitileView.RightBtIface() {
            @Override
            public void onClick() {
                Intent intent=new Intent();
                intent.putExtra("agentpaymentaInfo", agentpaymentaInfo);
                setResult(0x021,intent);
                finish();
            }
        });
    }

    private void mysendgoodsman(Map<String, String> map) {
        Call<BaseModel<AgentpaymentaInfo>> call = netApi.mysendgoodsman(map);
        call.enqueue(new Callback<BaseModel<AgentpaymentaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentpaymentaInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<AgentpaymentaInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        List<AgentpaymentaInfo> data = body.getData();
                        afterRespone(data);
                    }else showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                checkMyCustomerLinear.setVisibility(View.GONE);
                checkMyCustomerTvmsg.setVisibility(View.VISIBLE);
                showToast("连接服务器失败");
            }
        });
    }

    private String key;
    private MTextWatch textWatch=new MTextWatch(){
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            key=checkMyCustomerEdit.getText().toString().trim();
        }
    };
    private CheckMyCustomerAdapter adapter;
    private void agentpaymenta(String agentid) {
        showProgressDialog("获取数据中...");
        Map<String,String> map=new HashMap<>();
        map.put("agentid",agentid);
        Call<BaseModel<AgentpaymentaInfo>> call = netApi.agentpaymenta(map);
        call.enqueue(new Callback<BaseModel<AgentpaymentaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentpaymentaInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    final BaseModel<AgentpaymentaInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成功
                         List<AgentpaymentaInfo> data = body.getData();
                            afterRespone(data);
                    } else {
                        showToast(body.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                checkMyCustomerLinear.setVisibility(View.GONE);
                checkMyCustomerTvmsg.setVisibility(View.VISIBLE);
                showToast("连接服务器失败");
            }
        });
    }

    @OnClick(R.id.check_my_customer_btn)
    public void onClick() {
        agentpaymentaInfo=null;
        if (!TextUtils.isEmpty(key)){
            if (flags==3){
                map.clear();
                map.put("agentid",user.getAgentid());
                map.put("startdate",startdate);
                map.put("enddate",enddate);
                map.put("agentname",key);
                mysendgoodsman(map);
            }else agentpaymentb();
        }else {
            if (flags==3){
                map.clear();
                map.put("agentid",user.getAgentid());
                map.put("startdate",startdate);
                map.put("enddate",enddate);
                mysendgoodsman(map);
            }else agentpaymenta(user.getAgentid());
        }
    }
    private AgentpaymentaInfo agentpaymentaInfo;
    private void agentpaymentb() {
        showProgressDialog("查询中...");
        Map<String,String> map=new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("agentname",key);
        Call<BaseModel<AgentpaymentaInfo>> call = netApi.agentpaymentb(map);
        call.enqueue(new Callback<BaseModel<AgentpaymentaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentpaymentaInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    final BaseModel<AgentpaymentaInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        List<AgentpaymentaInfo> data = body.getData();
                        afterRespone(data);
                    }else showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                checkMyCustomerLinear.setVisibility(View.GONE);
                checkMyCustomerTvmsg.setVisibility(View.VISIBLE);
                showToast("连接服务器失败");
            }
        });
    }
    private void afterRespone(final List<AgentpaymentaInfo> data){
        checkMyCustomerLinear.setVisibility(View.VISIBLE);
        checkMyCustomerTvmsg.setVisibility(View.GONE);
        adapter = new CheckMyCustomerAdapter(CheckMyCustomer.this,
                R.layout.adapter_check_my_customer, data);
        checkMyCustomerLv.setAdapter(adapter);
        checkMyCustomerLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (data.get(i).ischeck()) {
                    data.get(i).setIscheck(false);
                    agentpaymentaInfo=null;
                    adapter.notifyDataSetChanged();
                    return;
                }
                for (AgentpaymentaInfo info:
                        data) {
                    info.setIscheck(false);

                }
                data.get(i).setIscheck(true);
                agentpaymentaInfo=data.get(i);
                Log.i("log", "onItemClick: ======"+agentpaymentaInfo.toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
