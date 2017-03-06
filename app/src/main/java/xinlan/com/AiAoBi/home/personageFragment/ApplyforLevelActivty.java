package xinlan.com.AiAoBi.home.personageFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
import xinlan.com.AiAoBi.entity.GetSetInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;


/**
 * Created by Administrator on 2016/10/26.
 */
public class ApplyforLevelActivty extends BaseActivty {
    TextView accredit_code, accredit_name, accredit_time, phone, car_id, wx_id, superior, referrer, level;
    ImageView pay_img;
    LinearLayout layoutIdCard;
    Call<BaseModel> agentupCall;
    Button agreeup;
    LinearLayout apply_for_level_layout, pay_layout;
    Spinner spApply_for_level, sPBrand;
    String suplevel;
    String brand;
    String brandid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agency_applyfor_upgrade_activity);
        initView();
        initSpiner();
        setTitle("代理商申请升级");
        setData();
    }

    /**
     * 初始化spinner
     */
    void initSpiner(){
        sPBrand = (Spinner) findViewById(R.id.series);
        getset(user.getPhone());
        spApply_for_level= (Spinner) findViewById(R.id.apply_for_level);
        String[] slevelArray=new String[]{"总代","一级","经销商","特约"};
        ArrayAdapter<String> spApplyAdapter=new ArrayAdapter<>(this,R.layout.adapter_mspinner_geography_bar,slevelArray);
        spApplyAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
        spApply_for_level.setAdapter(spApplyAdapter);
        spApply_for_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                suplevel=spApply_for_level.getSelectedItem().toString().trim();
                switch (suplevel){
                    /*case "官方":
                        suplevel="2";
                        break;*/
                    case "总代":
                        suplevel="3";
                        break;
                    case "一级":
                        suplevel="4";
                        break;
                    case "经销商":
                        suplevel="5";
                        break;
                    case "特约":
                        suplevel="6";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sPBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 brand = sPBrand.getSelectedItem().toString().trim();
                 brandid=brandids.get(sPBrand.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    List<String> brands;
    List<String> brandids;
    /**
     * 获取单品或全系
     */
    public void getset(String curuserid){
        brands =new ArrayList<>();
        brandids=new ArrayList<>();
        Call<GetSetInfo> call = netApi.getSet(curuserid);
       showProgressDialog("请求服务器...");
        call.enqueue(new Callback<GetSetInfo>() {
            @Override
            public void onResponse(Response<GetSetInfo> response, Retrofit retrofit) {
                Log.i("log","getSet.onResponse:"+response.code());
                if (response.isSuccess()){
                    GetSetInfo body = response.body();
                    if ("1".equals(body.getRes())){
                       dimissProgressDialog();
                        ArrayList<GetSetInfo.data> data = body.getData();
                        for (int i=0;i<data.size();i++){
                            brands.add(data.get(i).getGoods_name());
                            brandids.add(data.get(i).getGoodsid());
                        }
                        ArrayAdapter<String> spSeriesAdapter=new ArrayAdapter<String>(
                                ApplyforLevelActivty.this,R.layout.adapter_mspinner_geography_bar, brands);
                        spSeriesAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
                        sPBrand.setAdapter(spSeriesAdapter);
                    }else {
                        dimissProgressDialog();
                        finish();
                        showToast("服务器出错");
                    }
                }else {
                    dimissProgressDialog();
                    finish();
                   showToast("服务器出错");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                finish();
                showToast("连接服务器失败");
            }
        });
    }
   /* 功能编码：eventcode=agentup
    2 当前登录用户code: curuserid 例如135705401327
    4 申请代理商id：agentid
    5 申请代理商级别：slevel(取值【3:总代;4:一级;5:经销商;6:特约;7:花瓣】);
    6  申请代理商升级后级别：suplevel(取值【3:总代;4:一级;5:经销商;6:特约;7:花瓣】);
    7 品牌id: brandid (取值为货品id);
    shengji64 品牌:brand (取值为货品名称);*/
    //int[] slevels=new int[]{3,4,5,6,7};
    protected void initView() {
        layoutIdCard= (LinearLayout) findViewById(R.id.apply_layout_idcard);
        int slevel=Integer.valueOf(user.getSlevel());
        if (slevel>=5) layoutIdCard.setVisibility(View.GONE);
        agreeup = (Button) findViewById(R.id.agreeup);
        agreeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(user.getSlevel()) > 2) {
                    Log.i("log", "升级审批参数: curuserid"+"="+user.getPhone()+"slevel="+ user.getSlevel()+"suplevel="+suplevel+"agentid="+user.getAgentid()+"brand="+
                            brand+"brandid="+brandid);
                    requestData(user.getPhone(), user.getSlevel(),suplevel,user.getAgentid(),brand,brandid);
                } else {
                    showToast("总裁或官方通过后台升级");
                }
            }
        });
        apply_for_level_layout = (LinearLayout) findViewById(R.id.apply_for_level_layout);
        pay_layout = (LinearLayout) findViewById(R.id.pay_layout);
        //编号
        titileView = (TitileView) findViewById(R.id.title_view);
        accredit_code = (TextView) findViewById(R.id.accredit_code);
        accredit_name = (TextView) findViewById(R.id.accredit_name);
        accredit_time = (TextView) findViewById(R.id.accredit_time);
        phone = (TextView) findViewById(R.id.phone);
        car_id = (TextView) findViewById(R.id.car_id);
        wx_id = (TextView) findViewById(R.id.wx_id);
        //上级
        superior = (TextView) findViewById(R.id.superior);
        //推荐人
        referrer = (TextView) findViewById(R.id.referrer);
        //当前级别
        level = (TextView) findViewById(R.id.level);
        //pay_img
        pay_img = (ImageView) findViewById(R.id.pay_img);
        if (Integer.valueOf(user.getSlevel()) <= 2) {
            apply_for_level_layout.setVisibility(View.GONE);
        }else apply_for_level_layout.setVisibility(View.VISIBLE);

    }
    void setData() {
        level.setText(WaitExAdapter.slevelArray[Integer.valueOf(user.getSlevel()) - 1]);
        referrer.setText(user.getRefman() + "/" + user.getRefmantel());
        accredit_time.setText(user.getGrant_date()+"");
        Log.i("log","授权时间:"+user.getGrant_date());
        wx_id.setText(user.getWeixin());
        if (TextUtils.isEmpty(user.getCardno()))layoutIdCard.setVisibility(View.GONE);
        else car_id.setText(user.getCardno());
        phone.setText(user.getPhone());
        if (!TextUtils.isEmpty(user.getCurparentlevel())){

            int i = Integer.valueOf(user.getCurparentlevel());
            if (i==0) {
                superior.setText(user.getCurparentname() + "/" + user.getCurparenttel());
            }else superior.setText(user.getCurparentname()+"/"+user.getCurparenttel()+"/"
                    +WaitExAdapter.slevelArray[Integer.valueOf(user.getCurparentlevel())-1]);
        }
        accredit_code.setText(user.getAgentcode());
        accredit_name.setText(user.getAgentname());
        Log.i("log", "凭据url: "+user.getVoucher());
        if (TextUtils.isEmpty(user.getVoucher())) {
            pay_layout.setVisibility(View.GONE);
        } else {
            pay_layout.setVisibility(View.VISIBLE);
            Glide.with(this).load(user.getVoucher())
                    .error(ContextCompat.getDrawable(this,R.mipmap.error200))
                    .into(pay_img);
        }
        if (user.getIsmore().equals("0")&&user.getSlevel()!=null) {
            level.setText(WaitExAdapter.slevelArray[Integer.valueOf(user.getSlevel()) - 1] + "/" + user.getBrand());
        } else {
            level.setText(WaitExAdapter.slevelArray[Integer.valueOf(user.getSlevel()) - 1] + "/" + "全系");
        }
    }
    void requestData(String curuserid, String slevel, String suplevel,String agentid,String brand,String brandid) {

        showProgressDialog("连接服务器...");
        Map<String, String> map = new HashMap<>();
        map.put("curuserid",curuserid);
        map.put("agentid",agentid);
        map.put("slevel",slevel);
        map.put("suplevel",suplevel);
        map.put("brandid",brandid);
        map.put("brand",brand);
        Log.i("log","requestData: "+map);
        agentupCall = netApi.agentup(map);
        agentupCall.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    dimissProgressDialog();
                    showMsgDialog(response.body().getMsg());
                    /*switch (response.body().getRes()) {
                        case "0":
                            showMsgDialog("没有代理审批信息");
                            break;
                        case "1":
                            showMsgDialog("代理商升级申请成功，等待审核");
                            break;
                        case "01":
                            showMsgDialog("代理商升级申请成功，等待审核");
                            break;
                        case "02":
                            showMsgDialog("代理商级别有误");
                            break;
                        case "03":
                            showMsgDialog("当前代理商级别比升级后别级高,不能升级!");
                            break;
                        case "04":
                            showMsgDialog("代理商注册进展为【待总代审批、待总裁审批、待公司审批】");
                            break;
                        case "05":
                            showMsgDialog("生成总代审批失败");
                            break;
                        case "06":
                            showMsgDialog("生成总裁审批失败");
                            break;
                        case "07":
                            showMsgDialog("生成公司审批失败");
                            break;
                    }*/
                }
            }
            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败");
            }
        });
    }
    public void showMsgDialog(String msg) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("提示信息!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
