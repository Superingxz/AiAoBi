package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Msgnostock;
import xinlan.com.AiAoBi.entity.Msgnostockdet;
import xinlan.com.AiAoBi.view.JrComputeView;

/**
 * Created by Administrator on 2016/12/22.
 */
public class MsgnostockdetAdapter extends CommonAdapter<Msgnostockdet> {
    public MsgnostockdetAdapter(Context context, int itemLayoutId, List<Msgnostockdet> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
    MsgnostockdetAdapterIface adapterIface;
    public  interface  MsgnostockdetAdapterIface{
        void updateNum();
    }

    public MsgnostockdetAdapterIface getAdapterIface() {
        return adapterIface;
    }

    public void setAdapterIface(MsgnostockdetAdapterIface adapterIface) {
        this.adapterIface = adapterIface;
    }

    @Override
    public void convert(final ViewHolder helper, Msgnostockdet item) {
        helper.setText(R.id.goodsname, item.getGoodsname());
        helper.setText(R.id.costprice, item.getCostprice());
        helper.setText(R.id.usestock, item.getUsestock());
        helper.setText(R.id.minstock, item.getMinstock());
        helper.setText(R.id.diffnum, item.getDiffnum());
        getData().get(helper.getPosition()).setEditetext(item.getDiffnum());
        // helper.setText(R.id.need_num, item.getDiffnum());
        JrComputeView jrComputeView= (JrComputeView)helper.getView(R.id.need_num);
        jrComputeView.setMaxNum(Integer.MAX_VALUE);
        jrComputeView.setMinNum(1);
        jrComputeView.setNum(Integer.valueOf(item.getDiffnum()));
        jrComputeView.setCalculateCallBack(new JrComputeView.CalculateCallBack() {
            @Override
            public void reduceButtonBack(int num) {
                getData().get(helper.getPosition()).setEditetext(String.valueOf(num));
                adapterIface.updateNum();
            }

            @Override
            public void addButtonBack(int num) {
                getData().get(helper.getPosition()).setEditetext(String.valueOf(num));
                adapterIface.updateNum();
            }

            @Override
            public void editTextChange(int num) {
                getData().get(helper.getPosition()).setEditetext(String.valueOf(num));
                adapterIface.updateNum();
            }
        });

    }
}
