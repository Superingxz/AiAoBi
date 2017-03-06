package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.MyorderInfo;

/**
 * Created by Administrator on 2016/12/16.
 */
public class AdapterMyorder extends CommonAdapter<MyorderInfo>{
    public AdapterMyorder(Context context, int itemLayoutId, List<MyorderInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, MyorderInfo item) {
        helper.setText(R.id.adapter_my_lower_orders_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_my_lower_orders_month,item.getOptdate());
        helper.setText(R.id.adapter_my_lower_orders_agent,item.getAgent_name());
        helper.setText(R.id.adapter_my_lower_orders_pruduct,item.getGoodsname());
        helper.setText(R.id.adapter_my_lower_orders_counts,item.getNum());
        helper.setText(R.id.adapter_my_lower_orders_total_money,item.getMoney());
    }
}
