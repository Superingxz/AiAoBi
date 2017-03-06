package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent.indentInfo;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import xinlan.com.AiAoBi.entity.DoselagentaInfo;
import xinlan.com.AiAoBi.entity.DoselbargaindetInfo;

/**
 * Created by Administrator on 2016/11/4.
 */
public interface IndentInfoView extends MvpView {
    void showToast(String msg,int dur);
    void showProgressDialog(String msg);
    void showMsgDialog(String msg);
    void dismissProgressDiolog();
    void setVisbely(int lvVisbely,int tVvisbely);
    void setWaitVisbely(int lvVisbely,int tVvisbely);
    void setDataToActivity(List<DoselbargaindetInfo> list);
    void setDataToWaitAdapter(List<DoselagentaInfo> list);
}
