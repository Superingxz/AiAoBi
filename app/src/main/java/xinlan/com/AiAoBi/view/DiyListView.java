package xinlan.com.AiAoBi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/12/3.
 */
public class DiyListView extends ListView{
    public DiyListView(Context context) {
        this(context,null);
    }

    public DiyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
