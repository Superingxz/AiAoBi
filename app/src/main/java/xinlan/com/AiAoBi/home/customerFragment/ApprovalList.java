package xinlan.com.AiAoBi.home.customerFragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.WaitExAdapter;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.enity.WaitExaminationInfo;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/2.
 */
public class ApprovalList extends BaseActivty {
    @BindView(R.id.approval_title_view)
    TitileView titleView;
    @BindView(R.id.approvalist_lv)
    ListView approvalistLv;
    @BindView(R.id.approval_msg)
    TextView tvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvalist);
        App.getApp().setHandler(handler);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        titleView.setTitle("申请升级审核");
        getData();
        initEvent();
    }

    private void initEvent() {
        approvalistLv.setOnItemClickListener(itemClickListener);
    }
    private AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent=new Intent(ApprovalList.this,Approval.class);
            intent.putExtra("info",infos.get(i));
            startActivity(intent);
        }
    };
    Map<String,String > map;
    List<WaitExaminationInfo> infos;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(ApprovalList.this.isFinishing()&&ApprovalList.this.isDestroyed())return;
            }
            if (msg.what==0x002){
                Log.i("log","===============0x002");
                getData();
            }
        }
    };
    private void getData(){
        map=new HashMap<>();
        infos=new ArrayList<>();
        map.put("slevel",user.getSlevel());
        map.put("curuserid",user.getPhone());
        showProgressDialog("获取数据中...");
        Call<BaseModel<WaitExaminationInfo>> call = netApi.requestWaitExaminationInfo(map);
        call.enqueue(new Callback<BaseModel<WaitExaminationInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<WaitExaminationInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<WaitExaminationInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        List<WaitExaminationInfo> data = body.getData();
                        Log.i("log","代理商待审核数据："+data);
                        infos.clear();
                        for (WaitExaminationInfo info:data) {
                            Log.i("log","代理商待审核信息："+info.toString());
                            if ("up".equals(info.getReftype())){//如果业务类型为“代理商升级”，则取出数据
                                infos.add(info);
                            }
                        }
                        if (infos.size()==0){
                            approvalistLv.setVisibility(View.GONE);
                            tvMsg.setVisibility(View.VISIBLE);
                        }else {
                            approvalistLv.setVisibility(View.VISIBLE);
                            tvMsg.setVisibility(View.GONE);
                        }
                        //把数据显示在列表上
                        WaitExAdapter adapter=new WaitExAdapter(ApprovalList.this,R.layout.item_wait_exinfo,infos);
                        adapter.notifyDataSetChanged();
                        approvalistLv.setAdapter(adapter);
                    }else {
                        showToast(body.getMsg());
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
