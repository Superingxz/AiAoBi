package xinlan.com.AiAoBi.updateAPP;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LSY on 2016/9/20.
 */
public class ApkInfo {
    @SerializedName("resultcode")
    private String resultCode;
    @SerializedName("reason")
    private String versionCode;

    public String getResultCode() {
        return resultCode;
    }

    public int getversionCode() {
        return 10;
    }
}
