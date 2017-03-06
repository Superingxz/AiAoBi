package xinlan.com.AiAoBi.adapter;

import android.content.Context;

import java.util.List;

import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2016/12/2.
 */
public class AdapterMypriceMore extends CommonAdapter<String>{
    private String goodName;
    private String goodSize;
    private List<String> slevel;
    public AdapterMypriceMore(Context context, int itemLayoutId, List<String> mDatas,String goodName,String goodSize,List<String> slevel) {
        super(context, itemLayoutId, mDatas);
        this.goodName=goodName;
        this.goodSize=goodSize;
        this.slevel=slevel;
    }

    @Override
    public void convert(ViewHolder helper, String item) {
        helper.setText(R.id.adapter_my_price_name,slevel.get(helper.getPosition())+"（"+goodName+"/"+goodSize+"）");
        helper.setText(R.id.adapter_my_price_get,item);

    }
}
