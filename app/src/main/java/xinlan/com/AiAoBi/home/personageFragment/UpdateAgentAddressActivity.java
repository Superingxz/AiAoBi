package xinlan.com.AiAoBi.home.personageFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.AgentAddress;
import xinlan.com.AiAoBi.entity.GetCityInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 增加或修改地址
 * Created by Administrator on 2016/11/3.
 */
public class UpdateAgentAddressActivity extends BaseActivty implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.title_view)
    TitileView title;
    @BindView(R.id.spinner_area)
    Spinner spinner_area;
    @BindView(R.id.spinner_city)
    Spinner spinner_city;
    @BindView(R.id.spinner_province)
    Spinner spinner_province;
    @BindView(R.id.street)
    EditText street;
    AgentAddress agentAddress;
    @BindView(R.id.update_address_bt)
    Button update_address_bt;
    @BindView(R.id.name)
    EditText et_name;
    @BindView(R.id.phone)
    EditText et_phone;
    @BindView(R.id.isdefault_cb)
    CheckBox isdefault_cb;
    List<GetCityInfo.data> provinceId = new ArrayList<GetCityInfo.data>();
    List<GetCityInfo.data> cityId = new ArrayList<GetCityInfo.data>();
    List<GetCityInfo.data> areaId = new ArrayList<GetCityInfo.data>();
    boolean isFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_agent_address);
        ButterKnife.bind(this);
        title.setTitle("添加收货地址");
        agentAddress = (AgentAddress) getIntent().getSerializableExtra("agentAddress");
        initView();
    }

    private void initView() {
        titileView = (TitileView) findViewById(R.id.title_view);
        if (agentAddress == null) {
            if (null != getIntent()) {
                if (!TextUtils.isEmpty(getIntent().getStringExtra("default"))) {
                    et_name.setText(App.getApp().getUserInfo().getAgentname());
                    et_phone.setText(App.getApp().getUserInfo().getPhone());
                }
            }
            titileView.setTitle("新增地址");
            update_address_bt.setText("新增");
        } else {
            if (agentAddress.getIsdefault().equals("1")) {
                isdefault_cb.setChecked(true);
            }
            province = agentAddress.getProvince();
            city = agentAddress.getCity();
            area = agentAddress.getArea();
            et_name.setText(agentAddress.getInname());
            et_phone.setText(agentAddress.getPhone());
            street.setText(agentAddress.getAddress());
            titileView.setTitle("修改地址");
            update_address_bt.setText("修改");
        }
//        isdefault_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                isCheck=isChecked;
//            }
//        });
    }

    boolean isCheck;

    void updateAdress(String agent_addr_id) {
        if (TextUtils.isEmpty(street.getText().toString())) {
            showToast("请输入街道");
            return;
        }
        if (TextUtils.isEmpty(et_name.getText().toString())) {
            showToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            showToast("请输入手机号");
            return;
        }
        showProgressDialog("修改地址中...");
        HashMap<String, String> map = new HashMap<>();
        map.put("province", province);
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("agent_addr_id", agent_addr_id);
        map.put("city", city);
        map.put("area", area);
        map.put("address", street.getText().toString());
        map.put("inname", et_name.getText().toString());
        map.put("phone", et_phone.getText().toString());
        if (isdefault_cb.isChecked()) {
            map.put("isdefault", "1");
        } else {
            map.put("isdefault", "0");
        }
        Log.v("aupdateAddress", map.toString());
        Call<BaseModel> call = netApi.agentupDateAddr(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (response.body().getRes().equals("1")) {
                        showToast("修改收货地址成功");
                        setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        showToast("修改收货地址失败");
                    }
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    public void addAdress() {
        if (TextUtils.isEmpty(street.getText().toString())) {
            showToast("请输入街道");
            return;
        }
        if (TextUtils.isEmpty(et_name.getText().toString())) {
            showToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            showToast("请输入手机号");
            return;
        }
        showProgressDialog("新增地址中...");
        HashMap<String, String> map = new HashMap<>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("province", province);
        map.put("city", city);
        map.put("area", area);
        map.put("address", street.getText().toString());
        map.put("inname", et_name.getText().toString());
        map.put("phone", et_phone.getText().toString());
        if (isdefault_cb.isChecked()) {
            map.put("isdefault", "1");
        } else {
            map.put("isdefault", "0");
        }
        Log.v("addAddress", map.toString());

        Call<BaseModel> call = netApi.agentAddaddr(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (response.body().getRes().equals("1")) {
                        showToast("新增收货地址成功");
                        setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        showToast("新增收货地址失败");
                    }
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
                dimissProgressDialog();
            }
        });
    }

    @OnClick({R.id.update_address_bt})
    void OnClick(View view) {
        if (view.getId() == R.id.update_address_bt) {
            if (agentAddress == null) {
                addAdress();
            } else {
                updateAdress(agentAddress.getAgent_addr_id());
            }

        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getProvince();
    }

    /**
     * 获取省份
     */
    public void getProvince() {
        HashMap<String, String> map = new HashMap<>();
        map.put("curuserid", App.getApp().getUserInfo().getPhone());
        map.put("citylevel", "2");
        Call<GetCityInfo> call = netApi.getCity(map);
        call.enqueue(new Callback<GetCityInfo>() {
            @Override
            public void onResponse(Response<GetCityInfo> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    GetCityInfo body = response.body();
                    if ("1".equals(body.getRes())) {
                        Log.i("Log", "body.getRes():" + body.getRes());
                        if ("1".equals(body.getRes())) {
                            provinceId.clear();
                            if (agentAddress == null || isProvinceCheck) {
                                //显示定位省份
                                if(!isProvinceCheck&&!TextUtils.isEmpty(App.getApp().getLoginProvice())){
                                    int j = 0;
                                    for (int i = 0; i < body.getData().size(); i++) {
                                        if (body.getData().get(i).getCity_name().equals(App.getApp().getLoginProvice())) {
                                            j = i;
                                            break;
                                        }
                                    }
                                    provinceId.add(body.getData().get(j));
                                    body.getData().remove(j);
                                    getCity(App.getApp().getLoginProvice());
                                }else{
                                    getCity(body.getData().get(0).getCity_name());
                                }
                                provinceId.addAll(body.getData());
                            } else {
                                int j = 0;
                                for (int i = 0; i < body.getData().size(); i++) {
                                    if (body.getData().get(i).getCity_name().equals(province)) {
                                        j = i;
                                        break;
                                    }
                                }
                                provinceId.add(body.getData().get(j));
                                body.getData().remove(j);
                                provinceId.addAll(body.getData());
                                getCity(provinceId.get(0).getCity_name());
                            }
                            province = provinceId.get(0).getCity_name();
                            List<String> spRankData = new ArrayList<>();
                            for (GetCityInfo.data data : provinceId) {
                                spRankData.add(data.getCity_name());
                            }

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateAgentAddressActivity.this,
                                    R.layout.adapter_mspinner_geography_bar, spRankData);
                            arrayAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
                            spinner_province.setAdapter(arrayAdapter);
                            Log.i("Log", "getdritct获取区数据成功:" + body.getData());
                            Log.i("Log", "getCity获取城市数据成功:" + body.getData());
                        }
                    }
                } else showToast(response.body().getMsg());
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }


    /**
     * 获取城市
     */
    public void getCity(String cityName) {
        HashMap<String, String> map = new HashMap<>();
        map.put("curuserid", App.getApp().getUserInfo().getPhone());
        map.put("citylevel", "3");
        map.put("city_name", cityName);
        Call<GetCityInfo> call = netApi.getCity(map);
        call.enqueue(new Callback<GetCityInfo>() {
            @Override
            public void onResponse(Response<GetCityInfo> response, Retrofit retrofit) {
                Log.i("Log", "response.isSuccess():" + response.isSuccess());
                if (response.isSuccess()) {
                    GetCityInfo body = response.body();
                    Log.i("Log", "body.getRes():" + body.getRes());
                    if ("1".equals(body.getRes())) {
                        cityId.clear();
                        if (agentAddress == null || isProvinceCheck) {
                            if(!isProvinceCheck&&!TextUtils.isEmpty(App.getApp().getLoginCity())){
                                int j = 0;
                                for (int i = 0; i < body.getData().size(); i++) {
                                    if (body.getData().get(i).getCity_name().equals(App.getApp().getLoginCity())) {
                                        j = i;
                                        break;
                                    }
                                }
                                cityId.add(body.getData().get(j));
                                body.getData().remove(j);
                                getDisrict(App.getApp().getLoginCity());
                            }else{
                                getDisrict(body.getData().get(0).getCity_name());
                            }
                            cityId.addAll(body.getData());
                        } else {
                            int j = 0;
                            for (int i = 0; i < body.getData().size(); i++) {
                                if (body.getData().get(i).getCity_name().equals(city)) {
                                    j = i;
                                    break;
                                }
                            }
                            cityId.add(body.getData().get(j));
                            body.getData().remove(j);
                            getDisrict(cityId.get(0).getCity_name());
                            cityId.addAll(body.getData());
                        }
                        city = cityId.get(0).getCity_name();
                        List<String> spRankData = new ArrayList<>();
                        for (GetCityInfo.data data : cityId) {
                            spRankData.add(data.getCity_name());

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateAgentAddressActivity.this,
                                R.layout.adapter_mspinner_geography_bar, spRankData);
                        arrayAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
                        spinner_city.setAdapter(arrayAdapter);
                        Log.i("Log", "getdritct获取区数据成功:" + body.getData());
                        Log.i("Log", "getCity获取城市数据成功:" + body.getData());
                    }
                } else showToast(response.body().getMsg());
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    /**
     * 获取区
     */
    public void getDisrict(String cityName) {
        HashMap<String, String> map = new HashMap<>();
        map.put("curuserid", App.getApp().getUserInfo().getPhone());
        map.put("citylevel", "4");
        map.put("city_name", cityName);
        Call<GetCityInfo> call = netApi.getCity(map);
        call.enqueue(new Callback<GetCityInfo>() {
            @Override
            public void onResponse(Response<GetCityInfo> response, Retrofit retrofit) {
                Log.i("Log", "isSuccess():" + response.isSuccess());
                if (response.isSuccess()) {
                    GetCityInfo body = response.body();
                    Log.i("Log", "body.getRes()():" + body.getRes());
                    if ("1".equals(body.getRes())) {
                        List<String> spRankData = new ArrayList<>();
                        areaId.clear();
                        if (agentAddress == null || isCityCheck) {
                            areaId.addAll(body.getData());
                        } else {
                            int j = 0;
                            for (int i = 0; i < body.getData().size(); i++) {
                                if (body.getData().get(i).getCity_name().equals(area)) {
                                    j = i;
                                    break;
                                }
                            }
                            areaId.add(body.getData().get(j));
                            body.getData().remove(j);
                            areaId.addAll(body.getData());
                        }
                        area = areaId.get(0).getCity_name();
                        for (GetCityInfo.data data : areaId) {
                            spRankData.add(data.getCity_name());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateAgentAddressActivity.this,
                                R.layout.adapter_mspinner_geography_bar, spRankData);
                        arrayAdapter.setDropDownViewResource(R.layout.adapter_mspinner_geography);
                        spinner_area.setAdapter(arrayAdapter);
                        Log.i("Log", "getdritct获取区数据成功:" + body.getData());
                    }
                } else showToast(response.body().getMsg());
                Message msg = new Message();
                handler.sendMessageDelayed(msg, 1000);

            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(UpdateAgentAddressActivity.this.isFinishing()&&UpdateAgentAddressActivity.this.isDestroyed())return;
            }
            if (!isLoad) {
                spinner_area.setOnItemSelectedListener(UpdateAgentAddressActivity.this);
                spinner_province.setOnItemSelectedListener(UpdateAgentAddressActivity.this);
                spinner_city.setOnItemSelectedListener(UpdateAgentAddressActivity.this);
                isLoad = true;
            }

        }
    };
    String province, city, area;
    boolean isProvinceCheck, isCityCheck, isLoad;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_province:
                province = provinceId.get(position).getCity_name();
                Log.v("province", province);
                isProvinceCheck = true;
                getCity(provinceId.get(position).getCity_name());
                break;
            case R.id.spinner_city:
                isCityCheck = true;
                city = cityId.get(position).getCity_name();
                Log.v("city", cityId.get(position).getCity_name());
                getDisrict(cityId.get(position).getCity_name());
                break;
            case R.id.spinner_area:
                area = areaId.get(position).getCity_name();
                Log.v("area", area);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
