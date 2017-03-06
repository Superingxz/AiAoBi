package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterSelectMan;
import xinlan.com.AiAoBi.entity.ScangetmanInfo;
import xinlan.com.AiAoBi.entity.Scangetonemaninfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/5.
 */
public class ActivitySelectMan extends BaseActivty {
    @BindView(R.id.activity_select_man_title)
    TitileView title;
    @BindView(R.id.activity_select_man_keyword)
    EditText keyword;
    @BindView(R.id.activity_select_man_check_btn)
    Button checkBtn;
    @BindView(R.id.activity_select_man_lv)
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_man);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }
    private void initEvent() {
        title.setTitle("选择我的客户");
        keyword.addTextChangedListener(mTextWatch);
        title.setRightText("确定", new TitileView.RightBtIface() {
            @Override
            public void onClick() {
                Intent intent=new Intent();
                intent.putExtra("scangetmanInfo", scangetmanInfo);
                setResult(0x501,intent);
                finish();
            }
        });
        scangetman();//选择收货人
    }
    private String key;
    private MTextWatch mTextWatch=new MTextWatch(){
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            key=keyword.getText().toString().trim();
        }
    };
    private ScangetmanInfo scangetmanInfo;
    private void scangetman() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<ScangetmanInfo>> call = netApi.scangetman(user.getAgentid());
        call.enqueue(new Callback<BaseModel<ScangetmanInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<ScangetmanInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    final BaseModel<ScangetmanInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        final AdapterSelectMan adapterSelectMan = new AdapterSelectMan(ActivitySelectMan.this,
                                R.layout.adapter_check_my_customer,body.getData());
                        lv.setAdapter(adapterSelectMan);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                List<ScangetmanInfo> data = body.getData();
                                if (data.get(i).isCheck()) {
                                    data.get(i).setCheck(false);
                                    scangetmanInfo=null;
                                    adapterSelectMan.notifyDataSetChanged();
                                    return;
                                }
                                for (ScangetmanInfo info:
                                        data) {
                                    info.setCheck(false);

                                }
                                data.get(i).setCheck(true);
                                scangetmanInfo=data.get(i);
                                adapterSelectMan.notifyDataSetChanged();
                            }
                        });
                    }else showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }

    @OnClick(R.id.activity_select_man_check_btn)
    public void onClick() {
        scangetmanInfo=null;
        if (TextUtils.isEmpty(key)){
            scangetman();
        }else
        scangetoneman(user.getAgentid(),key);
    }

    private void scangetoneman(String agentid,String agentname) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<ScangetmanInfo>> call = netApi.scangetoneman(agentid, agentname);
        call.enqueue(new Callback<BaseModel<ScangetmanInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<ScangetmanInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    final BaseModel<ScangetmanInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        final AdapterSelectMan adapterSelectMan = new AdapterSelectMan(ActivitySelectMan.this,
                                R.layout.adapter_check_my_customer,body.getData());
                        lv.setAdapter(adapterSelectMan);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                List<ScangetmanInfo> data = body.getData();
                                if (data.get(i).isCheck()) {
                                    data.get(i).setCheck(false);
                                    scangetmanInfo=null;
                                    adapterSelectMan.notifyDataSetChanged();
                                    return;
                                }
                                for (ScangetmanInfo info:
                                        data) {
                                    info.setCheck(false);

                                }
                                data.get(i).setCheck(true);
                                scangetmanInfo=data.get(i);
                                adapterSelectMan.notifyDataSetChanged();
                            }
                        });
                    }else {
                        final AdapterSelectMan adapterSelectMan = new AdapterSelectMan(ActivitySelectMan.this,
                                R.layout.adapter_check_my_customer,body.getData());
                        lv.setAdapter(adapterSelectMan);
                        showMsgDialog(body.getMsg(),0);
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
