package xinlan.com.AiAoBi.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class GetSetInfo {
    /*
    {'res':'1','data':[{'flag':'0','goodsid':'all','goods_name':'全系'},
    {'flag':'1','goodsid':'jxstar20160919009','goods_name':'艾灸贴'},
    {'flag':'1','goodsid':'jxstar20160919014','goods_name':'玻尿酸保湿啫喱'},
    {'flag':'1','goodsid':'jxstar20160919015','goods_name':'玻尿酸泛酸钙原液'},
    {'flag':'1','goodsid':'jxstar20160919016','goods_name':'玻尿酸水库面霜'},
    {'flag':'1','goodsid':'jxstar20160919038','goods_name':'美白丸'},
    {'flag':'1','goodsid':'jxstar20160919039','goods_name':'美诺茵BB丸'}],'msg':'获取数据成功'}
     */



    String res;
    ArrayList<data> data;
    String msg;
    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public ArrayList<GetSetInfo.data> getData() {
        return data;
    }

    public void setData(ArrayList<GetSetInfo.data> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static class data{
        String  flag;
        String goodsid;
        String goods_name;
        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

    }
}
