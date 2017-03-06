package xinlan.com.AiAoBi.home.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/12/13.
 */
public class ActivityRankingMore extends BaseActivty {
    @BindView(R.id.activity_notice_more_title)
    TitileView activityNoticeMoreTitle;
    @BindView(R.id.notice_more_lv)
    ListView noticeMoreLv;
    @BindView(R.id.notice_more_tvmsg)
    TextView noticeMoreTvmsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_more);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }
}
