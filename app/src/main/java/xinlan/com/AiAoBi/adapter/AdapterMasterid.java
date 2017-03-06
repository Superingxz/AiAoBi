package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.home.homeFragment.ActivitySendGoods;

/**
 * Created by Administrator on 2016/12/6.
 */
public class AdapterMasterid extends CommonAdapter<String>{

    public AdapterMasterid(Context context, int itemLayoutId, List<String> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, String item) {
        helper.setText(R.id.masterid_text,item);
    }
}
