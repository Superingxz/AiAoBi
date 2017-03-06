package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.UserInfo;
import xinlan.com.AiAoBi.adapter.DoselagentaInfoAdapter;
import xinlan.com.AiAoBi.entity.DoselagentaInfo;
import xinlan.com.AiAoBi.entity.DoselagentbInfo;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.home.homeFragment.ScanDeliverGoods;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/11.
 * 待处理的代发订单详情
 */
public class IndentWaitInfoActivity extends MvpActivity<IndentWaitInfoView, IndentWaitInfoPresenter> implements IndentWaitInfoView {
    @BindView(R.id.indent_daifa_titleview)
    TitileView titleview;
    @BindView(R.id.indent_daifa_receive_man)
    TextView receiveMan;
    @BindView(R.id.indent_daifa_receive_number)
    TextView receiveNumber;
    @BindView(R.id.indent_daifa_receive_adress)
    TextView receiveAdress;
    @BindView(R.id.indent_info_lv)
    ListView indentInfoLv;
    @BindView(R.id.indent_daifa_tv_msg)
    LinearLayout tvMsg;
    @BindView(R.id.down_indent_daifa_scan_btn)
    Button scanBtn;
    @BindView(R.id.down_indent_daifa_down_tv)
    TextView text;
    @BindView(R.id.down_indent_daifa_me_editext)
    EditText meEditext;
    @BindView(R.id.down_indent_daifa_agree_btn)
    Button agreeBtn;
    @BindView(R.id.down_indent_daifa_back_btn)
    Button backBtn;
    private static final String TAG = "IndentWaitInfoActivity";
    @BindView(R.id.indent_daifa_profit)
    TextView indentDaifaProfit;
    @BindView(R.id.indent_daifa_allnum)
    TextView indentDaifaAllnum;
    @BindView(R.id.indent_daifa_allmon)
    TextView indentDaifaAllmon;
    private Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(IndentWaitInfoActivity.this.isFinishing()&&IndentWaitInfoActivity.this.isDestroyed())return;
            if (msg.what == 0x118) {
                //刷新下级代发订单详情列表
                presenter.getDoselagentbInfo(doselagentaInfo.getSkeyid(), App.getApp().getUserInfo().getAgentid());
            }
        }
    };

    @NonNull
    @Override
    public IndentWaitInfoPresenter createPresenter() {
        return new IndentWaitInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_daifa);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        titleview.setTitle("处理下级代发订单");
        initEvent();
        presenter.getDoselagentbInfo(doselagentaInfo.getSkeyid(), App.getApp().getUserInfo().getAgentid());
    }

    private UserInfo userInfo;
    private DoselagentaInfo doselagentaInfo;
    private String myReason;//我的代发原因
    String profit;

    private void initEvent() {
        TcheckList = new ArrayList<>();
        userInfo = App.getApp().getUserInfo();
        doselagentaInfo = (DoselagentaInfo) getIntent().getExtras().getSerializable("doselagentaInfo");
        indentDaifaProfit.setText(profit);//订单利润
        if (doselagentaInfo != null) {
            text.setText(doselagentaInfo.getReason());
            receiveMan.setText(doselagentaInfo.getInman() + "/" + doselagentaInfo.getIntel());
            receiveNumber.setText(doselagentaInfo.getRef_no());
            receiveAdress.setText(doselagentaInfo.getInaddr());
            indentDaifaProfit.setText( "我的利润："+doselagentaInfo.getProfit());

        }
        meEditext.addTextChangedListener(new MTextWatch() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                myReason = meEditext.getText().toString();

            }
        });
    }

    @OnClick({R.id.down_indent_daifa_scan_btn, R.id.down_indent_daifa_agree_btn, R.id.down_indent_daifa_back_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.down_indent_daifa_scan_btn://现在扫码发货
                Intent intent=new Intent(IndentWaitInfoActivity.this, ScanDeliverGoods.class);
                intent.setFlags(0x007);
                intent.putExtra("doselagentaInfo", doselagentaInfo);
                startActivity(intent);
                break;
            case R.id.down_indent_daifa_agree_btn://所选产品同意代发
                /*2 当前代理商：agentid
                3 原订单号：src_no，【从3_2_1处理下级订单(获取直属下级的订单)或3_2_3处理下级订单(获取直属订单下的代理订单)】接口中返回参数中获取
                4 原订单id：src_id，【同原订单号取值说明】
                5 提报订单号：ref_no，【同原订单号取值说明】
                6 提报订单id：ref_id，【同原订单号取值说明】
                7 订单类型：ordertype，【订单类型，apply申请，deal处理】，【同原订单号取值说明】
                8 代发原因：reason
                9 货品id：goodsid，【从3_2_2处理下级订单(通过订单获取订单明细)或
                3_2_4处理下级订单(通过代理订单获取订单明细)中获取】
                10 货品名称：goodsname，【同货品id取值说明】
                11 货品规格：goodssize，【同货品id取值说明】
                12 货品价格：price，【同货品id取值说明，取costprice字段值】
                13 货品单位：unitname，【同货品id取值说明】
                14 总订数：ordernum，【同货品id取值说明，取totalnum字段值】
                15 提报明细id：ref_detid，【同货品id取值说明】
                16 原订单明细id：src_detid，【同货品id取值说明】*/
                if (TcheckList.size()==0) {
                    showToast("请选择代发产品",Toast.LENGTH_SHORT);
                    return;
                }
                StringBuilder goodsids = new StringBuilder();
                StringBuilder goodsname = new StringBuilder();
                StringBuilder goodssize = new StringBuilder();
                StringBuilder price = new StringBuilder();
                StringBuilder unitname = new StringBuilder();
                StringBuilder ordernum = new StringBuilder();
                StringBuilder ref_detid = new StringBuilder();
                StringBuilder src_detid = new StringBuilder();
                for (DoselagentbInfo info :
                        TcheckList) {
                    goodsids.append(info.getGoodsid()).append(";");
                    goodsname.append(info.getGoodsname()).append(";");
                    goodssize.append(info.getGoodssize()).append(";");
                    price.append(info.getCostprice()).append(";");
                    unitname.append(info.getUnitname()).append(";");
                    ordernum.append(info.getOldnum()).append(";");
                    ref_detid.append(info.getRef_detid()).append(";");
                    src_detid.append(info.getSrc_detid()).append(";");
                }
                goodsids.deleteCharAt(goodsids.length() - 1);
                goodsname.deleteCharAt(goodsname.length() - 1);
                goodssize.deleteCharAt(goodssize.length() - 1);
                price.deleteCharAt(price.length() - 1);
                unitname.deleteCharAt(unitname.length() - 1);
                ordernum.deleteCharAt(ordernum.length() - 1);
                ref_detid.deleteCharAt(ref_detid.length() - 1);
                src_detid.deleteCharAt(src_detid.length() - 1);

                Map<String, String> map = new HashMap<>();
                map.put("agentid", userInfo.getAgentid());
                map.put("src_no", doselagentaInfo.getSrc_no());
                map.put("src_id", doselagentaInfo.getSrc_id());
                map.put("ref_no", doselagentaInfo.getRef_no());
                map.put("ref_id", doselagentaInfo.getRef_id());
                map.put("ordertype", doselagentaInfo.getOrdertype());
                map.put("reason", doselagentaInfo.getReason()+myReason);
                map.put("goodsid", goodsids.toString());
                map.put("goodsname", goodsname.toString());
                map.put("goodssize", goodssize.toString());
                map.put("price", price.toString());
                map.put("unitname", unitname.toString());
                map.put("ordernum", ordernum.toString());
                map.put("ref_detid", ref_detid.toString());
                map.put("src_detid", src_detid.toString());
                if (!"".equals(myReason) && myReason != null) {
                    App.getApp().setHandler(handler);
                    presenter.checkGoodsToCommit(map);
                    map.clear();
                } else showToast("代发原因不能为空", Toast.LENGTH_SHORT);
                break;
            case R.id.down_indent_daifa_back_btn:
                break;
        }
    }

    List<DoselagentbInfo> TcheckList;

    @Override
    public void setDataToActivity(final List<DoselagentbInfo> list) {
        int sum=0;
        float sumprice=0;
        for (DoselagentbInfo info:
            list ) {
            sum+=Integer.parseInt(info.getTotalnum());
            sumprice+=Float.parseFloat(info.getCostprice())*Integer.parseInt(info.getTotalnum());
        }
        indentDaifaAllmon.setText("总金额："+sumprice);
        indentDaifaAllnum.setText("总订货数："+sum);
        final DoselagentaInfoAdapter adapter = new DoselagentaInfoAdapter(this, R.layout.adapter_underclerk_down_wait_details, list);
        indentInfoLv.setAdapter(adapter);
        indentInfoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                    TcheckList.remove(list.get(i));
                } else {
                    list.get(i).setCheck(true);
                    TcheckList.add(list.get(i));
                }
                Log.i(TAG, "onItemClick: " + TcheckList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showToast(String msg, int dur) {
        Toast.makeText(this, msg, dur).show();
    }

    private ProgressDialog progressDialog;

    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = ProgressDialog.show(this, null, msg);
    }

    @Override
    public void showMsgDialog(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
    public void dismissProgressDiolog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setVisbely(int lvVisbely, int tVvisbely) {
        indentInfoLv.setVisibility(lvVisbely);
        tvMsg.setVisibility(tVvisbely);
    }
}
