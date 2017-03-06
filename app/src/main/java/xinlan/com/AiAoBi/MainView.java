package xinlan.com.AiAoBi;

import android.app.Activity;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import xinlan.com.AiAoBi.entity.BasesaleaxisInfo;

/**
 * Created by Administrator on 2016/9/21.
 */
public interface MainView extends MvpView{
    void showProgressDialog(String messege);
    void dimissProgressDialog();
    void finishActivity();
    void intentActivity(Class<?extends Activity> clazz, Intent intent);
    void showToast(String msg,int duration);
    void showDiyMsgDialog(String tvMsg,String sumbit,String cancle,final BaseActivty.Mcallback mcallback);
}
