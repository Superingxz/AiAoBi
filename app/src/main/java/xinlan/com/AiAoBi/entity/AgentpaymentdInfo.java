package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/11/30.
 */
public class AgentpaymentdInfo {
    /*json中字段说明
    operatdate:收款时间
    outagentname：打款代理商
    slevel：打款代理商级别
    amt：首款金额
    phone：打款人电话
    ismore：单品或全系
    brandid:品牌id(取值为货品id);
    brand:品牌(取值为货品名称);
    balance：余额*/
    private String operatdate;
    private String outagentname;
    private String slevel;
    private String amt;
    private String phone;
    private String ismore;
    private String brandid;
    private String brand;
    private String balance;

    public String getOperatdate() {
        return operatdate;
    }

    public String getOutagentname() {
        return outagentname;
    }

    public String getSlevel() {
        return slevel;
    }

    public String getAmt() {
        return amt;
    }

    public String getPhone() {
        return phone;
    }

    public String getIsmore() {
        return ismore;
    }

    public String getBrandid() {
        return brandid;
    }

    public String getBrand() {
        return brand;
    }

    public String getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "AgentpaymentdInfo{" +
                "operatdate='" + operatdate + '\'' +
                ", outagentname='" + outagentname + '\'' +
                ", slevel='" + slevel + '\'' +
                ", amt='" + amt + '\'' +
                ", phone='" + phone + '\'' +
                ", ismore='" + ismore + '\'' +
                ", brandid='" + brandid + '\'' +
                ", brand='" + brand + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
