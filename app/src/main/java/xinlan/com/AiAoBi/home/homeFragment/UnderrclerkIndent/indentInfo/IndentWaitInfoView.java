package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import xinlan.com.AiAoBi.entity.DoselagentbInfo;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface IndentWaitInfoView extends MvpView{
    void setDataToActivity(List<DoselagentbInfo> list);
    void showToast(String msg,int dur);
    void showProgressDialog(String msg);
    void showMsgDialog(String msg);
    void dismissProgressDiolog();
    void setVisbely(int lvVisbely,int tVvisbely);
}
