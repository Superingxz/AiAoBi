package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/21.
 */
public class Msgsalevisitdet {
    String agentid;
    String agent_salem_id;
    String agent_saled_id;
    String custom;
    String purchaseinfo;
    String rela;
    String optdate;
    String content_auto;
    String clienttel;

    public String getClienttel() {
        return clienttel;
    }

    public void setClienttel(String clienttel) {
        this.clienttel = clienttel;
    }

    boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getContent_auto() {
        return content_auto;
    }

    public void setContent_auto(String content_auto) {
        this.content_auto = content_auto;
    }

    public String getOptdate() {
        return optdate;
    }

    public void setOptdate(String optdate) {
        this.optdate = optdate;
    }

    public String getRela() {
        return rela;
    }

    public void setRela(String rela) {
        this.rela = rela;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getAgent_salem_id() {
        return agent_salem_id;
    }

    public void setAgent_salem_id(String agent_salem_id) {
        this.agent_salem_id = agent_salem_id;
    }

    public String getAgent_saled_id() {
        return agent_saled_id;
    }

    public void setAgent_saled_id(String agent_saled_id) {
        this.agent_saled_id = agent_saled_id;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getPurchaseinfo() {
        return purchaseinfo;
    }

    public void setPurchaseinfo(String purchaseinfo) {
        this.purchaseinfo = purchaseinfo;
    }
}
