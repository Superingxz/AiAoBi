package xinlan.com.AiAoBi.home.personageFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
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
import xinlan.com.AiAoBi.adapter.AdapterGetagentdowna;
import xinlan.com.AiAoBi.entity.GetagentdownInfo;
import xinlan.com.AiAoBi.entity.GetagentdownaInfo;
import xinlan.com.AiAoBi.entity.GetagentdownbInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ActivityMyUnderclerkInfo extends BaseActivty {
    @BindView(R.id.my_underclerk_info_title)
    TitileView title;
    @BindView(R.id.my_underclerk_info_num)
    TextView num;
    @BindView(R.id.my_underclerk_info_name)
    TextView name;
    @BindView(R.id.my_underclerk_info_time)
    TextView time;
    @BindView(R.id.my_underclerk_info_phone)
    TextView phone;
    @BindView(R.id.my_underclerk_info_slevel)
    TextView slevel;
    @BindView(R.id.my_underclerk_info_address)
    TextView address;
    @BindView(R.id.my_underclerk_info_this_month)
    TextView thisMonth;
    @BindView(R.id.my_underclerk_info_three_month)
    TextView threeMonth;
    @BindView(R.id.my_underclerk_info_all)
    TextView all;
    @BindView(R.id.my_underclerk_info_lv)
    ListView lv;
    @BindView(R.id.my_underclerk_info_all_num)
    TextView allNum;
    @BindView(R.id.my_underclerk_info_all_monney)
    TextView allMonney;
    @BindView(R.id.my_underclerk_info_titlename)
    TextView titleName;
    @BindView(R.id.my_underclerk_info_tvmsg)
    TextView tvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_underclerk_info);
        ButterKnife.bind(this);
        initEvent();
    }
    private Map<String,String> map;
    private GetagentdownInfo getagentdownInfo;
    private void initEvent() {
        title.setTitle("直属下级详情");
        map=new HashMap<>();
        getagentdownInfo= (GetagentdownInfo) getIntent().getSerializableExtra("getagentdownInfo");
        titleName.setText(getagentdownInfo.getAgentname()+"-"+getagentdownInfo.getBrand()+getagentdownInfo.getSlevelname());
        num.setText(getagentdownInfo.getAgentid());
        name.setText(getagentdownInfo.getAgentname());
        time.setText(getagentdownInfo.getGrant_date());
        phone.setText(getagentdownInfo.getPhone());
        if ("1".equals(getagentdownInfo.getIsmore())){
            slevel.setText("全系"+getagentdownInfo.getSlevelname());
        }
        if ("0".equals(getagentdownInfo.getIsmore())){
            slevel.setText(getagentdownInfo.getBrand()+getagentdownInfo.getSlevelname());
        }
        address.setText(getagentdownInfo.getAddress());
        getagentdowna(getagentdownInfo.getAgentid(),"curmonth");//获取本月订单
        threemonthData=new ArrayList<>();
    }
    private List<GetagentdownaInfo> curmonthData;
    private List<GetagentdownaInfo> threemonthData;
    private List<GetagentdownaInfo> allData;
    private AdapterGetagentdowna adapterGetagentdowna;
    private void getagentdowna(String agentid, final String quyflag) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<GetagentdownaInfo>> call = netApi.getagentdowna(agentid,quyflag);
        call.enqueue(new Callback<BaseModel<GetagentdownaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetagentdownaInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetagentdownaInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        lv.setVisibility(View.VISIBLE);
                        tvMsg.setVisibility(View.GONE);
                        if ("curmonth".equals(quyflag)){
                            curmonthData=body.getData();
                            adapterGetagentdowna=new AdapterGetagentdowna(ActivityMyUnderclerkInfo.this,
                                    R.layout.adapter_my_underclerk_info,curmonthData);
                            lv.setAdapter(adapterGetagentdowna);
                        }
                        if ("threemonth".equals(quyflag)){
                            threemonthData=body.getData();
                            adapterGetagentdowna=new AdapterGetagentdowna(ActivityMyUnderclerkInfo.this,
                                    R.layout.adapter_my_underclerk_info,threemonthData);
                            lv.setAdapter(adapterGetagentdowna);
                        }
                        setTextToNumAndMoney(body.getData());
                    }else{
                        showToast(body.getMsg());
                        lv.setVisibility(View.GONE);
                        tvMsg.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.my_underclerk_info_this_month, R.id.my_underclerk_info_three_month, R.id.my_underclerk_info_all})
    public void onClick(View view) {
       // resetTab();
        Drawable drawable = ContextCompat.getDrawable(ActivityMyUnderclerkInfo.this, R.drawable.shape_listview3);
        switch (view.getId()) {
            case R.id.my_underclerk_info_this_month:
              //  thisMonth.setBackground(drawable);
                adapterGetagentdowna=new AdapterGetagentdowna(ActivityMyUnderclerkInfo.this,
                        R.layout.adapter_my_underclerk_info,curmonthData);
                lv.setAdapter(adapterGetagentdowna);
                setTextToNumAndMoney(curmonthData);
                break;
            case R.id.my_underclerk_info_three_month:
               // threeMonth.setBackground(drawable);
                if (threemonthData.size()==0){
                    getagentdowna(getagentdownInfo.getAgentid(),"threemonth");
                }
                adapterGetagentdowna=new AdapterGetagentdowna(ActivityMyUnderclerkInfo.this,
                        R.layout.adapter_my_underclerk_info,threemonthData);
                lv.setAdapter(adapterGetagentdowna);
                setTextToNumAndMoney(threemonthData);
                break;
            case R.id.my_underclerk_info_all:
               // all.setBackground(drawable);
                showSelectDateDialog();
                break;
        }
    }
    private void setTextToNumAndMoney(List<GetagentdownaInfo> list){
        if (list==null) return;
        int nums=0;
        float allmon=0;
        for (GetagentdownaInfo info:list){
            nums+=Integer.parseInt(info.getNum());
            allmon+=Float.parseFloat(info.getMoney());
        }
        allNum.setText("总数量："+nums);
        allMonney.setText("总金额："+(float)allmon+"元");
    }
    private void getagentdownb(Map<String,String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<GetagentdownaInfo>> call = netApi.getagentdownb(map);
        call.enqueue(new Callback<BaseModel<GetagentdownaInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<GetagentdownaInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel<GetagentdownaInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        allData=body.getData();
                        adapterGetagentdowna=new AdapterGetagentdowna(ActivityMyUnderclerkInfo.this,
                                R.layout.adapter_my_underclerk_info,allData);
                        lv.setAdapter(adapterGetagentdowna);
                        setTextToNumAndMoney(allData);
                    }else {
                        showToast(body.getMsg());
                        adapterGetagentdowna=new AdapterGetagentdowna(ActivityMyUnderclerkInfo.this,
                                R.layout.adapter_my_underclerk_info,null);
                        lv.setAdapter(adapterGetagentdowna);
                        allNum.setText("总数量："+0);
                        allMonney.setText("总金额："+0);
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

    private void resetTab() {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_listview2);
        thisMonth.setBackground(drawable);
        threeMonth.setBackground(drawable);
        all.setBackground(drawable);
    }
    private String fromStr;
    private String toStr;
    private void showSelectDateDialog(){
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_select);
        final TextView to= (TextView) window.findViewById(R.id.dialog_select_to);
        final TextView from= (TextView) window.findViewById(R.id.dialog_select_from);
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog fromDialog=new DatePickerDialog(ActivityMyUnderclerkInfo.this,AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                String  monthstr;
                                if (month<10){
                                    monthstr="0"+(month+1);
                                }else monthstr=(month+1)+"";
                                from.setText(year+monthstr);
                            }
                        },2016,0,1);
                fromDialog.show();
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog toDialog=new DatePickerDialog(ActivityMyUnderclerkInfo.this,AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                String  monthstr;
                                if (month<10){
                                    monthstr="0"+(month+1);
                                }else monthstr=(month+1)+"";
                                to.setText(year+monthstr);
                            }
                        },2016,0,1);
                toDialog.show();
            }
        });
        Button yes= (Button) window.findViewById(R.id.dialog_select_btnyes);
        Button cancel= (Button) window.findViewById(R.id.dialog_select_btncancel);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fromStr= from.getText().toString().trim();
                toStr=to.getText().toString().trim();
                if ("选择日期".equals(fromStr)){
                    showToast("请选择日期");
                }else if ("选择日期".equals(toStr)){
                    showToast("请选择日期");
                }else {
                    map.clear();
                    map.put("agentid",getagentdownInfo.getAgentid());
                    map.put("startdate",fromStr);
                    map.put("enddate",toStr);
                    getagentdownb(map);
                    alertDialog.dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

}
