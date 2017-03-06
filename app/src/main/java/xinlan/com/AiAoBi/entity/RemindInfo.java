package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/11/25.
 */
public class RemindInfo {
   private String  type;
   private String  data;

    public RemindInfo(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
