package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.MyorderInfo;

/**
 * Created by Administrator on 2016/12/17.
 */
public class AdapterMyorderdet extends CommonAdapter<MyorderInfo>{
    public AdapterMyorderdet(Context context, int itemLayoutId, List<MyorderInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, MyorderInfo item) {

        helper.setText(R.id.adapter_my_lower_orders_details_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_my_lower_orders_details_dnum,item.getMasterid());
        helper.setText(R.id.adapter_my_lower_orders_details_agent,item.getAgent_name());
        helper.setText(R.id.adapter_my_lower_orders_details_name,item.getGoodsname());
        helper.setText(R.id.adapter_my_lower_orders_details_count,item.getNum());
        helper.setText(R.id.adapter_my_lower_orders_details_money,item.getMoney());
    }
}
