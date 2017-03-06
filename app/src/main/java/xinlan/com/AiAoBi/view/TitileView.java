package xinlan.com.AiAoBi.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xinlan.com.AiAoBi.R;

/**
 * Created by Administrator on 2016/10/26.
 */
public class TitileView extends RelativeLayout {
    TextView title, right_bt;
    ImageView lv;
    Context context;
    LinearLayout back_layout;

    public TitileView(Context context) {
        super(context);
        inttView(context);
    }

    public TitileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inttView(context);
    }


    void inttView(Context context) {
        inflate(context, R.layout.title_view, this);
        right_bt = (TextView) findViewById(R.id.right_bt);
        lv=(ImageView)findViewById(R.id.back_iv);
        title = (TextView) findViewById(R.id.title_text);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        this.context = context;
        back_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lefebtIface!=null){
                    lefebtIface.onClick();
                }
                back();
            }
        });
    }

    public void setTitle(String titletext) {
        title.setText(titletext);
    }

    public void back() {
        ((Activity) context).finish();
    }

    public void setRightText(String text, final RightBtIface rightBtIface) {
        right_bt.setText(text);
        right_bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rightBtIface.onClick();
            }
        });
        right_bt.setVisibility(View.VISIBLE);

    }


    RightBtIface rightBtIface;

    public interface RightBtIface {
        void onClick();
    }

    public interface LeftBtIface {
        void onClick();
    }

    public void setLeftBackGone() {
        back_layout.setVisibility(View.GONE);
    }

    public void setLeftIvGone() {
        lv.setVisibility(View.GONE);
    }

    public void setRgbtnVisible(int vis) {
        right_bt.setVisibility(vis);
    }

    public RightBtIface getRightBtIface() {
        return rightBtIface;
    }

    public LeftBtIface lefebtIface;

    public LeftBtIface getLefebtIface() {
        return lefebtIface;
    }

    public void setLefebtIface(LeftBtIface lefebtIface) {
        this.lefebtIface = lefebtIface;
    }

    public void setRightBtIface(RightBtIface rightBtIface) {
        this.rightBtIface = rightBtIface;
    }
}
