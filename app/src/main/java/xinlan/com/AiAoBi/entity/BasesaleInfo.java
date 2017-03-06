package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/11.
 */
public class BasesaleInfo implements Serializable{
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功!；-1:获取数据失败!]
     data:json格式，表示返回的记录

     json中字段说明
     agentid：代理商id
     agentcode：代理商编码
     agentname：代理商名称
     brand：品牌名称
     ismorename：单品或全系名称
     num：团队人数
     salemoney：销售业绩
     */
    private String agentid;
    private String agentcode;
    private String agentname;
    private String brand;
    private String ismorename;
    private String num;
    private String salemoney;
    private String ranking;

    public BasesaleInfo(String agentname, String salemoney) {
        this.agentname = agentname;
        this.salemoney = salemoney;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "BasesaleInfo{" +
                "agentid='" + agentid + '\'' +
                ", agentcode='" + agentcode + '\'' +
                ", agentname='" + agentname + '\'' +
                ", brand='" + brand + '\'' +
                ", ismorename='" + ismorename + '\'' +
                ", num='" + num + '\'' +
                ", salemoney='" + salemoney + '\'' +
                '}';
    }

    public String getAgentid() {
        return agentid;
    }

    public String getAgentcode() {
        return agentcode;
    }

    public String getAgentname() {
        return agentname;
    }

    public String getBrand() {
        return brand;
    }

    public String getIsmorename() {
        return ismorename;
    }

    public String getNum() {
        return num;
    }

    public String getSalemoney() {
        return salemoney;
    }
}
