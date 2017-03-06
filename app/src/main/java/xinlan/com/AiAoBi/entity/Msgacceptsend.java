package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/22.
 */
public class Msgacceptsend implements Serializable{
    String agentid;
    String bsys_msg_id;
    String builltypecode;
    String builltype;
    String msg;
    String optdate;
    String pagent_phone;
    String pagent_slevel;
    String pagent_name;
    String peruserflag;

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getBsys_msg_id() {
        return bsys_msg_id;
    }

    public void setBsys_msg_id(String bsys_msg_id) {
        this.bsys_msg_id = bsys_msg_id;
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

    public String getOptdate() {
        return optdate;
    }

    public void setOptdate(String optdate) {
        this.optdate = optdate;
    }

    public String getPagent_phone() {
        return pagent_phone;
    }

    public void setPagent_phone(String pagent_phone) {
        this.pagent_phone = pagent_phone;
    }

    public String getPagent_slevel() {
        return pagent_slevel;
    }

    public void setPagent_slevel(String pagent_slevel) {
        this.pagent_slevel = pagent_slevel;
    }

    public String getPagent_name() {
        return pagent_name;
    }

    public void setPagent_name(String pagent_name) {
        this.pagent_name = pagent_name;
    }

    public String getPeruserflag() {
        return peruserflag;
    }

    public void setPeruserflag(String peruserflag) {
        this.peruserflag = peruserflag;
    }
}
