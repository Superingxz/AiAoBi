package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.CustomerEnity;

/**
 * Created by Administrator on 2016/11/16.
 */
public class CustomerAdapter extends CommonAdapter<CustomerEnity> {

    public CustomerAdapter(Context context, int itemLayoutId, List<CustomerEnity> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, CustomerEnity item) {
        helper.setText(R.id.customer_lv_adapter_tv,item.getString());
        helper.setImageResource(R.id.customer_lv_adapter_iv,item.getRes());
    }
}
