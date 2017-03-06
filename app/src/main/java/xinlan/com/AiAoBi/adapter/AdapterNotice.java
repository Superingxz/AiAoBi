package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.BasenoteInfo;

/**
 * Created by Administrator on 2016/12/12.
 */
public class AdapterNotice extends CommonAdapter<BasenoteInfo>{

    public AdapterNotice(Context context, int itemLayoutId, List<BasenoteInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, BasenoteInfo item) {
        if (helper.getPosition() % 2 == 0) {
            helper.getConvertView().setBackgroundResource(R.mipmap.official_info_grid_bg);
        }
        helper.setText(R.id.adapter_notice_tvmsg,item.getNews_title());
    }
}
