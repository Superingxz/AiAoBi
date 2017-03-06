package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.AgentpaymentdInfo;

/**
 * Created by Administrator on 2016/11/30.
 */
public class FindLogAdapter extends CommonAdapter<AgentpaymentdInfo>{
    public FindLogAdapter(Context context, int itemLayoutId, List<AgentpaymentdInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, AgentpaymentdInfo item) {

        helper.setText(R.id.adapter_register_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_register_time,item.getOperatdate());
        helper.setText(R.id.adapter_register_man,item.getOutagentname());
        helper.setText(R.id.adapter_register_firstMon,item.getAmt());
        helper.setText(R.id.adapter_register_balance,item.getBalance());
    }
}
