package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.SalescanInfo;

/**
 * Created by Administrator on 2016/12/8.
 */
public class AdapterSaleScan extends CommonAdapter<SalescanInfo>{
    public AdapterSaleScan(Context context, int itemLayoutId, List<SalescanInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, SalescanInfo item) {

        helper.setText(R.id.sale_scan_name,item.getGoodsname());
        helper.setText(R.id.sale_scan_num,item.getBatchno());
        helper.setText(R.id.sale_scan_price,item.getSaleprice());
        helper.setText(R.id.sale_scan_count,item.getCount()+"");
    }
}
