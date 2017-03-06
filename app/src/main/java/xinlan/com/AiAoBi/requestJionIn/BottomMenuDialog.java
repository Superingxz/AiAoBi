package xinlan.com.AiAoBi.requestJionIn;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import xinlan.com.AiAoBi.R;


/**
 * [底部弹出dialog]
 *
 * @author devin.hu
 * @version 1.0
 * @date 2014-1-15
 *
 **/
public class BottomMenuDialog extends Dialog implements View.OnClickListener {

    private Button photographBtn;
    private Button localPhotosBtn;
    private Button cancelBtn;
    private Button checkBtn;
    private ImageView imageView;

    private View.OnClickListener confirmListener;
    private View.OnClickListener cancelListener;
    private View.OnClickListener middleListener;
    private View.OnClickListener checkListener;

    private String confirmText;
    private String middleText;
    private String cancelText;
    private String checkText;

    /**
     * @param context
     */
    public BottomMenuDialog(Context context) {
        super(context, R.style.dialogFullscreen);
    }

    /**
     * @param context
     * @param theme
     */
    public BottomMenuDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * @param context
     */
    public BottomMenuDialog(Context context, String confirmText, String middleText) {
        super(context, R.style.dialogFullscreen);
        this.confirmText = confirmText;
        this.middleText = middleText;
    }

    /**
     * @param context
     */
    public BottomMenuDialog(Context context, String confirmText, String middleText, String cancelText) {
        super(context, R.style.dialogFullscreen);
        this.confirmText = confirmText;
        this.middleText = middleText;
        this.cancelText = cancelText;
    }

    public BottomMenuDialog(Context context, String confirmText, String middleText, String cancelText,String checkText) {
        super(context, R.style.dialogFullscreen);
        this.confirmText = confirmText;
        this.middleText = middleText;
        this.cancelText = cancelText;
        this.checkText=checkText;
    }
    private Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_bottom);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(layoutParams);

        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        photographBtn = (Button) findViewById(R.id.photographBtn);
        localPhotosBtn = (Button) findViewById(R.id.localPhotosBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        checkBtn = (Button) findViewById(R.id.check);
        imageView=(ImageView)findViewById(R.id.dialog_imageview);
        if (!TextUtils.isEmpty(confirmText)) {
            photographBtn.setText(confirmText);
        }
        if (!TextUtils.isEmpty(middleText)) {
            localPhotosBtn.setText(middleText);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            cancelBtn.setText(cancelText);
        }
        if (!TextUtils.isEmpty(checkText)) {
            checkBtn.setText(checkText);
        }

        cancelBtn.setOnClickListener(this);
        photographBtn.setOnClickListener(this);
        localPhotosBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);

    }

    /*public void setCheckBtnVisible(int vis){
        setContentView(R.layout.layout_dialog_bottom);
        checkBtn = (Button) findViewById(R.id.check);
        checkBtn.setVisibility(vis);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(layoutParams);

        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        photographBtn = (Button) findViewById(R.id.photographBtn);
        localPhotosBtn = (Button) findViewById(R.id.localPhotosBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        checkBtn = (Button) findViewById(R.id.check);
        imageView=(ImageView)findViewById(R.id.dialog_imageview);
        if (!TextUtils.isEmpty(confirmText)) {
            photographBtn.setText(confirmText);
        }
        if (!TextUtils.isEmpty(middleText)) {
            localPhotosBtn.setText(middleText);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            cancelBtn.setText(cancelText);
        }
        if (!TextUtils.isEmpty(checkText)) {
            checkBtn.setText(checkText);
        }

        cancelBtn.setOnClickListener(this);
        photographBtn.setOnClickListener(this);
        localPhotosBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);
    };*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dismiss();
        return true;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photographBtn) {
            if (confirmListener != null) {
                confirmListener.onClick(v);
            }
            return;
        }
        if (id == R.id.localPhotosBtn) {
            if (middleListener != null) {
                middleListener.onClick(v);
            }
            return;
        }
        if (id == R.id.cancelBtn) {
            if (cancelListener != null) {
                cancelListener.onClick(v);
            }
            dismiss();
            return;
        }
        if (id == R.id.check) {
            if (checkListener != null) {
                checkListener.onClick(v);
            }
            dismiss();
            return;
        }
    }

   /* *//**
     * 显示大图片
     * @return
     *//*
    public void displayBitmap(Bitmap bitmap){
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(bitmap);
    }*/

    public View.OnClickListener getConfirmListener() {
        return confirmListener;
    }

    public void setConfirmListener(View.OnClickListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    public View.OnClickListener getCancelListener() {
        return cancelListener;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public View.OnClickListener getMiddleListener() {
        return middleListener;
    }

    public void setMiddleListener(View.OnClickListener middleListener) {
        this.middleListener = middleListener;
    }
    public View.OnClickListener getCheckListener() {
        return checkListener;
    }

    public void setCheckListener(View.OnClickListener checkListener) {
        this.checkListener = checkListener;
    }
}
