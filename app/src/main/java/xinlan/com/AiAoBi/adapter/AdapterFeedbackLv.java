package xinlan.com.AiAoBi.adapter;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;

/**
 * Created by Administrator on 2016/12/13.
 */
public class AdapterFeedbackLv extends CommonAdapter<BaseproduceInfo>{

    public AdapterFeedbackLv(Context context, int itemLayoutId, List<BaseproduceInfo> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, BaseproduceInfo item) {
        helper.setText(R.id.adapter_feedback_tv,item.getTitle());
        TextView tv=helper.getView(R.id.adapter_feedback_tv);
        tv.setSingleLine();
        tv.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        ImageView imageView=helper.getView(R.id.adapter_feedback_iv);
        Glide.with(mContext).load(item.getUrl())
                .placeholder(ResourcesCompat.getDrawable(mContext.getResources(),R.drawable.shape,null))
                .error(ResourcesCompat.getDrawable(mContext.getResources(),R.mipmap.error200,null))
                .into(imageView);
    }
}
