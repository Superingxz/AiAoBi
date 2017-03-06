package xinlan.com.AiAoBi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/12.
 */
public class BasenoteInfo implements Serializable{
    /**
     * 类型：多行数据的结果集
     结果值说明：返回用户信息
     res:[1:获取数据成功!；-1:获取数据失败!]
     data:json格式，表示返回的记录

     json中字段说明
     news_id：新闻主键id
     news_title：标题
     url：访问地址
     */
    private String news_id;
    private String news_title;
    private String url;

    @Override
    public String toString() {
        return "BasenoteInfo{" +
                "news_id='" + news_id + '\'' +
                ", news_title='" + news_title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getNews_id() {
        return news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getUrl() {
        return url;
    }
}
