package xinlan.com.AiAoBi.home.homeFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
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
import xinlan.com.AiAoBi.adapter.MyGoodsAdapter;
import xinlan.com.AiAoBi.entity.AgentAddress;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.entity.NeedbargainnumInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.UnderclerkIndentActivity;
import xinlan.com.AiAoBi.home.personageFragment.AgentAddressListActivty;
import xinlan.com.AiAoBi.view.GoodsBottomView;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 我的货品
 * Created by Administrator on 2016/11/4.
 */
public class MyGoodsActvity extends BaseActivty {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.user_info)
    TextView user_info;
    @BindView(R.id.update_ad_bt)
    TextView update_ad_bt;
    @BindView(R.id.order_goods_bt)
    TextView order_goods_bt;
    List<ChooseGoods> chooseGoodses = new ArrayList<>();
    MyGoodsAdapter adtapter;
    @BindView(R.id.mygoods_text)
    TextView mygoods_text;
    @BindView(R.id.order_wait)
    TextView order_wait;
    GoodsBottomView goodsBottomView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(MyGoodsActvity.this.isFinishing()&&MyGoodsActvity.this.isDestroyed())return;
            }
            if (msg.what==0x115){
                //更新订单收未处理订单数
                needbargainnum();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);
        ButterKnife.bind(this);
        initView();
        requestData();
        needbargainnum();
        getIntentData();
    }
    void getIntentData() {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("ClassName"))) {
            List<ChooseGoods> dataList = (List<ChooseGoods>) getIntent().getSerializableExtra("chooseGoodsList");
            mygoods_text.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            chooseGoodses.addAll(dataList);
            int totle = 0;
            for (ChooseGoods goods : chooseGoodses) {
                totle += Integer.valueOf(goods.getEditText());
            }
            goodsBottomView.setText(totle);
            adtapter.notifyDataSetChanged();
        }
    }

    private HashMap<String, String> constructionOrder() {
        StringBuffer goodsidString = new StringBuffer();
        StringBuffer goodsnameString = new StringBuffer();
        StringBuffer goodssizeString = new StringBuffer();
        StringBuffer priceString = new StringBuffer();
        StringBuffer ordernumString = new StringBuffer();
        StringBuffer unitnameString = new StringBuffer();
        for (ChooseGoods goods : chooseGoodses) {
            goodsidString.append(goods.getGoodsid() + ";");
            goodsnameString.append(goods.getGoodsname() + ";");
            goodssizeString.append(goods.getGoodssize() + ";");
            priceString.append(goods.getPrice() + ";");
            ordernumString.append((goods.getEditText() + ";"));
            unitnameString.append(goods.getUnitname() + ";");

        }

        goodsidString.deleteCharAt(goodsidString.length() - 1);
        goodsnameString.deleteCharAt(goodsnameString.length() - 1);
        goodssizeString.deleteCharAt(goodssizeString.length() - 1);
        priceString.deleteCharAt(priceString.length() - 1);
        unitnameString.deleteCharAt(unitnameString.length() - 1);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("goodsid", goodsidString.toString());
        map.put("goodsname", goodsnameString.toString());
        map.put("goodssize", goodssizeString.toString());
        map.put("price", priceString.toString());
        map.put("ordernum", ordernumString.toString());
        map.put("unitname", unitnameString.toString());

        Log.v("MyGoodsSubmitList", map.toString());
        return map;
    }

    private void initView() {
        App.getApp().setHandler5(handler);
        goodsBottomView = new GoodsBottomView(this);
        adtapter = new MyGoodsAdapter(this, R.layout.item_my_goods, chooseGoodses);
        adtapter.setEditeIface(new MyGoodsAdapter.EditeIface() {
            @Override
            public void edite(int position, String num) {
                // chooseGoodses.get(position).setEditText(num);
                Log.v("edite", String.valueOf(num) + "-" + String.valueOf(position));
                updateNum();
            }

            private void updateNum() {
                int totle = 0;
                for (ChooseGoods goods : chooseGoodses) {
                    totle += Integer.valueOf(goods.getEditText());
                }
                goodsBottomView.setText(totle);
            }

            @Override
            public void deleteItem(final int position) {
                showDiyMsgDialog("是否删除该货品?", new Mcallback() {
                    @Override
                    public void doSomething(AlertDialog alertDialog) {
                        adtapter.removeItem(position);
                        if(adtapter.getData().size()==0){
                            deleteAll();
                        }
                        updateNum();
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onClick(View v) {

                    }
                });

            }

            @Override
            public void deleteAll() {
                mygoods_text.setVisibility(View.VISIBLE);
                lv.setVisibility(View.GONE);
            }
        });
        titileView = (TitileView) findViewById(R.id.title_view);
        titileView.setTitle("我的货品");
        titileView.setRightText("选择货品", new TitileView.RightBtIface() {
            @Override
            public void onClick() {
                Intent intent = new Intent(MyGoodsActvity.this, ChooseGoodsActivity.class);
                intent.putExtra("chooseGoodsList", (Serializable) chooseGoodses);
                startActivityForResult(intent, 0x001);
            }
        });
        lv.setAdapter(adtapter);
        lv.addFooterView(goodsBottomView);
    }

    @OnClick({R.id.order_goods_bt, R.id.update_ad_bt, R.id.order_wait})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.update_ad_bt:
                startActivityForResult(new Intent(MyGoodsActvity.this, AgentAddressListActivty.class), 0x002);
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
            case R.id.order_wait:
                startActivity(new Intent(MyGoodsActvity.this, UnderclerkIndentActivity.class));
                break;
        }
    }

    void sumbit() {
        if (chooseGoodses.size() == 0) {
            showToast("请选择添加货品");
            return;
        }
        if (TextUtils.isEmpty(address.getText())) {
            showToast("请选择默认收货地址");
            return;
        }
        Call<BaseModel> call = netApi.needOkGoods(constructionOrder());
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if ("1".equals(response.body().getRes())) {
                        showMsgDialog("提交成功！", 0);
                        adtapter.clearAll();
                        mygoods_text.setVisibility(View.VISIBLE);
                        lv.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(getIntent().getStringExtra("ClassName"))) {
                                setResult(Activity.RESULT_OK);
                        }
                    } else {
                        showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败！");
            }
        });

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
                    if ("1".equals(response.body().getRes())) {
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

    /**
     * 待处理订单数
     */
    void needbargainnum() {
        Call<BaseModel<NeedbargainnumInfo>> call = netApi.needbargainnum(user.getAgentid());
        call.enqueue(new Callback<BaseModel<NeedbargainnumInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<NeedbargainnumInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<NeedbargainnumInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        String num = body.getData().get(0).getNum();
                        if ("0".equals(num)){
                            order_wait.setVisibility(View.GONE);
                        }else {
                            order_wait.setVisibility(View.VISIBLE);
                            SpannableString spannableString=new SpannableString("有" + num + "张新订单[请处理]");
                            spannableString.setSpan(new ForegroundColorSpan(Color.RED),7,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            order_wait.setText(spannableString);
                        }
                    } else order_wait.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                order_wait.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0x001) {
            mygoods_text.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            List<ChooseGoods> dataList = (List<ChooseGoods>) data.getSerializableExtra("chooseGoodsList");
            for (ChooseGoods goods : dataList) {
                goods.setEditText("1");
            }
            chooseGoodses.addAll(dataList);
            int totle = 0;
            for (ChooseGoods goods : chooseGoodses) {
                totle += Integer.valueOf(goods.getEditText());
            }
            goodsBottomView.setText(totle);
            adtapter.notifyDataSetChanged();
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

}
