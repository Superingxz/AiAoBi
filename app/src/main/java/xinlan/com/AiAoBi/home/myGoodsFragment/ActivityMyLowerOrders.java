package xinlan.com.AiAoBi.home.myGoodsFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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
import xinlan.com.AiAoBi.adapter.AdapterMyorder;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.entity.MyorderInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/16.
 */
public class ActivityMyLowerOrders extends BaseActivty {
    @BindView(R.id.my_lower_orders_title)
    TitileView myLowerOrdersTitle;
    @BindView(R.id.my_lower_orders_from_date)
    TextView myLowerOrdersFromDate;
    @BindView(R.id.my_lower_orders_to_date)
    TextView myLowerOrdersToDate;
    @BindView(R.id.my_lower_orders_spinner)
    AppCompatSpinner myLowerOrdersSpinner;
    @BindView(R.id.my_lower_orders_agent)
    EditText myLowerOrdersAgent;
    @BindView(R.id.my_lower_orders_btn_check)
    Button myLowerOrdersBtnCheck;
    @BindView(R.id.my_lower_orders_listview)
    ListView myLowerOrdersListview;
    @BindView(R.id.my_lower_orders_listview_tvmsg)
    TextView myLowerOrdersTvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lower_orders);
        ButterKnife.bind(this);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }

    private View inflate;
    private Map<String, String> map;

    private void initEvent() {
        myLowerOrdersTitle.setTitle("下级订单查询");
        inflate = LayoutInflater.from(ActivityMyLowerOrders.this).inflate(R.layout.adapter_my_lower_orders_bar, null, false);
        myLowerOrdersSpinner.setOnItemSelectedListener(listener);
        myLowerOrdersListview.setOnItemClickListener(itemClickListener);
        myLowerOrdersFromDate.setText(CommonUtils.getDate("yyyy-MM-dd"));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,3);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String date = f.format(calendar.getTime());
        myLowerOrdersToDate.setText(date);
        map = new HashMap<>();
        goodIdList = new ArrayList<>();
        map.put("curuserid", user.getPhone());
        getGoods(map);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (infos.size() != 0&&i<infos.size()) {
                Intent intent = new Intent(ActivityMyLowerOrders.this, ActivityMyLowerOrdersDetails.class);
                intent.putExtra("MyorderInfo", infos.get(i));
                startActivity(intent);
            }
        }
    };
    private String goodId;
    private static final String TAG = "MyLowerOrders";
    private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            int position = myLowerOrdersSpinner.getSelectedItemPosition();
            if (goodIdList.size() != 0) {
                goodId = goodIdList.get(position);
                Log.i(TAG, "onItemSelected: " + goodId);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private List<String> goodIdList;

    private void getGoods(Map<String, String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<Goods>> call = netApi.getGoods(map);
        call.enqueue(new Callback<BaseModel<Goods>>() {
            @Override
            public void onResponse(Response<BaseModel<Goods>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<Goods> body = response.body();
                    if ("1".equals(body.getRes())) {
                        List<Goods> data = body.getData();
                        List<String> gooodsName = new ArrayList<>();
                        gooodsName.clear();
                        gooodsName.add(0, "全部");
                        goodIdList.add(0, "all");
                        for (int i = 0; i < data.size(); i++) {
                            gooodsName.add((i + 1) + ". " + data.get(i).getGoodsname());
                            goodIdList.add(data.get(i).getGoodsid());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ActivityMyLowerOrders.this,
                                R.layout.adapter_mspinner_bar, gooodsName);
                        adapter.setDropDownViewResource(R.layout.adapter_mspinner);
                        myLowerOrdersSpinner.setAdapter(adapter);
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

    @OnClick(R.id.my_lower_orders_btn_check)
    public void onClick() {
        //11_1我的订单_(下级订单查询 )
        if ("选择日期".equals(myLowerOrdersFromDate.getText().toString().trim())) {
            showToast("请选择日期");
        } else if ("选择日期".equals(myLowerOrdersToDate.getText().toString().trim())) {
            showToast("请选择日期");
        } else {
            map.clear();
            map.put("agentid", user.getAgentid());
            map.put("startdate", myLowerOrdersFromDate.getText().toString().trim());
            map.put("enddate", myLowerOrdersToDate.getText().toString().trim());
            map.put("goodsid", goodId);
            map.put("quyagentid", myLowerOrdersAgent.getText().toString().trim());
            myorder(map);
        }
    }

    private List<MyorderInfo> infos;

    private void myorder(final Map<String, String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<MyorderInfo>> call = netApi.myorder(map);
        call.enqueue(new Callback<BaseModel<MyorderInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<MyorderInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<MyorderInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        infos = body.getData();
                        if (body.getData().size() == 0) {
                            myLowerOrdersTvMsg.setVisibility(View.VISIBLE);
                            myLowerOrdersListview.setVisibility(View.GONE);
                        } else {
                            myLowerOrdersTvMsg.setVisibility(View.GONE);
                            myLowerOrdersListview.setVisibility(View.VISIBLE);
                            AdapterMyorder adapterMyorder = new AdapterMyorder(ActivityMyLowerOrders.this,
                                    R.layout.adapter_my_lower_orders, body.getData());
                            TextView total = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_total);
                            TextView num = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_total_num);
                            TextView money = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_total_money);
                            TextView barnum = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_num);
                            TextView month = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_month);
                            TextView agent = (TextView) inflate.findViewById(R.id.adapter_my_lower_orders_bar_agent);
                            barnum.setText("");
                            month.setText("");
                            agent.setText("");
                            total.setText("小计");
                            int nums = 0;
                            float moneys = 0;
                            for (MyorderInfo info : body.getData()) {
                                nums += Integer.parseInt(info.getNum());
                                moneys += Float.parseFloat(info.getMoney());
                            }
                            num.setText(nums + "");
                            money.setText(moneys + "");
                            if (myLowerOrdersListview.getFooterViewsCount() > 0) {
                                myLowerOrdersListview.removeFooterView(inflate);
                            }
                            myLowerOrdersListview.addFooterView(inflate);
                            myLowerOrdersListview.setAdapter(adapterMyorder);
                        }
                    } else {
                        myLowerOrdersTvMsg.setVisibility(View.VISIBLE);
                        myLowerOrdersListview.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
                myLowerOrdersTvMsg.setVisibility(View.VISIBLE);
                myLowerOrdersListview.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.my_lower_orders_from_date, R.id.my_lower_orders_to_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_lower_orders_from_date:
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
                        myLowerOrdersFromDate.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2016,0,1);
                datePickerDialog.show();
                break;
            case R.id.my_lower_orders_to_date:
                DatePickerDialog to=new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,
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
                        myLowerOrdersToDate.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2016,0,1);
                to.show();
                break;
        }
    }
}
