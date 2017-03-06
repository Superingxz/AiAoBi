package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

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
import xinlan.com.AiAoBi.entity.DoselbargaindetInfo;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.requestJionIn.MTextWatch;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class CheckGoodsToCommit extends BaseActivty {
    @BindView(R.id.check_commit_title_view)
    TitileView titileView;
    @BindView(R.id.check_commit_edit)
    EditText editText;
    @BindView(R.id.check_commit_btn)
    Button button;
    List<DoselbargaindetInfo> checkList;
    private String src_id;
    private String src_no;
    private String ref_id;
    private String ref_no;
    private String ordertype;
    private String reason;
    private StringBuffer goodsids;
    private StringBuffer goodsnames;
    private StringBuffer goodssizes;
    private StringBuffer prices;
    private StringBuffer unitname;
    private StringBuffer parentnums;
    private StringBuffer ordernums;
    private StringBuffer ref_detid;
    private StringBuffer src_detid;
    private Map<String, String> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_commit);
        ButterKnife.bind(this);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        titileView.setTitle("我的代发原因");
        initDatas();
        editText.addTextChangedListener(new MTextWatch() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                reason = editText.getText().toString();
            }
        });
    }

    private void initDatas() {
        map = new HashMap<>();
        src_id = getIntent().getStringExtra("src_id");
        src_no = getIntent().getStringExtra("src_no");
        ref_id = getIntent().getStringExtra("ref_id");
        ref_no = getIntent().getStringExtra("ref_no");
        ordertype = getIntent().getStringExtra("ordertype");
        goodsids = new StringBuffer();
        goodsnames = new StringBuffer();
        goodssizes = new StringBuffer();
        prices = new StringBuffer();
        unitname = new StringBuffer();
        parentnums = new StringBuffer();
        ordernums = new StringBuffer();
        ref_detid = new StringBuffer();
        src_detid = new StringBuffer();
        checkList = (List<DoselbargaindetInfo>) getIntent().getExtras().getSerializable("checkList");
        if (checkList != null)
            for (DoselbargaindetInfo info :
                    checkList) {
                goodsids.append(info.getGoodsid()).append(";");
                goodsnames.append(info.getGoodsname()).append(";");
                if (info.getGoodssize().isEmpty()){
                    goodssizes.append("o").append(";");
                }else {
                    goodssizes.append(info.getGoodssize()).append(";");
                }
                if (info.getCostprice().isEmpty()){
                    prices.append("0").append(";");
                }else {
                    prices.append(info.getCostprice()).append(";");
                }
                unitname.append(info.getUnitname()).append(";");
                ordernums.append(info.getTotalnum()).append(";");
                ref_detid.append(info.getRef_detid()).append(";");
                src_detid.append(info.getSrc_detid()).append(";");
            }
        goodsids.deleteCharAt(goodsids.length() - 1);
        goodsnames.deleteCharAt(goodsnames.length() - 1);
        goodssizes.deleteCharAt(goodssizes.length() - 1);
        prices.deleteCharAt(prices.length() - 1);
        unitname.deleteCharAt(unitname.length() - 1);
        ordernums.deleteCharAt(ordernums.length() - 1);
        ref_detid.deleteCharAt(ref_detid.length() - 1);
        src_detid.deleteCharAt(src_detid.length() - 1);
    }

    /*参数清单如下：
1 功能编码：eventcode=doselagentc
2 当前代理商：agentid
3 原订单号：src_no，【从3_2_1处理下级订单(获取直属下级的订单)或3_2_3处理下级订单(获取直属订单下的代理订单)】接口中返回参数中获取
4 原订单id：src_id，【同原订单号取值说明】
5 提报订单号：ref_no，【同原订单号取值说明】
6 提报订单id：ref_id，【同原订单号取值说明】
7 订单类型：ordertype，【订单类型，apply申请，deal处理】，【同原订单号取值说明】
shengji64 代发原因：reason
9 货品id：goodsid，【从3_2_2处理下级订单(通过订单获取订单明细)或
3_2_4处理下级订单(通过代理订单获取订单明细)中获取】
10 货品名称：goodsname，【同货品id取值说明】
11 货品规格：goodssize，【同货品id取值说明】
12 货品价格：price，【同货品id取值说明，取costprice字段值】
13 货品单位：unitname，【同货品id取值说明】
14 总订数：ordernum，【同货品id取值说明，取totalnum字段值】
15 提报明细id：ref_detid，【同货品id取值说明】
16 原订单明细id：src_detid，【同货品id取值说明】

*/
    @OnClick(R.id.check_commit_btn)
    public void onClick() {
        if ("".equals(editText.getText().toString())) {
            showToast("请填写代发原因");
        } else {
            map.put("agentid", user.getAgentid());
            map.put("src_no", src_no);
            map.put("src_id", src_id);
            map.put("ref_no", ref_no);
            map.put("ref_id", ref_id);
            map.put("ordertype", ordertype);
            map.put("reason", reason);
            map.put("goodsid", goodsids.toString());
            map.put("goodsname", goodsnames.toString());
            map.put("goodssize", goodssizes.toString());
            map.put("price", prices.toString());
            map.put("unitname", unitname.toString());
            map.put("ordernum", ordernums.toString());
            map.put("ref_detid", ref_detid.toString());
            map.put("src_detid", src_detid.toString());
            Call<BaseModel> call = netApi.doselagentc(map);
            showProgressDialog("正在提交");
            call.enqueue(new Callback<BaseModel>() {
                @Override
                public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                    map.clear();
                    dimissProgressDialog();
                    if (response.isSuccess()) {
                        String res = response.body().getRes();
                        if ("1".equals(res)){
                            //发送消息通知更新订单详情列表 0x116
                            Handler handler = App.getApp().getHandler();
                            handler.sendEmptyMessage(0x116);
                            Handler handler2 = App.getApp().getHandler2();
                            handler2.sendEmptyMessage(0x116);
                            showMsgDialog("选中货品代理发货申请成功!",1);
                        }else showMsgDialog(response.body().getMsg(),0);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    map.clear();
                    dimissProgressDialog();
                    showToast("连接服务器失败");
                }
            });
        }
    }
}
