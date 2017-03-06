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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.IndentInfoAdapter;
import xinlan.com.AiAoBi.adapter.IndentInfoWaitAdapter;
import xinlan.com.AiAoBi.entity.DoselagentaInfo;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.entity.DoselbargaindetInfo;
import xinlan.com.AiAoBi.home.homeFragment.ScanDeliverGoods;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/4.
 * 我待处理的直属下级订单详情
 */
public class IndentInfoActivity extends MvpActivity<IndentInfoView, IndentInfoPresenter> implements IndentInfoView {
    @BindView(R.id.indent_info_titleview)
    TitileView titleview;
    @BindView(R.id.indent_info_receive_man)
    TextView receiveMan;
    @BindView(R.id.indent_info_receive_number)
    TextView receiveNumber;
    @BindView(R.id.indent_info_receive_adress)
    TextView receiveAdress;
    @BindView(R.id.indent_info_lv)
    ListView indentInfoLv;
    @BindView(R.id.indent_info_tv_msg)
    LinearLayout tvMsg;
    @BindView(R.id.indent_info_receive_all_number)
    TextView receiveAllNumber;
    @BindView(R.id.indent_info_receive_all_money)
    TextView receiveAllMoney;
    @BindView(R.id.indent_info_receive_my_profit)
    TextView receiveMyProfit;
    @BindView(R.id.indent_info_scan_btn)
    Button scanBtn;
    @BindView(R.id.indent_info_back_btn)
    Button backBtn;
    @BindView(R.id.indent_info_drop_shipping_btn)
    Button dropShippingBtn;
    @BindView(R.id.indent_info_wait_lv)
    ListView waitLv;
    @BindView(R.id.indent_info_wait_tv_msg)
    LinearLayout wtvMsg;
    private DoselbargainInfo doselbargainInfo;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(IndentInfoActivity.this.isFinishing()&&IndentInfoActivity.this.isDestroyed())return;
            if (msg.what == 0x116) {
                //刷新下级订单详情列表
                presenter.doselbargaindet(doselbargainInfo.getSkeyid(),
                        doselbargainInfo.getOrdertype(), App.getApp().getUserInfo().getAgentid());
                presenter.doselagenta(doselbargainInfo.getSkeyid());
            }
           /* if (msg.what == 0x118) {
                //刷新下级代发订单列表
            }*/
        }
    };

    @NonNull
    @Override
    public IndentInfoPresenter createPresenter() {
        return new IndentInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_info);
        ButterKnife.bind(this);
        titleview.setTitle("下级订单详情");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
        App.getApp().setHandler(handler);
        App.getApp().setHandler3(handler);
        App.getApp().setHandler7(handler);
    }

    private void initEvent() {
        list = new ArrayList<>();
        waitList = new ArrayList<>();
        checkList = new ArrayList<>();
        doselbargainInfo = (DoselbargainInfo) getIntent().getExtras().getSerializable("doselbargainInfo");
        if (doselbargainInfo != null) {
            receiveMan.setText(doselbargainInfo.getInman());
            receiveNumber.setText(doselbargainInfo.getMasterid());
            receiveAdress.setText(doselbargainInfo.getInaddr());
            showProgressDialog("获取数据中...");
            presenter.doselbargaindet(doselbargainInfo.getSkeyid(), doselbargainInfo.getOrdertype(), App.getApp().getUserInfo().getAgentid());
            presenter.doselagenta(doselbargainInfo.getSkeyid());
        }
        waitLv.setOnItemClickListener(clickListener);
    }

    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(IndentInfoActivity.this, IndentWaitInfoActivity.class);
            intent.putExtra("doselagentaInfo", waitList.get(i));
            intent.putExtra("profit", doselbargainInfo.getProfit());
            IndentInfoActivity.this.startActivity(intent);
        }
    };

    @OnClick({R.id.indent_info_scan_btn, R.id.indent_info_back_btn, R.id.indent_info_drop_shipping_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.indent_info_scan_btn:
                Intent intent = new Intent(IndentInfoActivity.this, ScanDeliverGoods.class);
                intent.putExtra("doselbargainInfo", doselbargainInfo);
                intent.setFlags(0x008);
                IndentInfoActivity.this.startActivity(intent);
                break;
            case R.id.indent_info_back_btn:
                break;
            case R.id.indent_info_drop_shipping_btn:
                Intent check = new Intent(IndentInfoActivity.this, CheckGoodsToCommit.class);

                if (checkList.size() == 0) {
                    showToast("请先选择产品", Toast.LENGTH_SHORT);
                } else {
                    check.putExtra("checkList", (Serializable) checkList);
                    check.putExtra("src_id", doselbargainInfo.getSrc_id());
                    check.putExtra("src_no", doselbargainInfo.getSrc_no());
                    check.putExtra("ref_id", doselbargainInfo.getRef_id());
                    check.putExtra("ref_no", doselbargainInfo.getRef_no());
                    check.putExtra("ordertype", doselbargainInfo.getOrdertype());
                    IndentInfoActivity.this.startActivity(check);
                    Log.i("Log", "选中产品：" + checkList);
                }
                break;
        }
    }

    @Override
    public void showToast(String msg, int dur) {
        Toast.makeText(this, msg, dur).show();
    }

    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = ProgressDialog.show(this, null, msg);
    }

    @Override
    public void showMsgDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("提示信息!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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

    @Override
    public void setWaitVisbely(int lvVisbely, int tVvisbely) {
        waitLv.setVisibility(lvVisbely);
        wtvMsg.setVisibility(tVvisbely);
    }

    /**
     * 计算下级总订货
     */
    private int getSum() {
        int sum = 0;
        for (DoselbargaindetInfo info :
                list) {
            sum += Integer.valueOf(info.getSumnum());
        }
        return sum;
    }


    private List<DoselbargaindetInfo> list;//下级订单详情
    private List<DoselagentaInfo> waitList;//下级代理订单
    List<DoselbargaindetInfo> checkList;

    //获取到数据后出事化Activity界面数据
    @Override
    public void setDataToActivity(final List<DoselbargaindetInfo> list) {
        this.list.addAll(list);
        checkList = new ArrayList<>();
        receiveAllNumber.setText("总数量：" + getSum());
        receiveAllMoney.setText("下级订货总金额：" + doselbargainInfo.getSalemoney() + "元");
        receiveMyProfit.setText("我的利润：" + doselbargainInfo.getProfit() + "元");
        final IndentInfoAdapter adapter = new IndentInfoAdapter(this, R.layout.adapter_underclerk_down, list);
        indentInfoLv.setAdapter(adapter);
        indentInfoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ("0".equals(list.get(i).getZsnum())){
                    showMDialog("你选选择的产品由于没有自收数量，不能进行代发，如果需要代发下级的订单，请点击窗口下方的代发订单列表进入订单详情查看代发原因后再进行代发。");
                    return;
                }
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                    checkList.remove(list.get(i));
                } else {
                    list.get(i).setCheck(true);
                    checkList.add(list.get(i));
                }
                Log.i(TAG, "onItemClick: " + checkList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showMDialog(String s) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_diy_nocancle);
        Button btnyes= (Button) window.findViewById(R.id.dialog_diy_btnyes);
        TextView textView= (TextView) window.findViewById(R.id.dialog_diy_text);
        textView.setText(s);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private static final String TAG = "IndentInfoActivity";

    @Override
    public void setDataToWaitAdapter(List<DoselagentaInfo> list) {
        waitList.addAll(list);
        IndentInfoWaitAdapter adapter = new IndentInfoWaitAdapter(this, R.layout.adapter_underclerk_down_wait, list);
        waitLv.setAdapter(adapter);
    }
}
