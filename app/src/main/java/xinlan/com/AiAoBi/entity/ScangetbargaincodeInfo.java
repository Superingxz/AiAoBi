package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ScangetbargaincodeInfo implements Serializable{
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功；-1:没有数据]
     data:json格式，表示返回的记录

     json中字段说明
     refagent_id：订单提报代理商id
     doagent_id：发货代理商id
     doagent_name：发货代理商名称
     agentid：收货代理商id
     agent_name：收货代理商名称
     ordertype：处理标识【apply：我的货品提报，deal：处理提报】
     ref_id：提报订单主键id
     src_id：原始订单主键id
     bargainflag：订单标识，值为self
     */
    private String masterid;

    public String getMasterid() {
        return masterid;
    }

    private String refagent_id;
    private String doagent_id;
    private String doagent_name;
    private String agentid;
    private String agent_name;
    private String ordertype;
    private String ref_id;
    private String src_id;
    private String bargainflag;
    private String inman;
    private String inaddr;
    private String intel;

    public String getInman() {
        return inman;
    }

    public String getInaddr() {
        return inaddr;
    }

    public String getIntel() {
        return intel;
    }

    public String getRefagent_id() {
        return refagent_id;
    }

    public String getDoagent_id() {
        return doagent_id;
    }

    public String getDoagent_name() {
        return doagent_name;
    }

    public String getAgentid() {
        return agentid;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public String getRef_id() {
        return ref_id;
    }

    public String getSrc_id() {
        return src_id;
    }

    public String getBargainflag() {
        return bargainflag;
    }

    @Override
    public String toString() {
        return "ScangetbargaincodeInfo{" +
                "masterid='" + masterid + '\'' +
                ", refagent_id='" + refagent_id + '\'' +
                ", doagent_id='" + doagent_id + '\'' +
                ", doagent_name='" + doagent_name + '\'' +
                ", agentid='" + agentid + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", ordertype='" + ordertype + '\'' +
                ", ref_id='" + ref_id + '\'' +
                ", src_id='" + src_id + '\'' +
                ", bargainflag='" + bargainflag + '\'' +
                ", inman='" + inman + '\'' +
                ", inaddr='" + inaddr + '\'' +
                ", intel='" + intel + '\'' +
                '}';
    }
}
