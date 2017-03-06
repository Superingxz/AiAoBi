package xinlan.com.AiAoBi.home.enity;

/**
 * Created by Administrator on 2016/11/22.
 */
public class MyUnderclerkInfo {
    private String name;
    private String msg;

    public MyUnderclerkInfo( String name, String msg) {
        this.name = name;
        this.msg=msg;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "MyUnderclerkInfo{" +
                ", name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
