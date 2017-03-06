package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 * 3_2_3处理下级订单(获取直属订单下的代理订单)
 */
public class DoselagentaInfo implements Serializable {

    /* json中字段说明
     skeyid：代理订单主键id
     ordertype：订单类型【apply：申请，deal：处理】
     reason：代发原因
     profit：上级利润
     ref_id：上报订单id
     ref_no：上报订单编号
     src_id：原始订单主键id
     src_no：原始订单号
     agent_id：申请代理商id
     agent_name：申请代理商名称
     slevel：申请代理商级别
     inman：收货人
     intel：收货人电话
     inaddr：收货地址
     bargainflag：订单标识，值为include*/
    private String skeyid;
    private String ordertype;
    private String reason;
    private String profit;
    private String salemoney;
    private String ref_id;
    private String ref_no;
    private String src_no;
    private String src_id;
    private String agent_id;
    private String agent_name;
    private String slevel;
    private String inman;
    private String intel;
    private String inaddr;
    private String bargainflag;
    private String slevelname;

    public String getSlevelname() {
        return slevelname;
    }

    public String getSalemoney() {
        return salemoney;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public String getSlevel() {
        return slevel;
    }

    public String getInman() {
        return inman;
    }

    public String getIntel() {
        return intel;
    }

    public String getInaddr() {
        return inaddr;
    }

    public String getBargainflag() {
        return bargainflag;
    }

    public String getSkeyid() {
        return skeyid;
    }

    public String getSrc_no() {
        return src_no;
    }

    public String getSrc_id() {
        return src_id;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public String getReason() {
        return reason;
    }

    public String getProfit() {
        return profit;
    }

    public String getRef_no() {
        return ref_no;
    }

    public String getRef_id() {
        return ref_id;
    }

    @Override
    public String toString() {
        return "DoselagentaInfo{" +
                "skeyid='" + skeyid + '\'' +
                ", src_no='" + src_no + '\'' +
                ", src_id='" + src_id + '\'' +
                ", ordertype='" + ordertype + '\'' +
                ", reason='" + reason + '\'' +
                ", profit='" + profit + '\'' +
                ", ref_no='" + ref_no + '\'' +
                ", ref_id='" + ref_id + '\'' +
                '}';
    }
}
