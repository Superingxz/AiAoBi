package xinlan.com.AiAoBi.home.personageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.MypriceAdapter;
import xinlan.com.AiAoBi.entity.GetagentpriceInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/22.
 * 我的价格
 */
public class ActivityMyPrice extends BaseActivty {
    @BindView(R.id.my_price_titleview)
    TitileView titileView;
    @BindView(R.id.my_price_tv_msg)
    TextView tvMsg;
    @BindView(R.id.my_price_lv)
    ListView listView;
    @BindView(R.id.my_price_bar_price1)
    TextView price1;
    @BindView(R.id.my_price_bar_price2)
    TextView price2;
    @BindView(R.id.my_price_bar_more)
    TextView more;
    private static final String TAG = "ActivityMyPrice";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_price);
    }
    private Intent intent;
    private ArrayList<String> prices;
    private ArrayList<String> slevel;
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();

    }

    private void initEvent() {
        titileView.setTitle("我的价格");
        checkLayoutAndData();
        getagentprice();
        listView.setOnItemClickListener(listener);
        list=new ArrayList<>();
        prices=new ArrayList<>();
        slevel=new ArrayList<>();

    }

    private void checkLayoutAndData(){
        switch (user.getSlevel()){
            case "1":
                price1.setText("总裁（元）");
                price2.setText("官方（元）");
                break;
            case "2":
                price1.setText("官方（元）");
                price2.setText("总代（元）");
                break;
            case "3":
                price1.setText("总代（元）");
                price2.setText("一级（元）");
                break;
            case "4":
                price1.setText("一级（元）");
                price2.setText("经销商（元）");
                break;
            case "5":
                price1.setText("经销商（元）");
                price2.setText("特约（元）");
                break;
            case "6":
                price1.setText("特约（元）");
                price2.setText("花瓣（元）");
                more.setVisibility(View.GONE);
                break;
            case "7":
                price1.setText("花瓣（元）");
                price2.setVisibility(View.GONE);
                more.setVisibility(View.GONE);
                break;
        }
    }

    private AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            prices.clear();
            slevel.clear();
            switch (user.getSlevel()){
                case "1":
                    slevel.add("总代");
                    slevel.add("一级");
                    slevel.add("经销商");
                    slevel.add("特约");
                    slevel.add("花辦");
                   prices.add(list.get(i).getPrice3());
                   prices.add(list.get(i).getPrice4());
                   prices.add(list.get(i).getPrice5());
                   prices.add(list.get(i).getPrice6());
                   prices.add(list.get(i).getPrice7());
                    break;
                case "2":
                    slevel.add("一级");
                    slevel.add("经销商");
                    slevel.add("特约");
                    slevel.add("花辦");
                    prices.add(list.get(i).getPrice4());
                    prices.add(list.get(i).getPrice5());
                    prices.add(list.get(i).getPrice6());
                    prices.add(list.get(i).getPrice7());
                    break;
                case "3":
                    slevel.add("经销商");
                    slevel.add("特约");
                    slevel.add("花辦");
                    prices.add(list.get(i).getPrice5());
                    prices.add(list.get(i).getPrice6());
                    prices.add(list.get(i).getPrice7());
                    break;
                case "4":
                    slevel.add("特约");
                    slevel.add("花辦");
                    prices.add(list.get(i).getPrice6());
                    prices.add(list.get(i).getPrice7());
                    break;
                case "5":
                    slevel.add("花辦");
                    prices.add(list.get(i).getPrice7());
                    break;
                case "6":
                    return;
                case "7":

                    return;
            }
                intent=new Intent(ActivityMyPrice.this,ActivityMyPriceMore.class);
                intent.putStringArrayListExtra("price",prices);
                intent.putStringArrayListExtra("slevel",slevel);
                intent.putExtra("goodName",list.get(i).getGoodsname());
                intent.putExtra("goodSize",list.get(i).getGoodssize());
                startActivity(intent);
            slevel.clear();
            prices.clear();

        }
    };
    private List<GetagentpriceInfo> list;
    private void getagentprice() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<GetagentpriceInfo>> call = netApi.getagentprice(user.getAgentid());
        call.enqueue(new Callback<BaseModel<GetagentpriceInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetagentpriceInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetagentpriceInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //h获取数据成功
                        tvMsg.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        MypriceAdapter adapter=new MypriceAdapter(ActivityMyPrice.this,R.layout.adapter_my_price,body.getData());
                        listView.setAdapter(adapter);
                        list.addAll(body.getData());
                    }else {
                        showToast(body.getMsg());
                        tvMsg.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
                tvMsg.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        });
    }
}
