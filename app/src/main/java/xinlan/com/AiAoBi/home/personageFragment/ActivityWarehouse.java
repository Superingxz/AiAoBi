package xinlan.com.AiAoBi.home.personageFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import xinlan.com.AiAoBi.adapter.AdapterWarehouse;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.homeFragment.ChooseGoodsActivity;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/27.
 */
public class ActivityWarehouse extends BaseActivty {
    @BindView(R.id.activity_warehouse_title)
    TitileView warehouseTitle;
    @BindView(R.id.activity_warehouse_lv)
    ListView warehouseLv;
    @BindView(R.id.activity_warehouse_tvmsg)
    TextView warehouseTvmsg;
    @BindView(R.id.activity_warehouse_btn)
    Button warehouseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }
    private TextView xj;
    private void initEvent() {
        warehouseTitle.setTitle("货存量设置");
        mlist =new ArrayList<>();
        map=new HashMap<>();
        goodsBottomView = LayoutInflater.from(this).inflate(R.layout.adapter_warehouse_fot,null);
        getquyminstock();//4_4_1个人中心中-查询代理商最低库存设置
        warehouseTitle.setRightText("选择货品", new TitileView.RightBtIface() {
            @Override
            public void onClick() {
                Intent intent=new Intent(ActivityWarehouse.this, ChooseGoodsActivity.class);
                intent.setFlags(0x006);
                intent.putExtra("warehouseList", (Serializable) mlist);
                startActivityForResult(intent,0x016);
            }
        });
    }
    private StringBuilder goodsids;
    private StringBuilder goodsnames;
    private StringBuilder goodssizes;
    private StringBuilder unitnames;
    private StringBuilder nums;
    private void initBuilder() {
         goodsids=new StringBuilder();
         goodsnames=new StringBuilder();
         goodssizes=new StringBuilder();
         unitnames=new StringBuilder();
         nums=new StringBuilder();
    }

    private void getquyminstock() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<Goods>> call = netApi.getquyminstock(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Goods>>() {
            @Override
            public void onResponse(Response<BaseModel<Goods>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<Goods> body = response.body();
                    if ("1".equals(body.getRes())){
                        warehouseTvmsg.setVisibility(View.GONE);
                        warehouseLv.setVisibility(View.VISIBLE);
                        List<Goods> data = body.getData();
                        for (Goods good:data){
                            good.setEditText(good.getMinstock());
                        }
                        mlist.addAll(data);
                        setDataToWarehouseLv(mlist);
                    }else {
                        warehouseTvmsg.setVisibility(View.VISIBLE);
                        warehouseLv.setVisibility(View.GONE);
                        showToast(body.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("服务器错误");
                warehouseTvmsg.setVisibility(View.VISIBLE);
                warehouseLv.setVisibility(View.GONE);
            }
        });
    }
    private List<Goods> mlist;
    private Map<String,String> map;
    @OnClick(R.id.activity_warehouse_btn)
    public void onClick() {
        if (mlist==null||mlist.isEmpty()){
            showToast("请输入货品信息后再保存");
            return;
        }
        initBuilder();
        for (Goods goods: mlist){
            goodsids.append(goods.getGoodsid()).append(";");
            goodsnames.append(goods.getGoodsname()).append(";");
            goodssizes.append(goods.getGoodssize()).append(";");
            unitnames.append(goods.getUnitname()).append(";");
            nums.append(goods.getEditText()).append(";");
        }
        goodsids.deleteCharAt(goodsids.length()-1);
        goodsnames.deleteCharAt(goodsnames.length()-1);
        goodssizes.deleteCharAt(goodssizes.length()-1);
        unitnames.deleteCharAt(unitnames.length()-1);
        map.clear();
        map.put("agentid",user.getAgentid());
        map.put("goodsid",goodsids.toString());
        map.put("goodsname",goodsnames.toString());
        map.put("goodssize",goodssizes.toString());
        map.put("unitname",unitnames.toString());
        map.put("num",nums.toString());
        getagminstock(map);
    }

    private void getagminstock(Map<String, String> map) {
        showProgressDialog("正在提交...");
        Call<BaseModel> call = netApi.getagminstock(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }

    private View goodsBottomView;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x016&&resultCode== Activity.RESULT_OK){
            warehouseTvmsg.setVisibility(View.GONE);
            warehouseLv.setVisibility(View.VISIBLE);
            //选择到的物品
            List<Goods> list= (List<Goods>) data.getSerializableExtra("chooseGoodsList");
            this.mlist.addAll(list);
            setDataToWarehouseLv(this.mlist);
        }
    }
    private void setDataToWarehouseLv(final List<Goods> list){
        final AdapterWarehouse adapterWarehouse=new AdapterWarehouse(this,R.layout.adapter_warehouse,list);
        warehouseLv.setAdapter(adapterWarehouse);
        if (warehouseLv.getFooterViewsCount()==0)
        warehouseLv.addFooterView(goodsBottomView);
        int totle=0;
        for (Goods goods:mlist){
            totle += Integer.valueOf(goods.getEditText());
        }

        final TextView xj= (TextView) goodsBottomView.findViewById(R.id.subtotal);
        xj.setText(totle+"");
        adapterWarehouse.setEditeIface(new AdapterWarehouse.EditeIface() {
            @Override
            public void edite(int position, String num) {
                Log.v("edite", String.valueOf(num) + "-" + String.valueOf(position));
                for (int i=0;i<mlist.size();i++){
                    if (i==position){
                        mlist.get(i).setEditText(num);
                    }
                }
               updateNum();
            }

            private void updateNum() {
                int totle = 0;
                for (Goods goods : mlist) {
                    totle += Integer.valueOf(goods.getEditText());
                }
                xj.setText(totle+"");

            }

            @Override
            public void deleteItem() {
                updateNum();
            }

            @Override
            public void deleteAll() {
                warehouseTvmsg.setVisibility(View.VISIBLE);
                warehouseLv.setVisibility(View.GONE);
            }
        });
    }

}
