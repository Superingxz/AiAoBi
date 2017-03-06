package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2016/12/13.
 */
public class BaseproduceInfo {
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功!；-1:获取数据失败!]
     data:json格式，表示返回的记录

     json中字段说明
     mkeyid：产品信息主键id
     remrak：产品说明
     url：访问地址
     */
    private String title;
    private String mkeyid;
    private String remrak;
    private String url;

    @Override
    public String toString() {
        return "BaseproduceInfo{" +
                "mkeyid='" + mkeyid + '\'' +
                ", remrak='" + remrak + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getMkeyid() {
        return mkeyid;
    }

    public String getRemrak() {
        return remrak;
    }

    public String getUrl() {
        return url;
    }
}
