package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.AgentmsgInfo;
import xinlan.com.AiAoBi.home.homeFragment.ActivityRemind;

/**
 * Created by Administrator on 2016/12/10.
 */
public class AdapterAgentmsg extends CommonAdapter<AgentmsgInfo> {

    public AdapterAgentmsg(Context context, int itemLayoutId, List<AgentmsgInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, AgentmsgInfo item) {
        if (helper.getPosition() % 2 == 0) {
            helper.getConvertView().setBackgroundResource(R.drawable.shape_listview2);
        } else {
            helper.getConvertView().setBackgroundResource(R.drawable.shape_listview3);
        }
        helper.setText(R.id.adapter_remind_gnumber,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_remind_type,item.getBuilltype());
        helper.setText(R.id.adapter_remind_data,item.getContent());
    }
}
