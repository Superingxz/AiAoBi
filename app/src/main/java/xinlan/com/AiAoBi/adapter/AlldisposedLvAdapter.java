package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.DoselagentqInfo;

/**
 * Created by Administrator on 2016/11/21.
 */
public class AlldisposedLvAdapter extends CommonAdapter<DoselagentqInfo>{
    public AlldisposedLvAdapter(Context context, int itemLayoutId, List<DoselagentqInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }
  /*  json中字段说明
    goodsname：货品名称
    goodssize：货品规格
    goodsid：货品id
    totalnum：下级总数
    selfnum：我加点货
    outnum：本次扫描
    dfnum：本次代发数
    notnum：本次未处理数*/
    @Override
    public void convert(ViewHolder helper, DoselagentqInfo item) {
        helper.setText(R.id.adapter_undercler_all_number,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_undercler_all_name,item.getGoodsname()+"/");
        helper.setText(R.id.adapter_undercler_all_sum,item.getTotalnum());
        helper.setText(R.id.adapter_undercler_all_scam_number,item.getOutnum());
        helper.setText(R.id.adapter_undercler_all_daifa,item.getDfnum());
        helper.setText(R.id.adapter_undercler_all_handle,item.getNotnum());
        helper.setText(R.id.adapter_undercler_all_addsome,item.getSelfnum());
    }
}
