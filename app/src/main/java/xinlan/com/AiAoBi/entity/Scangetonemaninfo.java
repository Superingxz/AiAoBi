package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/5.
 */
public class Scangetonemaninfo implements Serializable{
    /*
    * json中字段说明
refagent_id：订单提报代理商id
doagent_id：发货代理商id
doagent_name：发货代理商名称
doslevel:发货代理商级别
doslevelname:发货代理商级别名称
agent_id：收货代理商id
agent_name：收货代理商名称
slevel:收货代理商级别
slevelname:收货代理商级别名称*/
    private boolean isCheck;
    private String refagent_id;
    private String doagent_id;
    private String doagent_name;
    private String doslevel;
    private String doslevelname;
    private String agent_id;
    private String agent_name;
    private String slevel;
    private String slevelname;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
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

    public String getDoslevel() {
        return doslevel;
    }

    public String getDoslevelname() {
        return doslevelname;
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

    public String getSlevelname() {
        return slevelname;
    }

    @Override
    public String toString() {
        return "ScangetmanInfo{" +
                "refagent_id='" + refagent_id + '\'' +
                ", doagent_id='" + doagent_id + '\'' +
                ", doagent_name='" + doagent_name + '\'' +
                ", doslevel='" + doslevel + '\'' +
                ", doslevelname='" + doslevelname + '\'' +
                ", agent_id='" + agent_id + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", slevel='" + slevel + '\'' +
                ", slevelname='" + slevelname + '\'' +
                '}';
    }
}
