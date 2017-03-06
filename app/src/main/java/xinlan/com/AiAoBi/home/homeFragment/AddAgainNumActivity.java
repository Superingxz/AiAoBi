package xinlan.com.AiAoBi.home.homeFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AddAgainAdapter;
import xinlan.com.AiAoBi.entity.AddAaginOrder;
import xinlan.com.AiAoBi.entity.AgentAddress;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.personageFragment.AgentAddressListActivty;
import xinlan.com.AiAoBi.view.GoodsBottomView;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 我再加点
 * Created by Administrator on 2016/11/15.
 */
public class AddAgainNumActivity extends BaseActivty {
    @BindView(R.id.title_view)
    TitileView titileView;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.update_ad_bt)
    TextView update_ad_bt;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.order_goods_bt)
    TextView order_goods_bt;
    AddAgainAdapter adpter;
    List<AddAaginOrder> addlist = new ArrayList<>();
    @BindView(R.id.user_info)
    TextView user_info;
    @BindView(R.id.no_text)
    LinearLayout no_text;
    GoodsBottomView goodsBottomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_again);
        ButterKnife.bind(this);
        titileView.setTitle("我再多加点数量");
        initView();
        getData();
        requestData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0x001) {
            lv.setVisibility(View.VISIBLE);
            no_text.setVisibility(View.GONE);
            List<ChooseGoods> list = (List<ChooseGoods>) data.getSerializableExtra("chooseGoodsList");
            for (ChooseGoods goods : list) {
                AddAaginOrder addAaginOrder = new AddAaginOrder();
                addAaginOrder.setNum("0");
                addAaginOrder.setGoodsname(goods.getGoodsname());
                addAaginOrder.setGoodsid(goods.getGoodsid());
                addAaginOrder.setIs_new("0");
                addAaginOrder.setCostprice(goods.getPrice());
                addAaginOrder.setMinnum("0");
                addAaginOrder.setAdd(true);
                addAaginOrder.setUnitname(goods.getUnitname());
                addlist.add(addAaginOrder);
            }
            adpter.notifyDataSetChanged();
            updateNum();
        } else if (requestCode == 0x002 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (!TextUtils.isEmpty(data.getStringExtra("clear"))) {
                    address.setText(data.getStringExtra(""));
                    user_info.setText("");
                } else {
                    address.setText(data.getStringExtra("address"));
                    user_info.setText(data.getStringExtra("name") + " " + data.getStringExtra("phone"));
                }

            }
        }
    }

    void getData() {
        showProgressDialog("获取数据中...");
        HashMap<String, String> map = new HashMap<>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        Call<BaseModel<AddAaginOrder>> call = netApi.doselagentd(map);
        call.enqueue(new Callback<BaseModel<AddAaginOrder>>() {
            @Override
            public void onResponse(Response<BaseModel<AddAaginOrder>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<AddAaginOrder> body = response.body();
                    if ("1".equals(body.getRes())) {
                        addlist.clear();
                        addlist.addAll(response.body().getData());
                        adpter.notifyDataSetChanged();
                        for (AddAaginOrder addAaginOrder : addlist) {
                            addAaginOrder.setEditText(addAaginOrder.getNum());
                        }
                        updateNum();
                        lv.setVisibility(View.VISIBLE);
                        no_text.setVisibility(View.GONE);
                    } else if ("-1".equals(body.getRes())) {
                        lv.setVisibility(View.GONE);
                        no_text.setVisibility(View.VISIBLE);

                    } else if ("0".equals(body.getRes())) {
                        lv.setVisibility(View.GONE);
                        no_text.setVisibility(View.VISIBLE);
                    }
                }

                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
                dimissProgressDialog();
            }
        });

    }

    @OnClick({R.id.order_goods_bt, R.id.update_ad_bt})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.update_ad_bt:
                startActivityForResult(new Intent(AddAgainNumActivity.this, AgentAddressListActivty.class), 0x002);
                break;
            case R.id.order_goods_bt:
                showDiyMsgDialog("是否确认订货?", new Mcallback() {
                    @Override
                    public void doSomething(AlertDialog alertDialog) {
                        sumbit();
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }

    HashMap<String, String> constructionOrder() {
        StringBuffer price = new StringBuffer();
        StringBuffer goodsid = new StringBuffer();
        StringBuffer goodsname = new StringBuffer();
        StringBuffer goodssize = new StringBuffer();
        StringBuffer unitname = new StringBuffer();
        StringBuffer num = new StringBuffer();
        StringBuffer is_new = new StringBuffer();

        HashMap<String, String> map = new HashMap<>();
        for (AddAaginOrder data : addlist) {
            price.append(data.getCostprice() + ";");
            goodsid.append(data.getGoodsid() + ";");
            goodsname.append(data.getGoodsname() + ";");
            goodssize.append(data.getGoodssize() + ";");
            unitname.append(data.getUnitname() + ";");
            num.append(data.getEditText() + ";");
            is_new.append(data.getIs_new() + ";");
        }
        price.deleteCharAt(price.length() - 1);
        goodsid.deleteCharAt(goodsid.length() - 1);
        goodsname.deleteCharAt(goodsname.length() - 1);
        goodssize.deleteCharAt(goodssize.length() - 1);
        unitname.deleteCharAt(unitname.length() - 1);
        is_new.deleteCharAt(is_new.length() - 1);
        num.deleteCharAt(num.length() - 1);

        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("slevel", App.getApp().getUserInfo().getSlevel());
        map.put("goodsid", goodsid.toString());
        map.put("goodsname", goodsname.toString());
        map.put("goodssize", goodssize.toString());
        map.put("price", price.toString());
        map.put("unitname", unitname.toString());
        map.put("num", num.toString());
        map.put("is_new", is_new.toString());
        Log.v("AddAgainNumActivity", map.toString());
        return map;
    }

    private void sumbit() {
        if (addlist.size() == 0) {
            showToast("数据为空！");
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            showToast("请选择默认收货地址");
            return;
        }
        boolean isAdd = false;
        for (AddAaginOrder data : addlist) {
            if (Integer.valueOf(data.getEditText()) >= 1) {
                isAdd = true;
                break;
            }
        }
        if (!isAdd) {
            showToast("请添加货品数量");
            return;
        }
        showProgressDialog("提交货品中...");
        Call<BaseModel> call = netApi.doselagente(constructionOrder());
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())) {
                        showToast("提交成功");
                        getData();
                        App.getApp().getHandler6().sendEmptyMessage(0x113);
                    } else showToast(body.getMsg());
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
                dimissProgressDialog();
            }
        });

    }

    private void updateNum() {
        int totle = 0;
        for (AddAaginOrder data : addlist) {
            if (!TextUtils.isEmpty(data.getEditText())) {
                totle += Integer.valueOf(data.getEditText());
            }
        }
        if (null != addlist && addlist.size() == 0) {
            lv.setVisibility(View.GONE);
            no_text.setVisibility(View.VISIBLE);
        }
        goodsBottomView.setText(totle);
    }

    private void initView() {
        goodsBottomView = new GoodsBottomView(this);
        adpter = new AddAgainAdapter(this, R.layout.item_again_add, addlist);
        lv.setAdapter(adpter);
        lv.addFooterView(goodsBottomView);
        titileView.setRightText("选择货品", new TitileView.RightBtIface() {
            @Override
            public void onClick() {
                Intent intent = new Intent(AddAgainNumActivity.this, ChooseGoodsActivity.class);
                intent.putExtra("addList", (Serializable) addlist);
                startActivityForResult(intent, 0x001);
            }
        });
        adpter.setEditeIface(new AddAgainAdapter.EditeIface() {
            @Override
            public void edite(int position, String num) {
                updateNum();
            }

            @Override
            public void deleteItem(final int position) {
                showDiyMsgDialog("是否删除?", new Mcallback() {

                    @Override
                    public void onClick(View v) {

                    }

                    @Override
                    public void doSomething(AlertDialog alertDialog) {
                        adpter.removeItem(position);
                        updateNum();
                        alertDialog.dismiss();
                    }
                });

            }

            @Override
            public void deleteAll() {
                updateNum();
            }
        });

    }


    void requestData() {
        if (!TextUtils.isEmpty(address.getText().toString())) {
            return;
        }
        // showProgressDialog("获取收货地址数据中...");
        Map<String, String> map = new HashMap<>();
        map.put("phone", App.getApp().getUserInfo().getPhone());
        Call<BaseModel<AgentAddress>> agentAddressCall = netApi.agentgetAddr(map);
        agentAddressCall.enqueue(new Callback<BaseModel<AgentAddress>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentAddress>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (("1").equals(response.body().getRes())) {
                        for (AgentAddress agentAddress : response.body().getData()) {
                            if (agentAddress.getIsdefault().equals("1")) {
                                address.setText(agentAddress.getProvince() +
                                        agentAddress.getCity() +
                                        agentAddress.getArea() +
                                        agentAddress.getAddress());
                                user_info.setText(agentAddress.getInname() + " " + agentAddress.getPhone());
                                break;
                            }

                        }
                    }
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
