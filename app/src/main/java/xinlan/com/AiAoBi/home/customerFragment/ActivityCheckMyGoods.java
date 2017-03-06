package xinlan.com.AiAoBi.home.customerFragment;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
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
import xinlan.com.AiAoBi.adapter.AdapterChekMyGood;
import xinlan.com.AiAoBi.entity.AgentpaymentaInfo;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.entity.MysendgoodsInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.homeFragment.CheckMyCustomer;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ActivityCheckMyGoods extends BaseActivty {
    @BindView(R.id.check_my_good_title)
    TitileView checkMyGoodTitle;
    @BindView(R.id.check_my_good_from)
    TextView checkMyGoodFrom;
    @BindView(R.id.check_my_good_to)
    TextView checkMyGoodTo;
    @BindView(R.id.check_my_good_pruduct_sp)
    Spinner checkMyGoodPruductSp;
    @BindView(R.id.check_my_good_btn_check)
    Button checkMyGoodBtnCheck;
    @BindView(R.id.check_my_good_recevice_man)
    TextView checkMyGoodReceviceMan;
    @BindView(R.id.check_my_good_select)
    Button checkMyGoodSelect;
    @BindView(R.id.check_my_good_lv)
    ListView checkMyGoodLv;
    @BindView(R.id.check_my_good_total_counts)
    TextView checkMyGoodTotalCounts;
    @BindView(R.id.check_my_good_total_money)
    TextView checkMyGoodTotalMoney;
    @BindView(R.id.check_my_good_tvmsg)
    TextView tvmsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_goods);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
        getGoods();
    }

    private void initEvent() {
        checkMyGoodTitle.setTitle("发货查询");
        map=new HashMap<>();
        goodIds=new ArrayList<>();
        goodNames=new ArrayList<>();
        checkMyGoodPruductSp.setOnItemSelectedListener(listener);
    }
    private String goodId;
    private AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            goodId=goodIds.get(checkMyGoodPruductSp.getSelectedItemPosition());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    @OnClick({R.id.check_my_good_btn_check, R.id.check_my_good_select,
            R.id.check_my_good_from,R.id.check_my_good_to})
    public void onClick(View view) {
        String startdate = checkMyGoodFrom.getText().toString().trim();
        String enddate = checkMyGoodTo.getText().toString().trim();
        switch (view.getId()) {
            case R.id.check_my_good_btn_check:

                if ("选择日期".equals(startdate)||"选择日期".equals(enddate)) {
                    showToast("请选择日期！");
                    return;
                }else{
                    map.clear();
                    map.put("agentid", user.getAgentid());
                    map.put("startdate", startdate);
                    map.put("enddate", enddate);
                    map.put("goodsid", goodId);
                    map.put("inagentid", inagentid);
                    mysendgoods(map);
                }
                break;
            case R.id.check_my_good_select:
                if ("选择日期".equals(startdate)||"选择日期".equals(enddate)) {
                    showToast("请选择日期！");
                    return;
                }
                Intent intent=new Intent(ActivityCheckMyGoods.this, CheckMyCustomer.class);
                intent.setFlags(3);
                intent.putExtra("startdate",checkMyGoodFrom.getText().toString().trim());
                intent.putExtra("enddate",checkMyGoodTo.getText().toString().trim());
                startActivityForResult(intent,0x023);
                break;
            case R.id.check_my_good_from:
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String  monthstr;
                        String  daystr;
                        if (month<10){
                             monthstr="0"+(month+1);
                        }else monthstr=(month+1)+"";
                        if (dayOfMonth<10){
                             daystr="0"+dayOfMonth;
                        }else daystr=dayOfMonth+"";
                        checkMyGoodFrom.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2016,0,1);
                datePickerDialog.show();
                break;
            case R.id.check_my_good_to:
                DatePickerDialog todatePickerDialog=new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String  monthstr;
                        String  daystr;
                        if (month<10){
                            monthstr="0"+(month+1);
                        }else monthstr=(month+1)+"";
                        if (dayOfMonth<10){
                            daystr="0"+dayOfMonth;
                        }else daystr=dayOfMonth+"";
                        checkMyGoodTo.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2016,0,1);
                todatePickerDialog.show();
                break;
        }
    }
    private List<String> goodIds;
    private List<String> goodNames;
    private Map<String,String> map;
    public void getGoods(){
        map.clear();
        map.put("curuserid",user.getPhone());
        final Call<BaseModel<Goods>> goods = netApi.getGoods(map);
        goods.enqueue(new Callback<BaseModel<Goods>>() {
            @Override
            public void onResponse(Response<BaseModel<Goods>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<Goods> body = response.body();
                    if ("1".equals(body.getRes())){
                        goodNames.add(0,"全部");
                        goodIds.add(0,"all");
                        for (int i=0;i<body.getData().size();i++){
                            goodNames.add(i+"、"+body.getData().get(i).getGoodsname());
                            goodIds.add(body.getData().get(i).getGoodsid());
                        }
                        ArrayAdapter<String> adapter=new ArrayAdapter<>(ActivityCheckMyGoods.this,
                                R.layout.adapter_mspinner_bar,goodNames);
                        checkMyGoodPruductSp.setAdapter(adapter);
                        adapter.setDropDownViewResource(R.layout.adapter_mspinner);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败！");
            }
        });
    }
    private String inagentid;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x023&&resultCode==0x021){
            AgentpaymentaInfo agentpaymentaInfo= (AgentpaymentaInfo) data.getSerializableExtra("agentpaymentaInfo");
            if (agentpaymentaInfo!=null){
                inagentid=agentpaymentaInfo.getAgentid();
                checkMyGoodReceviceMan.setText(agentpaymentaInfo.getAgentname());
            }else {
                checkMyGoodReceviceMan.setText("");
                inagentid=null;
            }

        }
    }

   private void mysendgoods(Map<String,String> map){
       showProgressDialog("获取数据中...");
       Call<BaseModel<MysendgoodsInfo>> call = netApi.mysendgoods(map);
       call.enqueue(new Callback<BaseModel<MysendgoodsInfo>>() {
           @Override
           public void onResponse(Response<BaseModel<MysendgoodsInfo>> response, Retrofit retrofit) {
               dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<MysendgoodsInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        checkMyGoodLv.setVisibility(View.VISIBLE);
                        tvmsg.setVisibility(View.GONE);
                        AdapterChekMyGood adapterChekMyGood=new AdapterChekMyGood(ActivityCheckMyGoods.this,
                                R.layout.adapter_check_my_good,body.getData());
                        checkMyGoodLv.setAdapter(adapterChekMyGood);
                        int alreadynums=0;
                        float totalMoney=0;
                        for (MysendgoodsInfo info:body.getData()){
                            alreadynums+= Integer.parseInt(info.getAlreadynum());
                            totalMoney+= Float.parseFloat(info.getAlreadynum())*Integer.parseInt(info.getCostprice());
                        }
                        checkMyGoodTotalCounts.setText("发货总数量"+alreadynums);
                        checkMyGoodTotalMoney.setText("总金额"+(float)totalMoney+"元");
                    }else {
                        checkMyGoodLv.setVisibility(View.GONE);
                        tvmsg.setVisibility(View.VISIBLE);
                        showToast(body.getMsg());
                    }
                }
           }

           @Override
           public void onFailure(Throwable t) {
               dimissProgressDialog();
               checkMyGoodLv.setVisibility(View.GONE);
               tvmsg.setVisibility(View.VISIBLE);
           }
       });
   }
}
