package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/10.
 */

public class AgentmsgInfo {
    /**
     * json中字段说明
     bsys_msg_id：提醒主键id
     masterid：提醒编号
     builltype：业务描述
     builltypecode：业务类别编码
     agent_id：接收方代理商id
     agent_name：接收方代理商名称
     agent_phone：接收方代理商电话
     agent_slevel：接收方代理商级别
     content：系统自动生成的提醒内容
     issend：是否已发送
     islook：接收方是否已阅读
     lookdate：阅读时间
     peruserflag：阅读者标识，【accept：接收者阅读，send：发送者阅读】
      */
    private String bsys_msg_id;
    private String masterid;
    private String builltype;
    private String builltypecode;
    private String agent_id;
    private String agent_name;
    private String agent_phone;
    private String agent_slevel;
    private String content;
    private String issend;
    private String islook;
    private String lookdate;
    private String peruserflag;

    public String getBsys_msg_id() {
        return bsys_msg_id;
    }

    public String getMasterid() {
        return masterid;
    }

    public String getBuilltype() {
        return builltype;
    }

    public String getBuilltypecode() {
        return builltypecode;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public String getAgent_phone() {
        return agent_phone;
    }

    public String getAgent_slevel() {
        return agent_slevel;
    }

    public String getContent() {
        return content;
    }

    public String getIssend() {
        return issend;
    }

    public String getIslook() {
        return islook;
    }

    public String getLookdate() {
        return lookdate;
    }

    public String getPeruserflag() {
        return peruserflag;
    }

    @Override
    public String toString() {
        return "AgentmsgInfo{" +
                "bsys_msg_id='" + bsys_msg_id + '\'' +
                ", masterid='" + masterid + '\'' +
                ", builltype='" + builltype + '\'' +
                ", builltypecode='" + builltypecode + '\'' +
                ", agent_id='" + agent_id + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", agent_phone='" + agent_phone + '\'' +
                ", agent_slevel='" + agent_slevel + '\'' +
                ", content='" + content + '\'' +
                ", issend='" + issend + '\'' +
                ", islook='" + islook + '\'' +
                ", lookdate='" + lookdate + '\'' +
                ", peruserflag='" + peruserflag + '\'' +
                '}';
    }
}
