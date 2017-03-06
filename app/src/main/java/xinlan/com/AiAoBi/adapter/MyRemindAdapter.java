package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.RemindModel;

/**
 * Created by Administrator on 2016/12/21.
 */
public class MyRemindAdapter extends CommonAdapter<RemindModel> {
    public MyRemindAdapter(Context context, int itemLayoutId, List<RemindModel> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, RemindModel item) {
        helper.setText(R.id.adapter_remind_gnumber,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_remind_type,item.getType());
        helper.setText(R.id.adapter_remind_data,item.getCentent());
    }
}
