package xinlan.com.AiAoBi.home.homeFragment;

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
import xinlan.com.AiAoBi.adapter.WaitExAdapter;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.enity.WaitExaminationInfo;
import xinlan.com.AiAoBi.view.TitileView;

import static xinlan.com.AiAoBi.App.app;

/**
 * Created by Administrator on 2016/10/26.
 */
public class WaitExaminationActivity extends BaseActivty {
    ListView lv;
    TextView tvMsg;
    List<WaitExaminationInfo> waitExaminationInfoList=new ArrayList<>();
    WaitExAdapter waitExAdapter;
    Call<BaseModel<WaitExaminationInfo>> WaitExaminationInfoCall;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_examination_activity);
        initView();
        app.setHandler(handler);
    }
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if(WaitExaminationActivity.this.isFinishing()&&WaitExaminationActivity.this.isDestroyed())return;
                }
                if(msg.what==0x001){
                    requestData(user.getPhone(),user.getSlevel());
                }
            }
        };
    protected void initView() {
        lv = (ListView) findViewById(R.id.lv);
        tvMsg= (TextView) findViewById(R.id.wait_msg);
        waitExAdapter=new WaitExAdapter(context,R.layout.item_wait_exinfo,waitExaminationInfoList);
        lv.setAdapter(waitExAdapter);
        titileView = (TitileView) findViewById(R.id.title_view);
        titileView.setTitle("审核新代理商");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WaitExaminationInfo info = waitExaminationInfoList.get(position);
                Intent intent=new Intent(WaitExaminationActivity.this,ExaminationDetialsActivity.class);
                intent.putExtra("waitExaminationInfo",info);
                startActivity(intent);
            }
        });
        requestData(user.getPhone(),user.getSlevel());
    }
    void requestData(String curuserid,String slevel ) {
        showProgressDialog("获取待审核数据中...");
        Map<String,String> map=new HashMap<>();
        map.put("slevel",slevel );
        map.put("curuserid",curuserid);
        WaitExaminationInfoCall = netApi.requestWaitExaminationInfo(map);
        WaitExaminationInfoCall.enqueue(new Callback<BaseModel<WaitExaminationInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<WaitExaminationInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    dimissProgressDialog();
                    BaseModel<WaitExaminationInfo> body = response.body();
                    if("1".equals(body.getRes())){
                        waitExaminationInfoList.clear();
                        for (WaitExaminationInfo info:body.getData()){
                            if ("apply".equals(info.getReftype())){
                                waitExaminationInfoList.add(info);
                            }
                        }
                        waitExAdapter.notifyDataSetChanged();
                        if (waitExaminationInfoList.size()==0){
                            lv.setVisibility(View.GONE);
                            tvMsg.setVisibility(View.VISIBLE);
                        }else {
                            lv.setVisibility(View.VISIBLE);
                            tvMsg.setVisibility(View.GONE);
                        }
                    }else{
                        showToast("没有待审核数据");
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
