package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ScanmansumIfo implements Serializable{
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功；-1:没有数据]
     data:json格式，表示返回的记录

     json中字段说明
     refagent_id：订单提报代理商id
     doagent_id：发货代理商id
     doagent_name：发货代理商名称
     agent_id：收货代理商id
     agent_name：收货代理商名称
     neednum：要货数
     alreadynum：已发货数
     nonum：欠货数
     curnum：本次发货数
     */
    private String refagent_id;
    private String doagent_id;
    private String doagent_name;
    private String agent_id;
    private String agent_name;
    private String neednum;
    private String alreadynum;
    private String nonum;
    private String curnum;

    public String getRefagent_id() {
        return refagent_id;
    }

    public String getDoagent_id() {
        return doagent_id;
    }

    public String getDoagent_name() {
        return doagent_name;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public String getNeednum() {
        return neednum;
    }

    public String getAlreadynum() {
        return alreadynum;
    }

    public String getNonum() {
        return nonum;
    }

    public String getCurnum() {
        return curnum;
    }

    @Override
    public String toString() {
        return "ScanmansumIfo{" +
                "refagent_id='" + refagent_id + '\'' +
                ", doagent_id='" + doagent_id + '\'' +
                ", doagent_name='" + doagent_name + '\'' +
                ", agent_id='" + agent_id + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", neednum='" + neednum + '\'' +
                ", alreadynum='" + alreadynum + '\'' +
                ", nonum='" + nonum + '\'' +
                ", curnum='" + curnum + '\'' +
                '}';
    }
}
