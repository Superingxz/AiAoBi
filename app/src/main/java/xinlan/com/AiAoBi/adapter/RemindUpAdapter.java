package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.Msgupsenddet;

/**
 * Created by Administrator on 2016/12/22.
 */
public class RemindUpAdapter extends CommonAdapter<Msgupsenddet> {
    public RemindUpAdapter(Context context, int itemLayoutId, List<Msgupsenddet> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, Msgupsenddet item) {
        helper.setText(R.id.goods_name, item.getGoodsname());
        helper.setText(R.id.num, item.getNum());
        helper.setText(R.id.stocknum, item.getStocknum());

    }
}
