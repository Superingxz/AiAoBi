package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/22.
 */
public class Msgnostock implements Serializable{
    String agentid;
    String builltypecode;
    String builltype;
    String msg;
    String num;
    String optdate;

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getBuilltypecode() {
        return builltypecode;
    }

    public void setBuilltypecode(String builltypecode) {
        this.builltypecode = builltypecode;
    }

    public String getBuilltype() {
        return builltype;
    }

    public void setBuilltype(String builltype) {
        this.builltype = builltype;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOptdate() {
        return optdate;
    }

    public void setOptdate(String optdate) {
        this.optdate = optdate;
    }
}
