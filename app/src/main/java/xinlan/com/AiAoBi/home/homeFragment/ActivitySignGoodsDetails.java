package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterSignDoodsDetaisl;
import xinlan.com.AiAoBi.adapter.AdapterSignDoodsDetaislFoot;
import xinlan.com.AiAoBi.entity.AMData;
import xinlan.com.AiAoBi.entity.ASumData;
import xinlan.com.AiAoBi.entity.MBaseModel;
import xinlan.com.AiAoBi.entity.Msgtakegoods;
import xinlan.com.AiAoBi.entity.OkgoodsInfo;
import xinlan.com.AiAoBi.view.DiyListView;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/3.
 */
public class ActivitySignGoodsDetails extends BaseActivty {
    private ListView listView;
    private TitileView titile;
    private OkgoodsInfo okgoodsInfo;
    private View footview;
    private static final String TAG = "Details";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_goods_details);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initView();
        initEvent();
    }
    private DiyListView footLv;//listView尾部的listView
    private TextView tvMsg;//listView尾部的TextView
    private void initView() {
        listView= (ListView) findViewById(R.id.sign_goods_details_lv);
        titile= (TitileView) findViewById(R.id.sign_goods_details_title);
        footview = LayoutInflater.from(ActivitySignGoodsDetails.this).inflate(R.layout.activity_sign_goods_details_lv_foot,null);
        footLv= (DiyListView) footview.findViewById(R.id.sign_goods_details_lv_foot_lv);
        tvMsg= (TextView) footview.findViewById(R.id.sign_goods_details_lv_foot_tvmsg);
        listView.addFooterView(footview);
    }
    private View view;
    private int flags;
    private Msgtakegoods msgtakegoods;
    private void initEvent() {
        titile.setTitle("货品详情");
        Intent intent = getIntent();
        flags = intent.getFlags();
        if (flags==0x801){
            msgtakegoods = (Msgtakegoods) intent.getSerializableExtra("msgtakegoods");
            //5_2货品签收_(查询签收内容)
            okquygoods(msgtakegoods.getSkeyid(),msgtakegoods.getOrdertype());
        }else {
        okgoodsInfo= (OkgoodsInfo)intent.getSerializableExtra("okGoodsInfos");
            okquygoods(okgoodsInfo.getMkeyid(),okgoodsInfo.getOrdertype());
        }
        sumData=new ArrayList<>();
        view= LayoutInflater.from(this).inflate(R.layout.adapter_sign_goods_details_lv_foot_foot,null,false);
    }
    private List<AMData> data;
    private List<ASumData> sumData;
    private void okquygoods(String smkeyid,String ordertype) {
        showProgressDialog("获取数据中...");
        Map<String,String> map=new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("slevel",user.getSlevel());
        map.put("smkeyid",smkeyid);
        map.put("ordertype",ordertype);

        Call<MBaseModel> call = netApi.okquygoods(map);
        call.enqueue(new Callback<MBaseModel>() {
            @Override
            public void onResponse(Response<MBaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    MBaseModel body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                       if (body.getData()!=null){
                            data = body.getData();
                       }
                        if (body.getSumdata()!=null){
                             sumData = body.getSumdata();
                        }
                        if (data!=null&&data.size()!=0){
                            AdapterSignDoodsDetaisl adapter=new AdapterSignDoodsDetaisl(ActivitySignGoodsDetails.this,
                                    R.layout.adapter_sign_goods_details,data);
                            listView.setAdapter(adapter);
                        }
                        AdapterSignDoodsDetaislFoot footAdapter=new AdapterSignDoodsDetaislFoot(ActivitySignGoodsDetails.this,
                                R.layout.adapter_sign_goods_details_lv_foot,sumData);
                        if (sumData.size()!=0){
                            if (flags==0x801){
                                tvMsg.setText("订单号："+msgtakegoods.getMasterid()+"\n"+"订单收货汇总（我累积业绩:   )");
                            }else {
                                tvMsg.setText("订单号："+okgoodsInfo.getMasterid()+"\n"+"订单收货汇总（我累积业绩:   )");
                            }
                            if (footLv.getFooterViewsCount()==0){
                                int ordernums=0;
                                int deliverNums=0;
                                int OweGoodss=0;
                                for (ASumData info:sumData){
                                    if (!TextUtils.isEmpty(info.getTotalnum())&&!TextUtils.isEmpty(info.getAlreadynum())&&
                                            !TextUtils.isEmpty(info.getLessnum()))
                                    ordernums+=Integer.parseInt(info.getTotalnum());
                                    deliverNums+=Integer.parseInt(info.getAlreadynum());
                                    OweGoodss+=Integer.parseInt(info.getLessnum());
                                }
                                TextView ordernum= (TextView) view.findViewById(R.id.adapter_sign_goods_details_lv_foot_foot_ordernum);
                                TextView deliverNum= (TextView) view.findViewById(R.id.adapter_sign_goods_details_lv_foot_foot_deliverNum);
                                TextView OweGoods= (TextView) view.findViewById(R.id.adapter_sign_goods_details_lv_foot_foot_OweGoods);
                                ordernum.setText(ordernums+"");
                                deliverNum.setText(deliverNums+"");
                                OweGoods.setText(OweGoodss+"");
                                footLv.addFooterView(view);
                            }
                            footLv.setAdapter(footAdapter);
                        }
                    }else {
                        showToast(body.getMsg());
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
