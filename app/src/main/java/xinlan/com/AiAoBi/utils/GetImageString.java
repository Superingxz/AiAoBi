package xinlan.com.AiAoBi.utils;

import android.util.Base64;
import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * Created by Administrator on 2016/10/25.
 * 把图片文件转为字符串
 */
public class GetImageString {
    public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//		String imgFile = "d:\\111.jpg";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
//		 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode = Base64.encodeToString(data, Base64.DEFAULT);
        Log.i("Log","Base64:"+encode);
//		 对字节数组Base64编码
        //BASE64Encoder encoder = new BASE64Encoder();
        return encode;// 返回Base64编码过的字节数组字符串
    }

}
