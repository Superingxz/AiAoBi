package xinlan.com.AiAoBi.home.homeFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
import xinlan.com.AiAoBi.adapter.AdapterRecentResults;
import xinlan.com.AiAoBi.entity.GetagentdownaInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ActivityRecentResults extends BaseActivty {
    @BindView(R.id.activity_recent_result_title)
    TitileView title;
    @BindView(R.id.activity_recent_result_from_date_msg)
    TextView fromDateMsg;
    @BindView(R.id.activity_recent_result_btn)
    TextView fromDateBtn;
    @BindView(R.id.activity_recent_result_to_date_msg)
    TextView toDateMsg;
    @BindView(R.id.activity_recent_result_to_date_btn)
    TextView toDateBtn;
    @BindView(R.id.activity_recent_result_lv)
    ListView lv;
    @BindView(R.id.activity_recent_result_tvmsg)
    TextView tvmsg;
    @BindView(R.id.activity_recent_result_check_btn)
    Button check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_results);
        ButterKnife.bind(this);
        initEvent();
    }
    private  Map<String,String> map;
    private  String agentid;
    private void initEvent() {
        title.setTitle("总代最近业绩");
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH,-1);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(instance.getTime());
        fromDateMsg.setText(date);
        toDateMsg.setText(CommonUtils.getDate("yyyy-MM-dd"));
        agentid=getIntent().getStringExtra("parentId");
        map=new HashMap<>();
        map.put("agentid",agentid);
        map.put("startdate",fromDateMsg.getText().toString().trim());
        map.put("enddate",toDateMsg.getText().toString().trim());
        getagentdownb(map);
    }

    private void getagentdownb(Map<String, String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<GetagentdownaInfo>> call = netApi.getagentdownb(map);
        call.enqueue(new Callback<BaseModel<GetagentdownaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetagentdownaInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetagentdownaInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        lv.setVisibility(View.VISIBLE);
                        tvmsg.setVisibility(View.GONE);
                        AdapterRecentResults adapterRecentResults=new AdapterRecentResults(ActivityRecentResults.this,
                                R.layout.adapter_recent_result,body.getData());
                        lv.setAdapter(adapterRecentResults);
                    }else {
                        lv.setVisibility(View.GONE);
                        tvmsg.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败");
                lv.setVisibility(View.GONE);
                tvmsg.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick({R.id.activity_recent_result_btn, R.id.activity_recent_result_to_date_btn,R.id.activity_recent_result_check_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_recent_result_btn:
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
                                fromDateMsg.setText(year+"-"+monthstr+"-"+daystr);
                            }
                        },2016,0,1);
                datePickerDialog.show();
                break;
            case R.id.activity_recent_result_to_date_btn:
                DatePickerDialog todate=new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
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
                                toDateMsg.setText(year+"-"+monthstr+"-"+daystr);
                            }
                        },2016,0,1);
                todate.show();
                break;
            case R.id.activity_recent_result_check_btn:
                map.put("agentid",agentid);
                map.put("startdate",fromDateMsg.getText().toString().trim());
                map.put("enddate",toDateMsg.getText().toString().trim());
                getagentdownb(map);
                break;
        }
    }
}
