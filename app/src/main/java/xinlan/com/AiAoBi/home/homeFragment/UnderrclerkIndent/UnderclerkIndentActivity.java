package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

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
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.UserInfo;
import xinlan.com.AiAoBi.adapter.AlldisposedLvAdapter;
import xinlan.com.AiAoBi.adapter.DialogAtapter;
import xinlan.com.AiAoBi.entity.DoselagentkInfo;
import xinlan.com.AiAoBi.entity.DoselagentqInfo;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.home.homeFragment.AddAgainNumActivity;
import xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo.IndentInfoActivity;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/4.
 * 代理商处理下级订单
 */
public class UnderclerkIndentActivity extends MvpActivity<UnderclerkIndentView,UnderclerkIndentPresenter>
        implements UnderclerkIndentView,AdapterView.OnItemClickListener{
    @BindView(R.id.uderclerk_indent_titleview)
    TitileView titleview;
    @BindView(R.id.uderclerk_indent_will_dispose_lv)
    ListView willDisposeLv;
    @BindView(R.id.uderclerk_indent_tv_add)
    Button tvAdd;
    @BindView(R.id.uderclerk_indent_alldisposed_lv)
    ListView alldisposedLv;
    @BindView(R.id.uderclerk_indent_btn_postage)
    Button btnPostage;
    @BindView(R.id.uderclerk_indent_btn_comit)
    Button btnComit;
    @BindView(R.id.uderclerk_indent_tv_msg)
    TextView tvMsg;
    @BindView(R.id.uderclerk_indent_alldisposed_tv_msg)
    TextView tvAllMsg;
    private ProgressDialog progressDialog;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_underclerk_indent);
        titleview.setTitle("处理下级订单");
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(UnderclerkIndentActivity.this.isFinishing()&&UnderclerkIndentActivity.this.isDestroyed())return;
            }
            if (msg.what==0x113||msg.what==0x116){
                //刷新订单汇总列表
                Log.i("log","刷新订单汇总列表============");
                if(smkeyids!=null){
                    presenter.doselagentq(userInfo.getAgentid(),smkeyids.toString());
                }

            }

        }
    };

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        inintEvent();
        App.getApp().setHandler(handler);
        App.getApp().setHandler6(handler);
    }
    private UnderclerkAdapter underclerkAdapter;
    private List<DoselbargainInfo> list;
    private void inintEvent() {
        list=new ArrayList<>();
        userInfo=App.getApp().getUserInfo();
        presenter.doselbargain(userInfo.getAgentid());
        App.getApp().setHandler2(handler);
    }

    @NonNull
    @Override
    public UnderclerkIndentPresenter createPresenter() {
        return new UnderclerkIndentPresenter();
    }


    @OnClick({R.id.uderclerk_indent_tv_add, R.id.uderclerk_indent_btn_postage, R.id.uderclerk_indent_btn_comit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.uderclerk_indent_tv_add://我再加点
                Intent intent=new Intent(UnderclerkIndentActivity.this, AddAgainNumActivity.class);
                startActivity(intent);
                break;
            case R.id.uderclerk_indent_btn_postage://official_indent_post
                /*showToast("该功能正在开发中，敬请期待",Toast.LENGTH_SHORT);
                final Map<String,String> map=new HashMap<>();
                map.put("agentid",userInfo.getAgentid());
                map.put("slevel",userInfo.getSlevel());
                List<DoselagentkInfo> list=new ArrayList<>();
                list.add(new DoselagentkInfo("艾灸贴","盒","3"));
                list.add(new DoselagentkInfo("艾灸贴","盒","3"));
                list.add(new DoselagentkInfo("艾灸贴","盒","3"));
                list.add(new DoselagentkInfo("艾灸贴","盒","3"));
                list.add(new DoselagentkInfo("艾灸贴","盒","3"));
                showDiyDialog("您本次订货的产品中有不足起订数，具体如下：\n",
                        "是，直接下单", "我马上再加点",list, new UnderclerkIndentActivity.Mcallback() {

                            @Override
                            public void okDoSomething() {
                                Call<BaseModel> doselagentw =  NetCilent.getInstance().getNetApi().doselagentw(map);
                               showProgressDialog("正在提交订单");
                                doselagentw.enqueue(new Callback<BaseModel>() {
                                    @Override
                                    public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                                        if (response.isSuccess()){
                                           dismissProgressDiolog();
                                           showMsgDialog(response.body().getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                       dismissProgressDiolog();
                                        showToast(t.toString(),Toast.LENGTH_SHORT);
                                    }
                                });
                            }

                            @Override
                            public void cancleDoSomething() {
                                intentToActivity(AddAgainNumActivity.class);
                            }
                        });*/
                break;
            case R.id.uderclerk_indent_btn_comit://提交
                presenter.doselagentk(userInfo.getAgentid(),userInfo.getSlevel());
                break;
        }
    }

    /**
     * listView的监听事件
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(UnderclerkIndentActivity.this, IndentInfoActivity.class);
        intent.putExtra("doselbargainInfo",list.get(i));
        Log.i("log","onItemClick:"+list.get(i).toString());
        startActivity(intent);
    }
    @Override
    public void showToast(String msg, int dur) {
        Toast.makeText(this,msg,dur).show();
    }

    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog!=null){
            progressDialog.cancel();
        }
        progressDialog=ProgressDialog.show(this,null,msg);
    }


    @Override
    public void showMsgDialog(String msg) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("提示信息!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
    @Override
    public void setVisbely(int lvVisbely,int tvVisbely) {
        willDisposeLv.setVisibility(lvVisbely);
        tvMsg.setVisibility(tvVisbely);
    }
    @Override
    public void setAlldisposedLvVisbely(int lvVisbely,int tvVisbely) {
        alldisposedLv.setVisibility(lvVisbely);
        tvAllMsg.setVisibility(tvVisbely);
    }

    @Override
    public void setBtnComitText(String text) {
        btnComit.setText(text);
    }

    //刷新页面
    @Override
    public void notifyDataChange() {
        presenter.doselbargain(userInfo.getAgentid());
       // presenter.doselagentq(userInfo.getAgentid(),smkeyids.toString());
        //通知刷新“我的货品”模块中的订单数提示
        if (App.getApp().getHandler5()!=null)
        App.getApp().getHandler5().sendEmptyMessage(0x115);
    }
    private AlertDialog alertDialog;
    @Override
    public void showDiyDialog(String tvMsg, String sumbit, String cancle, List<DoselagentkInfo> list, final Mcallback mcallback){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_diy);
        Button btnyes= (Button) window.findViewById(R.id.dialog_diy_btnyes);
        Button btncancle= (Button) window.findViewById(R.id.dialog_diy_btncancel);
        TextView textView= (TextView) window.findViewById(R.id.dialog_diy_text);
        ListView lv= (ListView) window.findViewById(R.id.dialog_lv);
        lv.setVisibility(View.VISIBLE);
        btnyes.setText(sumbit);
        btncancle.setText(cancle);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                mcallback.okDoSomething();
            }
        });
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                mcallback.cancleDoSomething();
            }
        });
        textView.setText(tvMsg);
        DialogAtapter atapter=new DialogAtapter(this,R.layout.adapter_dialog,list);
        lv.setAdapter(atapter);
    }

    @Override
    public void intentToActivity(Class ActivityClass) {
        Intent intent=new Intent(this,ActivityClass);
        startActivity(intent);
    }

    public interface Mcallback {
        void okDoSomething();
        void cancleDoSomething();
    }

    @Override
    public void dismissProgressDiolog() {
        progressDialog.dismiss();
    }
    private StringBuffer smkeyids;
    @Override
    public void setDataToActivity(List<DoselbargainInfo> list) {
        //获取直属下级的订单
        if (list==null||list.size()==0){
            Log.i("list", "setDataToActivity: "+list);
            setAlldisposedLvVisbely(View.GONE,View.VISIBLE);
            return;
        }
        this.list.addAll(list);
        Log.i("log","321:"+list.get(0).toString());
        underclerkAdapter=new UnderclerkAdapter(this,R.layout.adapter_underclerk,list);
        willDisposeLv.setOnItemClickListener(this);
        willDisposeLv.setAdapter(underclerkAdapter);
        float xj=0;
        for (DoselbargainInfo info:list){
            if (!TextUtils.isEmpty(info.getSalemoney())){
                xj+=Float.parseFloat(info.getSalemoney());
            }
        }
        if (willDisposeLv.getFooterViewsCount()==0){
            View view = LayoutInflater.from(this).inflate(R.layout.underclerk_foot_view, null);
            TextView xjtext= (TextView) view.findViewById(R.id.undercler_foot_view_money);
            xjtext.setText(xj+"");
            willDisposeLv.addFooterView(view);
        }
        smkeyids=new StringBuffer();
        for (int i=0;i<list.size();i++){
            smkeyids.append(list.get(i).getSkeyid()).append(",");
        }
        smkeyids.deleteCharAt(smkeyids.length()-1);
        presenter.doselagentq(userInfo.getAgentid(),smkeyids.toString());
    }

    @Override
    public void setDataToAlldLv(List<DoselagentqInfo> list) {
        if (list==null||list.size()==0){
            Log.i("list", "setDataToAlldLv: "+list);
            setAlldisposedLvVisbely(View.GONE,View.VISIBLE);
            return;
        }
        int dfNums=0;
        int selfNums=0;
        for (DoselagentqInfo info:list){
            dfNums+=Integer.parseInt(info.getDfnum());
            selfNums+=Integer.parseInt(info.getSelfnum());
        }
        btnComit.setText("提交订单（代发："+dfNums+",自收："+selfNums+"）");
        AlldisposedLvAdapter adapter=new AlldisposedLvAdapter(this,R.layout.adapter_undercler_all,list);
        alldisposedLv.setAdapter(adapter);
    }

}
