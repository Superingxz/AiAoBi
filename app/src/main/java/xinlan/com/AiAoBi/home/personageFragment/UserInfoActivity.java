package xinlan.com.AiAoBi.home.personageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.WaitExAdapter;
import xinlan.com.AiAoBi.entity.AgentAddress;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 个人中心-基本信息
 * Created by Administrator on 2016/11/3.
 */
public class UserInfoActivity extends BaseActivty {

    @BindView(R.id.title_view)
    TitileView title_view;
    @BindView(R.id.accredit_code)
    TextView accredit_code;
    @BindView(R.id.accredit_name)
    TextView accredit_name;
    @BindView(R.id.accredit_time)
    TextView accredit_time;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.car_id)
    TextView car_id;
    @BindView(R.id.superior)
    TextView superior;
    @BindView(R.id.referrer)
    TextView referrer;
    @BindView(R.id.my_address)
    TextView my_address;
    @BindView(R.id.wx_id)
    TextView wx_id;
    @BindView(R.id.accredit_level)
    TextView accredit_level;
    @BindView(R.id.price)
    TextView price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        title_view.setTitle("个人中心");
        setData();
    }

    private void setData() {
        price.setText(App.getApp().getUserInfo().getBalance());
        Log.i("log", "setData: "+App.getApp().getUserInfo().getBalance());
        accredit_level.setText(user.getBrand()+WaitExAdapter.ismoreString[Integer.valueOf(user.getIsmore())]
                + WaitExAdapter.slevelArray[Integer.valueOf(user.getSlevel()) - 1]);
        accredit_code.setText(user.getAgentcode());
        accredit_name.setText(user.getAgentname());
        referrer.setText(user.getRefman() + "/" + user.getRefmantel());
        accredit_time.setText(user.getGrant_date() + "");
        wx_id.setText(user.getWeixin());
        car_id.setText(user.getCardno());
        phone.setText(user.getPhone());
        if (!TextUtils.isEmpty(user.getCurparentlevel())){
            int i = Integer.valueOf(user.getCurparentlevel());
            if (i == 0) {
                superior.setText(user.getCurparentname() + "/" + user.getCurparenttel());
            } else superior.setText(user.getCurparentname() + "/" + user.getCurparenttel() + "/"
                    + WaitExAdapter.slevelArray[Integer.valueOf(user.getCurparentlevel()) - 1]);
        }

    }

    @OnClick({R.id.add_address})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.add_address:
                startActivityForResult(new Intent(UserInfoActivity.this, AgentAddressListActivty.class), 0x001);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    void requestData() {
        showProgressDialog("获取收货地址数据中...");
        Map<String, String> map = new HashMap<>();
        map.put("phone", App.getApp().getUserInfo().getPhone());
        Call<BaseModel<AgentAddress>> agentAddressCall = netApi.agentgetAddr(map);
        agentAddressCall.enqueue(new Callback<BaseModel<AgentAddress>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentAddress>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    if (response.body().getRes().equals("1")) {
                        boolean isDef=false;
                        for (AgentAddress agentAddress : response.body().getData()) {
                            if (agentAddress.getIsdefault().equals("1")) {
                                my_address.setText(agentAddress.getProvince() +
                                        agentAddress.getCity() +
                                        agentAddress.getArea() +
                                        agentAddress.getAddress());
                                isDef=true;
                                break;
                            }

                        }
                        if(!isDef){
                            my_address.setText("暂无默认收货地址信息");
                        }
                    } else {
                        my_address.setText("暂无默认收货地址信息");
                        showToast("没有收货地址数据");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }
}
