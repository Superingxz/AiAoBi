package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.widget.CheckBox;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.AgentpaymentaInfo;

/**
 * Created by Administrator on 2016/11/29.
 */
public class CheckMyCustomerAdapter extends CommonAdapter<AgentpaymentaInfo>{
    public CheckMyCustomerAdapter(Context context, int itemLayoutId, List<AgentpaymentaInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, AgentpaymentaInfo item) {
        helper.setText(R.id.adapter_check_my_customer_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_check_my_customer_name,item.getAgentname());
        CheckBox checkBox=helper.getView(R.id.adapter_check_my_customer_cb);
        if (item.ischeck()) checkBox.setChecked(true);
        else checkBox.setChecked(false);
    }
}
