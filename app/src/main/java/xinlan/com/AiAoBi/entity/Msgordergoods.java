package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/21.
 */
public class Msgordergoods implements Serializable{
    //    keyid：提醒主键id
//    code：提醒编码
//    operatdate：提醒时间
//    builltypecode：类别编码
//    builltype：类别编码描述
//    agentid：接收代理商id
//    agentname：接收代理商名称
//    phone：接收代理商电话
//    slevel：接收代理商级别
//    slevelname：接收代理商级别名称
//    ismorename：接收代理商全系或单品名称
//    pagentid：发送代理商id
//    pagentname：发送代理名称
//    pphone：发送代理商电话
//    pslevel：发送代理商级别
//    msg：提醒内容
//    minmoney：计划业绩
//    money：本月要货金额
//    diffmoney：差额
//    content_auto：自动生成的提醒语
    String builltypecode;
    String phone;
    String keyid;
    String code;
    String operatdate;
    String builltype;
    String agentid;
    String agentname;
    String slevel;
    String slevelname;
    String ismorename;
    String pagentid;
    String pagentname;
    String pphone;
    String pslevel;
    String msg;
    String minmoney;
    String money;
    String diffmoney;
    String content_auto;

    public String getBuilltypecode() {
        return builltypecode;
    }

    public void setBuilltypecode(String builltypecode) {
        this.builltypecode = builltypecode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOperatdate() {
        return operatdate;
    }

    public void setOperatdate(String operatdate) {
        this.operatdate = operatdate;
    }

    public String getBuilltype() {
        return builltype;
    }

    public void setBuilltype(String builltype) {
        this.builltype = builltype;
    }

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

    public String getSlevelname() {
        return slevelname;
    }

    public void setSlevelname(String slevelname) {
        this.slevelname = slevelname;
    }

    public String getIsmorename() {
        return ismorename;
    }

    public void setIsmorename(String ismorename) {
        this.ismorename = ismorename;
    }

    public String getPagentid() {
        return pagentid;
    }

    public void setPagentid(String pagentid) {
        this.pagentid = pagentid;
    }

    public String getPagentname() {
        return pagentname;
    }

    public void setPagentname(String pagentname) {
        this.pagentname = pagentname;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getPslevel() {
        return pslevel;
    }

    public void setPslevel(String pslevel) {
        this.pslevel = pslevel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMinmoney() {
        return minmoney;
    }

    public void setMinmoney(String minmoney) {
        this.minmoney = minmoney;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDiffmoney() {
        return diffmoney;
    }

    public void setDiffmoney(String diffmoney) {
        this.diffmoney = diffmoney;
    }

    public String getContent_auto() {
        return content_auto;
    }

    public void setContent_auto(String content_auto) {
        this.content_auto = content_auto;
    }


}
