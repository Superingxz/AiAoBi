package xinlan.com.AiAoBi.home.personageFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdressAdapter;
import xinlan.com.AiAoBi.entity.AgentAddress;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 收货地址列表
 * Created by Administrator on 2016/11/3.
 */
public class AgentAddressListActivty extends BaseActivty {

    @BindView(R.id.add_address_bt)
    Button add_address_bt;
    @BindView(R.id.agent_lv)
    ListView agent_lv;
    AdressAdapter adressAdapter;
    List<AgentAddress> agentAddresses = new ArrayList<>();
    Call<BaseModel<AgentAddress>> agentAddressCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        requestData(App.getApp().getUserInfo().getPhone());
    }

    private void initView() {
        titileView = (TitileView) findViewById(R.id.title_view);
        titileView.setTitle("修改我的收货地址");
        titileView.setLefebtIface(new TitileView.LeftBtIface() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                boolean isData=false;
                for (AgentAddress agentAddress : adressAdapter.getData()) {
                    if (agentAddress.getIsdefault().equals("1")) {
                        isData=true;
                        intent.putExtra("name", agentAddress.getInname());
                        intent.putExtra("phone", agentAddress.getPhone());
                        intent.putExtra("address", agentAddress.getProvince() + agentAddress.getCity() + agentAddress.getArea() + agentAddress.getAddress());
                        setResult(Activity.RESULT_OK, intent);
                        break;
                    }
                }
                if (adressAdapter.getData().size() == 0||!isData) {
                    intent = new Intent();
                    intent.putExtra("clear", "clear");
                    setResult(Activity.RESULT_OK, intent);
                }
            }
        });

        adressAdapter = new AdressAdapter(this, R.layout.item_address, agentAddresses);
        adressAdapter.setAddressOnClickIface(new AdressAdapter.AddressOnClickIface() {
            @Override
            public void update(int position) {
                Intent intent = new Intent(AgentAddressListActivty.this, UpdateAgentAddressActivity.class);
                intent.putExtra("agentAddress", agentAddresses.get(position));
                startActivityForResult(intent, 0x001);
            }

            @Override
            public void delete(final int position) {
                showDiyMsgDialog("是否删除该地址?", new Mcallback() {
                    @Override
                    public void doSomething(AlertDialog alertDialog) {
                        deleteAddress(position, agentAddresses.get(position).getAgent_addr_id());
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onClick(View v) {

                    }
                });

            }

            @Override
            public void isCheck(boolean isChecked, int position) {
                if (isChecked) {
                    if(agentAddresses.get(position).getIsdefault().equals("1")){
                        return;
                    }
                    updateAdress(position, isChecked);
                } else {
                    if (agentAddresses.get(position).getIsdefault().equals("1")) {
                        updateAdress(position, false);
                    }
                }

            }
        });
        agent_lv.setAdapter(adressAdapter);
    }

    @OnClick({R.id.add_address_bt})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.add_address_bt:
                Intent intent = new Intent(AgentAddressListActivty.this, UpdateAgentAddressActivity.class);
                if (adressAdapter.getData().size() == 0) {
                    intent.putExtra("default", "default");
                }
                startActivityForResult(intent, 0x001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            requestData(App.getApp().getUserInfo().getPhone());
        }
    }

    void deleteAddress(final int position, String agent_addr_id) {
        showProgressDialog("删除中...");
        Map<String, String> map = new HashMap<>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("agent_addr_id", agent_addr_id);
        Call<BaseModel> deleteCallCall = netApi.agentDeladdr(map);
        deleteCallCall.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    dimissProgressDialog();
                    if (response.body().getRes().equals("1")) {
                        showToast("删除收货地址成功");
                        adressAdapter.removeItem(position);
                    } else {
                        showToast("删除收货地址失败");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("服务器异常");
            }
        });

    }

    void updateAdress(final int position, final boolean isCheck) {
        HashMap<String, String> map = new HashMap<>();
        map.put("province", agentAddresses.get(position).getProvince());
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("agent_addr_id", agentAddresses.get(position).getAgent_addr_id());
        map.put("city", agentAddresses.get(position).getCity());
        map.put("area", agentAddresses.get(position).getArea());
        map.put("address", agentAddresses.get(position).getAddress());
        map.put("inname", agentAddresses.get(position).getInname());
        map.put("phone", App.getApp().getUserInfo().getPhone());
        if (isCheck) {
            map.put("isdefault", "1");
        } else {
            map.put("isdefault", "0");
        }
        Log.v("aupdateAddress", map.toString());
        Call<BaseModel> call = netApi.agentupDateAddr(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (response.body().getRes().equals("1")) {
                        showToast("设置成功");
                        for (AgentAddress agentAddress : agentAddresses) {
                            agentAddress.setIsdefault("0");
                        }
                        if (isCheck) {
                            agentAddresses.get(position).setIsdefault("1");
                        }
                        adressAdapter.notifyDataSetChanged();
                    } else {
                        showToast("设置失败");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    void requestData(String phone) {
        showProgressDialog("获取收货地址数据中...");
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        agentAddressCall = netApi.agentgetAddr(map);
        agentAddressCall.enqueue(new Callback<BaseModel<AgentAddress>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentAddress>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    dimissProgressDialog();
                    if (response.body().getRes().equals("1")) {
                        adressAdapter.clearAll();
                        Log.i("addressList", response.body().getData().toString());
                        adressAdapter.add(response.body().getData());
                    } else {
                        showToast("没有收货地址数据");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                if (t.equals("SocketTimeoutException"))
                    showToast("登录超时");
            }
        });
    }
}
