package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/11/21.
 */
public class DoselagentqInfo {
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功!；-1:没有数据!；0:没有货品信息!]
     data:json格式，表示返回的记录

     json中字段说明
     goodsname：货品名称
     goodssize：货品规格
     goodsid：货品id
     totalnum：下级总数
     selfnum：我加点货
     outnum：本次扫描
     dfnum：本次代发数
     notnum：本次未处理数
     */
    private String goodsname;
    private String goodssize;
    private String goodsid;
    private String totalnum;
    private String selfnum;
    private String outnum;
    private String dfnum;
    private String notnum;

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodssize() {
        return goodssize;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public String getSelfnum() {
        return selfnum;
    }

    public String getOutnum() {
        return outnum;
    }

    public String getDfnum() {
        return dfnum;
    }

    public String getNotnum() {
        return notnum;
    }

    @Override
    public String toString() {
        return "DoselagentqInfo{" +
                "goodsname='" + goodsname + '\'' +
                ", goodssize='" + goodssize + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", totalnum='" + totalnum + '\'' +
                ", selfnum='" + selfnum + '\'' +
                ", outnum='" + outnum + '\'' +
                ", dfnum='" + dfnum + '\'' +
                ", notnum='" + notnum + '\'' +
                '}';
    }
}
