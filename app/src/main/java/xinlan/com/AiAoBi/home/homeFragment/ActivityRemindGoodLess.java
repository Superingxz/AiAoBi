package xinlan.com.AiAoBi.home.homeFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xinlan.com.AiAoBi.adapter.MsgnostockdetAdapter;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.entity.Msgnostockdet;
import xinlan.com.AiAoBi.entity.Msgtakegoods;
import xinlan.com.AiAoBi.entity.RemindModel;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.utils.ToastUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/21.
 */
public class ActivityRemindGoodLess extends BaseActivty {
    @BindView(R.id.activity_remind_good_less_title)
    TitileView title;
    @BindView(R.id.activity_remind_good_less_lv)
    ListView lv;
    @BindView(R.id.activity_remind_good_less_allmoney)
    TextView allmoney;
    @BindView(R.id.activity_remind_good_less_btn)
    Button btn;
    MsgnostockdetAdapter msgnostockAdapter;
    List<Msgnostockdet> msgnostockdetList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_good_less);
        init();
        msgnostockdet();
    }

    private void init() {

        msgnostockAdapter = new MsgnostockdetAdapter(this, R.layout.item_remind_good_less, msgnostockdetList);
        msgnostockAdapter.setAdapterIface(new MsgnostockdetAdapter.MsgnostockdetAdapterIface() {
            @Override
            public void updateNum() {
                int totle = 0;
                for (Msgnostockdet msgnostockdet : msgnostockdetList) {
                    totle += Integer.valueOf(msgnostockdet.getEditetext()) * Integer.valueOf(msgnostockdet.getCostprice());
                }
                allmoney.setText("总金额:"+String.valueOf(totle));
            }
        });
        lv.setAdapter(msgnostockAdapter);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        title.setTitle("我的货存不足提醒");
    }

    @OnClick(R.id.activity_remind_good_less_btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_remind_good_less_btn:
                needselgoods();
                break;
        }
    }

    private HashMap<String, String> constructionOrder() {
        StringBuffer goodsidString = new StringBuffer();
        StringBuffer goodsnameString = new StringBuffer();
        StringBuffer goodssizeString = new StringBuffer();
        StringBuffer priceString = new StringBuffer();
        StringBuffer unitnameString = new StringBuffer();
        for (Msgnostockdet goods : msgnostockdetList) {
            goodsidString.append(goods.getGoodsid() + ";");
            goodsnameString.append(goods.getGoodsname() + ";");
            goodssizeString.append(goods.getGoodssize() + ";");
            priceString.append(goods.getCostprice() + ";");
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
        map.put("unitname", unitnameString.toString());
        Log.v("smbumitList", map.toString());
        return map;
    }

    void needselgoods() {
        if (msgnostockdetList.size() == 0) {
            ToastUtils.show(ActivityRemindGoodLess.this, "没有欠货数据");
            return;
        }
        showProgressDialog("请求数据中...");
        Call<BaseModel<ChooseGoods>> neeDselGoods = netApi.neeDselGoods(constructionOrder());
        neeDselGoods.enqueue(new Callback<BaseModel<ChooseGoods>>() {
            @Override
            public void onResponse(Response<BaseModel<ChooseGoods>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (("1").equals(response.body().getRes())) {
                        Intent intent = new Intent(ActivityRemindGoodLess.this, MyGoodsActvity.class);
                        for (Msgnostockdet goods : msgnostockdetList) {
                            for(ChooseGoods choose:response.body().getData()){
                                if(choose.getGoodsid().equals(goods.getGoodsid())){
                                    choose.setEditText(goods.getEditetext());
                                }
                            }
                        }
                        intent.putExtra("chooseGoodsList", (Serializable) response.body().getData());
                        intent.putExtra("ClassName", "ActivityRemindGoodLess");
                        startActivityForResult(intent, 0x005);
                    } else {
                        showToast(response.body().getMsg());
                    }
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("服务器错误");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x005 && resultCode == Activity.RESULT_OK) {
            finish();
        }
    }

    //9_11我的提醒_(我的货品不足_明细)
    void msgnostockdet() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<Msgnostockdet>> call = netApi.msgnostockdet(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgnostockdet>>() {

            @Override
            public void onResponse(Response<BaseModel<Msgnostockdet>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    if (response.body().getRes().equals("1")) {
                        msgnostockdetList.clear();
                        msgnostockdetList.addAll(response.body().getData());
                        int totle = 0;
                        for (Msgnostockdet msgnostockdet : msgnostockdetList) {
                            totle += Integer.valueOf(msgnostockdet.getDiffnum()) * Integer.valueOf(msgnostockdet.getCostprice());
                        }
                        allmoney.setText("总金额:"+String.valueOf(totle));
                        msgnostockAdapter.notifyDataSetChanged();
                    } else {
                        showToast("连接服务器失败");
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
