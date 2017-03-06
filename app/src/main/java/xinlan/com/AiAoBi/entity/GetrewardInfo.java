package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2017/1/18.
 */
public class GetrewardInfo {
    /**
     * json中字段说明
     agent_account_id：主键id
     optdate：奖励时间
     changetype:奖励类型
     amt:奖励金额
     remark:备注说明
     */
    private String agent_account_id;
    private String optdate;
    private String changetype;
    private String amt;
    private String remark;

    public String getOptdate() {
        return optdate;
    }

    public String getAgent_account_id() {
        return agent_account_id;
    }

    public String getChangetype() {
        return changetype;
    }

    public String getAmt() {
        return amt;
    }

    public String getRemark() {
        return remark;
    }
}
