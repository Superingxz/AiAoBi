package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.RemindInfo;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RemindAdapter extends CommonAdapter<RemindInfo> {
    public RemindAdapter(Context context, int itemLayoutId, List<RemindInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, RemindInfo item) {
        helper.setText(R.id.remind_data,item.getData());
        helper.setText(R.id.remind_number,helper.getPosition()+1+"");
        helper.setText(R.id.remind_type,item.getType());
    }
}
