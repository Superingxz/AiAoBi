package xinlan.com.AiAoBi.home.homeFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import xinlan.com.AiAoBi.adapter.MsgsalevisitdetAdapter;
import xinlan.com.AiAoBi.entity.Msgsalevisit;
import xinlan.com.AiAoBi.entity.Msgsalevisitdet;
import xinlan.com.AiAoBi.entity.Msgtakegoods;
import xinlan.com.AiAoBi.entity.RemindModel;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.utils.ToastUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 零售客户回访
 * Created by Administrator on 2016/12/21.
 */
public class ActivityRemindReturnVist extends BaseActivty {
    @BindView(R.id.activity_remind_return_vist_title)
    TitileView title;
    @BindView(R.id.activity_remind_return_vist_lv)
    ListView lv;
    @BindView(R.id.activity_remind_return_vist_remark)
    EditText remark;
    @BindView(R.id.activity_remind_return_vist_btn)
    Button btn;
    Msgsalevisit msgsalevisit;
    MsgsalevisitdetAdapter msgsalevisitdetAdapter;
    List<Msgsalevisitdet> msgsalevisitdetList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_return_vist);
        msgsalevisitdet();
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        title.setTitle("回访零售客户");
        Spannable sn=remark.getText();
        remark.setSelection(sn.length());
        msgsalevisitdetAdapter = new MsgsalevisitdetAdapter(ActivityRemindReturnVist.this, R.layout.item_remind_retrun_vist, msgsalevisitdetList);
        lv.setAdapter(msgsalevisitdetAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!TextUtils.isEmpty(msgsalevisitdetList.get(position).getContent_auto())) {
                    remark.setText(msgsalevisitdetList.get(position).getContent_auto());
                } else {
                    remark.setText("");
                }
                for (Msgsalevisitdet cche : msgsalevisitdetList) {
                    cche.setCheck(false);
                }
                msgsalevisitdetList.get(position).setCheck(true);
                msgsalevisitdetAdapter.notifyDataSetChanged();
                content_auto = msgsalevisitdetList.get(position).getContent_auto();
                agent_saled_id = msgsalevisitdetList.get(position).getAgent_saled_id();
                agent_salem_id = msgsalevisitdetList.get(position).getAgent_salem_id();
            }
        });
    }

    @OnClick(R.id.activity_remind_return_vist_btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_remind_return_vist_btn:
                msgsalevisitdetins();
                break;
        }

    }

    String content_auto, agent_saled_id, agent_salem_id;

    void msgsalevisitdetins() {
        if (TextUtils.isEmpty(agent_salem_id)) {
            ToastUtils.show(ActivityRemindReturnVist.this, "请选择客户");
            return;
        }
        showProgressDialog("保存中...");
        Map<String, String> map = new HashMap<>();
        map.put("agentid", user.getAgentid());
        map.put("content_auto", content_auto);
        map.put("agent_saled_id", agent_saled_id);
        map.put("agent_salem_id", agent_salem_id);
        map.put("content_update", remark.getText().toString());
        Call<BaseModel> call = netApi.msgsalevisitdetins(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                showToast(response.body().getMsg());
                if (response.body().getRes().equals("1")) {
                    for (Msgsalevisitdet msgsalevisitdet : msgsalevisitdetList) {
                        if (msgsalevisitdet.getAgent_saled_id().equals(agent_saled_id)) {
                            msgsalevisitdet.setContent_auto(remark.getText().toString());
                            break;
                        }
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

    void msgsalevisitdet() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<Msgsalevisitdet>> call = netApi.msgsalevisitdet(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgsalevisitdet>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgsalevisitdet>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<Msgsalevisitdet> body = response.body();
                    if ("1".equals(body.getRes())) {
                        msgsalevisitdetList.clear();
                        response.body().getData().get(0).setCheck(true);
                        msgsalevisitdetList.addAll(response.body().getData());
                        msgsalevisitdetAdapter.notifyDataSetChanged();
                        remark.setText(msgsalevisitdetList.get(0).getContent_auto());
                        content_auto = msgsalevisitdetList.get(0).getContent_auto();
                        agent_saled_id = msgsalevisitdetList.get(0).getAgent_saled_id();
                        agent_salem_id = msgsalevisitdetList.get(0).getAgent_salem_id();
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
}
