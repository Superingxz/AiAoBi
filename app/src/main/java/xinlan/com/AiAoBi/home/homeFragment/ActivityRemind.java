package xinlan.com.AiAoBi.home.homeFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
import xinlan.com.AiAoBi.adapter.AdapterAgentmsg;
import xinlan.com.AiAoBi.adapter.MyRemindAdapter;
import xinlan.com.AiAoBi.entity.AgentmsgInfo;
import xinlan.com.AiAoBi.entity.Msgacceptsend;
import xinlan.com.AiAoBi.entity.Msgnostock;
import xinlan.com.AiAoBi.entity.Msgordergoods;
import xinlan.com.AiAoBi.entity.Msgsalevisit;
import xinlan.com.AiAoBi.entity.Msgtakegoods;
import xinlan.com.AiAoBi.entity.Msgupgrade;
import xinlan.com.AiAoBi.entity.Msgupsendgoods;
import xinlan.com.AiAoBi.entity.RemindModel;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ActivityRemind extends BaseActivty {
    @BindView(R.id.activity_remind_title)
    TitileView titileView;
    @BindView(R.id.remind_notread)
    TextView notread;
    @BindView(R.id.remind_hasread)
    TextView hasread;
    @BindView(R.id.remind_lv)
    ListView listView;
    @BindView(R.id.remind_lv2)
    ListView listView2;
    @BindView(R.id.remind_linear)
    LinearLayout remindLinear;
    @BindView(R.id.remind_tvmsg)
    LinearLayout remindTvmsg;
    private List<AgentmsgInfo> agentweekmsgData = new ArrayList<AgentmsgInfo>();//最近一周已读数据
    List<RemindModel> remindModels2;//最近一周已读封装数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        ButterKnife.bind(this);
        initView();
    }

    List<RemindModel> list = new ArrayList<>();

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
        // agentmsg();//获取未读提醒
        initView();
        //9_5我的提醒_(提醒客户订货)
        msgordergoods();
        //9_6我的提醒_(零售客户回访)
        msgsalevisit();
        //9_7我的提醒_(临近升级)
        msgupgrade();
        //9_8我的提醒_(提醒签收货品)
        msgtakegoods();
        //9_10我的提醒_(提醒上级发货)
        msgupsendgoods();
        //9_11我的提醒_(我的货品不足)
        msgnostock();
        //9_12我的提醒_(下级提醒自己发货)
        msgacceptsend();

    }


    boolean isAlreadyRead;

    void showView() {
        isAlreadyRead = true;
        remindLinear.setVisibility(View.VISIBLE);
        remindTvmsg.setVisibility(View.GONE);
    }

    private void initView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Intent intent = new Intent();
                switch (list.get(position).getType()) {
                    case "提醒客户订货":
                        intent.setClass(ActivityRemind.this, RemindOrderActivity.class);
                        intent.putExtra("msgordergoods", (Msgordergoods) list.get(position).getT());
                        startActivity(intent);
                        break;
                    case "零售客户回访":
                        intent.setClass(ActivityRemind.this, ActivityRemindReturnVist.class);
                        intent.putExtra("msgsalevisit", (Msgsalevisit) list.get(position).getT());
                        startActivity(intent);
                        break;
                    case "提醒签收货品":
                        intent.setClass(ActivityRemind.this, ActivitySignGoodsDetails.class);
                        intent.putExtra("msgtakegoods", (Msgtakegoods) list.get(position).getT());
                        intent.setFlags(0x801);
                        startActivity(intent);
                        break;
                    case "临近升级":
                        showDiyMsgDialog("临近升级差额提醒", "我马上去订货", "知道了,晚点订货", new Mcallback() {
                            @Override
                            public void doSomething(AlertDialog alertDialog) {
                                intent.setClass(ActivityRemind.this, MyGoodsActvity.class);
                                startActivity(intent);
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onClick(View v) {

                            }
                        });
                        break;
                    case "提醒上级发货":
                        intent.setClass(ActivityRemind.this, AvtivityRemindUp.class);
                        intent.putExtra("msgupsendgoods", (Msgupsendgoods) list.get(position).getT());
                        startActivity(intent);
                        break;
                    case "我的货品不足":
                        intent.setClass(ActivityRemind.this, ActivityRemindGoodLess.class);
                        intent.putExtra("msgnostock", (Msgnostock) list.get(position).getT());
                        startActivity(intent);
                        break;
                    case "下级提醒自己发货":
                        showDiyMsgDialog(list.get(position).getCentent(),1, new Mcallback() {
                            @Override
                            public void doSomething(AlertDialog alertDialog) {
                                Msgacceptsend msgacceptsend=(Msgacceptsend) list.get(position).getT();
                                agentupdateflag(msgacceptsend.getBsys_msg_id(),msgacceptsend.getPeruserflag());
                            }
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        break;
                }

            }
        });
        adapter = new MyRemindAdapter(ActivityRemind.this, R.layout.adapter_remind, list);
        listView.setAdapter(adapter);
    }

    //9_4我的提醒_(注明已阅读标识)
    void agentupdateflag(String bsys_msg_id, String peruserflag) {
        Map<String, String> map = new HashMap<>();
        map.put("bsys_msg_id", bsys_msg_id);
        map.put("peruserflag", peruserflag);
        Call<BaseModel> call = netApi.agentupdateflag(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())) {
                        //showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_11我的提醒_(我的货品不足)
    void msgnostock() {
        Call<BaseModel<Msgnostock>> call = netApi.msgnostock(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgnostock>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgnostock>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<Msgnostock> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成
                        for (Msgnostock model : body.getData()) {
                            RemindModel remindmodel = new RemindModel();
                            remindmodel.setType(model.getBuilltype());
                            remindmodel.setCentent(model.getMsg());
                            remindmodel.setT(model);
                            list.add(remindmodel);
                        }
                        showView();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_8我的提醒_(提醒签收货品)
    void msgtakegoods() {
        Call<BaseModel<Msgtakegoods>> call = netApi.msgtakegoods(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgtakegoods>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgtakegoods>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<Msgtakegoods> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成
                        for (Msgtakegoods model : body.getData()) {
                            RemindModel remindmodel = new RemindModel();
                            remindmodel.setType(model.getBuilltype());
                            remindmodel.setCentent(model.getMsg());
                            remindmodel.setT(model);
                            list.add(remindmodel);
                        }
                        showView();
                        adapter.notifyDataSetChanged();


                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_5我的提醒_(提醒客户订货)
    private void msgordergoods() {
        Call<BaseModel<Msgordergoods>> call = netApi.msgordergoods(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgordergoods>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgordergoods>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<Msgordergoods> body = response.body();
                    if ("1".equals(body.getRes())) {
                        for (Msgordergoods msgordergoods : body.getData()) {
                            RemindModel model = new RemindModel();
                            model.setCentent(msgordergoods.getMsg());
                            model.setType(msgordergoods.getBuilltype());
                            model.setT(msgordergoods);
                            list.add(model);
                        }
                        showView();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_7我的提醒_(临近升级)
    private void msgupgrade() {
        Call<BaseModel<Msgupgrade>> call = netApi.msgupgrade(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgupgrade>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgupgrade>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<Msgupgrade> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成
                        for (Msgupgrade model : body.getData()) {
                            RemindModel remindmodel = new RemindModel();
                            remindmodel.setType(model.getBuilltype());
                            remindmodel.setCentent(model.getMsg());
                            remindmodel.setT(model);
                            list.add(remindmodel);
                        }
                        showView();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_6我的提醒_(零售客户回访)
    private void msgsalevisit() {
        Call<BaseModel<Msgsalevisit>> call = netApi.msgsalevisit(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgsalevisit>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgsalevisit>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<Msgsalevisit> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成
                        for (Msgsalevisit model : body.getData()) {
                            RemindModel remindmodel = new RemindModel();
                            remindmodel.setType(model.getBuilltype());
                            remindmodel.setCentent(model.getMsg());
                            remindmodel.setT(model);
                            list.add(remindmodel);
                        }
                        showView();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_10我的提醒_(提醒上级发货)
    private void msgupsendgoods() {
        Call<BaseModel<Msgupsendgoods>> call = netApi.msgupsendgoods(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgupsendgoods>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgupsendgoods>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<Msgupsendgoods> body = response.body();
                    if ("1".equals(body.getRes())) {
                        for (Msgupsendgoods model : body.getData()) {
                            RemindModel remindmodel = new RemindModel();
                            remindmodel.setType(model.getBuilltype());
                            remindmodel.setCentent(model.getMsg());
                            remindmodel.setT(model);
                            list.add(remindmodel);
                        }
                        showView();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //9_12我的提醒_(下级提醒自己发货)
    void msgacceptsend() {
        Call<BaseModel<Msgacceptsend>> call = netApi.msgacceptsend(user.getAgentid());
        call.enqueue(new Callback<BaseModel<Msgacceptsend>>() {
            @Override
            public void onResponse(Response<BaseModel<Msgacceptsend>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseModel<Msgacceptsend> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成
                        for (Msgacceptsend model : body.getData()) {
                            RemindModel remindmodel = new RemindModel();
                            remindmodel.setType(model.getBuilltype());
                            remindmodel.setCentent(model.getMsg());
                            remindmodel.setT(model);
                            list.add(remindmodel);
                        }
                        showView();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToast("连接服务器失败");
            }
        });
    }

    //z最近一周已读
    private void agentweekmsg() {
        showProgressDialog("获取数据中...");
        Call<BaseModel<AgentmsgInfo>> call = netApi.agentweekmsg(user.getAgentid());
        call.enqueue(new Callback<BaseModel<AgentmsgInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<AgentmsgInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<AgentmsgInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        agentweekmsgData.clear();
                        agentweekmsgData.addAll(body.getData());
                        remindModels2 = new ArrayList<RemindModel>();
                        for (AgentmsgInfo agentmsgInfo : agentweekmsgData) {
                            RemindModel remindModel1 = new RemindModel();
                            remindModel1.setType(agentmsgInfo.getBuilltype());
                            remindModel1.setCentent(agentmsgInfo.getContent());
                            remindModels2.add(remindModel1);
                        }
                        MyRemindAdapter adapter = new MyRemindAdapter(ActivityRemind.this, R.layout.adapter_remind, remindModels2);
                        listView2.setAdapter(adapter);
                        remindLinear.setVisibility(View.VISIBLE);
                        remindTvmsg.setVisibility(View.GONE);
                        listView2.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    } else {
                        remindLinear.setVisibility(View.GONE);
                        remindTvmsg.setVisibility(View.VISIBLE);
                        listView2.setVisibility(View.GONE);
                        listView.setVisibility(View.GONE);
                       // showToast(body.getMsg());
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

    private void initEvent() {
        titileView.setTitle("我的提醒");

    }

    private MyRemindAdapter adapter;

    @OnClick({R.id.remind_notread, R.id.remind_hasread})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.remind_notread://未读提醒
                if (isAlreadyRead) {
                    showView();
                }
                listView.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.GONE);
                break;
            case R.id.remind_hasread:
                agentweekmsg();
                break;
        }
    }
}
