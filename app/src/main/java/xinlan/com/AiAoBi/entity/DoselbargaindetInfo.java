package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */
public class DoselbargaindetInfo implements Serializable{
    /*json中字段说明
detid：订单明细id
skeyid：订单主键id
goodsid：货品id
goodsno：货品编码
goodsname：货品名称
goodssize：规格型号
unitname：单位
costprice：单价
zsnum：自收
dfnum：代发
totalnum：总数
ref_detid：提报订单明细id
src_detid：原始订单明细id*/
    private String detid;
    private String skeyid;
    private String goodsid;
    private String goodsno;
    private String goodsname;
    private String goodssize;
    private String unitname;
    private String costprice;
    private String zsnum;
    private String dfnum;
    private String totalnum;
    private String ref_detid;
    private String src_detid;
    private boolean isCheck=false;
    private String sumnum;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getDetid() {
        return detid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getSkeyid() {
        return skeyid;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getZsnum() {
        return zsnum;
    }

    public String getDfnum() {
        return dfnum;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public String getCostprice() {
        return costprice;
    }

    public String getRef_detid() {
        return ref_detid;
    }

    public String getSrc_detid() {
        return src_detid;
    }

    @Override
    public String toString() {
        return "DoselbargaindetInfo{" +
                "detid='" + detid + '\'' +
                ", skeyid='" + skeyid + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", unitname='" + unitname + '\'' +
                ", costprice='" + costprice + '\'' +
                ", zsnum='" + zsnum + '\'' +
                ", dfnum='" + dfnum + '\'' +
                ", totalnum='" + totalnum + '\'' +
                ", ref_detid='" + ref_detid + '\'' +
                ", src_detid='" + src_detid + '\'' +
                '}';
    }

    public String getSumnum() {
        return sumnum;
    }
}
