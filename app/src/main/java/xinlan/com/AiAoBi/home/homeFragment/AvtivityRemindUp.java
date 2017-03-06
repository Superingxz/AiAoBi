package xinlan.com.AiAoBi.home.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import xinlan.com.AiAoBi.adapter.RemindUpAdapter;
import xinlan.com.AiAoBi.entity.Msgsalevisit;
import xinlan.com.AiAoBi.entity.Msgupsendcode;
import xinlan.com.AiAoBi.entity.Msgupsenddet;
import xinlan.com.AiAoBi.entity.Msgupsendgoods;
import xinlan.com.AiAoBi.entity.RemindModel;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 发送提醒给上级
 * Created by Administrator on 2016/12/21.
 */
public class AvtivityRemindUp extends BaseActivty {
    @BindView(R.id.activity_remind_up_title)
    TitileView activityRemindUpTitle;
    @BindView(R.id.activity_remind_up_name)
    TextView activityRemindUpName;
    @BindView(R.id.activity_remind_up_phone)
    TextView activityRemindUpPhone;
    @BindView(R.id.activity_remind_up_selvel)
    TextView activityRemindUpSelvel;
    @BindView(R.id.activity_remind_up_sp)
    Spinner activityRemindUpSp;
    @BindView(R.id.activity_remind_up_lv)
    ListView activityRemindUpLv;
    @BindView(R.id.activity_remind_up_edit)
    EditText activityRemindUpEdit;
    @BindView(R.id.activity_remind_up_cb)
    CheckBox activityRemindUpCb;
    @BindView(R.id.activity_remind_up_btn)
    Button activityRemindUpBtn;
    Msgupsendgoods msgupsendgoods;
    RemindUpAdapter remindUpAdapter;
    List<Msgupsenddet> msgupsenddetList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_up);
        init();
        msgupsendcode();
    }

    private void init() {
        activityRemindUpEdit.setSelection(activityRemindUpEdit.getText().length());
        remindUpAdapter = new RemindUpAdapter(AvtivityRemindUp.this, R.layout.item_remind_up, msgupsenddetList);
        activityRemindUpLv.setAdapter(remindUpAdapter);
        msgupsendgoods = (Msgupsendgoods) getIntent().getSerializableExtra("msgupsendgoods");
        activityRemindUpName.setText(msgupsendgoods.getDoagent_name());
        activityRemindUpPhone.setText(msgupsendgoods.getDoagent_phone());
        activityRemindUpSelvel.setText(msgupsendgoods.getDoslevelname());
        activityRemindUpCb.setChecked(true);
        activityRemindUpCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    activityRemindUpEdit.setText(msgupsendgoods.getContent_auto());
                }
            }
        });
        activityRemindUpEdit.setText(msgupsendgoods.getContent_auto());
        activityRemindUpSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < msgupsendcodes.size(); i++) {
                    if (msgupsendcodes.get(i).getMasterid().equals(string[position])) {
                        msgupsenddet(msgupsendcodes.get(i).getSrc_id());
                        break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    ArrayAdapter<String> spApplyAdapter;
    String[] string;
    List<Msgupsendcode> msgupsendcodes;

    //9_10_1我的提醒_(提醒签收货品，获取订单号)
    private void msgupsendcode() {
        showProgressDialog("获取数据中...");
        Map<String, String> map = new HashMap<>();
        map.put("agentid", user.getAgentid());
        map.put("doagent_id", msgupsendgoods.getDoagent_id());
        Call<BaseModel<Msgupsendcode>> call = netApi.msgupsendcode(map);
        call.enqueue(new Callback<BaseModel<Msgupsendcode>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgupsendcode>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<Msgupsendcode> body = response.body();
                    if ("1".equals(body.getRes())) {
                        msgupsendcodes = new ArrayList<Msgupsendcode>();
                        msgupsendcodes.addAll(body.getData());
                        string = new String[body.getData().size()];
                        for (int i = 0; i < body.getData().size(); i++) {
                            string[i] = body.getData().get(i).getMasterid();
                        }
                        spApplyAdapter = new ArrayAdapter<>(AvtivityRemindUp.this,
                                R.layout.adapter_mspinner_geography_bar, string);
                        spApplyAdapter.setDropDownViewResource( R.layout.adapter_mspinner_geography);
                        activityRemindUpSp.setAdapter(spApplyAdapter);

                    } else {
                        showToast(body.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
            }
        });
    }

    //9_10_2我的提醒_(提醒签收货品，通过订单号查询明细)
    void msgupsenddet(String skeyid) {
        showProgressDialog("获取数据中...");
        Map<String, String> map = new HashMap<>();
        map.put("agentid", user.getAgentid());
        map.put("skeyid", skeyid);
        map.put("ordertype", msgupsendgoods.getOrdertype());
        Call<BaseModel<Msgupsenddet>> call = netApi.msgupsenddet(map);
        call.enqueue(new Callback<BaseModel<Msgupsenddet>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgupsenddet>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<Msgupsenddet> body = response.body();
                    if ("1".equals(body.getRes())) {
                        msgupsenddetList.clear();
                        msgupsenddetList.addAll(body.getData());
                        remindUpAdapter.notifyDataSetChanged();
                    } else {
                        showToast(body.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
            }
        });
    }

    //9_10_3我的提醒_(提醒签收货品，发送提醒给上级)
    void msgupsendins() {
        showProgressDialog("发送中...");
        Map<String, String> map = new HashMap<>();
        map.put("agentid", user.getAgentid());
        map.put("agentname", user.getAgentname());
        map.put("doagent_id", msgupsendgoods.getDoagent_id());
        map.put("doagent_name", msgupsendgoods.getDoagent_name());
        map.put("content_auto", msgupsendgoods.getContent_auto());
        map.put("content_update", activityRemindUpEdit.getText().toString().trim());
        Call<BaseModel> call = netApi.msgupsendins(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel body = response.body();
                   showMsgDialog(body.getMsg(),0);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
            }
        });
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        activityRemindUpTitle.setTitle("提醒上级发货");
    }

    @OnClick(R.id.activity_remind_up_btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_remind_up_btn:
                msgupsendins();
                break;
        }
    }

}
