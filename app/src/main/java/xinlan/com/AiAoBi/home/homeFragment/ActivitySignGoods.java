package xinlan.com.AiAoBi.home.homeFragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterSignGoods;
import xinlan.com.AiAoBi.entity.OkgoodsInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ActivitySignGoods extends BaseActivty {
    @BindView(R.id.activity_sign_goods_title)
    TitileView title;
    @BindView(R.id.sign_goods_lv)
    ListView signGoodsLv;
    @BindView(R.id.sign_goods_tvmsg)
    TextView signGoodsTvmsg;
    private Handler handler=new Handler(){
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(ActivitySignGoods.this.isFinishing()&&ActivitySignGoods.this.isDestroyed())return;
            if (msg.what==0x121){
                if (ActivitySignGoods.this.isFinishing()&&ActivitySignGoods.this.isDestroyed()) return;
                //刷新列表
                okgoods(user.getAgentid());
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_goods);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
        okgoods(user.getAgentid());
    }
    private Intent intent;
    private List<OkgoodsInfo> list;
    private void initEvent() {
        title.setTitle("货品签收");
        intent=new Intent();
        list=new ArrayList<>();
        signGoodsLv.setOnItemClickListener(listener);
        App.getApp().setHandler(handler);
    }
    private AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            intent.setClass(ActivitySignGoods.this,ActivitySignGoodsDetails.class);
            intent.putExtra("okGoodsInfos",list.get(i));
            startActivity(intent);
        }
    };

    private void okgoods(String agentid) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<OkgoodsInfo>> call = netApi.okgoods(agentid);
        call.enqueue(new Callback<BaseModel<OkgoodsInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<OkgoodsInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<OkgoodsInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        signGoodsTvmsg.setVisibility(View.GONE);
                        signGoodsLv.setVisibility(View.VISIBLE);
                        //获取数据成功
                        list=body.getData();
                        AdapterSignGoods adapter=new AdapterSignGoods(ActivitySignGoods.this,
                                R.layout.adapter_sign_goods,body.getData());
                        signGoodsLv.setAdapter(adapter);
                    }else {
                        showToast(body.getMsg());
                        signGoodsTvmsg.setVisibility(View.VISIBLE);
                        signGoodsLv.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                signGoodsTvmsg.setVisibility(View.VISIBLE);
                signGoodsLv.setVisibility(View.GONE);
            }
        });
    }
}
