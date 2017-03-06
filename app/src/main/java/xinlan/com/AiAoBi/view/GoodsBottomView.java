package xinlan.com.AiAoBi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2016/12/3.
 */
public class GoodsBottomView extends LinearLayout {
    TextView totle_text;
    public GoodsBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GoodsBottomView(Context context) {
        super(context);
        initView(context);
    }

   void  initView(Context context){
       View.inflate(context, R.layout.item_goodsbottomview,this);
       totle_text=(TextView)findViewById(R.id.totle_text);
    }
    public  void setText(int textnum){
        totle_text.setText(String.valueOf(textnum));
    }
}
