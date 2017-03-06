package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/21.
 */
public class Msgsalevisit implements Serializable{
    String agentid;
    String num;
    String builltypecode;
    String builltype;
    String msg;
//{'res':'1','msg':'获取数据成功','data':[{'num':'10','builltype':'零售客户回访','builltypecode':'02','msg':'总共有10个客户需要回访','agentid':'00021'}]}
    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
}
