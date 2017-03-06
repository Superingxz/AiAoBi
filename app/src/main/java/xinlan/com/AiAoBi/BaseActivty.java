package xinlan.com.AiAoBi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import xinlan.com.AiAoBi.view.TitileView;

/**
 * Created by Administrator on 2016/10/26.
 */
public abstract class BaseActivty extends AppCompatActivity {
    protected TitileView titileView;
    protected NetApi netApi;
    protected ProgressDialog progressDialog;
    protected Context context;
    protected UserInfo user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=App.getApp().getUserInfo();
        netApi= NetCilent.getInstance().getNetApi();
        context=this;
        titileView=new TitileView(this);
    }

    public void showProgressDialog(String massege) {
        if (progressDialog!=null) {
            progressDialog.cancel();
        }
        progressDialog = ProgressDialog.show(this,null,massege);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void showToast1(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void dimissProgressDialog() {
        if (progressDialog!=null)
        progressDialog.dismiss();
    }

    protected void setTitle(String title) {
        titileView.setTitle(title);
    }

    /**
     *
     * @param msg maseege
     * @param style 0.dismiss 1.finish
     */
    public void showMsgDialog(String msg, final int style) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("提示信息!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (style==0){
                    dialog.dismiss();
                }
                if (style==1){
                    finish();
                }
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
    private AlertDialog alertDialog;
    public void showDiyMsgDialog(String tvMsg, final Mcallback mcallback){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_diy);
        Button btnyes= (Button) window.findViewById(R.id.dialog_diy_btnyes);
        Button btncancle= (Button) window.findViewById(R.id.dialog_diy_btncancel);
        TextView textView= (TextView) window.findViewById(R.id.dialog_diy_text);
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
    public void showDiyMsgDialog(String tvMsg,int type,final Mcallback mcallback){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_diy2);
        RelativeLayout left=(RelativeLayout) window.findViewById(R.id.dialog_diy_btncancel_layout);
        RelativeLayout right=(RelativeLayout) window.findViewById(R.id.dialog_diy_btnyes_layout);
        Button btnyes= (Button) window.findViewById(R.id.dialog_diy_btnyes);
        Button btncancle= (Button) window.findViewById(R.id.dialog_diy_btncancel);
        if(type==0){
            right.setVisibility(View.GONE);
        }else {
            left.setVisibility(View.GONE);
        }
        TextView textView= (TextView) window.findViewById(R.id.dialog_diy_text);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcallback.doSomething(alertDialog);
                alertDialog.dismiss();
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

    public void showDiyMsgDialog(String tvMsg,String sumbit,String cancle,final Mcallback mcallback){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
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
    public interface Mcallback extends View.OnClickListener{
        void doSomething(AlertDialog alertDialog);
    }
    /**
     * 判断相机是否可以打开
     */
    public boolean isPermision(){
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }
}
