package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.ASumData;

/**
 * Created by Administrator on 2016/12/5.
 */
public class AdapterSignDoodsDetaislFoot extends CommonAdapter<ASumData>{
    public AdapterSignDoodsDetaislFoot(Context context, int itemLayoutId, List<ASumData> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, ASumData item) {
        helper.setText(R.id.adapter_sign_goods_details_lv_foot_num,helper.getPosition()+1+"");
        helper.setText(R.id.adapter_sign_goods_details_lv_foot_name,item.getGoodsname()+"/"+item.getGoodssize());
        helper.setText(R.id.adapter_sign_goods_details_lv_foot_price,item.getCostprice());
        helper.setText(R.id.adapter_sign_goods_details_lv_foot_ordernum,item.getTotalnum());
        helper.setText(R.id.adapter_sign_goods_details_lv_foot_deliverNum,item.getAlreadynum());
        helper.setText(R.id.adapter_sign_goods_details_lv_foot_OweGoods,item.getLessnum());
    }
}
