package xinlan.com.AiAoBi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Doselagentg;
import xinlan.com.AiAoBi.home.customerFragment.ActivitySaleScanLvInfo;
import xinlan.com.AiAoBi.home.homeFragment.ScanDeliverGoods;
import xinlan.com.AiAoBi.view.JrComputeView;

/**
 * Created by Administrator on 2016/11/15.
 */
public class DoselagentgAdapter extends CommonAdapter<Doselagentg> {
    public DoselagentgAdapter(Context context, int itemLayoutId, List<Doselagentg> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    long downTime;

    @Override
    public void convert(final ViewHolder helper, final Doselagentg item) {
        if (!item.isAddGoods()) {
            ((TextView) helper.getView(R.id.name)).setTextColor(mContext.getResources().getColor(R.color.orange));
            ((LinearLayout) helper.getView(R.id.item_order_scan_layout)).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            ((LinearLayout) helper.getView(R.id.item_order_scan_layout)).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.v("MotionEvent",String.valueOf(event.getAction()));
                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        downTime = System.currentTimeMillis();
                    }else if(MotionEvent.ACTION_UP == event.getAction()){
                        if(System.currentTimeMillis()-downTime<=500){
                            onItemClickIface.ItemClick(helper.getPosition());
                            downTime=0;
                        }
                    }
                    return false;
                }
            });
            ((TextView) helper.getView(R.id.delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickIface.ItemClick(helper.getPosition());
                }
            });
            ((TextView) helper.getView(R.id.delete)).setText("详情");
            ((TextView) helper.getView(R.id.delete)).setTextColor(mContext.getResources().getColor(R.color.orange));
            helper.setText(R.id.alreadynum, item.getAlreadynum());
            helper.getView(R.id.num).setVisibility(View.VISIBLE);
            helper.getView(R.id.num_view).setVisibility(View.GONE);
            helper.setText(R.id.lessnum, String.valueOf(Integer.valueOf(item.getTotal()) - Integer.valueOf(item.getAlreadynum()) - Integer.valueOf(item.getNum())));
            helper.setText(R.id.total, item.getTotal());
        } else {
            ((TextView) helper.getView(R.id.delete)).setText("删除");
            ((TextView) helper.getView(R.id.delete)).setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            ((TextView) helper.getView(R.id.name)).setTextColor(Color.GRAY);
            ((TextView) helper.getView(R.id.delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickIface.ItemDelete(helper.getPosition());
                }
            });
            helper.setText(R.id.total, "");
            helper.setText(R.id.lessnum, "");
            helper.setText(R.id.alreadynum, "");
            helper.getView(R.id.num).setVisibility(View.GONE);
            helper.getView(R.id.num_view).setVisibility(View.VISIBLE);
            JrComputeView computeView = (JrComputeView) helper.getView(R.id.num_view);
            computeView.setMinNum(0);
            computeView.setMaxNum(Integer.MAX_VALUE);
            if (TextUtils.isEmpty(item.getNum())) {
                computeView.setNum(0);
            } else {
                computeView.setNum(Integer.valueOf(item.getNum()));
            }
            computeView.setCalculateCallBack(new JrComputeView.CalculateCallBack() {
                @Override
                public void reduceButtonBack(int num) {
                    getData().get(helper.getPosition()).setNum(String.valueOf(num));
                }

                @Override
                public void addButtonBack(int num) {
                    getData().get(helper.getPosition()).setNum(String.valueOf(num));
                }

                @Override
                public void editTextChange(int num) {
                    getData().get(helper.getPosition()).setNum(String.valueOf(num));
                }
            });
        }
        helper.setText(R.id.name, item.getGoodsname());
        helper.setText(R.id.num, item.getNum());
    }

    OnItemClickIface onItemClickIface;

    public OnItemClickIface getOnItemLongClickIface() {
        return onItemClickIface;
    }

    public void setOnItemClickIface(OnItemClickIface onItemClickIface) {
        this.onItemClickIface = onItemClickIface;
    }

    public interface OnItemClickIface {
        void ItemClick(int position);

        void ItemDelete(int position);
    }
}
