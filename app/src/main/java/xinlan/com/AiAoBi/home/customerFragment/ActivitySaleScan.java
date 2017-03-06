package xinlan.com.AiAoBi.home.customerFragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import xinlan.com.AiAoBi.App;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterSaleScan;
import xinlan.com.AiAoBi.entity.SalescanInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.scancode.activity.CaptureActivity;
import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/7.
 */
public class ActivitySaleScan extends BaseActivty {
    private static final String TAG = "ActivitySaleScan";
    @BindView(R.id.activity_sale_scan_title)
    TitileView title;
    @BindView(R.id.activity_sale_scan_phone)
    EditText phone;
    @BindView(R.id.activity_sale_scan_name)
    EditText name;
    @BindView(R.id.activity_sale_scan_btn)
    Button scanBtn;
    @BindView(R.id.activity_sale_scan_textmsg)
    EditText textmsg;
    @BindView(R.id.activity_sale_scan_lv)
    ListView scanLv;
    @BindView(R.id.activity_sale_scan_allcount)
    TextView allcount;
    @BindView(R.id.activity_sale_scan_allprice)
    TextView allprice;
    @BindView(R.id.activity_sale_scan_yes_btn)
    Button yesBtn;
    @BindView(R.id.activity_sale_scan_yes)
    Button ok;
    private String phoneString;
    private String nameString;
    private MTextWatch phoneTextWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            phoneString = phone.getText().toString().trim();
        }
    };
    private MTextWatch nameTextWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            nameString = name.getText().toString().trim();
        }
    };
    private Map<String, String> map;
    private List<SalescanInfo> list;//当前listVIew的数据源
    private List<String> nameLists;//所有的产品名字集合
    private List<String> barcodes;//所有输码成功的条码集合
    private List<SalescanInfo> allLists;//所有输码成功的数据集合
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(ActivitySaleScan.this, ActivitySaleScanLvInfo.class);

            List<String> mbarcode = new ArrayList<>();
            for (SalescanInfo info : allLists) {
                if (info.getGoodsname().equals(list.get(i).getGoodsname())) {
                    mbarcode.add(info.getSmallcode());
                }
            }
            intent.putStringArrayListExtra("barcode", (ArrayList<String>) mbarcode);
            intent.putExtra("goodName", list.get(i).getGoodsname());
            startActivity(intent);
        }
    };
    private AdapterSaleScan adapter;
    private String barCode;
    private Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(ActivitySaleScan.this.isFinishing()&&ActivitySaleScan.this.isDestroyed())return;
            if (msg.what == 0x701) {
                String[] item = (String[]) msg.obj;
                barcodes.remove(item[0]);//从代码集里移除
                for (int i = 0; i < allLists.size(); i++) {
                    if (allLists.get(i).getSmallcode().equals(item[0])) {
                        allLists.remove(i);
                    }
                }
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getGoodsname().equals(item[1])) {
                        if (list.get(j).getCount() > 0) {
                            list.get(j).setCount(list.get(j).getCount() - 1);
                        }
                        if (list.get(j).getCount() == 0) {
                            list.remove(j);
                            nameLists.remove(j);
                        }
                    }
                }
                allcount.setText("总数量：" + barcodes.size());
                int allprices = 0;
                for (SalescanInfo info : list) {
                    allprices += info.getCount() * Integer.parseInt(info.getSaleprice());
                }
                allprice.setText("总价格：" + allprices + "元");
                adapter.notifyDataSetChanged();
                Log.i(TAG, "handleMessage: " + item.toString() + "\n" + "allLists:" + allLists+"\nnameList:"+nameLists.toString());
            }
        }
    };
    private MTextWatch msgTextWatch = new MTextWatch() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            barCode = textmsg.getText().toString().trim();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_scan);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        title.setTitle("消费者零售购买");
        map = new HashMap<>();
        list = new ArrayList<>();
        nameLists = new ArrayList<>();
        barcodes = new ArrayList<>();
        allLists = new ArrayList<>();
        phone.addTextChangedListener(phoneTextWatch);
        name.addTextChangedListener(nameTextWatch);
        textmsg.addTextChangedListener(msgTextWatch);
        App.getApp().setHandler(handler);
    }
    @OnClick({R.id.activity_sale_scan_btn, R.id.activity_sale_scan_yes_btn, R.id.activity_sale_scan_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_sale_scan_btn:
                // 调用ZXIng开源项目源码  扫描二维码
                boolean permision = isPermision();
                if (!permision){
                    showMsgDialog("相机打开失败，请检查是否已为该应用开启相机权限！",0);
                    return;
                }
                Intent openCameraIntent = new Intent(ActivitySaleScan.this,
                        CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.activity_sale_scan_yes://输码确认
                if (TextUtils.isEmpty(textmsg.getText().toString().trim())) {
                    showToast("条码不能为空！");
                } else if (barcodes.contains(textmsg.getText().toString().trim())) {
                    showMsgDialog("该条码已存在！", 0);
                } else {
                    map.put("bar_code", barCode);
                    salescan(map);
                }
                break;
            case R.id.activity_sale_scan_yes_btn:
                if (TextUtils.isEmpty(phoneString)){
                   /*String errorText="请输入电话号码！";
                    Drawable errorIcon=ContextCompat.getDrawable(this,R.drawable.edit_erorr16);
                    errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(),
                            errorIcon.getIntrinsicHeight()));
                    ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(errorText);
                    ssbuilder.setSpan(fgcspan, 0, errorText.length(), 0);
                    phone.setError(ssbuilder, errorIcon);
                    phone.requestFocus();*/
                    showToast("请输入电话号码！");

                }else if (TextUtils.isEmpty(nameString)){
                   showToast("请输入姓名！");
                }else if (list==null||list.size()==0){
                    showToast("产品信息为空！");
                }else saleok();
                break;
        }
    }


    private void saleok() {
        /*2 当前代理商：agentid，从【2_1获取代理商信息】接口返回参数agentid获取
3 货品id：goodsid，从页面获取，多条数据之间用逗号分开，例如：jxstar20160919009，jxstar20160919008
4 小码：smallcode，【同货品id】
5 回访天数：comedays，【同货品id】
6 货品编号：goodsno，【同货品id】
7 货品名称：goodsname，【同货品id】
8 规则型号：goodssize，【同货品id】
9 货品单位：unitname，【同货品id】
10 销售单价：saleprice，【同货品id】
11 批次号：batchno，【同货品id】
12 数量：num，【同货品id】
13 金额：salemoney，【同货品id】
14 销售单价：saleprice，【同货品id】
15 电话：clienttel，从页面输入
16 姓名：clientname，从页面输入
17 备注：remark，从页面输入*/
        StringBuilder goodsid=new StringBuilder();
        StringBuilder smallcode=new StringBuilder();
        StringBuilder comedays=new StringBuilder();
        StringBuilder goodsno=new StringBuilder();
        StringBuilder goodsname=new StringBuilder();
        StringBuilder goodssize=new StringBuilder();
        StringBuilder unitname=new StringBuilder();
        StringBuilder saleprice=new StringBuilder();
        final StringBuilder batchno=new StringBuilder();
        final StringBuilder num=new StringBuilder();
        StringBuilder salemoney=new StringBuilder();

        for (SalescanInfo salescanInfo:allLists){
            smallcode.append(salescanInfo.getSmallcode()).append(";");
        }
        for (SalescanInfo salescanInfo:list){
            goodsid.append(salescanInfo.getGoodsid()).append(";");
            goodsname.append(salescanInfo.getGoodsname()).append(";");
            comedays.append(salescanInfo.getComedays()).append(";");
            goodsno.append(salescanInfo.getGoodsno()).append(";");
            goodssize.append(salescanInfo.getGoodssize()).append(";");
            unitname.append(salescanInfo.getUnitname()).append(";");
            saleprice.append(salescanInfo.getSaleprice()).append(";");
            batchno.append(salescanInfo.getBatchno()).append(";");
            num.append(salescanInfo.getCount()).append(";");
            salemoney.append(Float.parseFloat(salescanInfo.getSaleprice())*salescanInfo.getCount()).append(";");
        }
        if (list!=null&&list.size()!=0) {
            goodsid.deleteCharAt(goodsid.length() - 1);
            smallcode.deleteCharAt(smallcode.length() - 1);
            comedays.deleteCharAt(comedays.length() - 1);
            goodsno.deleteCharAt(goodsno.length() - 1);
            goodsname.deleteCharAt(goodsname.length() - 1);
            goodssize.deleteCharAt(goodssize.length() - 1);
            unitname.deleteCharAt(unitname.length() - 1);
            saleprice.deleteCharAt(saleprice.length() - 1);
            batchno.deleteCharAt(batchno.length() - 1);
            num.deleteCharAt(num.length() - 1);
            salemoney.deleteCharAt(salemoney.length() - 1);
        }
        map.clear();
        map.put("agentid",user.getAgentid());
        map.put("goodsid",goodsid.toString());
        map.put("smallcode",smallcode.toString());
        map.put("comedays",comedays.toString());
        map.put("goodsno",goodsno.toString());
        map.put("goodsname",goodsname.toString());
        map.put("goodssize",goodssize.toString());
        map.put("unitname",unitname.toString());
        map.put("saleprice",saleprice.toString());
        map.put("batchno",batchno.toString());
        map.put("num",num.toString());
        map.put("salemoney",salemoney.toString());
        map.put("clienttel",phoneString);
        map.put("clientname",nameString);
        map.put("remark",user.getAgentid());
        showProgressDialog("正在提交...");
        Call<BaseModel> call = netApi.saleok(map);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                map.clear();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    if ("1".equals(body.getRes())){
                        showMsgDialog(body.getMsg(),0);
                        textmsg.setText("");
                        adapter = new AdapterSaleScan(ActivitySaleScan.this,
                                R.layout.adapter_sale_scan, null);
                        scanLv.setAdapter(adapter);
                        list.clear();
                        allLists.clear();
                    }else showMsgDialog(body.getMsg(),0);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
                map.clear();
            }
        });
    }

    private void salescan(Map<String, String> map) {
        showProgressDialog("获取数据中...");
        Call<BaseModel<SalescanInfo>> call = netApi.salescan(map);
        call.enqueue(new Callback<BaseModel<SalescanInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<SalescanInfo>> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()) {
                    BaseModel<SalescanInfo> body = response.body();
                    if ("1".equals(body.getRes())) {
                        //获取数据成功
                        List<SalescanInfo> data = body.getData();
                        SalescanInfo salescanInfo = data.get(0);
                        if (list.size() == 0) {
                            list.add(salescanInfo);
                            barcodes.add(barCode);
                            nameLists.add(salescanInfo.getGoodsname());
                        } else {
                            for (int i = 0; i < list.size(); i++) {
                                SalescanInfo listInfo = list.get(i);
                                //nameLists.add(listInfo.getGoodsname());
                                if (listInfo.getGoodsname().equals(salescanInfo.getGoodsname())) {
                                    listInfo.setCount(listInfo.getCount() + 1);
                                    barcodes.add(barCode);
                                }
                            }
                            if (!nameLists.contains(salescanInfo.getGoodsname())) {
                                list.add(salescanInfo);
                                nameLists.add(salescanInfo.getGoodsname());
                                barcodes.add(barCode);
                            }
                        }
                        salescanInfo.setBarcode(barCode);
                        allLists.add(salescanInfo);
                        allcount.setText("总数量：" + barcodes.size());
                        float allprices = 0;
                        for (SalescanInfo info : list) {
                            allprices += info.getCount() * Float.parseFloat(info.getSaleprice());
                        }
                        allprice.setText("总价格：" + allprices + "元");
                        adapter = new AdapterSaleScan(ActivitySaleScan.this,
                                R.layout.adapter_sale_scan, list);
                        scanLv.setAdapter(adapter);
                        scanLv.setOnItemClickListener(itemClickListener);
                        Log.i(TAG, "barcodes: " + barcodes + "\n" + "allLists:" + allLists+"\nnamelist:"+nameLists);

                    } else showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            barCode = bundle.getString("result");
            if (barCode!=null){
                if (barCode.contains("=")) {
                    String[] strings = barCode.split("=");
                    if (strings.length >= 1)
                        barCode = strings[1];
                }
            }
            textmsg.setText(barCode);
            Log.i(TAG, "onActivityResult: " + barCode);
        }
    }
}
