package xinlan.com.AiAoBi.home.homeFragment.UnderrclerkIndent;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import xinlan.com.AiAoBi.BaseActivty;
import xinlan.com.AiAoBi.entity.DoselagentkInfo;
import xinlan.com.AiAoBi.entity.DoselagentqInfo;
import xinlan.com.AiAoBi.entity.DoselbargainInfo;
import xinlan.com.AiAoBi.home.homeFragment.AddAgainNumActivity;

/**
 * Created by Administrator on 2016/11/4.
 */
public interface UnderclerkIndentView extends MvpView{
    void showToast(String msg,int dur);
    void showProgressDialog(String msg);
    void showMsgDialog(String msg);
    void dismissProgressDiolog();
    void setVisbely(int lvVisbely,int tVvisbely);
    void setDataToActivity(List<DoselbargainInfo> list);
    void setDataToAlldLv(List<DoselagentqInfo> list);
    void setAlldisposedLvVisbely(int lvVisbely,int tvVisbely);
    void setBtnComitText(String text);
    void notifyDataChange();
    void showDiyDialog(String tvMsg, String sumbit, String cancle, List<DoselagentkInfo> list,final UnderclerkIndentActivity.Mcallback mcallback);
    void intentToActivity(Class ActivityClass);
}
