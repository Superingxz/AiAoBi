package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * 我的地址列表
 * Created by Administrator on 2016/11/3.
 */
public class AgentAddress  implements Serializable{

//    province:收货省
//    city:收货市
//    area:收货区
//    address:收货街道
//    inname:收货人姓名
//    phone:收货人电话
//    isdefault:属于默认地址 0 ，1
//    optdate:增加时间
//    agent_addr_id:主键
//    agentid:代理商id
    String province;
    String city;
    String area;
    String address;
    String inname;
    String phone;
    String isdefault;
    String optdate;
    String agent_addr_id;
    String agentid;

    @Override
    public String toString() {
        return "AgentAddress{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", inname='" + inname + '\'' +
                ", phone='" + phone + '\'' +
                ", isdefault='" + isdefault + '\'' +
                ", optdate='" + optdate + '\'' +
                ", agent_addr_id='" + agent_addr_id + '\'' +
                ", agentid='" + agentid + '\'' +
                '}';
    }
    public AgentAddress(){

    }

    public AgentAddress(String province, String city, String area, String address, String inname, String phone, String isdefault) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
        this.inname = inname;
        this.phone = phone;
        this.isdefault = isdefault;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInname() {
        return inname;
    }

    public void setInname(String inname) {
        this.inname = inname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getOptdate() {
        return optdate;
    }

    public void setOptdate(String optdate) {
        this.optdate = optdate;
    }

    public String getAgent_addr_id() {
        return agent_addr_id;
    }

    public void setAgent_addr_id(String agent_addr_id) {
        this.agent_addr_id = agent_addr_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
