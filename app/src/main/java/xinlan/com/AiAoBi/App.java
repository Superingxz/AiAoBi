package xinlan.com.AiAoBi;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2016/10/27.
 */
public class App extends Application implements Thread.UncaughtExceptionHandler {

    public static App app;
    public UserInfo userInfo;
    private Handler handler;
    private Handler handler2;
    private Handler handler3;
    private Handler handler5;
    private String loginProvice;
    private String loginCity;
    private Handler handler7;
    private Handler handlerClienta;
    private Handler handlerClientb;

    public Handler getHandlerClientb() {
        return handlerClientb;
    }

    public void setHandlerClientb(Handler handlerClientb) {
        this.handlerClientb = handlerClientb;
    }

    public Handler getHandlerClienta() {
        return handlerClienta;
    }

    public void setHandlerClienta(Handler handlerClienta) {
        this.handlerClienta = handlerClienta;
    }

    public Handler getHandler6() {
        return handler6;
    }

    private Handler handler6;

    public static App getApp() {

        return app;

    }

    public Handler getHandler5() {
        return handler5;
    }

    public void setHandler5(Handler handler5) {
        this.handler5 = handler5;
    }

    public String getLoginProvice() {
        return loginProvice;
    }

    public void setLoginProvice(String loginProvice) {
        this.loginProvice = loginProvice;
    }

    public String getLoginCity() {
        return loginCity;
    }

    public void setLoginCity(String loginCity) {
        this.loginCity = loginCity;
    }

    public Handler getHandler3() {
        return handler3;
    }

    public void setHandler3(Handler handler3) {
        this.handler3 = handler3;
    }

    public Handler getHandler2() {
        return handler2;
    }

    public void setHandler2(Handler handler2) {
        this.handler2 = handler2;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
       // CrashReport.initCrashReport(getApplicationContext(), "4a4214e57e", false);
        Bugly.init(getApplicationContext(), "4a4214e57e", false);
        app = this;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void setHandler6(Handler handler6) {
        this.handler6 = handler6;
    }

    public Handler getHandler7() {
        return handler7;
    }

    public void setHandler7(Handler handler7) {
        this.handler7 = handler7;
    }
}
