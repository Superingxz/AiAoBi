package xinlan.com.AiAoBi.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public class MBaseModel {
    private String res;
    private String msg;
    private List<AMData> data;
    private List<ASumData> sumdata;

    public String getRes() {
        return res;
    }

    public String getMsg() {
        return msg;
    }

    public List<AMData> getData() {
        return data;
    }

    public List<ASumData> getSumdata() {
        return sumdata;
    }

    @Override
    public String toString() {
        return "MBaseModel{" +
                "res='" + res + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", sumdata=" + sumdata +
                '}';
    }
}
