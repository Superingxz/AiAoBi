package xinlan.com.AiAoBi.home.myGoodsFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
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
import xinlan.com.AiAoBi.adapter.AdapterCheckMyOrders;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.entity.MyorderInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/16.
 */
public class ActivityCheckMyOrders extends BaseActivty {
    @BindView(R.id.my_lower_orders_title)
    TitileView myLowerOrdersTitle;
    @BindView(R.id.check_my_orders_from_date)
    TextView checkMyOrdersFromDate;
    @BindView(R.id.check_my_orders_to_date)
    TextView checkMyOrdersToDate;
    @BindView(R.id.check_my_orders_spinner)
    AppCompatSpinner checkMyOrdersSpinner;
    @BindView(R.id.check_my_orders_btn_check)
    Button checkMyOrdersBtnCheck;
    @BindView(R.id.check_my_orders_listview)
    ListView checkMyOrdersListview;
    @BindView(R.id.check_my_orders_listview_tvmsg)
    TextView checkMyOrdersListviewTvmsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_orders);
        ButterKnife.bind(this);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }

    private Map<String, String> map;

    private void initEvent() {
        myLowerOrdersTitle.setTitle("我的订单查询");
        checkMyOrdersSpinner.setOnItemSelectedListener(listener);
        gooodsName = new ArrayList<>();
        goodIdList = new ArrayList<>();
        checkMyOrdersFromDate.setText(CommonUtils.getDate("yyyy-MM-dd"));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,3);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String date = f.format(calendar.getTime());
        checkMyOrdersToDate.setText(date);
        map = new HashMap<>();
        map.put("curuserid", user.getPhone());
        getGoods(map);
    }

    private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            int position = checkMyOrdersSpinner.getSelectedItemPosition();
            if (goodIdList.size() != 0) {
                goodId = goodIdList.get(position);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private String goodId;
    private List<String> gooodsName;
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
                    List<Goods> data = body.getData();
                    if ("1".equals(body.getRes())) {
                        gooodsName.clear();
                        gooodsName.add(0, "全部");
                        goodIdList.add(0, "all");
                        for (int i = 0; i < data.size(); i++) {
                            gooodsName.add((i + 1) + ". " + data.get(i).getGoodsname());
                            goodIdList.add(data.get(i).getGoodsid());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ActivityCheckMyOrders.this,
                                R.layout.adapter_mspinner_bar, gooodsName);
                        adapter.setDropDownViewResource(R.layout.adapter_mspinner);
                        checkMyOrdersSpinner.setAdapter(adapter);
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

    @OnClick(R.id.check_my_orders_btn_check)
    public void onClick() {
        //11_1我的订单_(下级订单查询 )
        if ("选择日期".equals(checkMyOrdersFromDate.getText().toString().trim())) {
            showToast("请选择日期");
            checkMyOrdersFromDate.requestFocus();
        } else if ("选择日期".equals(checkMyOrdersToDate.getText().toString().trim())) {
            showToast("请选择日期");
            checkMyOrdersToDate.requestFocus();
        } else {
            map.clear();
            map.put("agentid", user.getAgentid());
            map.put("startdate", checkMyOrdersFromDate.getText().toString().trim());
            map.put("enddate", checkMyOrdersToDate.getText().toString().trim());
            map.put("goodsid", goodId);
            myorderquy(map);
        }
    }

    /**
     * 11_2我的订单_(我的订单查询 )
     *
     * @param map
     */
    private void myorderquy(Map<String, String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<MyorderInfo>> call = netApi.myorderquy(map);
        call.enqueue(new Callback<BaseModel<MyorderInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<MyorderInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<MyorderInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        checkMyOrdersListviewTvmsg.setVisibility(View.GONE);
                        checkMyOrdersListview.setVisibility(View.VISIBLE);
                        AdapterCheckMyOrders adapterCheckMyOrders = new AdapterCheckMyOrders(ActivityCheckMyOrders.this,
                                R.layout.adapter_check_my_orders, body.getData());
                        View inflate = LayoutInflater.from(ActivityCheckMyOrders.this).inflate(R.layout.adapter_check_my_orders_foot, null, false);
                        TextView counts= (TextView) inflate.findViewById(R.id.adapter_check_my_orders_foot_counts);
                        TextView money= (TextView) inflate.findViewById(R.id.adapter_check_my_orders_foot_money);
                        int nums = 0;
                        float LMoney=0;
                        for (MyorderInfo info:body.getData()){
                            nums+=Integer.parseInt(info.getNum());
                            LMoney+=Float.parseFloat(info.getMoney());
                        }
                        counts.setText(nums+"");
                        money.setText(LMoney+"");
                        if (checkMyOrdersListview.getFooterViewsCount()==0){
                            checkMyOrdersListview.addFooterView(inflate);
                        }
                        checkMyOrdersListview.setAdapter(adapterCheckMyOrders);
                    } else {
                        checkMyOrdersListviewTvmsg.setVisibility(View.VISIBLE);
                        checkMyOrdersListview.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                checkMyOrdersListviewTvmsg.setVisibility(View.VISIBLE);
                checkMyOrdersListview.setVisibility(View.GONE);
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }

    @OnClick({R.id.check_my_orders_from_date, R.id.check_my_orders_to_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_my_orders_from_date:
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
                        checkMyOrdersFromDate.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2016,0,1);
                datePickerDialog.show();
                break;
            case R.id.check_my_orders_to_date:
                DatePickerDialog to=new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
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
                        checkMyOrdersToDate.setText(year+"-"+monthstr+"-"+daystr);
                    }
                },2016,0,1);
                to.show();
                break;
        }
    }
}
