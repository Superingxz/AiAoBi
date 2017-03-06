package xinlan.com.AiAoBi.home.myGoodsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterMyorderdet;
import xinlan.com.AiAoBi.entity.MyorderInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/17.
 */
public class ActivityMyLowerOrdersDetails extends BaseActivty {
    @BindView(R.id.activity_my_lower_orders_info_title)
    TitileView title;
    @BindView(R.id.activity_my_lower_orders_info_month)
    TextView month;
    @BindView(R.id.activity_my_lower_orders_info_agent)
    TextView agent;
    @BindView(R.id.activity_my_lower_orders_info_pruduct)
    TextView pruduct;
    @BindView(R.id.activity_my_lower_orders_info_cb)
    CheckBox cb;
    @BindView(R.id.activity_my_lower_orders_info_listview)
    ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lower_orders_infos);
    }
    private Map<String,String> map;
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
        myorderdet(map);
    }
    private MyorderInfo myorderInfo;
    private View inflate;
    private TextView totalNum;
    private TextView totalMoney;
    private void initEvent() {
        title.setTitle("每月明细");
        myorderInfo= (MyorderInfo) getIntent().getSerializableExtra("MyorderInfo");
        month.setText(myorderInfo.getOptdate());
        agent.setText(myorderInfo.getAgent_name());
        pruduct.setText(myorderInfo.getGoodsname());
        cb.setOnCheckedChangeListener(onCheckedChangeListener);
        inflate= LayoutInflater.from(this).inflate(R.layout.adapter_my_lower_orders_details_foot,null,false);
        totalNum = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_total_num);
        totalMoney = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_total_money);
        map=new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("month",myorderInfo.getOptdate());
        map.put("goodsid",myorderInfo.getGoodsid());
        map.put("quyagentid",myorderInfo.getAgent_id());
        map.put("isall",isall);
    }
    private String isall="no";
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b){
              isall="all";
            }else {
                isall="no";
            }
            map.put("isall",isall);
            myorderdet(map);
        }
    };

    private void myorderdet(Map<String,String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<MyorderInfo>> call = netApi.myorderdet(map);
        call.enqueue(new Callback<BaseModel<MyorderInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<MyorderInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<MyorderInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        AdapterMyorderdet adapterMyorderdet=new AdapterMyorderdet(ActivityMyLowerOrdersDetails.this,
                                R.layout.adapter_my_lower_orders_details,body.getData());
                        int nums=0;
                        float money=0;
                        for (MyorderInfo info:body.getData()){
                            nums += Integer.parseInt(info.getNum());
                            money += Float.parseFloat(info.getMoney());
                        }
                        totalNum.setText(nums+"");
                        totalMoney.setText(money+"");
                        if (listview.getFooterViewsCount()>0){
                            listview.removeFooterView(inflate);
                        }
                        listview.addFooterView(inflate);
                        listview.setAdapter(adapterMyorderdet);
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
