package xinlan.com.AiAoBi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/27.
 * 代理商信息
 */
public class GetAgentInfo {
    /**
     * {'res':'1','msg':'获取数据成功','data':[
     * {'curparentname':'黄小国','agentcode':'02-A1001-B0002-C0003',
     * 'phone':'13526841511','refmantel':'13588822281','slevel':'4',
     * 'refman':'黄小国','brandid':null,'agentname':'黄小国一级1',
     * 'curparenttel':'13588822281','ismore':'1','weixin':'abcdefff',
     * 'brand':null,'cardno':'422201198510109732'}]}
     */
    String res;
    String meg;
    ArrayList<data> data;

    public String getRes() {
        return res;
    }

    public String getMeg() {
        return meg;
    }

    public ArrayList<GetAgentInfo.data> getData() {
        return data;
    }

    public static class data {
        /* agentid:代理商id
         agentcode:代理商编码
         agentname:代理商名称
         cardno:身份证
         slevel:级别
         phone:电话
         weixin:微信
         ismore:单品或全系
         brand:品牌
         refman:推荐人
         refmantel:推荐人电话
         curparentname:当前上级名称
         curparenttel:当前上级电话
         brandid:品牌id
         voucher:申请人付款凭证
         curparentlevel:当前上级级别【1:总裁;2:官方;3:总代;4:一级;5:经销商;6:特约;7:特约】);
         refmanid:推荐人id
         curparentid:当前上级id
             String regedit_date;*/
        String auditing;
        String status;
        @SerializedName("agentid")
        String agentid;
        @SerializedName("curparentname")
        String curparentname;
        @SerializedName("agentcode")
        String agentcode;
        @SerializedName("phone")
        String phone;
        @SerializedName("refmantel")
        String refmantel;
        @SerializedName("slevel")
        String slevel;
        @SerializedName("refman")
        String refman;
        @SerializedName("brandid")
        String brandid;
        @SerializedName("agentname")
        String agentname;
        @SerializedName("curparenttel")
        String curparenttel;
        @SerializedName("ismore")
        String ismore;
        @SerializedName("weixin")
        String weixin;
        @SerializedName("brand")
        String brand;
        @SerializedName("cardno")
        String cardno;
        @SerializedName("voucher")
        String voucher;
        @SerializedName("curparentlevel")
        String curparentlevel;
        @SerializedName("refmanid")
        String refmanid;
        @SerializedName("curparentid")
        String curparentid;
        @SerializedName("regedit_date")
        String regedit_date;
        @SerializedName("grant_date")
        String grant_date;
        @SerializedName("balance")
        String balance;
        @SerializedName("province")
        String province;
        @SerializedName("city")
        String city;
        @SerializedName("area")
        String area;
        @SerializedName("address")
        String address;

        public String getAuditing() {
            return auditing;
        }

        public String getStatus() {
            return status;
        }

        public String getRegedit_date() {
            return regedit_date;
        }

        public String getAgentid() {
            return agentid;
        }

        public String getCurparentname() {
            return curparentname;
        }

        public String getAgentcode() {
            return agentcode;
        }

        public String getPhone() {
            return phone;
        }

        public String getRefmantel() {
            return refmantel;
        }

        public String getSlevel() {
            return slevel;
        }

        public String getRefman() {
            return refman;
        }

        public String getBrandid() {
            return brandid;
        }

        public String getAgentname() {
            return agentname;
        }

        public String getCurparenttel() {
            return curparenttel;
        }

        public String getIsmore() {
            return ismore;
        }

        public String getWeixin() {
            return weixin;
        }

        public String getBrand() {
            return brand;
        }

        public String getCardno() {
            return cardno;
        }

        public String getVoucher() {
            return voucher;
        }

        public String getCurparentlevel() {
            return curparentlevel;
        }

        public String getRefmanid() {
            return refmanid;
        }

        public String getCurparentid() {
            return curparentid;
        }

        public String getGrant_date() {
            return grant_date;
        }

        public String getBalance() {
            return balance;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getArea() {
            return area;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "data{" +
                    "agentid='" + agentid + '\'' +
                    ", curparentname='" + curparentname + '\'' +
                    ", agentcode='" + agentcode + '\'' +
                    ", phone='" + phone + '\'' +
                    ", refmantel='" + refmantel + '\'' +
                    ", slevel='" + slevel + '\'' +
                    ", refman='" + refman + '\'' +
                    ", brandid='" + brandid + '\'' +
                    ", agentname='" + agentname + '\'' +
                    ", curparenttel='" + curparenttel + '\'' +
                    ", ismore='" + ismore + '\'' +
                    ", weixin='" + weixin + '\'' +
                    ", brand='" + brand + '\'' +
                    ", cardno='" + cardno + '\'' +
                    ", voucher='" + voucher + '\'' +
                    ", curparentlevel='" + curparentlevel + '\'' +
                    ", refmanid='" + refmanid + '\'' +
                    ", curparentid='" + curparentid + '\'' +
                    ", grant_date='" + grant_date + '\'' +
                    ", balance='" + balance + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", area='" + area + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetAgentInfo{" +
                "res='" + res + '\'' +
                ", meg='" + meg + '\'' +
                ", data=" + data +
                '}';
    }
}
