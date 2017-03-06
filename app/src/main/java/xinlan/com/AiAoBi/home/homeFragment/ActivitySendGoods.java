package xinlan.com.AiAoBi.home.homeFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
import xinlan.com.AiAoBi.adapter.AdapterMasterid;
import xinlan.com.AiAoBi.adapter.AdapterScanmansum;
import xinlan.com.AiAoBi.entity.ScangetbargaincodeInfo;
import xinlan.com.AiAoBi.entity.ScangetmanInfo;
import xinlan.com.AiAoBi.entity.ScanmansumIfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/5.
 */
public class ActivitySendGoods extends BaseActivty {
    @BindView(R.id.activity_send_goods_title)
    TitileView title;
    @BindView(R.id.activity_send_goods_receive_name)
    TextView receiveName;
    @BindView(R.id.activity_send_goods_receive_name2)
    TextView receiveName2;
    @BindView(R.id.activity_send_goods_select_btn)
    Button selectBtn;
    @BindView(R.id.activity_send_goods_lv)
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_goods);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        title.setTitle("我要发货");
        lv.setOnItemClickListener(clickListener);
        masterids=new ArrayList<>();
        lists=new ArrayList<>();
    }
    private AdapterView.OnItemClickListener clickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            scangetbargaincode();
        }
    };
    private List<String > masterids;
    private List<ScangetbargaincodeInfo > lists;
    /**
     * 6_3扫码发货_(通过收货人，查询订单号)
     */
    private void scangetbargaincode() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<ScangetbargaincodeInfo>> call = netApi.scangetbargaincode(scangetmanInfo.getAgent_id(),
                scangetmanInfo.getRefagent_id());
        call.enqueue(new Callback<BaseModel<ScangetbargaincodeInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<ScangetbargaincodeInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<ScangetbargaincodeInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        List<ScangetbargaincodeInfo> list = body.getData();
                        lists.clear();
                        lists.addAll(list);
                        masterids.clear();
                        for (ScangetbargaincodeInfo data:
                        list){
                            masterids.add(data.getMasterid());
                        }
                        showListDialog();
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

    private void showListDialog() {
        AdapterMasterid adapterMasterid=new AdapterMasterid(this,R.layout.adapter_masterid,masterids);
        new AlertDialog.Builder(this)
                .setTitle("请选择要处理的订单：")
                .setIcon(ContextCompat.getDrawable(this,R.mipmap.select))
                .setSingleChoiceItems(adapterMasterid, 0,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(ActivitySendGoods.this,ScanDeliverGoods.class);
                                intent.setFlags(0x502);
                                intent.putExtra("scangetbargaincodeInfo",lists.get(which));
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }
                )
                .setNegativeButton("取消", null)
                .show();
    }

    @OnClick(R.id.activity_send_goods_select_btn)
    public void onClick() {
        Intent intent=new Intent(ActivitySendGoods.this,ActivitySelectMan.class);
        startActivityForResult(intent,0x500);
    }
    private ScangetmanInfo scangetmanInfo;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x500&&resultCode==0x501){
            scangetmanInfo= (ScangetmanInfo) data.getSerializableExtra("scangetmanInfo");
            if (scangetmanInfo!=null){
                receiveName.setText(scangetmanInfo.getSlevelname()+"/"+scangetmanInfo.getAgent_name());
                receiveName2.setText(scangetmanInfo.getAgent_name()+"的发货客户订单：");
                scanmansum();
            }else {
                receiveName.setText("");
                receiveName2.setVisibility(View.INVISIBLE);
                adapterScanmansum=new AdapterScanmansum(ActivitySendGoods.this,R.layout.adapter_send_goods_lv,null);
                lv.setAdapter(adapterScanmansum);
            }
        }
    }
    private AdapterScanmansum adapterScanmansum;
    private void scanmansum() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<ScanmansumIfo>> call = netApi.scanmansum(user.getAgentid(), scangetmanInfo.getRefagent_id());
        call.enqueue(new Callback<BaseModel<ScanmansumIfo>>() {
            @Override
            public void onResponse(Response<BaseModel<ScanmansumIfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<ScanmansumIfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        List<ScanmansumIfo> datas = body.getData();
                        adapterScanmansum=new AdapterScanmansum(ActivitySendGoods.this,R.layout.adapter_send_goods_lv,datas);
                        lv.setAdapter(adapterScanmansum);
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
