package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.MyorderInfo;
import xinlan.com.AiAoBi.home.myGoodsFragment.ActivityCheckMyOrders;

/**
 * Created by Administrator on 2016/12/17.
 */
public class AdapterCheckMyOrders extends CommonAdapter<MyorderInfo> {

    public AdapterCheckMyOrders(Context context, int itemLayoutId, List<MyorderInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, MyorderInfo item) {
        helper.setText(R.id.adapter_check_my_orders_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_check_my_orders_order_num,item.getMasterid());
        helper.setText(R.id.adapter_check_my_orders_pruduct,item.getGoodsname());
        helper.setText(R.id.adapter_check_my_orders_total_money,item.getMoney());
        helper.setText(R.id.adapter_check_my_orders_total_num,item.getNum());
    }
}
