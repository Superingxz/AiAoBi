package xinlan.com.AiAoBi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinlan.com.AiAoBi.requestJionIn.JionInActivity;
import xinlan.com.AiAoBi.updateAPP.UpdateApkManager;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {
    @BindView(R.id.activity_main_imageview)
    ImageView imageview;
    @BindView(R.id.activity_main_phonenumber)
    EditText phonenumber;
    @BindView(R.id.activity_main_password)
    EditText password;
    @BindView(R.id.activity_main_checkbox)
    CheckBox checkbox;
    @BindView(R.id.activity_main_remenberPassword)
    TextView remenberPassword;
    @BindView(R.id.activity_main_forgetPasswoed)
    TextView forgetPasswoed;
    @BindView(R.id.activity_main_login)
    Button login;
   /* @BindView(R.id.activity_main_cancle)
    Button cancle;
    @BindView(R.id.activity_main_version)
    TextView version;*/
    @BindView(R.id.general_agency)
    TextView generalAgency;
    @BindView(R.id.stair)
    TextView stair;
    @BindView(R.id.dealer_down)
    TextView dealerDown;

    private UpdateApkManager manager;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private String number;
    private String pd;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(MainActivity.this.isFinishing()&&MainActivity.this.isDestroyed())return;
            }
            if (msg.what==0x3){
                presenter.login(number, pd);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        App.getApp().setHandlerClientb(handler);
    }
    private AMapLocationClient  locationClient;
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        /*manager=new UpdateApkManager(this);
        //检测更新
        manager.checkoutUpdate();*/
        netCilent=NetCilent.getInstance();
        //basurl=netCilent.getBasurl();
        setUserMsgFromSP();
        checkbox.setOnCheckedChangeListener(checkedChangeListener);
        phonenumber.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);
        //把editText的光标移到最后
        phonenumber.setSelection(phonenumber.getText().length());
        locationClient=new AMapLocationClient(App.app.getApplicationContext());
        presenter.initLocation(locationClient);
    }

    /**
     * 文本输入框的监听事件
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            number = phonenumber.getText().toString().trim();
            pd = password.getText().toString().trim();
        }
    };

    /**
     * 设置文本框信息
     */
    private void setUserMsgFromSP() {
        shared = getSharedPreferences("userMessage", Activity.MODE_PRIVATE);
        boolean isCheck = shared.getBoolean("isCheck", false);
        if (isCheck) {
            phonenumber.setText(shared.getString("number", null));
            password.setText(shared.getString("password", null));
            number = phonenumber.getText().toString().trim();
            pd = password.getText().toString().trim();
        } else {
            phonenumber.setText(null);
            password.setText(null);
        }
    }

    /**
     * 是否记住密码的监听事件
     */
    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
            editor = shared.edit();
            if (isCheck) {//保存用户信息
                editor.putBoolean("isCheck", true);
                editor.putString("number", number);
                editor.putString("password", pd);
                editor.apply();
            } else {
                editor.putBoolean("isCheck", false);
                editor.putString("number", null);
                editor.putString("password", null);
                editor.apply();
            }
        }
    };
    private static final int OFFICIAL=5;
    private static final int GENERAL_AFENCY=1;
    private static final int STAIR=2;
    private static final int DEALER_DOWN=3;
    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @OnClick({R.id.activity_main_remenberPassword,
            R.id.activity_main_forgetPasswoed,
            R.id.activity_main_login,
            R.id.general_agency,
            R.id.stair,
            R.id.dealer_down,
            R.id.official,
            R.id.activity_main_kangyu})
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.official://官方申请加入
                intent.putExtra("state",OFFICIAL);
                intentActivity(JionInActivity.class,intent);
                break;
            case R.id.activity_main_forgetPasswoed:
                String s=null;
                showToast("功能暂未开放，敬请期待",Toast.LENGTH_SHORT);
                break;
            case R.id.activity_main_kangyu:
                if (null==number|"".equals(number)) showToast("请输入账号", Toast.LENGTH_SHORT);
                else if (pd==null|"".equals(pd)) showToast("请输入密码", Toast.LENGTH_SHORT);
                else if ("appadmin".equals(number)&&"888888".equals(pd)){
                    showChangeDialog();
                } else {
                    App.getApp().getHandlerClienta().sendEmptyMessage(0x1);
                   // presenter.login(number, pd);
                }
                break;
            case R.id.activity_main_login:
                if (null==number|"".equals(number)) showToast("请输入账号", Toast.LENGTH_SHORT);
                else if (pd==null|"".equals(pd)) showToast("请输入密码", Toast.LENGTH_SHORT);
                else if ("appadmin".equals(number)&&"888888".equals(pd)){
                    showChangeDialog();
                } else {
                    App.getApp().getHandlerClienta().sendEmptyMessage(0x2);
                   // presenter.login(number, pd);
                }
                break;
           /* case R.id.activity_main_cancle:
                finish();
                break;
            case R.id.activity_main_version:
                break;*/
            case R.id.general_agency://总代理申请
                intent.putExtra("state",GENERAL_AFENCY);
                intentActivity(JionInActivity.class,intent);
                break;
            case R.id.stair:
                intent.putExtra("state",STAIR);
                intentActivity(JionInActivity.class,intent);
                break;
            case R.id.dealer_down:
                intent.putExtra("state",DEALER_DOWN);
                intentActivity(JionInActivity.class,intent);
                break;
        }
    }

    private ProgressDialog progressDialog;
    @Override
    public void showProgressDialog(String massege) {
        if (progressDialog!=null){
            progressDialog.cancel();
        }
        progressDialog = ProgressDialog.show(this, null, massege);
    }

    @Override
    public void dimissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void intentActivity(Class<? extends Activity> actCalss,Intent intent) {
        intent.setClass(this,actCalss);
        startActivity(intent);
    }

    @Override
    public void showToast(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    @Override
    public void showDiyMsgDialog(String tvMsg, String sumbit, String cancle, final BaseActivty.Mcallback mcallback) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_diy);
        Button btnyes= (Button) window.findViewById(R.id.dialog_diy_btnyes);
        Button btncancle= (Button) window.findViewById(R.id.dialog_diy_btncancel);
        TextView textView= (TextView) window.findViewById(R.id.dialog_diy_text);
        btnyes.setText(sumbit);
        btncancle.setText(cancle);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcallback.doSomething(alertDialog);
            }
        });
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        textView.setText(tvMsg);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClient!=null){
            locationClient.stopLocation();
            locationClient.onDestroy();
            Log.i("log","停止定位服务");
        }
    }
    private void showChangeDialog(){
        alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.change_dialog);
        ip= (EditText) window.findViewById(R.id.change_dialog_ip);
        qz= (EditText) window.findViewById(R.id.change_dialog_qianzui);
        checkBox= (CheckBox) window.findViewById(R.id.change_dialog_cb);
        Button checbtn= (Button) window.findViewById(R.id.change_dialog_checkbtn);
        Button yesbtn= (Button) window.findViewById(R.id.change_dialog_yesbtn);
        Button canclebtn= (Button) window.findViewById(R.id.change_dialog_canclebtn);
        checbtn.setOnClickListener(listener);
        yesbtn.setOnClickListener(listener);
        canclebtn.setOnClickListener(listener);
        ip.setText("120.76.54.180");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    ip.setText("120.76.54.180");
                }else {
                    ip.setText("");
                }
            }
        });
    }
    private AlertDialog alertDialog;
    private EditText ip;
    private EditText qz;
    private CheckBox checkBox;
    private NetCilent netCilent;
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String ipstr = ip.getText().toString().trim();
            String qzstr = qz.getText().toString().trim();
            switch (view.getId()){
                case R.id.change_dialog_checkbtn:
                    if (TextUtils.isEmpty(ipstr)){
                        showToast("请输入ip地址！",Toast.LENGTH_SHORT);
                    }else if (TextUtils.isEmpty(qzstr)){
                        netCilent.setBasurl("http://"+ipstr+":85/ayb/");
                        presenter.check();
                        Log.i("halo", "basurl: "+netCilent.getBasurl());
                    }else {
                        netCilent.setBasurl("http://"+ipstr+":85/"+qzstr+"/");
                        Log.i("halo", "basurl: "+netCilent.getBasurl());
                        presenter.check();
                    }
                    break;
                case R.id.change_dialog_yesbtn:

                    if (TextUtils.isEmpty(ipstr)){
                        showToast("请输入ip地址！",Toast.LENGTH_SHORT);
                    }else if (TextUtils.isEmpty(qzstr)){
                        netCilent.setBasurl("http://"+ipstr+":85/ayb/");
                        Log.i("halo", "basurl: "+netCilent.getBasurl());
                        showToast("设置成功",Toast.LENGTH_SHORT);
                        alertDialog.dismiss();

                    }else {
                        netCilent.setBasurl("http://"+ipstr+":85/"+qzstr+"/");
                        showToast("设置成功",Toast.LENGTH_SHORT);
                        alertDialog.dismiss();
                    }

                    break;
                case R.id.change_dialog_canclebtn:
                    alertDialog.dismiss();
                    break;
            }
        }
    };
}
