package xinlan.com.AiAoBi.home.personageFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
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
import xinlan.com.AiAoBi.adapter.AdapterMyAward;
import xinlan.com.AiAoBi.entity.GetrewardInfo;
import xinlan.com.AiAoBi.entity.GetrewardtypeInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2017/1/18.
 */
public class ActivityMyAward extends BaseActivty {
    @BindView(R.id.my_award_title)
    TitileView myAwardTitle;
    @BindView(R.id.my_award_agent)
    TextView myAwardAgent;
    @BindView(R.id.my_award_all_award)
    TextView myAwardAllAward;
    @BindView(R.id.my_award_from_date)
    TextView myAwardFromDate;
    @BindView(R.id.my_award_to_date)
    TextView myAwardToDate;
    @BindView(R.id.my_award_sp)
    Spinner myAwardSp;
    @BindView(R.id.my_award_check)
    Button myAwardCheck;
    @BindView(R.id.my_award_lv)
    ListView myAwardLv;
    @BindView(R.id.my_award_lv_msg)
    TextView tvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_award);
        ButterKnife.bind(this);
        initEvent();
    }
    private List<String> typeList;
    private  ArrayAdapter<String> arrayAdapter;
    private List<GetrewardInfo> list;
    private AdapterMyAward adapterMyAward;
    private void initEvent() {
        myAwardTitle.setTitle("我的奖励");
        typeList=new ArrayList<>();
        list=new ArrayList<>();
        myAwardFromDate.addTextChangedListener(fromMtextWatch);
        myAwardToDate.addTextChangedListener(toMtextWatch);
        arrayAdapter=new ArrayAdapter<>(this,R.layout.adapter_mspinner_bar,typeList);
        arrayAdapter.setDropDownViewResource(R.layout.adapter_mspinner);
        myAwardSp.setAdapter(arrayAdapter);
        myAwardSp.setOnItemSelectedListener(onItemSelectedListener);
        getrewardtype();//4_5_1个人中心-我的奖励明细(获取奖励类型)
        adapterMyAward=new AdapterMyAward(this,R.layout.adapter_my_award_lvitem,list);
        myAwardLv.setAdapter(adapterMyAward);
    }
    private AdapterView.OnItemSelectedListener onItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            changetype=myAwardSp.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    /**
     * 4_5_1个人中心-我的奖励明细(获取奖励类型)
     */
    private void getrewardtype() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<GetrewardtypeInfo>> call = netApi.getrewardtype(user.getAgentid());
        call.enqueue(new Callback<BaseModel<GetrewardtypeInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetrewardtypeInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetrewardtypeInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                       for (GetrewardtypeInfo info:body.getData()){
                           typeList.add(info.getChangetype());
                       }
                        arrayAdapter.notifyDataSetChanged();
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

    private String fromDate;
    private String toDate;
    private MTextWatch fromMtextWatch=new MTextWatch(){
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            fromDate= myAwardFromDate.getText().toString().trim();
        }
    };

    private MTextWatch toMtextWatch=new MTextWatch(){
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            toDate=myAwardToDate.getText().toString().trim();
        }
    };

    @OnClick({R.id.my_award_from_date, R.id.my_award_to_date, R.id.my_award_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_award_from_date:
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
                                myAwardFromDate.setText(year+"-"+monthstr+"-"+daystr);
                            }
                        },2016,0,1);
                datePickerDialog.show();
                break;
            case R.id.my_award_to_date:
                DatePickerDialog todatePickerDialog=new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
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
                                myAwardToDate.setText(year+"-"+monthstr+"-"+daystr);
                            }
                        },2017,0,1);
                todatePickerDialog.show();
                break;
            case R.id.my_award_check:
                if ("选择日期".equals(fromDate)||"选择日期".equals(toDate)) {
                    showToast("请选择日期！");
                    return;
                }
                if (changetype==null){
                    showToast("奖励类型不能为空！");
                    return;
                }
                getreward();
                break;
        }
    }
    private String changetype;
    /**
     * 4_5个人中心-我的奖励明细
     */
    private void getreward() {
        showProgressDialog("获取数据中...");
        Map<String,String> map=new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("startdate",fromDate);
        map.put("enddate",toDate);
        map.put("changetype",changetype);
        Call<BaseModel<GetrewardInfo>> call = netApi.getreward(map);
        call.enqueue(new Callback<BaseModel<GetrewardInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetrewardInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetrewardInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        myAwardLv.setVisibility(View.VISIBLE);
                        tvMsg.setVisibility(View.GONE);
                        list.addAll(body.getData());
                        adapterMyAward.notifyDataSetChanged();
                    }else {
                        showToast(body.getMsg());
                        myAwardLv.setVisibility(View.GONE);
                        tvMsg.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
                myAwardLv.setVisibility(View.GONE);
                tvMsg.setVisibility(View.VISIBLE);
            }
        });
    }
}
