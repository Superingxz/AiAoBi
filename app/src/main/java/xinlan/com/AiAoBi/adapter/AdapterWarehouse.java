package xinlan.com.AiAoBi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import xinlan.com.AiAoBi.NetApi;
import xinlan.com.AiAoBi.NetCilent;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Goods;
import xinlan.com.AiAoBi.home.enity.BaseModel;
import xinlan.com.AiAoBi.view.JrComputeView;

/**
 * Created by Administrator on 2016/12/27.
 */
public class AdapterWarehouse extends CommonAdapter<Goods>{
    public AdapterWarehouse(Context context, int itemLayoutId, List<Goods> mDatas) {
        super(context, itemLayoutId, mDatas);
        netApi= NetCilent.getInstance().getNetApi();
    }

    @Override
    public void convert(final ViewHolder helper, final Goods item) {
        helper.setText(R.id.adapter_warehouse_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_warehouse_name,item.getGoodsname());
        helper.getView(R.id.adapter_warehouse_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(helper);
                if (getData().size() == 0) {
                    editeIface.deleteAll();
                }
            }
        });
        final JrComputeView jrComputeView=(JrComputeView)helper.getView(R.id.adapter_warehouse_jrComputerView);
        jrComputeView.getEditText().clearFocus();
        jrComputeView.setMinNum(1);
        jrComputeView.setMaxNum(Integer.MAX_VALUE);
        if (TextUtils.isEmpty(item.getEditText())) {
            jrComputeView.setNum(1);
        } else {
            jrComputeView.setNum(Integer.valueOf(item.getEditText()));
        }
        jrComputeView.setCalculateCallBack(new JrComputeView.CalculateCallBack() {
            @Override
            public void reduceButtonBack(int num) {
                getData().get(helper.getPosition()).setEditText(String.valueOf(num));
                editeIface.edite(helper.getPosition(), String.valueOf(num));

            }

            @Override
            public void addButtonBack(int num) {
                getData().get(helper.getPosition()).setEditText(String.valueOf(num));
                editeIface.edite(helper.getPosition(), String.valueOf(num));
            }

            @Override
            public void editTextChange(int num) {
                Log.v("num",helper.getPosition()+"-"+num);
                getData().get(helper.getPosition()).setEditText(String.valueOf(num));
                editeIface.edite(helper.getPosition(), String.valueOf(num));

            }
        });
    }
    private void showDialog(final ViewHolder helper) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext).setTitle("提示").setMessage("是否删除该货品?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getItem(helper.getPosition()).getAgent_minstock_id()!=null){
                    getdelminstock(getItem(helper.getPosition()).getAgent_minstock_id(),helper.getPosition());
                }else {
                    removeItem(helper.getPosition());
                    editeIface.deleteItem();
                    if (getData().size() == 0) {
                        editeIface.deleteAll();
                    }
                }

            }
        });
        alertDialog.show();
    }
    public EditeIface getEditeIface() {
        return editeIface;
    }

    public void setEditeIface(EditeIface editeIface) {
        this.editeIface = editeIface;
    }

    EditeIface editeIface;

    public interface EditeIface {
        void edite(int position, String num);

        void deleteItem();

        void deleteAll();
    }
    private NetApi netApi;
    private void getdelminstock(String agent_minstock_id, final int position) {
        showProgressDialog("删除中...");
        Call<BaseModel> call = netApi.getdelminstock(agent_minstock_id);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Response<BaseModel> response, Retrofit retrofit) {
                dimissProgressDialog();
                if (response.isSuccess()){
                    BaseModel body = response.body();
                    showToast(body.getMsg());
                    if ("1".equals(body.getRes())){
                        removeItem(position);
                        editeIface.deleteItem();
                        if (getData().size() == 0) {
                            editeIface.deleteAll();
                        }
                    }else showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dimissProgressDialog();
                showToast("连接服务器失败！");
            }
        });
    }
}
