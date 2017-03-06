package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.GetagentdownInfo;

/**
 * Created by Administrator on 2016/12/1.
 */
public class MyUnderclerkAdapter extends CommonAdapter<GetagentdownInfo>{
    public MyUnderclerkAdapter(Context context, int itemLayoutId, List<GetagentdownInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, GetagentdownInfo item) {
        helper.setText(R.id.recadapter_name,item.getAgentname()+"-"+item.getBrand()+item.getSlevelname());
        helper.setText(R.id.recadapter_msg,null);
    }
}
