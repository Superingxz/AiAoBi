package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * 9_8我的提醒_(提醒签收货品)
 * Created by Administrator on 2016/12/21.
 */
public class Msgtakegoods implements Serializable{
    String agentid;
    String agentname;
    String slevel;
    String skeyid;
    String masterid;
    String builltypecode;
    String builltype;
    String msg;
    String ordertype;//处理标识【apply：我的货品提报，deal：处理提报】


    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getSlevel() {
        return slevel;
    }

    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    public String getSkeyid() {
        return skeyid;
    }

    public void setSkeyid(String skeyid) {
        this.skeyid = skeyid;
    }

    public String getMasterid() {
        return masterid;
    }

    public void setMasterid(String masterid) {
        this.masterid = masterid;
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

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }
}
