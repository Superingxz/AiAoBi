package xinlan.com.AiAoBi.home.personageFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
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
import xinlan.com.AiAoBi.adapter.AdapterMyPerformanc;
import xinlan.com.AiAoBi.entity.GettotalInfo;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2017/2/10.
 * 我的累积业绩
 */
public class ActivityMyPerformance extends BaseActivty {
    @BindView(R.id.activity_performance_title)
    TitileView activityPerformanceTitle;
    @BindView(R.id.activity_performance_from_date)
    TextView activityPerformanceFromDate;
    @BindView(R.id.activity_performance_to_date)
    TextView activityPerformanceToDate;
    @BindView(R.id.activity_performance_spinner)
    Spinner activityPerformanceSpinner;
    @BindView(R.id.activity_performance_btn_check)
    Button activityPerformanceBtnCheck;
    @BindView(R.id.activity_performance_lv)
    ListView activityPerformanceLv;
    @BindView(R.id.activity_performance_lv_msg)
    TextView tvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_performmance);
        ButterKnife.bind(this);
        initEvent();
    }
    private List<String> goodList;
    private List<String> goodIdList;
    private ArrayAdapter<String> adapter;
    private List<GettotalInfo> list;
    private AdapterMyPerformanc adapterMyPerformanc;
    private String goodId;
    private void initEvent() {
        activityPerformanceTitle.setTitle("我的累积业绩");
        goodList=new ArrayList();
        goodIdList=new ArrayList();
        list=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,R.layout.adapter_mspinner,goodList);
        activityPerformanceSpinner.setAdapter(adapter);
        activityPerformanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                goodId=goodIdList.get(i);
                Log.i("titian", "========onItemSelected: "+goodId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getgoods();//选择和货品
        adapterMyPerformanc=new AdapterMyPerformanc(this,R.layout.adapter_my_performance_item,list);
        activityPerformanceLv.setAdapter(adapterMyPerformanc);
    }
    private void gettotal() {
        Map<String,String> map=new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("startdate",activityPerformanceFromDate.getText().toString().trim());
        map.put("enddate",activityPerformanceToDate.getText().toString().trim());
        map.put("goodsid",goodId);
        showProgressDialog("获取数据中...");
        Call<BaseModel<GettotalInfo>> call = netApi.gettotal(map);
        call.enqueue(new Callback<BaseModel<GettotalInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GettotalInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GettotalInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        tvMsg.setVisibility(View.GONE);
                        activityPerformanceLv.setVisibility(View.VISIBLE);
                        list.addAll(body.getData());
                        adapterMyPerformanc.notifyDataSetChanged();
                    }else {
                        showToast(body.getMsg());
                        tvMsg.setVisibility(View.VISIBLE);
                        activityPerformanceLv.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
                tvMsg.setVisibility(View.VISIBLE);
                activityPerformanceLv.setVisibility(View.GONE);
            }
        });
    }

    private void getgoods() {
        Map<String,String> map=new HashMap<>();
        map.put("curuserid",user.getPhone());
        map.put("type","all");
        Call<BaseModel<Goods>> call = netApi.getGoods(map);
        showProgressDialog("获取数据中...");
        call.enqueue(new Callback<BaseModel<Goods>>() {
            @Override
            public void onResponse(Response<BaseModel<Goods>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    dimissProgressDialog();
                    BaseModel<Goods> body = response.body();
                    if ("1".equals(body.getRes())){
                        goodList.add("全部");
                        goodIdList.add("all");
                        for (Goods info : body.getData()) {
                            goodList.add(info.getGoodsname());
                            goodIdList.add(info.getGoodsid());
                        }
                        adapter.notifyDataSetChanged();
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

    @OnClick({R.id.activity_performance_btn_check,R.id.activity_performance_from_date,R.id.activity_performance_to_date})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_performance_btn_check:
                if (activityPerformanceFromDate.getText().toString().trim().equals("选择日期")||
                        activityPerformanceToDate.getText().toString().trim().equals("选择日期")){
                    showToast("请先选择日期");
                    return;
                }
                gettotal();//获取列表数据
                break;
            case R.id.activity_performance_from_date:
                showDateDialog(activityPerformanceFromDate);
                break;
            case R.id.activity_performance_to_date:
                showDateDialog(activityPerformanceToDate);
                break;
        }
    }
    private void showDateDialog(final TextView view){
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
                        view.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2017,0,1);
        datePickerDialog.show();
    }
}
