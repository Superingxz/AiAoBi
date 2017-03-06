package xinlan.com.AiAoBi.home.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

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
import xinlan.com.AiAoBi.entity.Msgordergoods;
import xinlan.com.AiAoBi.entity.Msgtakegoods;
import xinlan.com.AiAoBi.entity.RemindModel;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 提醒客户订货
 * Created by Administrator on 2016/12/21.
 */
public class RemindOrderActivity extends BaseActivty {

    @BindView(R.id.title_view)
    TitileView title_view;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.plan)
    TextView plan;
    @BindView(R.id.need_order)
    TextView need_order;
    @BindView(R.id.need_price)
    TextView need_price;
    @BindView(R.id.remind_data)
    EditText remind_data;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.check_box)
    CheckBox check_box;
    Msgordergoods msgordergoods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_order);
        ButterKnife.bind(this);
        init();
    }

    //9_8我的提醒_(提醒签收货品)
    void msgordergoodsdet() {
        showProgressDialog("发送中...");
        Map<String, String> map = new HashMap<>();
        map.put("agentid",user.getAgentid());
        map.put("code",msgordergoods.getCode());
        map.put("builltypecode",msgordergoods.getBuilltypecode());
        map.put("builltype",msgordergoods.getBuilltype());
        map.put("agentname",msgordergoods.getAgentname());
        map.put("phone",msgordergoods.getPhone());
        map.put("slevel",msgordergoods.getSlevel());
        map.put("pagentid",msgordergoods.getPagentid());
        map.put("pagentname",msgordergoods.getPagentname());
        map.put("pphone",msgordergoods.getPphone());
        map.put("pslevel",msgordergoods.getPslevel());
        map.put("msg",msgordergoods.getMsg());
        map.put("content_update",remind_data.getText().toString());
        Call<BaseModel> call = netApi.msgordergoodsdet(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())) {
                        showToast(body.getMsg());
                    } else {
                        showToast(body.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败");
            }
        });
    }

    void init() {
        title_view.setTitle("提醒客户订货");
        msgordergoods = (Msgordergoods) getIntent().getSerializableExtra("msgordergoods");
        name.setText(msgordergoods.getAgentname() + " " + msgordergoods.getPhone());
        level.setText(msgordergoods.getSlevelname());
        need_order.setText(msgordergoods.getMoney());
        need_price.setText(msgordergoods.getDiffmoney());
        plan.setText(msgordergoods.getMinmoney());
        remind_data.setText(msgordergoods.getContent_auto());
        check_box.setChecked(true);
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    remind_data.setText(msgordergoods.getContent_auto());
                }
            }
        });

    }

    @OnClick({R.id.submit})
    void OnClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                msgordergoodsdet();
                break;
        }
    }
}
