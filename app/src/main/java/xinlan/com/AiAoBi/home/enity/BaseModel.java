package xinlan.com.AiAoBi.home.enity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class BaseModel<T>  {
    String res;
    String msg;
    List<T> data;

    public String getRes() {
        return res;
    }
    public void setRes(String res) {
        this.res = res;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
