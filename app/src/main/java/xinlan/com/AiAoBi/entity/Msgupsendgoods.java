package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * 提醒上级要货
 * Created by Administrator on 2016/12/22.
 */
public class Msgupsendgoods implements Serializable{
    String agentid;
    String agentname;
    String slevel;
    String builltypecode;
    String builltype;
    String msg;
    String content_auto;
    String ordertype;
    String doagent_id;
    String doagent_name;
    String doslevel;
    String doslevelname;
    String doagent_phone;

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

    public String getContent_auto() {
        return content_auto;
    }

    public void setContent_auto(String content_auto) {
        this.content_auto = content_auto;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getDoagent_id() {
        return doagent_id;
    }

    public void setDoagent_id(String doagent_id) {
        this.doagent_id = doagent_id;
    }

    public String getDoagent_name() {
        return doagent_name;
    }

    public void setDoagent_name(String doagent_name) {
        this.doagent_name = doagent_name;
    }

    public String getDoslevel() {
        return doslevel;
    }

    public void setDoslevel(String doslevel) {
        this.doslevel = doslevel;
    }

    public String getDoslevelname() {
        return doslevelname;
    }

    public void setDoslevelname(String doslevelname) {
        this.doslevelname = doslevelname;
    }

    public String getDoagent_phone() {
        return doagent_phone;
    }

    public void setDoagent_phone(String doagent_phone) {
        this.doagent_phone = doagent_phone;
    }
}
