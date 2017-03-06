package xinlan.com.AiAoBi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GeneralAgentRuqest {
    /**
     * {'res':'1','data':{'cur_personid':'E40963A3-80DB-41B0-B868-A0B49E5DE679'},'msg':'保存成功!'}
     */
    @SerializedName("res")
    String res;
    Class data;
    String msg;
    public static class data{
        String cur_personid;

        public String getCur_personid() {
            return cur_personid;
        }

        public void setCur_personid(String cur_personid) {
            this.cur_personid = cur_personid;
        }
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Class getData() {
        return data;
    }

    public void setData(Class data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
