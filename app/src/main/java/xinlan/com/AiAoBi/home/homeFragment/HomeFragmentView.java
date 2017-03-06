package xinlan.com.AiAoBi.home.homeFragment;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import xinlan.com.AiAoBi.entity.BasenoteInfo;
import xinlan.com.AiAoBi.entity.BaseproduceInfo;
import xinlan.com.AiAoBi.entity.BasesaleInfo;
import xinlan.com.AiAoBi.entity.BasesaleaxisInfo;

/**
 * Created by Administrator on 2016/9/22.
 */
public interface HomeFragmentView extends MvpView {

    void setDataToRanking(List<BasesaleInfo> data);
    void showProgressDialog(String messege);
    void dimissProgressDialog();
    void showMsgDialog(String msg,int style);
    void showToast(String msg);
    void setDataToNotice(List<BasenoteInfo> data);

    void setDataToFeedback(List<BaseproduceInfo> data);

    void setFeedBackVis(int visible);

    void setRefreshing(boolean b);

    void setDataToRankingb(List<BasesaleaxisInfo> data);

    void setDataToRankingc(List<BasesaleInfo> data);
}
