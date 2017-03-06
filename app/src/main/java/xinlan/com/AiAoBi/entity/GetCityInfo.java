package xinlan.com.AiAoBi.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class GetCityInfo {
    /*
    {'res':'1','data':[
    {'city_id':'10030002','city_name':'许昌市','city_code':'1202','city_level':'2'},
    {'city_id':'10030003','city_name':'洛阳市','city_code':'1203','city_level':'2'},
    {'city_id':'10030004','city_name':'南阳市','city_code':'1204','city_level':'2'},
    {'city_id':'10030005','city_name':'新郑市','city_code':'1205','city_level':'2'},
    {'city_id':'10030006','city_name':'驻马店','city_code':'1206','city_level':'2'},
    {'city_id':'10030007','city_name':'漯河市','city_code':'1207','city_level':'2'},
    {'city_id':'10030008','city_name':'安阳市','city_code':'1208','city_level':'2'},
    {'city_id':'10030001','city_name':'郑州市','city_code':'1201','city_level':'2'}],
    'msg':'获取数据成功'}
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

    public ArrayList<GetCityInfo.data> getData() {
        return data;
    }

    public void setData(ArrayList<GetCityInfo.data> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

   public static class data{
        String city_id;
        String city_name;
        String city_code;
        String getCity_level;

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getGetCity_level() {
            return getCity_level;
        }

        public void setGetCity_level(String getCity_level) {
            this.getCity_level = getCity_level;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }
    }

}
