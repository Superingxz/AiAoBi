package xinlan.com.AiAoBi.home.personageFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.adapter.AdapterMypriceMore;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/2.
 */
public class ActivityMyPriceMore extends BaseActivty {
    @BindView(R.id.my_price_more_title)
    TitileView myPriceMoreTitle;
    @BindView(R.id.my_price_more_lv)
    ListView myPriceMoreLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_price_more);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        myPriceMoreTitle.setTitle("更多价格");
        List<String > prices=getIntent().getStringArrayListExtra("price");
        List<String> slevel=getIntent().getStringArrayListExtra("slevel");
        String goodName=getIntent().getStringExtra("goodName");
        String goodSize=getIntent().getStringExtra("goodSize");
        AdapterMypriceMore adapter=new AdapterMypriceMore(this,R.layout.adapter_my_price_more,prices,goodName,goodSize,slevel);
        myPriceMoreLv.setAdapter(adapter);
    }
}
