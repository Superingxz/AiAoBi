package xinlan.com.AiAoBi.requestJionIn;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import xinlan.com.AiAoBi.entity.GetCityInfo;
import xinlan.com.AiAoBi.entity.GetSetInfo;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface JionInView extends MvpView {
    void showPhotoDialog(int state,float width,float height);
    void showToast(String masge,int dur);
    void setDataToSpRank(List<GetSetInfo.data> list);
    void setDataToSpProvince(List<GetCityInfo.data> list);
    void setDatatoSpCity(List<GetCityInfo.data> list);
    void setDatatoSpDisrict(List<GetCityInfo.data> list);
    void showProgressDialog(String messege);
    void dimissProgressDialog();
    void finishActivity();
    void setTextToAgentname(String agentname);
    void setTextTorefman(String agentname);
    void setTextToSlevel(String slevel);
    void setEnableForEditAgentName(Boolean b);
    void showMsgDialog(String msg,int style);
}
