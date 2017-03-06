package xinlan.com.AiAoBi.home.homeFragment;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.Serializable;
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
import xinlan.com.AiAoBi.adapter.DoselagentgAdapter;
import xinlan.com.AiAoBi.entity.ChooseGoods;
import xinlan.com.AiAoBi.entity.DoselagentaInfo;
import xinlan.com.AiAoBi.entity.Doselagentg;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.entity.ExpressInfo;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.entity.GoodsID;
import xinlan.com.AiAoBi.entity.ScangetbargaincodeInfo;
import xinlan.com.AiAoBi.home.customerFragment.ActivitySaleScanLvInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.scancode.activity.CaptureActivity;

import xinlan.com.AiAoBi.utils.CommonUtils;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * 扫描发货
 * Created by Administrator on 2016/11/7.
 */

public class ScanDeliverGoods extends BaseActivty {
    @BindView(R.id.scan_title_view)
    TitileView titileView;
    @BindView(R.id.scan_listview)
    ListView listView;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.scan_bt)
    Button scan_bt;
    @BindView(R.id.user_info)
    TextView userinfo;
    //    @BindView(R.id.order_id)
//    TextView order_id;
    @BindView(R.id.user_info_adress)
    TextView user_info_adress;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.edite_code)
    EditText edite_code;
    @BindView(R.id.edit_code_sure)
    Button edit_code_sure;
    @BindView(R.id.submit_add)
    Button submit_add;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.reminder)
    LinearLayout reminder;
    @BindView(R.id.user_phone)
    TextView user_phone;

    DoselagentgAdapter adapter;
    List<Doselagentg> Scanlist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_goods);
        ButterKnife.bind(this);
        titileView.setTitle("扫码发货");
        initEvent();
        initViews();
        getexpress();
        App.getApp().setHandler(handler);
    }

    String express;

    private void initViews() {
        adapter = new DoselagentgAdapter(this, R.layout.item_doselagentg, Scanlist);
        listView.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (list!=null&&list.size()>0)
                express = list.get(position);
                Log.i("onItemSelected", "onItemSelected: "+express);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter.setOnItemClickIface(new DoselagentgAdapter.OnItemClickIface() {
            @Override
            public void ItemClick(int position) {
                Log.i("log", "onItemClick: ");
                if (!Scanlist.get(position).isAddGoods()) {
                    Intent intent = new Intent(ScanDeliverGoods.this, ActivitySaleScanLvInfo.class);
                    intent.putExtra("goodName", Scanlist.get(position).getGoodsname());
                    intent.putExtra("goodsid", Scanlist.get(position).getGoodsid());
                    intent.putStringArrayListExtra("barcode", (ArrayList<String>) Scanlist.get(position).getSanCodeList());
                    startActivity(intent);
                }
            }

            @Override
            public void ItemDelete(final int position) {
                showDiyMsgDialog("是否删除?", new Mcallback() {
                    @Override
                    public void doSomething(AlertDialog alertDialog) {
                        adapter.removeItem(position);
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }







    private ScangetbargaincodeInfo scangetbargaincodeInfo;
    private DoselbargainInfo doselbargainInfo;
    private DoselagentaInfo doselagentaInfo;

    private void initEvent() {
        //下级直属订单
        if (getIntent().getFlags() == 0x008)
            doselbargainInfo = (DoselbargainInfo) getIntent().getExtras().getSerializable("doselbargainInfo");
        //下级代理订单
        if (getIntent().getFlags() == 0x007)
            doselagentaInfo = (DoselagentaInfo) getIntent().getExtras().getSerializable("doselagentaInfo");
        if (getIntent().getFlags() == 0x502) {
            scangetbargaincodeInfo = (ScangetbargaincodeInfo) getIntent().getSerializableExtra("scangetbargaincodeInfo");
        }
        if (null != doselagentaInfo) {
            doselbargainInfo = new DoselbargainInfo();
            doselbargainInfo.setRef_id(doselagentaInfo.getRef_id());
            doselbargainInfo.setOrdertype(doselagentaInfo.getOrdertype());
            doselbargainInfo.setBargainflag(doselagentaInfo.getBargainflag());
            doselbargainInfo.setSrc_id(doselagentaInfo.getSrc_id());
            userinfo.setText(doselagentaInfo.getInman());
            user_info_adress.setText(doselagentaInfo.getInaddr());
            user_phone.setText(doselbargainInfo.getIntel());
            // getData();
        } else if (doselbargainInfo != null) {
            userinfo.setText(doselbargainInfo.getInman());
            user_info_adress.setText(doselbargainInfo.getInaddr());
            user_phone.setText(doselbargainInfo.getIntel());
            //  getData();
        } else if (null != scangetbargaincodeInfo) {
            doselbargainInfo = new DoselbargainInfo();
            doselbargainInfo.setRef_id(scangetbargaincodeInfo.getRef_id());
            doselbargainInfo.setOrdertype(scangetbargaincodeInfo.getOrdertype());
            doselbargainInfo.setBargainflag(scangetbargaincodeInfo.getBargainflag());
            doselbargainInfo.setSrc_id(scangetbargaincodeInfo.getSrc_id());
            userinfo.setText(scangetbargaincodeInfo.getInman());
            user_info_adress.setText(scangetbargaincodeInfo.getInaddr());
            user_phone.setText(scangetbargaincodeInfo.getIntel());
            // getData();
        }

    }

    /**
     * 获取快递公司
     */
    private List<String> list;
    void getexpress(){
        Call<BaseModel<ExpressInfo>> call = netApi.getexpress(user.getPhone());
        call.enqueue(new Callback<BaseModel<ExpressInfo>>() {
            @Override
            public void onResponse(Response<BaseModel<ExpressInfo>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    BaseModel<ExpressInfo> body = response.body();
                    if ("1".equals(body.getRes())){
                        //获取数据成功
                        list=new ArrayList<>();
                        for (ExpressInfo info:body.getData()){
                            list.add(info.getComp());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ScanDeliverGoods.this,
                                R.layout.adapter_mspinner_bar, list);
                        arrayAdapter.setDropDownViewResource(R.layout.adapter_mspinner);
                        spinner.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    @OnClick({R.id.submit, R.id.scan_bt, R.id.submit_add, R.id.edit_code_sure,R.id.express_btn})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.edit_code_sure:
                if (TextUtils.isEmpty(edite_code.getText().toString())) {
                    showToast1("请输入条码");
                    return;
                }
                codeSure();
                break;
            case R.id.submit_add:
                List<ChooseGoods> list = new ArrayList<>();
                for (Doselagentg de : adapter.getData()) {
                    if (de.isAddGoods()) {
                        ChooseGoods goods = new ChooseGoods();
                        goods.setGoodsid(de.getGoodsid());
                        list.add(goods);
                    }
                }
                Intent intent = new Intent(ScanDeliverGoods.this, ChooseGoodsActivity.class);
                intent.putExtra("materialList", "materialList");
                intent.putExtra("chooseGoodsList", (Serializable) list);
                startActivityForResult(intent, 0x003);
                break;
            case R.id.submit:
                submit();
                break;
            case R.id.scan_bt:
                boolean permision = isPermision();
                if (!permision){
                    showMsgDialog("相机打开失败，请检查是否已为该应用开启相机权限！",0);
                    return;
                }
                sanBt(0);
                break;
            case R.id.express_btn:
                if (!isPermision()){
                    showMsgDialog("相机打开失败，请检查是否已为该应用开启相机权限！",0);
                    return;
                }
                sanBt(0x112);
                break;
        }
    }

    void submit() {
        if (TextUtils.isEmpty(number.getText().toString())) {
            showToast1("请输入快递单号");
            return;
        }
        if (adapter.getData().size() == 0) {
            showToast1("暂时没有发货信息,请输码确认");
            return;
        }
        showProgressDialog("确认发货中...");
        Map<String, String> map;
        map = new HashMap<>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("bargainflag", doselbargainInfo.getBargainflag());
        map.put("ref_id", doselbargainInfo.getRef_id());
        map.put("ordertype", doselbargainInfo.getOrdertype());
        map.put("emscomp", express);
        map.put("emsno", number.getText().toString());
        map.put("src_id", doselbargainInfo.getSrc_id());
        StringBuffer goodsid = new StringBuffer();
        StringBuffer goodsno = new StringBuffer();
        StringBuffer goodsname = new StringBuffer();
        StringBuffer goodssize = new StringBuffer();
        StringBuffer unitname = new StringBuffer();
        StringBuffer costprice = new StringBuffer();
        StringBuffer src_detid = new StringBuffer();
        StringBuffer ref_detid = new StringBuffer();
        StringBuffer num = new StringBuffer();
        StringBuffer alreadynum = new StringBuffer();//界面输入
        StringBuffer barcode = new StringBuffer();
        StringBuffer neednum = new StringBuffer();

        //条码：barcode，同一记录之间条码用逗号分开，多条记录之间用分号分开，
        //例如：1234567890123801，1234567890123802，1234567890123803；1234567890123501，1234567890123502
        for (Doselagentg doselagentg : adapter.getData()) {
            barcode.append(";");
            for (int i = 0; i < doselagentg.getSanCodeList().size(); i++) {
                if (i != doselagentg.getSanCodeList().size() - 1) {
                    barcode.append(doselagentg.getSanCodeList().get(i) + ",");
                } else {
                    barcode.append(doselagentg.getSanCodeList().get(i));
                }
            }
            goodsid.append(doselagentg.getGoodsid() + ";");
            goodsno.append(doselagentg.getGoodsno() + ";");
            goodsname.append(doselagentg.getGoodsname() + ";");
            goodssize.append(doselagentg.getGoodssize() + ";");
            unitname.append(doselagentg.getUnitname() + ";");
            num.append(doselagentg.getNum() + ";");
            costprice.append(doselagentg.getCostprice() + ";");
            src_detid.append(doselagentg.getSrc_detid() + ";");
            ref_detid.append(doselagentg.getRef_detid() + ";");
            alreadynum.append(doselagentg.getAlreadynum() + ";");
            neednum.append(doselagentg.getTotal() + ";");
        }
        barcode.deleteCharAt(0);
        goodsid.deleteCharAt(goodsid.length() - 1);
        goodsno.deleteCharAt(goodsno.length() - 1);
        goodsname.deleteCharAt(goodsname.length() - 1);
        goodssize.deleteCharAt(goodssize.length() - 1);
        unitname.deleteCharAt(unitname.length() - 1);
        num.deleteCharAt(num.length() - 1);
        costprice.deleteCharAt(costprice.length() - 1);
        src_detid.deleteCharAt(src_detid.length() - 1);
        ref_detid.deleteCharAt(ref_detid.length() - 1);
        alreadynum.deleteCharAt(alreadynum.length() - 1);
        neednum.deleteCharAt(neednum.length() - 1);


        map.put("neednum", neednum.toString());
        map.put("goodsid", goodsid.toString());
        map.put("goodsname", goodsname.toString());
        map.put("goodsno", goodsno.toString());
        map.put("goodssize", goodssize.toString());
        map.put("unitname", unitname.toString());
        map.put("num", num.toString());
        map.put("costprice", costprice.toString());
        map.put("src_detid", src_detid.toString());
        map.put("ref_detid", ref_detid.toString());
        map.put("alreadynum", alreadynum.toString());
        map.put("barcode", barcode.toString());

        Log.v("submitScanData", map.toString());
        Call<BaseModel> baseModelCall = netApi.scanok(map);
        baseModelCall.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                if (null != response.body().getRes()) {
                    if (response.body().getRes().equals("1")) {
                        adapter.clearAll();
                        number.setText("");
                        showToast1("确认发货成功");
                        //刷新下级订单详情
                        App.getApp().getHandler3().sendEmptyMessage(0x116);
                    } else if (response.body().getRes().equals("-1")) {
                        showToast1("确认发货失败");
                    } else {
                        showToast1(response.body().getMsg());
                    }
                } else {
                    showToast1("确认发货失败");
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast1("连接服务器失败");
            }
        });

    }

    private void sanBt(int requestCode) {
        Intent openCameraIntent = new Intent(ScanDeliverGoods.this,
                CaptureActivity.class);
        startActivityForResult(openCameraIntent, requestCode);
    }

    private void codeSure() {
        if (TextUtils.isEmpty(edite_code.getText().toString())) {
            showToast1("条码不能为空");
            return;
        }
        for (Doselagentg dlo : adapter.getData()) {
            if (null != dlo.getSanCodeList()) {
                for (String string : dlo.getSanCodeList()) {
                    if (string.contains(edite_code.getText().toString())) {
                        showMsgDialog("该条码已输码确认", 0);
                        return;
                    }
                }
            }
        }
        showProgressDialog("输码确认中...");
        Map<String, String> map;
        map = new HashMap<>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("bar_code", edite_code.getText().toString());
        Call<BaseModel<GoodsID>> call = netApi.scanbarcode(map);
        call.enqueue(new Callback<BaseModel<GoodsID>>() {
            @Override
            public void onResponse(Response<BaseModel<GoodsID>> response, Retrofit retrofit) {
                if (response.body().getRes().equals("1")) {
                    if (null != response.body() && null != response.body().getData()) {
                        String goodsid = response.body().getData().get(0).getGoodsid();
                        boolean check = false;
                        for (Doselagentg doselagentg : adapter.getData()) {
                            if (doselagentg.getGoodsid().equals(goodsid)) {
                                check = true;
                                if (Integer.valueOf(doselagentg.getTotal())
                                        - Integer.valueOf(doselagentg.getAlreadynum())
                                        - Integer.valueOf(doselagentg.getNum()) >= 1) {
                                    String num = doselagentg.getNum();
                                    doselagentg.getSanCodeList().add(edite_code.getText().toString().trim());
                                    doselagentg.setNum(String.valueOf(Integer.valueOf(num) + 1));
                                    adapter.notifyDataSetChanged();
                                    showToast1(response.body().getMsg());
                                    edite_code.setText("");
                                    showToast1(response.body().getMsg());
                                } else {
                                    showMsgDialog(doselagentg.getGoodsname() + "本次发货数已达上限", 0);
                                }
                                break;
                            }
                        }
                        if (!check) {
                            showToast1(response.body().getMsg());
                            getScanData(goodsid, edite_code.getText().toString().trim());
                        }
                    }
                } else {
                    showToast1(response.body().getMsg());
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast1("连接服务器失败");
            }
        });
    }

    private void getScanData(String goodsid, final String code) {
        showProgressDialog("获取产品数据中...");
        Map<String, String> map;
        map = new HashMap<>();
        map.put("agentid", App.getApp().getUserInfo().getAgentid());
        map.put("bargainflag", doselbargainInfo.getBargainflag());
        map.put("goodsid", goodsid);
        map.put("ref_id", doselbargainInfo.getRef_id());
        map.put("ordertype", doselbargainInfo.getOrdertype());
        Call<BaseModel<Doselagentg>> call = netApi.scangetnum(map);
        call.enqueue(new Callback<BaseModel<Doselagentg>>() {
            @Override
            public void onResponse(Response<BaseModel<Doselagentg>> response, Retrofit retrofit) {
                if (null != response.body()) {
                    if (response.body().getRes().equals("1")) {
                        if (!TextUtils.isEmpty(response.body().getData().get(0).getTotal()) && !TextUtils.isEmpty(response.body().getData().get(0).getAlreadynum())) {
                            if (Integer.valueOf(response.body().getData().get(0).getTotal()) - Integer.valueOf(response.body().getData().get(0).getAlreadynum()) <= 0) {
                                showMsgDialog(response.body().getData().get(0).getGoodsname() + "已不欠货,请更换其他产品", 0);
                            } else {
                                response.body().getData().get(0).setNum("1");
                                List<String> list = new ArrayList<String>();
                                list.add(code);
                                response.body().getData().get(0).setSanCodeList(list);
                                adapter.add(response.body().getData());
                                edite_code.setText("");
                                showToast1(response.body().getMsg());
                            }
                        }
                    } else {
                        showToast1(response.body().getMsg());
                    }
                }
                dimissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast1("连接服务器失败");
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(ScanDeliverGoods.this.isFinishing()&&ScanDeliverGoods.this.isDestroyed())return;
            }
            if (msg.what == 0x701) {
                String[] item = (String[]) msg.obj;
                boolean isDelete = false;
                int i = 0;
                for (Doselagentg doselagentg : adapter.getData()) {
                    if (doselagentg.getGoodsname().equals(item[1])) {
                        doselagentg.getSanCodeList().remove(item[0]);
                        if (doselagentg.getSanCodeList().size() == 0) {
                            isDelete = true;
                        } else {
                            doselagentg.setNum(String.valueOf(doselagentg.getSanCodeList().size()));
                        }
                        break;
                    }
                    i++;
                }
                if (isDelete) {
                    adapter.removeItem(i);
                }
                adapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 取得返回信息
        if (resultCode == RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            if (scanResult!= null) {
                if (scanResult.contains("=")){
                    String[] strings = scanResult.split("=");
                    if (strings.length>=1)
                    scanResult = strings[1];
                }
            }
            edite_code.setText(scanResult);
        }else if (resultCode == RESULT_OK && requestCode == 0x112){
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            if (scanResult!= null) {
                if (scanResult.contains("=")){
                    String[] strings = scanResult.split("=");
                    if (strings.length>=1)
                        scanResult = strings[1];
                }
            }
            number.setText(scanResult);
        }
       else if (resultCode == Activity.RESULT_OK && requestCode == 0x003) {
            List<Goods> goodsList = new ArrayList<Goods>();
            goodsList.addAll((List<Goods>) data.getSerializableExtra("chooseGoodsList"));
            for (Goods g : goodsList) {
                Doselagentg dose = new Doselagentg();
                dose.setGoodsid(g.getGoodsid());
                dose.setCostprice(g.getPrice());
                dose.setUnitname(g.getMixunit());
                dose.setGoodsno("");
                dose.setAlreadynum("0");
                dose.setRef_detid("");
                dose.setSrc_detid("");
                dose.setNum("0");
                dose.setTotal("0");
                dose.setGoodssize(g.getGoodssize());
                dose.setGoodsname(g.getGoodsname());
                dose.setSanCodeList(new ArrayList<String>());
                dose.setAddGoods(true);
                adapter.add(dose);
            }
        }
    }
}
