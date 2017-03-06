package xinlan.com.AiAoBi.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;


/**
 * [从本地选择图片以及拍照工具类，完美适配2.0-5.0版本]
 *
 * @author huxinwu
 * @version 1.0
 * @date 2015-1-7
 **/
public class PhotoUtils {

    private final String tag = PhotoUtils.class.getSimpleName();

    /**
     * 裁剪图片成功后返回
     **/
    public static final int INTENT_CROP = 2;
    /**
     * 拍照成功后返回
     **/
    public static final int INTENT_TAKE = 3;
    /**
     * 拍照成功后返回
     **/
    public static final int INTENT_SELECT = 4;

    public static final String CROP_FILE_NAME = "crop_file.jpg";

    private McallBack mcallBack;
    private WeakReference<Activity> activityWeakReference;

    public PhotoUtils(McallBack mcallBack,Activity activity){
        this.mcallBack=mcallBack;
        this.activityWeakReference=new WeakReference<>(activity);
    }
    private @Nullable Activity getActivity(){
        if (activityWeakReference!=null) return activityWeakReference.get();
        return null;
    }
    /**
     * 拍照
     */
    public void takePicture() {
        if (getActivity()==null) return;
        try {
            //每次选择图片吧之前的图片删除
            clearCropFile(buildUri());
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, buildUri());
            if (!isIntentAvailable(intent)) {
                Log.i("Tag","!isIntentAvailable");
                return;
            }
            getActivity().startActivityForResult(intent, INTENT_TAKE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 选择一张图片
     * 图片类型，这里是image/*，当然也可以设置限制
     * 如：image/jpeg等
     */
    @SuppressLint("InlinedApi")
    public void selectPicture() {
        if (getActivity()==null) return;
        try {
            //每次选择图片吧之前的图片删除
            clearCropFile(buildUri());

            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

            if (!isIntentAvailable(intent)) {
                return;
            }
            getActivity().startActivityForResult(intent, INTENT_SELECT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建uri
     *
     * @return
     */
    private Uri buildUri() {
        if (getActivity()==null) return null;
        if (CommonUtils.checkSDCard()) {
            return Uri.fromFile(Environment.getExternalStorageDirectory()).buildUpon().appendPath(CROP_FILE_NAME).build();
        } else {
            return Uri.fromFile(getActivity().getCacheDir()).buildUpon().appendPath(CROP_FILE_NAME).build();
        }
    }

    /**
     * intent是否已经存在
     * @param intent
     * @return
     */
    protected boolean isIntentAvailable(Intent intent) {
        if (getActivity()==null) return false;
        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * 图片剪切
     * @param uri
     * @return boolean
     */
    private boolean corp(Uri uri,float width,float height) {
        if (getActivity()==null) return false;
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, "image/*");
        cropIntent.putExtra("crop", "true");
       /* cropIntent.putExtra("aspectX", 200);//只能接收int数据
        cropIntent.putExtra("aspectY", 120);//*/
        cropIntent.putExtra("outputX", width);
        cropIntent.putExtra("outputY", height);
        cropIntent.putExtra("return-data", false);
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        Uri cropuri = buildUri();
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, cropuri);
        if (!isIntentAvailable(cropIntent)) {
            return false;
        } else {
            try {
                getActivity().startActivityForResult(cropIntent, INTENT_CROP);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * 返回结果处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data,int state,float width,float height) {
        switch (requestCode) {
            //拍照
            case INTENT_TAKE:
                if (new File(buildUri().getPath()).exists()) {
                    if (corp(buildUri(),width,height)) {
                        return;
                    }
                }
                break;

            //选择图片
            case INTENT_SELECT:
                if (data != null && data.getData() != null) {
                    Uri imageUri = data.getData();
                    if (corp(imageUri,width,height)) {
                        return;
                    }
                }
                break;

            //截图
            case INTENT_CROP:
                File file=new File(buildUri().getPath());
                if (resultCode == Activity.RESULT_OK && file.exists()) {
                    Bitmap bitmap = decodeBitmap(file.getPath(),width,height);
                    try {
                        saveBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i("Tag","bitmap:"+bitmap);
                    mcallBack.getBitmapFile(bitmap,file.getPath(),state);
                }
                break;
        }
    }

    /**
     * 保存图片
     * @param bm
     * @throws IOException
     */
    public void saveBitmap(Bitmap bm) throws IOException {
        String uri = buildUri().getPath();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uri));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);//80压缩为原来的百分之八十
        bos.flush();
        bos.close();
    }

    /**
     * 删除文件
     *
     * @param uri
     * @return
     */
    public boolean clearCropFile(Uri uri) {
        if (uri == null) {
            return false;
        }

        File file = new File(uri.getPath());
        if (file.exists()) {
            boolean result = file.delete();
            if (result) {
                Log.i(tag, "Cached crop file cleared.");
            } else {
                Log.e(tag, "Failed to clear cached crop file.");
            }
            return result;
        } else {
            Log.w(tag, "Trying to clear cached crop file but it does not exist.");
        }

        return false;
    }

    /**
     * 从path中获取图片信息
     * @param path
     * @return
     */

    private Bitmap decodeBitmap(String path,float width,float height){
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, op); //获取尺寸信息
        //获取比例大小
        op.inPreferredConfig = Bitmap.Config.RGB_565;
        int wRatio = (int)Math.ceil(op.outWidth/width);
        int hRatio = (int)Math.ceil(op.outHeight/height);
        Log.i("Log","wRatio:"+wRatio+";width:"+width+";hR:"+hRatio+";he:"+height);
        //如果超出指定大小，则缩小相应的比例
        if(wRatio > 1 && hRatio > 1){
            if(wRatio > hRatio){
                op.inSampleSize = wRatio;
            }else{
                op.inSampleSize = hRatio;
            }
        }
        op.inJustDecodeBounds = false;
       Bitmap bmp = BitmapFactory.decodeFile(path, op);
        return bmp;
    }

    /**
     * 回调接口
     */
    public interface McallBack{
        void getBitmapFile(Bitmap bitmap,String path,int state);
    }
}
