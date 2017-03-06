package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.CommonAdapter;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;

/**
 * Created by Administrator on 2016/11/4.
 */
public class UnderclerkAdapter extends CommonAdapter<DoselbargainInfo> {
    public UnderclerkAdapter(Context context, int itemLayoutId, List<DoselbargainInfo> mDatas) {
        super(context,itemLayoutId,mDatas);
    }

    @Override
    public void convert(ViewHolder helper, DoselbargainInfo item) {
        helper.setText(R.id.adapter_undercler_number,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_undercler_indent_number,item.getMasterid());//订单编号
        helper.setText(R.id.adapter_undercler_man,item.getInman());//订货人
        helper.setText(R.id.adapter_undercler_time,item.getOptdate());//申请时间
        helper.setText(R.id.adapter_undercler_money,item.getSalemoney());//金额
    }


}
